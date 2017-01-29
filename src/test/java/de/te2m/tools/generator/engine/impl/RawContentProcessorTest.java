/*
* RawContentProcessorTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * The Class RawContentProcessorTest.
 *
 * @author ffischer
 */
public class RawContentProcessorTest {

    /**
     * Instantiates a new raw content processor test.
     */
    public RawContentProcessorTest() {
    }

    /**
     * Test for generate params null.
     */
    @Test
    public void testGenerateParamsNull() {

        RawContentProcessor p = new RawContentProcessor();
        assertNull(p.generate(null, null));

    }

    /**
     * Test for generate null.
     */
    @Test
    public void testGenerateNull() {

        RawContentProcessor p = new RawContentProcessor();

        HashMap<String, Object> params = new HashMap<String, Object>();

        assertNull(p.generate(params, null));
    }

    /**
     * Test for generate byte array.
     */
    @Test
    public void testGenerateByteArray() {

        RawContentProcessor p = new RawContentProcessor();

        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put(RawContentProcessor.DEFAULT_PARAM_NAME, RawContentProcessor.DEFAULT_PARAM_NAME.getBytes());

        Object o = p.generate(params, null);

        assertTrue(o instanceof byte[]);

        assertEquals(new String((byte[]) o), RawContentProcessor.DEFAULT_PARAM_NAME);

    }

    /**
     * Test for generate other.
     */
    @Test
    public void testGenerateOther() {

        RawContentProcessor p = new RawContentProcessor();

        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put(RawContentProcessor.DEFAULT_PARAM_NAME, BigDecimal.ZERO);

        assertNull(p.generate(params, null));

    }
}
