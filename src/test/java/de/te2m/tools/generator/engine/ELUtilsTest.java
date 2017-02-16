/*
* ELUtilsTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine;

import de.te2m.report.api.model.dev.java.JavaClassDescriptor;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * The Class ELUtilsTest.
 *
 * @author ffischer
 */
public class ELUtilsTest {

    /**
     * Instantiates a new eL utils test.
     */
    public ELUtilsTest() {
    }

    /**
     * Test of evalExpression method, of class ELUtils.
     */
    @Test
    public void testEvalExpression() {
        HashMap params = new HashMap();

        JavaClassDescriptor jcd = JavaClassDescriptor.builder()
                .withName("JCD")
                .withPackage("a.b.c.d")
                .build();
        params.put("bo", jcd);
        String expression = "bo.packageName.replace('.','/').concat('/').concat(bo.name).concat('.java')";
        Object expResult = "a/b/c/d/JCD.java";
        Object result = ELUtils.evalExpression(params, expression);
        assertEquals(expResult, result);
    }
}
