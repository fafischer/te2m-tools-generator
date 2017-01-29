/*
 * ManagedDescriptor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.model;

import de.te2m.tools.generator.model.mgmt.TaskDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ClassDescriptor.
 *
 * @author ffi
 */
public class ManagedDescriptor extends BasicDescriptor {

    /**
     * Builder.
     *
     * @return the builder
     */
    public static Builder<?> builder() {
        return new Builder2();
    }

    /**
     * The attributes.
     */
    private List<TaskDescriptor> tasks = new ArrayList<>();

    /**
     * Gets the tasks.for the current context including all subtasks.
     *
     * @return the tasks
     */
    public List<TaskDescriptor> getAllSubTasks() {
        return tasks;
    }

    /**
     * Gets the tasks.for the current context.
     *
     * @return the tasks
     */
    public List<TaskDescriptor> getTasks() {
        return tasks;
    }

    /**
     * Gets the total estimation.
     *
     * @return the total estimation
     */
    public float getTotalEstimation() {
        float est = 0;

        if (null != getAllSubTasks()) {
            List<TaskDescriptor> allT = getAllSubTasks();
            for (TaskDescriptor td : allT) {
                est = est + td.getEstimation();
            }
        }
        return est;
    }

    /**
     * Sets the tasks.
     *
     * @param tasks the new tasks
     */
    private void setTasks(List<TaskDescriptor> tasks) {
        this.tasks = tasks;
    }

    /**
     * The Class Builder.
     *
     * @param <T> the generic type
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    public static abstract class Builder<T extends Builder<T>> extends BasicDescriptor.Builder<T> {

        /**
         * The inner tasks.
         */
        private List<TaskDescriptor> innerTasks = new ArrayList<>();

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.BasicDescriptor.Builder#build()
         */
        @Override
        public ManagedDescriptor build() {
            ManagedDescriptor cd = (ManagedDescriptor) super.enrich(new ManagedDescriptor());
            cd.setTasks(innerTasks);
            return cd;
        }

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.BasicDescriptor.Builder#enrich(java.lang.Object)
         */
        @Override
        public Object enrich(Object obj) {
            if (null != obj && (obj instanceof ManagedDescriptor)) {
                ManagedDescriptor cd = (ManagedDescriptor) super.enrich(((ManagedDescriptor) obj));

                cd.setTasks(innerTasks);

                return cd;
            }
            return null;

        }

        /**
         * With task.
         *
         * @param od the od
         * @return the t
         */
        public T withTask(TaskDescriptor od) {

            if (null == innerTasks) {
                innerTasks = new ArrayList<>();
            }
            if (null != od) {
                innerTasks.add(od);
            }
            return self();
        }

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.BasicDescriptor.Builder#self()
         */
        protected abstract T self();
    }

    /**
     * The Class Builder2.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    private static class Builder2 extends Builder<Builder2> {

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.ManagedDescriptor.Builder#self()
         */
        @Override
        protected Builder2 self() {
            return this;
        }
    }

}
