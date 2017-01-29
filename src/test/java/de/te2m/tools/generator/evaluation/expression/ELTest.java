/*
* ELTest.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.evaluation.expression;

import javax.el.ELProcessor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * The Class ELTest.
 *
 * @author ffischer
 */
public class ELTest {

    /**
     * The elp.
     */
    ELProcessor elp = new ELProcessor();

    /**
     * Test for el basic.
     */
    @Test
    public void testELBasic() {
        // obligatory test
        eval("'Hello, world!'");
        // static field reference
        eval("Boolean.TRUE");
        // static method reference
        eval("Integer.numberOfTrailingZeros(16)");
        // string concatenation
        eval("10 += 11");
        // assignment and semicolon operator
        eval("xx = 100; yy = 11; xx+yy");
        // lambda expression: immediate invokation
        eval("(x->x+1)(10)");
        // lambda expression: set to variable and recursive invokation
        eval("fact = n->n==0? 1: n*fact(n-1); fact(5)");
        // List construction
        eval("['eenie', 'meenie', 'miney', 'moe']");
        // Map construction
        eval("{'one':10, 'two':20, 'three':300}");
        // Simple collection operation
        eval("[1,2,3,4,5,6,7,8].stream().filter(i->i%2 == 0) \n"
                + "                          .map(i->i*10) \n"
                + "                          .toList()");
        // sort a list in descending order
        eval("[1,5,3,7,4,2,8].stream().sorted((i,j)->j-i).toList()");
    }

    /**
     * Test for boolean comparison.
     */
    @Test
    public void testBooleanComparison() {
        TestEnumCtnr ctnr = new TestEnumCtnr();
        ctnr.setValue(TestEnum.NO);
        elp.defineBean("test", ctnr);
        assertNotNull(elp.eval("test.testValue"));
        assertNotNull(elp.eval("test.value"));
        Boolean result = (Boolean) elp.eval("test.value.toString().equals('NO')");
        assertTrue("Wrong enum eval result", result);


    }

    /**
     * Test for string concatenation basic.
     */
    @Test
    public void testStringConcatenationBasic() {
        TestEnumCtnr ctnr = new TestEnumCtnr();
        ctnr.setValue(TestEnum.NO);
        elp.defineBean("test", ctnr);
        elp.defineBean("base", "/a/b/c/");
        String result = (String) elp.eval("base.concat('NO')");
        assertNotNull("Missing string concatenation result", result);
        assertEquals("Wrong concatenation result", "/a/b/c/NO", result);

    }

    /**
     * Test for string concatenation static string.
     */
    @Test
    public void testStringConcatenationStaticString() {
        TestEnumCtnr ctnr = new TestEnumCtnr();
        ctnr.setValue(TestEnum.NO);
        elp.defineBean("test", ctnr);
        elp.defineBean("base", "/a/b/c/");
        String result = (String) elp.eval("'/a/b/c/'.concat('NO')");
        assertNotNull("Missing string concatenation result", result);
        assertEquals("Wrong concatenation result", "/a/b/c/NO", result);

    }

    /**
     * Test for string concatenation sub eval.
     */
    @Test
    public void testStringConcatenationSubEval() {
        TestEnumCtnr ctnr = new TestEnumCtnr();
        ctnr.setValue(TestEnum.NO);
        elp.defineBean("test", ctnr);
        elp.defineBean("base", "/a/b/c/");
        String result = (String) elp.eval("'/a/b/c/'.concat(test.value.toString())");
        assertNotNull("Missing string concatenation result", result);
        assertEquals("Wrong concatenation result", "/a/b/c/NO", result);

    }

    /**
     * Eval.
     *
     * @param input the input
     */
    void eval(String input) {
        System.out.println("\n====");
        System.out.println("Input EL string: " + input);
        Object ret = elp.eval(input);
        assertNotNull("Return value missing", ret);
        System.out.println("Output Value: " + ret);
    }

    /**
     * The Class TestEnumCtnr.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    public class TestEnumCtnr {

        /**
         * The value.
         */
        private TestEnum value;

        /**
         * Gets the value.
         *
         * @return the value
         */
        public TestEnum getValue() {
            return value;
        }

        /**
         * Gets the test for value.
         *
         * @return the test for value
         */
        public String getTestValue() {
            return value.toString();
        }

        /**
         * Sets the value.
         *
         * @param value the new value
         */
        public void setValue(TestEnum value) {
            this.value = value;
        }
    }

    /**
     * The Enum TestEnum.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    private enum TestEnum {

        /**
         * The yes.
         */
        YES,
        
        /**
         * The no.
         */
        NO;
    }
}
