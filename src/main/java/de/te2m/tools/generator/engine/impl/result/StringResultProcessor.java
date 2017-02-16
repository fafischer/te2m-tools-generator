/*
* StringResultProcessor.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine.impl.result;

import de.te2m.tools.generator.engine.GeneratorResultProcessor;
import de.te2m.report.api.model.GeneratorTarget;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * The Class StringResultProcessor.
 *
 * @author ffischer
 */
public class StringResultProcessor implements GeneratorResultProcessor {

    /**
     * The target output stream.
     */
    OutputStream targetOutputStream;

    /**
     * The result.
     */
    private String result;

    /**
     * Instantiates a new zip result processor.
     *
     */
    public StringResultProcessor() {

        targetOutputStream = new ByteArrayOutputStream();
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.engine.GeneratorResultProcessor#processGeneratorResult(byte[], de.te2m.tools.generator.model.GeneratorTarget, java.util.HashMap)
     */
    @Override
    public Object processGeneratorResult(byte[] generated, GeneratorTarget target, Map<String, Object> params) {
        try {
            targetOutputStream.write(generated);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.engine.GeneratorResultProcessor#preProcess()
     */
    @Override
    public void preProcess() {

    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.engine.GeneratorResultProcessor#postProcess()
     */
    @Override
    public void postProcess() {
        result = targetOutputStream.toString();
        try {
            targetOutputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Gets the result.
     *
     * @return the result
     */
    public String getResult() {
        return result;
    }
}
