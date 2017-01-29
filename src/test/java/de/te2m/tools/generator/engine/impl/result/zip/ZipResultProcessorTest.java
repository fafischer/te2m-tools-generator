/*
* ZipResultProcessorTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine.impl.result.zip;

import de.te2m.tools.generator.engine.ReportBaseTest;
import de.te2m.tools.generator.engine.SimpleGenerator;
import de.te2m.tools.generator.engine.impl.freemarker.FreeMarkerProcessor;
import de.te2m.tools.generator.model.GeneratorTarget;
import de.te2m.tools.generator.model.Report;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 * The Class FileResultProcessorTest.
 *
 * @author ffischer
 */
public class ZipResultProcessorTest extends ReportBaseTest {

    /**
     * Instantiates a new zip result processor test.
     */
    public ZipResultProcessorTest() {
    }

    /**
     * Test of processGeneratorResult method, of class ZipResultProcessor.
     */
    @Test
    public void testProcessGeneratorResult() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("test.zip");
            ZipResultProcessor zrp = new ZipResultProcessor(fos);
            FreeMarkerProcessor fmp = new FreeMarkerProcessor();
            Report r = new Report();
            GeneratorTarget t = new GeneratorTarget();
            t.setName("'blah'");
            t.setTemplate("abcdefgh");
            r.addTarget(t);
            SimpleGenerator g = new SimpleGenerator(r, fmp, zrp);
            HashMap<String, Object> params = new HashMap<String, Object>();
            g.generate(params);
            fos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ZipResultProcessorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ZipResultProcessorTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ZipResultProcessorTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
