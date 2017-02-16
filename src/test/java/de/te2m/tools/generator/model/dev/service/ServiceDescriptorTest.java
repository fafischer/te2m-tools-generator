/*
 * ServiceDescriptorTest.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.model.dev.service;

import de.te2m.report.api.model.dev.OperationDescriptor;
import de.te2m.report.api.model.dev.java.JavaClassDescriptor;
import de.te2m.report.api.model.dev.service.ServiceDescriptor;
import de.te2m.report.api.model.mgmt.TaskDescriptor;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * The Class ServiceDescriptorTest.
 *
 * @author ffischer
 */
public class ServiceDescriptorTest {

    /**
     * Instantiates a new service descriptor test.
     */
    public ServiceDescriptorTest() {
    }

    /**
     * Test for builder basic.
     */
    @Test
    public void testBuilderBasic() {
        System.out.println("testBuilderBasic");
        //ServiceDescriptor instance = new ServiceDescriptor.Builder
        ServiceDescriptor sd = ServiceDescriptor.builder()
                .withName("name")
                .withDescription("description")
                .withType("type")
                .withPackage("pkg.abc")
                .withConfiguration(JavaClassDescriptor.builder()
                        .withName("name2")
                        .withPackage("pkg2.def")
                        .withType("type2")
                        .build())
                .withConfiguration(JavaClassDescriptor.builder()
                        .withName("name3")
                        .withPackage("pkg3.abc")
                        .withType("type3")
                        .build()
                )
                .build();

        assertNotNull("Descriptor missing", sd);

        assertEquals("Wrong name returned", sd.getName(), "name");

        assertEquals("Cfg missing", sd.getConfiguration().size(), 2);

        List<String> imports = sd.getImports();

        assertEquals("Imports missing", imports.size(), 2);

    }

    /**
     * Test for builder with tasks.
     */
    @Test
    public void testBuilderWithTasks() {
        System.out.println("testBuilderWithTasks");
        //ServiceDescriptor instance = new ServiceDescriptor.Builder
        ServiceDescriptor sd = ServiceDescriptor.builder()
                .withName("name")
                .withDescription("description")
                .withType("type")
                .withPackage("pkg.abc")
                .withOperation(OperationDescriptor.builder()
                        .withTask(TaskDescriptor.builder()
                                .withName("Do it!")
                                .withDescription("do something useless")
                                .withOwner("test@example.com")
                                .withEstimation(1, TaskDescriptor.EstimationUnit.HOUR)
                                .build())
                        .withName("OpName")
                        .withDescription("Desc Op 1")
                        .build())
                .withConfiguration(JavaClassDescriptor.builder()
                        .withName("name2")
                        .withPackage("pkg2.def")
                        .withType("type2")
                        .build())
                .withConfiguration(JavaClassDescriptor.builder()
                        .withName("name3")
                        .withPackage("pkg3.abc")
                        .withType("type3")
                        .withTask(TaskDescriptor.builder()
                                .withName("Do it!")
                                .withDescription("do something useless")
                                .withOwner("test@example.com")
                                .withEstimation(2, TaskDescriptor.EstimationUnit.HOUR)
                                .build())
                        .build()
                )
                .withTask(TaskDescriptor.builder()
                        .withName("Do it!")
                        .withDescription("do something useless")
                        .withOwner("test@example.com")
                        .withEstimation(3, TaskDescriptor.EstimationUnit.HOUR)
                        .build())
                .build();

        assertNotNull("Descriptor missing", sd);

        assertEquals("Wrong name returned", sd.getName(), "name");

        assertEquals("Cfg missing", sd.getConfiguration().size(), 2);

        List<String> imports = sd.getImports();

        assertEquals("Imports missing", imports.size(), 2);

        assertNotNull("Task missing", sd.getTasks());

        assertEquals("Task number wrong", sd.getTasks().size(), 1);

        assertNotNull("All tasks missing", sd.getAllSubTasks());

        assertEquals("Task number for all sub tasks wrong", 3, sd.getAllSubTasks().size());

        assertTrue("wrong total estimation: expected 6, was " + sd.getTotalEstimation(), sd.getTotalEstimation() == 6);

    }

    /**
     * Test for builder estimation calculation.
     */
    @Test
    public void testBuilderEstimationCalculation() {
        System.out.println("testBuilderBasic");
        //ServiceDescriptor instance = new ServiceDescriptor.Builder
        ServiceDescriptor sd = ServiceDescriptor.builder()
                .withName("name")
                .withDescription("description")
                .withType("type")
                .withPackage("pkg.abc")
                .withConfiguration(JavaClassDescriptor.builder()
                        .withName("name2")
                        .withPackage("pkg2.def")
                        .withType("type2")
                        .build())
                .withConfiguration(JavaClassDescriptor.builder()
                        .withName("name3")
                        .withPackage("pkg3.abc")
                        .withType("type3")
                        .build()
                )
                .withTask(TaskDescriptor.builder()
                        .withName("Do it!")
                        .withDescription("do something useless")
                        .withOwner("test@example.com")
                        .withEstimation(3, TaskDescriptor.EstimationUnit.HOUR)
                        .build())
                .withTask(TaskDescriptor.builder()
                        .withName("Do it!")
                        .withDescription("do something useless")
                        .withOwner("test@example.com")
                        .withEstimation(4, TaskDescriptor.EstimationUnit.HOUR)
                        .build())
                .withTask(TaskDescriptor.builder()
                        .withName("Do it!")
                        .withDescription("do something useless")
                        .withOwner("test@example.com")
                        .withEstimation(5, TaskDescriptor.EstimationUnit.HOUR)
                        .build())
                .withTask(TaskDescriptor.builder()
                        .withName("Do it!")
                        .withDescription("do something useless")
                        .withOwner("test@example.com")
                        .withEstimation(6, TaskDescriptor.EstimationUnit.HOUR)
                        .build())
                .build();

        assertNotNull("Descriptor missing", sd);

        assertEquals("Wrong name returned", sd.getName(), "name");

        assertEquals("Cfg missing", sd.getConfiguration().size(), 2);

        List<String> imports = sd.getImports();

        assertEquals("Imports missing", imports.size(), 2);
        assertTrue("wrong total estimation: expected 18, was " + sd.getTotalEstimation(), sd.getTotalEstimation() == 18);

    }
}
