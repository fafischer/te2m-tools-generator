/*
 * SimpleGenerator.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import de.te2m.report.api.model.Configuration;
import de.te2m.report.api.model.GeneratorTarget;
import de.te2m.report.api.model.Report;

/**
 * The Class SimpleGenerator.
 *
 * @author ffischer
 */
public class SimpleGenerator {

    /**
     * The Constant REPORT_PROCESSOR_MAP.
     */
    public static final String REPORT_PROCESSOR_MAP = "te2m.generator.ReportProcessorMap";

    /**
     * The Constant RESULT_PROCESSOR.
     */
    public static final String RESULT_PROCESSOR = "te2m.generator.ResultProcessor";

    /**
     * The Constant RESULT_PROCESSOR_IS_INITIALIZED.
     */
    public static final String RESULT_PROCESSOR_IS_INITIALIZED = "te2m.generator.ResultProcessor.isInitialized";

    /**
     * The Constant DEFAULT_LOOP_VAR_NAME.
     */
    public static final String DEFAULT_LOOP_VAR_NAME = "current";

    /**
     * The report.
     */
    protected Report report;

    /**
     * The default report processor.
     */
    protected ReportProcessor defaultReportProcessor;

    /**
     * The result processor.
     */
    protected GeneratorResultProcessor resultProcessor;

    /**
     * The report processors.
     */
    protected Hashtable<String, ReportProcessor> reportProcessors;

    /**
     * Constructor.
     *
     * @param rep the rep
     * @param repProc the rep proc
     * @param resProc the res proc
     */
    public SimpleGenerator(Report rep, ReportProcessor repProc, GeneratorResultProcessor resProc) {
        this.report = rep;
        this.defaultReportProcessor = repProc;
        this.resultProcessor = resProc;
        this.reportProcessors = new Hashtable<String, ReportProcessor>();
        if (null != repProc) {
            this.reportProcessors.put(repProc.getProcessingTarget(), repProc);

        }
    }

    /**
     * Register processor.
     *
     * @param repProc the rep proc
     */
    public void registerProcessor(ReportProcessor repProc) {
        if (null != repProc) {
            getReportProcessors().put(repProc.getProcessingTarget(), repProc);

        }
    }

    /**
     * Register processors.
     *
     * @param repProcs the rep procs
     */
    public void registerProcessors(List<ReportProcessor> repProcs) {
        if (null != repProcs) {
            for (ReportProcessor repProc : repProcs) {
                registerProcessor(repProc);
            }

        }
    }

    /**
     * Register processors.
     *
     * @param repProcs the rep procs
     */
    public void registerProcessors(Enumeration<ReportProcessor> repProcs) {
        if (null != repProcs) {
            while (repProcs.hasMoreElements()) {
                ReportProcessor reportProcessor = (ReportProcessor) repProcs
                        .nextElement();
                registerProcessor(reportProcessor);
            }

        }
    }

    /**
     * Returns a list of all generator target keys.
     *
     * @return the generator target keys
     */
    public List<String> getGeneratorTargetKeys() {
        List<String> keyList = new ArrayList<String>();
        if (null != report) {
            List<GeneratorTarget> targets = report.getTargets();
            for (GeneratorTarget t : targets) {
                if (!keyList.contains(t.getTarget())) {
                    keyList.add(t.getTarget());
                }
            }
        }
        return keyList;
    }

    /**
     * Returns a list of GeneratorTargets for the provided key.
     *
     * @param key the key
     * @return the generator targets
     */
    public List<GeneratorTarget> getGeneratorTargets(String key) {
        List<GeneratorTarget> targetList = new ArrayList<GeneratorTarget>();
        if (null != report) {
            List<GeneratorTarget> targets = report.getTargets();
            for (GeneratorTarget t : targets) {
                if (key.equals(t.getTarget())) {
                    targetList.add(t);
                }
            }
        }
        return targetList;
    }

