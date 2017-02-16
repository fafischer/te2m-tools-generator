/*
* ZipResultProcessor.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine.impl.result.zip;

import de.te2m.tools.generator.engine.ELUtils;
import de.te2m.tools.generator.engine.GeneratorResultProcessor;
import de.te2m.report.api.model.GeneratorTarget;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * The Class ZipResultProcessor.
 *
 * @author ffischer
 */
public class ZipResultProcessor implements GeneratorResultProcessor {

    /**
     * The zos.
     */
    ZipOutputStream zos;
    
    /**
     * The target output stream.
     */
    OutputStream targetOutputStream;

    /**
     * Instantiates a new zip result processor.
     *
     * @param os the os
     */
    public ZipResultProcessor(OutputStream os) {

        targetOutputStream = os;
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.engine.GeneratorResultProcessor#processGeneratorResult(byte[], de.te2m.tools.generator.model.GeneratorTarget, java.util.HashMap)
     */
    public Object processGeneratorResult(byte[] generated, GeneratorTarget target, Map<String, Object> params) {
        try {
            String name = target.getName();
            if (null == name || name.trim().length() == 0) {
                name = UUID.randomUUID().toString();

            }
            String fname = (String) ELUtils.evalExpression(params, name);

            System.out.println(name + " --> " + fname);

            ZipEntry ze = new ZipEntry(fname);

            zos.putNextEntry(ze);
            zos.write(generated);
            zos.closeEntry();
        } catch (IOException ex) {
            Logger.getLogger(ZipResultProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.engine.GeneratorResultProcessor#preProcess()
     */
    public void preProcess() {

        zos = new ZipOutputStream(targetOutputStream);
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.engine.GeneratorResultProcessor#postProcess()
     */
    public void postProcess() {
        try {
            zos.flush();
            zos.close();
        } catch (IOException ex) {
            Logger.getLogger(ZipResultProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
