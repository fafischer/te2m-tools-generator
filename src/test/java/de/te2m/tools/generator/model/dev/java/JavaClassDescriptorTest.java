/*
 * JavaClassDescriptorTest.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.model.dev.java;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class JavaClassDescriptorTest.
 *
 * @author ffi
 */
public class JavaClassDescriptorTest {

    /**
     * Instantiates a new java class descriptor test.
     */
    public JavaClassDescriptorTest() {
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
     * Test of getJavaName method, of class JavaClassDescriptor.
     */
    @Test
    public void testGetJavaNameDefault() {
        System.out.println("getJavaNameDefault");
        JavaClassDescriptor instance = JavaClassDescriptor.builder().withName("Test").withDescription("Test").build();
        String expResult = "Test";
        String result = instance.getJavaName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetJavaZypeNameDefault() {
        System.out.println("getJavaTypeNameDefault");
        JavaClassDescriptor instance = JavaClassDescriptor.builder().withName("Test").withDescription("Test").build();
        String expResult = "Test";
        String result = instance.getJavaName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJavaName method, of class JavaClassDescriptor.
     */
    @Test
    public void testGetJavaName1stLower() {
        System.out.println("getJavaName1stLower");
        JavaClassDescriptor instance = JavaClassDescriptor.builder().withName("Test").withDescription("Test").build();
        String expResult = "Test";
        String result = instance.getJavaName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJavaName method, of class JavaClassDescriptor.
     */
    @Test
    public void testGetJavaNameSpaces() {
        System.out.println("getJavaNameSpaces");
        JavaClassDescriptor instance = JavaClassDescriptor.builder().withName("Test 1").build();
        String expResult = "Test1";
        String result = instance.getJavaName();
        assertEquals(expResult, result);
    }
}