    /**
     * Generates the targets defined in the report.
     *
     * @param params the params
     */
    public void generate(Map<String, Object> params) {

        if (null == params) {
            params = new HashMap<String, Object>();
        }

        Boolean isAlreadyInitialized = (Boolean) params.get(RESULT_PROCESSOR_IS_INITIALIZED);
        if (!Boolean.TRUE.equals(isAlreadyInitialized)) {
            resultProcessor.preProcess();
            params.put(RESULT_PROCESSOR_IS_INITIALIZED, Boolean.TRUE);
            params.put(REPORT_PROCESSOR_MAP, reportProcessors);
            params.put(RESULT_PROCESSOR, resultProcessor);
        }
        if (null != report) {
            for (GeneratorTarget target : report.getTargets()) {

                System.out.println("*** " + target.getName());
                // clone params in order to avoid messing up the global params
                Map<String, Object> tmpParams = new HashMap<String, Object>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    tmpParams.put(entry.getKey(), entry.getValue());
                }

                if (null != target.getConfig() && !target.getConfig().isEmpty()) {
                    for (Configuration cfg : target.getConfig()) {
                        tmpParams.put(cfg.getKey(), (String) ELUtils.evalExpression(tmpParams, cfg.getValue()));
                    }
                }

                String currentTargetName = target.getName();

                tmpParams.put("CurrentTargetName", (String) ELUtils.evalExpression(tmpParams, currentTargetName));

                ReportProcessor rp = defaultReportProcessor;
                if (null != target.getTemplateType()) {
                    if (reportProcessors.containsKey(target.getTemplateType())) {
                        rp = reportProcessors.get(target.getTemplateType());
                    }
                }
                if (null == target.getForEach() || target.getForEach().trim().length() == 0) {
                    byte[] result = rp.generate(tmpParams, target.getTemplate());
                    if (rp.createsOutput()) {
                        processGeneratorResult(result, target, tmpParams);
                    }
                } else {
                    List<Object> subTargets = getSubTargets(tmpParams, target);

                    String loopVarName = determineLoopVarName(target);

                    for (Object o : subTargets) {
                        tmpParams.put(loopVarName, o);
                        byte[] result = rp.generate(tmpParams, target.getTemplate());
                        if (rp.createsOutput()) {
                            processGeneratorResult(result, target, tmpParams);
                        }

                    }
                }
            }
        }
        if (!Boolean.TRUE.equals(isAlreadyInitialized)) {
            resultProcessor.postProcess();
        }

    }

    /**
     * Determines the loop var name.
     *
     * @param target the target
     * @return the string
     */
    protected String determineLoopVarName(GeneratorTarget target) {

        if (null != target.getVar() && target.getVar().trim().length() > 0) {
            return target.getVar();
        } else {
            return DEFAULT_LOOP_VAR_NAME;
        }
    }

    /**
     * Process generator result.
     *
     * @param generated the generated
     * @param target the target
     * @param params the params
     */
    protected void processGeneratorResult(byte[] generated, GeneratorTarget target, Map<String, Object> params) {
        if (null != resultProcessor && null != generated) {
            resultProcessor.processGeneratorResult(generated, target, params);
        }
    }

    /**
     * Gets the sub targets.
     *
     * @param params the params
     * @param target the target
     * @return the sub targets
     */
    protected List<Object> getSubTargets(Map<String, Object> params, GeneratorTarget target) {
        List<Object> subTargets = new ArrayList<Object>();

        String expression = target.getForEach();

        Object o = ELUtils.evalExpression(params, expression);

        if (o != null) {
            if (o instanceof Iterable) {
                for (Object current : (Iterable) o) {
                    subTargets.add(current);
                }

            } else if (o instanceof Enumeration) {
                while (((Enumeration) o).hasMoreElements()) {
                    Object current = ((Enumeration) o).nextElement();
                    subTargets.add(current);
                }
            } else if (o.getClass().isArray()) {
                subTargets.addAll(Arrays.asList((Object[]) o));
            } else {
                subTargets.add(o);
            }
        }

        return subTargets;
    }

    /**
     * Gets the report processors.
     *
     * @return the report processors
     */
    public Hashtable<String, ReportProcessor> getReportProcessors() {
        if (null == reportProcessors) {
            reportProcessors = new Hashtable<String, ReportProcessor>();
        }
        return reportProcessors;
    }

}
