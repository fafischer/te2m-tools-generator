/*
 * FreeMarkerProcessor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.engine.impl.freemarker;

import de.te2m.tools.generator.engine.ReportProcessor;
import de.te2m.report.api.model.TemplateType;
import java.util.Map;

/**
 * The Class FreeMarkerProcessor.
 *
 * @author ffischer
 */
public class FreeMarkerProcessor implements ReportProcessor {

    /**
     * Instantiates a new free marker processor.
     */
    public FreeMarkerProcessor() {
    }

    @Override
    public boolean createsOutput() {
        return true;
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.engine.ReportProcessor#generate(java.util.HashMap, java.lang.String)
     */
    @Override
    public byte[] generate(Map<String, Object> params, String template) {
        return FreemarkerUtils.generate(params, template).getBytes();
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.engine.ReportProcessor#getProcessingTarget()
     */
    @Override
    public String getProcessingTarget() {
        return TemplateType.FREEMARKER.name();
    }
}
