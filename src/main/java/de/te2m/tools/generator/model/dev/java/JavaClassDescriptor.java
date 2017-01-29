/*
 * JavaClassDescriptor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.model.dev.java;

import de.te2m.tools.generator.engine.GeneratorUtils;
import de.te2m.tools.generator.model.ManagedDescriptor;
import de.te2m.tools.generator.model.dev.OperationDescriptor;
import de.te2m.tools.generator.model.mgmt.TaskDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class JavaClassDescriptor.
 *
 * @author ffi
 */
public class JavaClassDescriptor extends ManagedDescriptor {

    /**
     * Builder.
     *
     * @return the builder
     */
    public static Builder<?> builder() {
        return new Builder2();
    }

    /**
     * The members.
     */
    private List<JavaClassDescriptor> members;

    /**
     * The deployment unit.
     */
    private String deploymentUnit;
    /**
     * The operations.
     */
    private List<OperationDescriptor> operations;

    /**
     * The package name.
     */
    private String packageName;

    /**
     * The type.
     */
    private String type;

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.model.ManagedDescriptor#getAllSubTasks()
     */
    @Override
    public List<TaskDescriptor> getAllSubTasks() {

        List<TaskDescriptor> allTasksCD = new ArrayList<>();

        if (null != members) {
            for (JavaClassDescriptor currentCD : members) {
                if (null != currentCD.getAllSubTasks()) {
                    allTasksCD.addAll(currentCD.getAllSubTasks());
                }
            }
        }

        if (null != operations) {
            for (OperationDescriptor cd : operations) {
                if (null != cd.getAllSubTasks()) {
                    allTasksCD.addAll(cd.getAllSubTasks());
                }
            }
        }

        allTasksCD.addAll(super.getAllSubTasks());
        return allTasksCD;
    }

    /**
     * Gets the members.
     *
     * @return the members
     */
    public List<JavaClassDescriptor> getMembers() {
        return members;
    }

    /**
     * Gets the imports.
     *
     * @return the imports
     */
    public List<String> getImports() {
        List<String> imports = new ArrayList<String>();
        List<OperationDescriptor> ops = getOperations();
        if (null != ops) {
            for (OperationDescriptor op : ops) {
                // return values

                if (null != op.getReturnValue() && ((JavaClassDescriptor) op.getReturnValue()).hasPackage()) {
                    processImport(op.getReturnValue(), imports);
                }

                List<JavaClassDescriptor> params = op.getParameters();

                if (null != params) {
                    for (JavaClassDescriptor cd : params) {
                        if (((JavaClassDescriptor) cd).hasPackage()) {
                            processImport(cd, imports);
                        }

                    }
                }
            }
        }

        List<JavaClassDescriptor> jcds = getMembers();
        if (null != jcds) {
            for (JavaClassDescriptor cd : jcds) {
                if (((JavaClassDescriptor) cd).hasPackage()) {
                    processImport(cd, imports);
                }
            }
        }
        return imports;

    }

    /**
     * Gets the java name.
     *
     * @return the java name
     */
    public String getJavaName() {
        return GeneratorUtils.toJavaClassName(getName());
    }

    /**
     * Gets the java type name. If type is null then a fallback to name is
     * performed
     *
     * @return the java type name
     */
    public String getJavaTypeName() {

        return GeneratorUtils.toJavaClassName(getType() != null ? getType() : getName());
    }

    /**
     * Gets the package name.
     *
     * @return the package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets the package name.
     *
     * @param packageName the new package name
     */
    private void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Checks for package.
     *
     * @return true, if successful
     */
    public boolean hasPackage() {
        return null != packageName && packageName.trim().length() > 0 && !"java.lang".equals(packageName);
    }

    /**
     * Process import.
     *
     * @param cd the cd
     * @param imports the imports
     */
    protected void processImport(JavaClassDescriptor cd, List<String> imports) {
        JavaClassDescriptor c = (JavaClassDescriptor) cd;
        String fqpn = c.getPackageName() + "." + c.getJavaTypeName();
        if (!imports.contains(fqpn)) {
            imports.add(fqpn);
        }
    }

    /**
     * Sets the members.
     *
     * @param members the new members
     */
    private void setMembers(List<JavaClassDescriptor> members) {
        this.members = members;
    }

