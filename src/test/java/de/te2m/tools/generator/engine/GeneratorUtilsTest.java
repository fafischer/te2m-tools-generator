/*
* GeneratorUtilsTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * The Class GeneratorUtilsTest.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class GeneratorUtilsTest {
	
    /**
     * Test for java name1.
     */
    @Test
    public void testJavaName1() {

        assertEquals("Aewww", GeneratorUtils.toJavaClassName("   Äwww"));
    }
    
    /**
     * Test for java name2.
     */
    @Test
    public void testJavaName2() {

        assertEquals("", GeneratorUtils.toJavaClassName("              "));
    }
    
    /**
     * Test for java name3.
     */
    @Test
    public void testJavaName3() {

        assertEquals("", GeneratorUtils.toJavaClassName("!\"§$%%%%%&&((((=="));
    }
    
    /**
     * Test for java name4.
     */
    @Test
    public void testJavaName4() {

        assertEquals("ThisIsATestForCamelCasing", GeneratorUtils.toJavaClassName(" this is a test for §$%! camel casing"));
    }
    
    /**
     * Test for java name5.
     */
    @Test
    public void testJavaName5() {

        assertEquals("AtHome", GeneratorUtils.toJavaClassName(" @ home "));
    }
}
