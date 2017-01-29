/*
 * ChildReportProcessor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.engine.impl;

import de.te2m.tools.generator.engine.GeneratorResultProcessor;
import de.te2m.tools.generator.engine.ReportProcessor;
import de.te2m.tools.generator.engine.SimpleGenerator;
import de.te2m.tools.generator.engine.impl.freemarker.FreeMarkerProcessor;
import de.te2m.tools.generator.model.Report;
import de.te2m.tools.generator.model.TemplateType;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * This class can be used for executing child reports.
 *
 * @author ffischer
 */
public class ChildReportProcessor implements ReportProcessor {

    /**
     * Instantiates a new child report processor.
     */
    public ChildReportProcessor() {
    }

    @Override
    public boolean createsOutput() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * de.te2m.tools.generator.engine.ReportProcessor#generate(java.util.HashMap
     * , java.lang.String)
     */
    @Override
    public byte[] generate(Map<String, Object> params, String template) {
        if (null != template) {
            InputStream is = new ByteArrayInputStream(template.getBytes());
            JAXBContext context;
            try {
                context = JAXBContext.newInstance(Report.class);

                Unmarshaller um = context.createUnmarshaller();
                Report r = (Report) um.unmarshal(is);

                if (null != r) {

                    GeneratorResultProcessor grp = (GeneratorResultProcessor) params
                            .get(SimpleGenerator.RESULT_PROCESSOR);

                    if (null != grp) {
                        SimpleGenerator g = new SimpleGenerator(r, new FreeMarkerProcessor(), grp);
                        Hashtable<String, ReportProcessor> definedProcs = (Hashtable<String, ReportProcessor>) params
                                .get(SimpleGenerator.REPORT_PROCESSOR_MAP);
                        g.registerProcessors(definedProcs.elements());
                        g.generate(params);
                    }
                }
            } catch (JAXBException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.te2m.tools.generator.engine.ReportProcessor#getProcessingTarget()
     */
    @Override
    public String getProcessingTarget() {
        return TemplateType.CHILD_REPORT.name();
    }
}
