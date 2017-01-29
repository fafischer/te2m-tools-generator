/*
 * TaskDescriptor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.model.mgmt;

import de.te2m.tools.generator.model.BasicDescriptor;

/**
 * The Class ClassDescriptor.
 *
 * @author ffi
 */
public class TaskDescriptor extends BasicDescriptor {

    /**
     * The Enum EstimationUnit.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    public enum EstimationUnit {

        /**
         * Working hours.
         */
        HOUR,
        /**
         * Working days.
         */
        SD;
    }

    /**
     * Builder.
     *
     * @return the builder
     */
    public static Builder<?> builder() {
        return new Builder2();
    }

    /**
     * The type.
     */
    private String owner;

    /**
     * The estimation.
     */
    private float estimation;

    /**
     * The unit.
     */
    private EstimationUnit unit;

    /**
     * Gets the owner.
     *
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner.
     *
     * @param owner the new owner
     */
    private void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets the estimation.
     *
     * @return the estimation
     */
    public float getEstimation() {
        return estimation;
    }

    /**
     * Sets the estimation.
     *
     * @param estimation the new estimation
     */
    private void setEstimation(float estimation) {
        this.estimation = estimation;
    }

    /**
     * Gets the unit.
     *
     * @return the unit
     */
    public EstimationUnit getUnit() {
        return unit;
    }

    /**
     * Sets the unit.
     *
     * @param unit the new unit
     */
    public void setUnit(EstimationUnit unit) {
        this.unit = unit;
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
         * The inner owner.
         */
        private String innerOwner;

        /**
         * The inner estimation.
         */
        private float innerEstimation;

        /**
         * The inner unit.
         */
        private EstimationUnit innerUnit;

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.BasicDescriptor.Builder#build()
         */
        @Override
        public TaskDescriptor build() {
            TaskDescriptor cd = (TaskDescriptor) super.enrich(new TaskDescriptor());
            cd.setEstimation(innerEstimation);
            cd.setUnit(innerUnit);
            cd.setOwner(innerOwner);
            return cd;
        }

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.BasicDescriptor.Builder#enrich(java.lang.Object)
         */
        @Override
        public Object enrich(Object obj) {
            if (null != obj && (obj instanceof TaskDescriptor)) {
                TaskDescriptor cd = (TaskDescriptor) super.enrich(((TaskDescriptor) obj));

                return cd;
            }
            return null;

        }

        /**
         * With owner.
         *
         * @param dep the dep
         * @return the t
         */
        public T withOwner(String dep) {

            innerOwner = dep;
            return self();
        }

        /**
         * With estimation.
         *
         * @param val the val
         * @param unit the unit
         * @return the t
         */
        public T withEstimation(float val, EstimationUnit unit) {

            innerEstimation = val;
            innerUnit = unit;
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
         * @see de.te2m.tools.generator.model.mgmt.TaskDescriptor.Builder#self()
         */
        @Override
        protected Builder2 self() {
            return this;
        }
    }

}
