/*
 * ClassPathResourcerProcessor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.engine.impl;

import de.te2m.tools.generator.engine.ReportProcessor;
import de.te2m.tools.generator.model.TemplateType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * This class can be used for reading content from the current class path. This
 * can be used for getting static resources from the current class path
 * <p>
 * Possible use cases:
 * <ul>
 * <li>license files</li>
 * <li>CSS</li>
 * <li>static images</li>
 * </ul>
 * <(p>
 *
 * @author ffischer
 */
public class ClassPathResourcerProcessor implements ReportProcessor {

    /**
     * Instantiates a new class path resourcer processor.
     */
    public ClassPathResourcerProcessor() {
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
        try {
            if (null == template) {
                return null;
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            InputStream is = this.getClass().getResourceAsStream(template);
            int next = is.read();
            while (next != -1) {
                bos.write(next);
                next = is.read();
            }
            bos.flush();
            return bos.toByteArray();
        } catch (IOException ex) {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.engine.ReportProcessor#getProcessingTarget()
     */
    @Override
    public String getProcessingTarget() {
        return TemplateType.CLASSPATHREADER.name();
    }
}
