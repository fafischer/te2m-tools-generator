/*
 * RawContentProcessor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.engine.impl;

import de.te2m.tools.generator.engine.ReportProcessor;
import de.te2m.report.api.model.TemplateType;
import java.util.Map;

/**
 * This class can be used for passing raw content directly to the result
 * processing.
 * <p>
 * Possible use cases:
 * <ul>
 * <li>licennse files</li>
 * <li>CSS</li>
 * <li>static images</li>
 * </ul>
 * <(p>
 *
 * @author ffischer
 */
public class RawContentProcessor implements ReportProcessor {

    /**
     * The Constant DEFAULT_PARAM_NAME.
     */
    public static final String DEFAULT_PARAM_NAME = "RAW_CONTENT";

    /**
     * Instantiates a new raw content processor.
     */
    public RawContentProcessor() {
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
        if (null == params) {
            return null;
        }

        Object content = params.get(DEFAULT_PARAM_NAME);

        if (null != content && (content instanceof byte[])) {
            return (byte[]) content;
        }

        return null;
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.engine.ReportProcessor#getProcessingTarget()
     */
    @Override
    public String getProcessingTarget() {
        return TemplateType.CLASSPATHREADER.name();
    }
}
