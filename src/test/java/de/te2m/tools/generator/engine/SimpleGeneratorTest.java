/*
* SimpleGeneratorTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine;

import de.te2m.report.api.model.GeneratorTarget;
import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * The Class SimpleGeneratorTest.
 *
 * @author ffischer
 */
public class SimpleGeneratorTest {

    /**
     * Instantiates a new simple generator test.
     */
    public SimpleGeneratorTest() {
    }

    /**
     * Test of getSubTargets method, of class SimpleGenerator.
     */
    @Test
    public void testGetSubTargets() {
        System.out.println("getSubTargets");
        HashMap params = new HashMap();
        params.put("test", new Object[]{"1", "2", "3"});
        GeneratorTarget target = new GeneratorTarget();
        target.setForEach("test");
        SimpleGenerator instance = new SimpleGenerator(null, null, null);
        int exp = 3;
        List result = instance.getSubTargets(params, target);
        assertEquals(exp, result.size());
    }

    /**
     * Test of getSubTargets method, of class SimpleGenerator.
     */
    @Test
    public void testGetSubTargetsDeep() {
        System.out.println("getSubTargetsDeep");
        HashMap params = new HashMap();

        TestObject t = new TestObject();
        t.setArray(new Object[]{"1", "2", "3"});
        params.put("test", t);
        GeneratorTarget target = new GeneratorTarget();
        target.setForEach("test.array");
        SimpleGenerator instance = new SimpleGenerator(null, null, null);
        int exp = 3;
        List result = instance.getSubTargets(params, target);
        assertEquals(exp, result.size());
    }

    /**
     * The Class TestObject.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    public class TestObject {

        /**
         * Gets the array.
         *
         * @return the array
         */
        public Object[] getArray() {
            return array;
        }

        /**
         * Sets the array.
         *
         * @param array the new array
         */
        public void setArray(Object[] array) {
            this.array = array;
        }
        
        /**
         * The array.
         */
        Object[] array;
    }
}
