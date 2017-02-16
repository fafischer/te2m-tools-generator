/*
 * FreemarkerGeneratorTest.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.engine.impl.freemarker;

import de.te2m.report.api.model.dev.java.JavaClassDescriptor;
import de.te2m.report.api.model.dev.service.ServiceDescriptor;
import de.te2m.tools.generator.engine.ReportBaseTest;
import de.te2m.tools.generator.engine.SimpleGenerator;
import de.te2m.report.api.model.GeneratorTarget;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class FreemarkerGeneratorTest.
 *
 * @author ffi
 */
public class FreemarkerGeneratorTest extends ReportBaseTest {

    /**
     * Instantiates a new freemarker generator test.
     */
    public FreemarkerGeneratorTest() {
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
        setupSimpleReport();
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test for get generator target keys.
     */
    @Test
    public void testGetGeneratorTargetKeys() {
        System.out.println("getGeneratorTargetKeys");
        SimpleGenerator g = new SimpleGenerator(simpleReport, new FreeMarkerProcessor(), null);
        List<String> keys = g.getGeneratorTargetKeys();
        assertTrue("wrong number of keys returned", keys.size() == 2);
        assertTrue("Key missing", keys.contains(JavaClassDescriptor.class.getCanonicalName()));
        assertTrue("Key missing", keys.contains(ServiceDescriptor.class.getCanonicalName()));
    }

    /**
     * Test for get generator targets.
     */
    @Test
    public void testGetGeneratorTargets() {
        System.out.println("getGeneratorTargets");
        SimpleGenerator g = new SimpleGenerator(simpleReport, new FreeMarkerProcessor(), null);
        List<GeneratorTarget> keys = g.getGeneratorTargets(JavaClassDescriptor.class.getCanonicalName());
        assertTrue("wrong number of keys returned", keys.size() == 2);
        keys = g.getGeneratorTargets(ServiceDescriptor.class.getCanonicalName());
        assertTrue("wrong number of keys returned", keys.size() == 1);
    }
}