    /**
     * Gets the deployment unit.
     *
     * @return the deployment unit
     */
    public String getDeploymentUnit() {
        return deploymentUnit;
    }

    /**
     * Gets the operations.
     *
     * @return the operations
     */
    public List<OperationDescriptor> getOperations() {
        return operations;
    }

    /**
     * Sets the operations.
     *
     * @param operations the new operations
     */
    private void setOperations(List<OperationDescriptor> operations) {
        this.operations = operations;
    }

    /**
     * Adds the operation.
     *
     * @param o the o
     */
    private void addOperation(OperationDescriptor o) {
        if (null == this.operations) {
            this.operations = new ArrayList<OperationDescriptor>();
        }
        this.operations.add(o);
    }

    /**
     * Sets the deployment unit.
     *
     * @param deploymentUnit the new deployment unit
     */
    private void setDeploymentUnit(String deploymentUnit) {
        this.deploymentUnit = deploymentUnit;
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
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {

        if (null != type && type.trim().length() > 0) {
            return type;
        } else {
            return "Object";
        }
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    private void setType(String type) {
        this.type = type;
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
         * The pkg.
         */
        private String pkg;
        /**
         * The members.
         */
        private List<JavaClassDescriptor> innerMembers;

        private List<JavaClassDescriptor> members;
        /**
         * The deployment unit.
         */
        private String innerDeplymentUnit;

        /**
         * The type.
         */
        private String innerType;
        /**
         * The operations.
         */
        private List<OperationDescriptor> operations;

        /**
         * With package.
         *
         * @param pack the pack
         * @return the t
         */
        public T withPackage(String pack) {
            this.pkg = pack;
            return self();
        }

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.dev.ClassDescriptor.Builder#build()
         */
        public JavaClassDescriptor build() {

            JavaClassDescriptor cd = (JavaClassDescriptor) super.enrich(new JavaClassDescriptor());
            cd.setDeploymentUnit(innerDeplymentUnit);
            cd.setOperations(operations);
            cd.setType(innerType);
            cd.setMembers(innerMembers);
            cd.setPackageName(pkg);
            return cd;
        }

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.dev.ClassDescriptor.Builder#enrich(java.lang.Object)
         */
        public Object enrich(Object obj) {
            if (null != obj && (obj instanceof JavaClassDescriptor)) {
                JavaClassDescriptor cd = (JavaClassDescriptor) super.enrich(((JavaClassDescriptor) obj));
                cd.setPackageName(pkg);
                cd.setType(innerType);
                cd.setDeploymentUnit(innerDeplymentUnit);
                cd.setOperations(operations);
                cd.setMembers(innerMembers);
                return cd;
            }
            return null;

        }

        /**
         * With attribute.
         *
         * @param od the od
         * @return the t
         */
        public T withMember(JavaClassDescriptor od) {

            if (null == innerMembers) {
                innerMembers = new ArrayList<>();
            }
            if (null != od) {
                innerMembers.add(od);
            }
            return self();
        }

        /**
         * With deployment unit.
         *
         * @param dep the dep
         * @return the t
         */
        public T withDeploymentUnit(String dep) {

            innerDeplymentUnit = dep;
            return self();
        }

        /**
         * With operation.
         *
         * @param od the od
         * @return the t
         */
        public T withOperation(OperationDescriptor od) {

            if (null == operations) {
                operations = new ArrayList<>();
            }
            if (null != od) {
                operations.add(od);
            }
            return self();
        }

        public T withOperations(List<OperationDescriptor> ops) {

            if (null != ops) {

                if (null == operations) {
                    operations = new ArrayList<>();
                }
                for (OperationDescriptor cfg : ops) {
                    if (null != cfg) {
                        operations.add(cfg);

                    }
                }
            }
            return self();
        }

        /**
         * With type.
         *
         * @param dep the dep
         * @return the t
         */
        public T withType(String dep) {

            innerType = dep;
            return self();
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

        /* (non-Javadoc)
         * @see de.te2m.tools.generator.model.dev.ClassDescriptor.Builder#self()
         */
        @Override
        protected Builder2 self() {
            return this;
        }
    }

}
