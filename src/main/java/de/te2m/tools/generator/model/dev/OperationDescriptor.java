/*
 * OperationDescriptor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.model.dev;

import de.te2m.tools.generator.engine.GeneratorUtils;
import de.te2m.tools.generator.model.ManagedDescriptor;
import de.te2m.tools.generator.model.dev.java.JavaClassDescriptor;
import de.te2m.tools.generator.model.mgmt.TaskDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class OperationDescriptor.
 *
 * @author ffi
 */
public class OperationDescriptor extends ManagedDescriptor {

    /**
     * Builder.
     *
     * @return the builder
     */
    public static Builder<?> builder() {
        return new Builder2();
    }

    /**
     * The return value.
     */
    private JavaClassDescriptor returnValue;

    /**
     * The parameters.
     */
    private List<JavaClassDescriptor> parameters;

    /**
     * The parameters.
     */
    private List<JavaClassDescriptor> errors;

    /**
     * Gets the return value.
     *
     * @return the return value
     */
    public JavaClassDescriptor getReturnValue() {
        return returnValue;
    }

    /**
     * Sets the return value.
     *
     * @param returnValue the new return value
     */
    public void setReturnValue(JavaClassDescriptor returnValue) {
        this.returnValue = returnValue;
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.model.ManagedDescriptor#getAllSubTasks()
     */
    @Override
    public List<TaskDescriptor> getAllSubTasks() {
        List<TaskDescriptor> allTasksOD = getTasks();

        if (null != returnValue && null != returnValue.getAllSubTasks()) {
            allTasksOD.addAll(returnValue.getAllSubTasks());
        }

        if (null != parameters) {
            for (JavaClassDescriptor cdParam : parameters) {
                if (null != cdParam.getAllSubTasks()) {
                    allTasksOD.addAll(cdParam.getAllSubTasks());
                }
            }
        }

        if (null != errors) {
            for (JavaClassDescriptor cdError : errors) {
                if (null != cdError.getAllSubTasks()) {
                    allTasksOD.addAll(cdError.getAllSubTasks());
                }
            }
        }

        return allTasksOD;
    }

    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public List<JavaClassDescriptor> getParameters() {

        return parameters;
    }

    /**
     * Sets the parameters.
     *
     * @param parameters the new parameters
     */
    public void setParameters(List<JavaClassDescriptor> parameters) {
        this.parameters = parameters;
    }

    /**
     * Adds the parameter.
     *
     * @param param the param
     */
    public void addParameter(JavaClassDescriptor param) {
        if (null == parameters) {
            parameters = new ArrayList<JavaClassDescriptor>();
        }
        getParameters().add(param);
    }

    /**
     * Gets the errors.
     *
     * @return the errors
     */
    public List<JavaClassDescriptor> getErrors() {

        return errors;
    }

    /**
     * Sets the errors.
     *
     * @param errors the new errors
     */
    public void setErrors(List<JavaClassDescriptor> errors) {
        this.errors = errors;
    }

    /**
     * Adds the error.
     *
     * @param param the param
     */
    public void addError(JavaClassDescriptor param) {
        if (null == errors) {
            errors = new ArrayList<JavaClassDescriptor>();
        }
        getErrors().add(param);
    }

    /**
     * Gets the name as java class name.
     *
     * @return the name as java class name
     */
    public String getNameAsJavaClassName() {
        return GeneratorUtils.toJavaClassName(name);
    }

    /**
     * Gets the name as java constant name.
     *
     * @return the name as java constant name
     */
    public String getNameAsJavaConstantName() {
        return GeneratorUtils.toJavaConstantName(name);
    }

    /**
     * The Class Builder.
     *
     * @param <T> the generic type
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    public static abstract class Builder<T extends Builder<T>> extends ManagedDescriptor.Builder<T> {

        /**
         * The operations.
         */
        private List<JavaClassDescriptor> errors;

        /**
         * The inner return.
         */
        private JavaClassDescriptor innerReturn;

        /**
         * The attributes.
         */
        private List<JavaClassDescriptor> parameters;

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.BasicDescriptor.Builder#build()
         */
        @Override
        public OperationDescriptor build() {

            OperationDescriptor cd = (OperationDescriptor) super.enrich(new OperationDescriptor());
            cd.setParameters(parameters);
            cd.setErrors(errors);
            cd.setReturnValue(innerReturn);
            return cd;
        }

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.BasicDescriptor.Builder#enrich(java.lang.Object)
         */
        @Override
        public Object enrich(Object obj) {
            if (null != obj && (obj instanceof OperationDescriptor)) {
                OperationDescriptor cd = (OperationDescriptor) super.enrich(((OperationDescriptor) obj));
                cd.setParameters(parameters);
                cd.setErrors(errors);
                cd.setReturnValue(innerReturn);
                return cd;
            }
            return null;

        }

        /**
         * With deployment unit.
         *
         * @param dep the dep
         * @return the t
         */
        public T withDeploymentUnit(String dep) {

            return self();
        }

        /**
         * With error.
         *
         * @param od the od
         * @return the t
         */
        public T withError(JavaClassDescriptor od) {

            if (null == errors) {
                errors = new ArrayList<>();
            }
            if (null != od) {
                errors.add(od);
            }
            return self();
        }

        /**
         * Add a new parameter.
         *
         * @param od the od
         * @return the t
         */
        public T withParameter(JavaClassDescriptor od) {

            if (null == parameters) {
                parameters = new ArrayList<>();
            }
            if (null != od) {
                parameters.add(od);
            }
            return self();
        }

        /**
         * Add a return value.
         *
         * @param ret the ret
         * @return the t
         */
        public T withReturnValue(JavaClassDescriptor ret) {

            innerReturn = ret;
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
         * @see de.te2m.tools.generator.model.dev.OperationDescriptor.Builder#self()
         */
        @Override
        protected Builder2 self() {
            return this;
        }
    }
}
