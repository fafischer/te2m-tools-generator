/*
* ReportTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.report;

import de.te2m.tools.generator.model.FileType;
import de.te2m.tools.generator.model.GeneratorTarget;
import de.te2m.tools.generator.model.Report;
import de.te2m.tools.generator.model.TemplateType;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class ReportTest.
 *
 * @author ffi
 */
public class ReportTest {

    /**
     * Instantiates a new report test.
     */
    public ReportTest() {
    }

    /**
     * Sets the up class.
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Tear down class.
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getTargets method, of class Report.
     */
    @Test
    public void testCreateReport() {
        System.out.println("getTargets");
        Report instance = new Report();

        GeneratorTarget t1 = new GeneratorTarget();
        t1.setTemplateType(TemplateType.FREEMARKER.toString());
        t1.setTargetType(FileType.HTML);
        t1.setName("Test1");
        t1.setDescription("Description for Test1");
        t1.setRelPath("/test/test1");

        t1.setTemplate("<html><head><title>Test</title></head><body><h1>Test</h1></body></html>");

        instance.getTargets().add(t1);

        List result = instance.getTargets();

        assertNotNull("Result is null", result);

        assertEquals("Wrong number of targets", 1, result.size());
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test for simple xml marshalling.
     */
    @Test
    public void testSimpleXMLMarshalling() {
        try {
            System.out.println("SimpleXMLMarshalling");
            Report instance = new Report();

            GeneratorTarget t1 = new GeneratorTarget();
            t1.setTemplateType(TemplateType.FREEMARKER.toString());
            t1.setTargetType(FileType.HTML);
            t1.setName("Test1");
            t1.setDescription("Description for Test1");
            t1.setRelPath("/test/test1");

            t1.setTemplate("<html><head><title>Test</title></head><body><h1>Test</h1></body></html>");

            instance.addTarget(t1);

            JAXBContext context;
            context = JAXBContext.newInstance(Report.class);

            StringWriter sw = new StringWriter();

            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(instance, sw);

            String xml = sw.toString();

            System.out.println(xml);

            assertTrue("Generated string is incomplete", xml.indexOf("<template>&lt;html&gt;&lt;head&gt;") != -1);
            assertTrue("Target type wrong or missing", xml.indexOf("<targetType>HTML</targetType>") != -1);
        } catch (JAXBException ex) {
            Logger.getLogger(ReportTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("JAXB Exception occured " + ex.getMessage());
        }
    }
}
