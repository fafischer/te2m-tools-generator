/*
 * ServiceDescriptor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.model.dev.service;

import de.te2m.tools.generator.model.dev.java.JavaClassDescriptor;
import de.te2m.tools.generator.model.mgmt.TaskDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ServiceDescriptor.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class ServiceDescriptor extends JavaClassDescriptor {

    /**
     * The configuration.
     */
    private List<JavaClassDescriptor> configuration;

    /**
     * Builder.
     *
     * @return the builder
     */
    public static Builder<?> builder() {
        return new Builder2();
    }

    /**
     * Gets the configuration.
     *
     * @return the configuration
     */
    public List<JavaClassDescriptor> getConfiguration() {
        if (null == configuration) {
            configuration = new ArrayList<>();
        }
        return configuration;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.te2m.tools.generator.model.dev.java.JavaClassDescriptor#getAllSubTasks()
     */
    @Override
    public List<TaskDescriptor> getAllSubTasks() {
        List<TaskDescriptor> allTasksSD = new ArrayList<>();
        // super.getAllSubTasks();

        if (null != configuration) {
            for (JavaClassDescriptor cfgCD : configuration) {
                if (null != cfgCD.getAllSubTasks()) {
                    allTasksSD.addAll(cfgCD.getAllSubTasks());
                }
            }
        }
        allTasksSD.addAll(super.getAllSubTasks());
        return allTasksSD;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * de.te2m.tools.generator.model.dev.java.JavaClassDescriptor#getImports()
     */
    @Override
    public List<String> getImports() {
        List<String> imports = super.getImports();
        List<JavaClassDescriptor> ops = getConfiguration();
        for (JavaClassDescriptor op : ops) {

            if (op.hasPackage()) {
                processImport(op, imports);
            }

        }

        return imports;

    }

    /**
     * Add a new configuration.
     *
     * @param cfg the new configuration
     */
    private void addConfiguration(JavaClassDescriptor cfg) {
        if (null != cfg) {
            getConfiguration().add(cfg);

        }
    }

    /**
     * Add a new configuration.
     *
     * @param cfgs the cfgs
     */
    private void addMultipleConfigurations(List<JavaClassDescriptor> cfgs) {
        if (null != cfgs) {
            getConfiguration().addAll(cfgs);

        }
    }

    /**
     * The Class Builder.
     *
     * @param <T> the generic type
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    public static abstract class Builder<T extends Builder<T>> extends
            JavaClassDescriptor.Builder<T> {

        /**
         * The configuration.
         */
        private final List<JavaClassDescriptor> configuration = new ArrayList<>();

        /**
         * Add a new configuration.
         *
         * @param cfg the new configuration
         * @return the t
         */
        public T withConfiguration(JavaClassDescriptor cfg) {
            if (null != cfg) {
                configuration.add(cfg);

            }
            return self();
        }

        public T withConfigurations(List<JavaClassDescriptor> cfgs) {

            if (null != cfgs) {

                for (JavaClassDescriptor cfg : cfgs) {
                    if (null != cfg) {
                        configuration.add(cfg);

                    }
                }
            }
            return self();
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * de.te2m.tools.generator.model.dev.java.JavaClassDescriptor.Builder
         * #build()
         */
        @Override
        public ServiceDescriptor build() {

            ServiceDescriptor cd = (ServiceDescriptor) super
                    .enrich(new ServiceDescriptor());
            cd.addMultipleConfigurations(configuration);
            return cd;
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * de.te2m.tools.generator.model.dev.java.JavaClassDescriptor.Builder
         * #enrich(java.lang.Object)
         */
        @Override
        public Object enrich(Object obj) {
            if (null != obj && (obj instanceof JavaClassDescriptor)) {
                ServiceDescriptor cd = (ServiceDescriptor) super
                        .enrich(((ServiceDescriptor) obj));
                cd.addMultipleConfigurations(configuration);
                return cd;
            }
            return null;

        }

    }

    /**
     * The Class Builder2.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    private static class Builder2 extends Builder<Builder2> {

        /*
         * (non-Javadoc)
         *
         * @see de.te2m.tools.generator.model.dev.ClassDescriptor.Builder#self()
         */
        @Override
        protected Builder2 self() {
            return this;
        }
    }

}
