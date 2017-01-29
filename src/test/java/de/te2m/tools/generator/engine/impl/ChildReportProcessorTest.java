/*
* ChildReportProcessorTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine.impl;

import de.te2m.tools.generator.engine.ReportBaseTest;
import de.te2m.tools.generator.engine.SimpleGenerator;
import de.te2m.tools.generator.engine.impl.freemarker.FreeMarkerProcessor;
import de.te2m.tools.generator.engine.impl.result.StringResultProcessor;
import de.te2m.tools.generator.model.GeneratorTarget;
import de.te2m.tools.generator.model.Report;
import de.te2m.tools.generator.model.TemplateType;
import java.util.HashMap;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * The Class ChildReportProcessorTest.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class ChildReportProcessorTest extends ReportBaseTest{

    /**
     * Instantiates a new child report processor test.
     */
    public ChildReportProcessorTest() {
    }

    /**
     * Test for generate null.
     */
    @Test
    public void testGenerateNull() {

        ChildReportProcessor p = new ChildReportProcessor();
        assertNull(p.generate(null, null));

    }
    
    /**
     * Test for full generate.
     */
    @Test
    public void testFullGenerate() {

        ChildReportProcessor p = new ChildReportProcessor();
        
        Report parentReport = new Report();
        Report subReport = new Report();
        String testTemplate = "${test} world";
        GeneratorTarget gt = new GeneratorTarget();
        gt.setTemplateType(TemplateType.FREEMARKER.toString());
        gt.setTemplate(testTemplate);
        subReport.addTarget(gt);
        GeneratorTarget subReportTarget = new GeneratorTarget();
                
        subReportTarget.setTemplateType(TemplateType.CHILD_REPORT.toString());
        
        String marshalledSubReport =marshallReport(subReport); 
        
        assertNotNull("marshalledSubReport missing", marshalledSubReport);
        
        subReportTarget.setTemplate(marshalledSubReport);
        
        parentReport.addTarget(subReportTarget);
        
        assertNotNull(parentReport);
        
        String marshalledParentReport = marshallReport(parentReport);
        
        assertNotNull("marshalledParentReportMissing", marshalledParentReport);
        
        assertTrue("included sub report missing", marshalledParentReport.indexOf("&lt;report")!=-1);

        assertTrue("inner template missing", marshalledParentReport.indexOf(testTemplate)!=-1);

        StringResultProcessor strgRP =  new StringResultProcessor();
        
        SimpleGenerator g = new SimpleGenerator(parentReport, new ChildReportProcessor(), strgRP);

        g.registerProcessor(new FreeMarkerProcessor());
        
        HashMap<String, Object> params = new HashMap();
        
        params.put("test", "Hello");
        
        g.generate(params);

        assertNotNull("Unable to get result", strgRP.getResult());
        
        assertTrue("Wrong generator result", strgRP.getResult().indexOf("Hello world")!=-1);
        
        

    }

}
