/*
* FileResultProcessorTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine.impl.result.file;

import de.te2m.tools.generator.engine.ReportBaseTest;
import de.te2m.tools.generator.engine.SimpleGenerator;
import de.te2m.tools.generator.engine.impl.freemarker.FreeMarkerProcessor;
import de.te2m.tools.generator.model.GeneratorTarget;
import de.te2m.tools.generator.model.Report;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * The Class FileResultProcessorTest.
 *
 * @author ffischer
 */
public class FileResultProcessorTest extends ReportBaseTest {

  
	
	/**
	 * The Constant TEST_TEMPLATE_CONTENT.
	 */
	private static final String TEST_TEMPLATE_CONTENT = "abcdefgh";
	
	/**
	 * The Constant TEST_FILE_NAME.
	 */
	private static final String TEST_FILE_NAME = "blah";

	/**
     * Instantiates a new zip result processor test.
     */
    public FileResultProcessorTest() {
    }
    
    /**
     * Test of processGeneratorResult method, of class ZipResultProcessor.
     */
    @Test
    public void testProcessGeneratorResult() {
        try {
            FileSystemResultProcessor zrp = new FileSystemResultProcessor(System.getProperty("user.dir"));
            FreeMarkerProcessor fmp = new FreeMarkerProcessor();
            Report r = new Report();
            GeneratorTarget t = new GeneratorTarget();
            t.setName("'"+TEST_FILE_NAME+"'");
            t.setTemplate(TEST_TEMPLATE_CONTENT);
            r.addTarget(t);
            SimpleGenerator g = new SimpleGenerator(r, fmp, zrp);
            HashMap<String, Object> params = new HashMap<String, Object>();
            g.generate(params);
            Path path = FileSystems.getDefault().getPath(System.getProperty("user.dir"), TEST_FILE_NAME);
            assertTrue("Test file missing", Files.exists(path));
            String result = new String(Files.readAllBytes(path), "UTF-8");
            assertEquals("Wrong content found",result , TEST_TEMPLATE_CONTENT);
            Files.delete(path);
        } catch (IOException ex) {
            Logger.getLogger(FileResultProcessorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        } 

    }
}
