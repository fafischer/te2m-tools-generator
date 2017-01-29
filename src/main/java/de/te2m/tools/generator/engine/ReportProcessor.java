/*
 * ReportProcessor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.engine;

import java.util.Map;

/**
 * The Interface ReportProcessor.
 *
 * @author ffischer
 */
public interface ReportProcessor {

    /**
     * Gets the processing target.
     *
     * @return the processing target
     */
    public String getProcessingTarget();

    /**
     * Generate.
     *
     * @param params the params
     * @param template the template
     * @return the byte[]
     */
    public byte[] generate(Map<String, Object> params, String template);

    public boolean createsOutput();
}
