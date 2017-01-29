/*
* FreemarkeUtilsTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine.impl.freemarker;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class FreemarkeUtilsTest.
 *
 * @author ffi
 */
public class FreemarkeUtilsTest {

    /**
     * Instantiates a new freemarke utils test.
     */
    public FreemarkeUtilsTest() {
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
     * Test of generate method, of class FreemarkerUtils.
     */
    @Test
    public void testGenerateTemplateAsString() {
        System.out.println("generateTemplateAsString");
        HashMap hashMap = new HashMap();
        hashMap.put("test", "hello");
        String templateContent = "${test} world";
        String expResult = "hello world";
        String result = FreemarkerUtils.generate(hashMap, templateContent);
        assertEquals(expResult, result);

    }

    /**
     * Test of generate method, of class FreemarkerUtils.
     */
    @Test
    public void testGenerateTemplateAsFile() {
        System.out.println("generateTemplateAsFile");
        HashMap hashMap = new HashMap();
        hashMap.put("test", "hello");
        String expResult = "hello world";
        String result = FreemarkerUtils.generate(hashMap, "/testtemplates", "test.ftl");

        assertEquals(expResult, result);
    }
    
    /**
     * Test of generate method, of class FreemarkerUtils.
     */
    @Test
    public void testMacroTemplate() {
        System.out.println("testMacroTemplate");
        HashMap hashMap = new HashMap();
        hashMap.put("counter", 10);
        //String expResult = "hello world";
        String result = FreemarkerUtils.generate(hashMap, "/testtemplates", "macrotest.ftl");

        System.out.println(result);
        
        //assertEquals(expResult, result);
    }
    
    /**
     * Test for macro template2.
     */
    @Test
    public void testMacroTemplate2() {
        System.out.println("testMacroTemplate2");
        HashMap hashMap = new HashMap();
        hashMap.put("counter", 10);
        //String expResult = "hello world";
        String result = FreemarkerUtils.generate(hashMap, "/testtemplates", "macrotest2.ftl");

        System.out.println(result);
        
        //assertEquals(expResult, result);
    }
}
