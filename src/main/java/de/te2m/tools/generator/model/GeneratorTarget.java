/*
 * GeneratorTarget.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Container class for storing the configuration for an single generation
 * target.
 *
 * @author ffi
 */
public class GeneratorTarget {

    /**
     * The target.
     */
    private String target;

    /**
     * The name.
     */
    private String name;

    /**
     * The description.
     */
    private String description;

    /**
     * The rel path.
     */
    private String relPath;

    /**
     * The template.
     */
    private String template;

    /**
     * The target type.
     */
    private FileType targetType;

    /**
     * The unit name.
     */
    private String unitName;

    /**
     * The for each.
     */
    private String forEach;

    /**
     * The condition.
     */
    private String condition;

    /**
     * The var.
     */
    private String var;

    /**
     * The template type.
     */
    private String templateType;

    /**
     * The config.
     */
    @XmlElementWrapper(name = "configuration")
    @XmlElement(name = "config")
    private List<Configuration> config;

    /**
     * Gets the config.
     *
     * @return the config
     */
    public List<Configuration> getConfig() {
        return config;
    }

    /**
     * Sets the config.
     *
     * @param config the new config
     */
    protected void setConfig(List<Configuration> config) {
        this.config = config;
    }

    /**
     * Returns the unit name of the report. The unit name is used for organizing
     * the generated content on a high level (eg. the target source code
     * project)
     *
     * @return the unit name
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Set the unit name The unit name is used for organizing the generated
     * content on a high level (eg. the target source code project)
     *
     * @param unitName the new unit name
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * Gets the target.
     *
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the generator target.
     *
     * @param target the new target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * Returns the name of the target The name is used for documentation only.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the target The name is used for documentation only.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the target The description is used for
     * documentation only.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the target The description is used for
     * documentation only.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the relative path of the generated object.
     *
     * @return the rel path
     */
    public String getRelPath() {
        return relPath;
    }

    /**
     * Set the relative path of the generated object.
     *
     * @param relPath the new rel path
     */
    public void setRelPath(String relPath) {
        this.relPath = relPath;
    }

    /**
     * Returns the template content.
     *
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * Set the template content.
     *
     * @param template the new template
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * Return the targetType of the generated object.
     *
     * @return the target type
     */
    public FileType getTargetType() {
        return targetType;
    }

    /**
     * Set the targetType of the generated object.
     *
     * @param targetType the new target type
     */
    public void setTargetType(FileType targetType) {
        this.targetType = targetType;
    }

    /**
     * Returns en expression for determining possible sub targets.
     *
     * @return expression
     */
    public String getForEach() {
        return forEach;
    }

    /**
     * Sets an expression for determining possible sub targets.
     *
     * @param forEach the new for each
     */
    public void setForEach(String forEach) {
        this.forEach = forEach;
    }

    /**
     * Returns an expression for additional validation of the sub targets.
     *
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets an expression for additional validation of the sub targets.
     *
     * @param condition the new condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Returns an customized name for the loop var for procession the sub
     * targets.
     *
     * @return the var
     */
    public String getVar() {
        return var;
    }

    /**
     * Sets an customized name for the loop var for procession the sub targets.
     *
     * @param var the new var
     */
    public void setVar(String var) {
        this.var = var;
    }

    /**
     * Sets the template type.
     *
     * @param templateType the new template type
     */
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    /**
     * Gets the template type.
     *
     * @return the template type
     */
    public String getTemplateType() {
        return templateType;
    }

    /**
     * Builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The Class Builder.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    public static class Builder {

        /**
         * The condition.
         */
        private String innerCondition;

        /**
         * The inner config.
         */
        private List<Configuration> innerConfig;
        /**
         * The description.
         */
        private String innerDescription;
        /**
         * The for each.
         */
        private String innerForEach;
        /**
         * The name.
         */
        private String innerName;

        /**
         * The rel path.
         */
        private String innerRelPath;

        /**
         * The inner target.
         */
        private String innerTarget;

        /**
         * The target type.
         */
        private FileType innerTargetType;

        /**
         * The template.
         */
        private String innerTemplate;

        /**
         * The template type.
         */
        private String innerTemplateType;

        /**
         * The unit name.
         */
        private String innerUnitName;

        /**
         * The var.
         */
        private String innerVar;

        /**
         * Builds the.
         *
         * @return the report
         */
        public GeneratorTarget build() {
            GeneratorTarget newTarget = new GeneratorTarget();
            newTarget.setName(innerName);
            newTarget.setDescription(innerDescription);
            newTarget.setCondition(innerCondition);
            newTarget.setForEach(innerForEach);
            newTarget.setRelPath(innerRelPath);
            newTarget.setTarget(innerTarget);
            newTarget.setTargetType(innerTargetType);
            newTarget.setTemplate(innerTemplate);
            newTarget.setTemplateType(innerTemplateType);
            newTarget.setUnitName(innerUnitName);
            newTarget.setVar(innerVar);
            if (null != innerConfig && !innerConfig.isEmpty()) {
                newTarget.setConfig(innerConfig);
            }
            return newTarget;
        }

        /**
         * With condition.
         *
         * @param newCondition the new condition
         * @return the builder
         * @see GeneratorTarget#condition
         */
        public Builder withCondition(String newCondition) {
            innerCondition = newCondition;
            return this;
        }

        /**
         * With config.
         *
         * @param key the key
         * @param value the value
         * @return the builder
         */
        public Builder withConfig(String key, String value) {
            if (null != key) {
                if (null == innerConfig) {
                    innerConfig = new ArrayList<>();
                }

                Configuration cfg = new Configuration();
                cfg.setKey(key);
                cfg.setValue(value);
                innerConfig.add(cfg);
            }

            return this;
        }

        /**
         * With description.
         *
         * @param newDesc the new desc
         * @return the builder
         * @see GeneratorTarget#description
         */
        public Builder withDescription(String newDesc) {
            innerDescription = newDesc;
            return this;
        }

        /**
         * With for each.
         *
         * @param newForEach the new for each
         * @return the builder
         * @see GeneratorTarget#forEach
         */
        public Builder withForEach(String newForEach) {
            innerForEach = newForEach;
            return this;
        }

        /**
         * With loop var.
         *
         * @param newVar the new var
         * @return the builder
         * @see GeneratorTarget#var
         */
        public Builder withLoopVar(String newVar) {
            innerVar = newVar;
            return this;
        }

        /**
         * With name.
         *
         * @param newName the new name
         * @return the builder
         * @see GeneratorTarget#name
         */
        public Builder withName(String newName) {
            innerName = newName;
            return this;
        }

        /**
         * With rel path.
         *
         * @param newRelPath the new rel path
         * @return the builder
         * @see GeneratorTarget#relPath
         */
        public Builder withRelPath(String newRelPath) {
            innerRelPath = newRelPath;
            return this;
        }

        /**
         * With target.
         *
         * @param newTarget the new target
         * @return the builder
         * @see GeneratorTarget#target
         */
        public Builder withTarget(String newTarget) {
            innerTarget = newTarget;
            return this;
        }

        /**
         * With target type.
         *
         * @param newTargetType the new target type
         * @return the builder
         * @see GeneratorTarget#targetType
         */
        public Builder withTargetType(FileType newTargetType) {
            innerTargetType = newTargetType;
            return this;
        }

        /**
         * With template.
         *
         * @param newTemplate the new template
         * @return the builder
         * @see GeneratorTarget#template
         */
        public Builder withTemplate(String newTemplate) {
            innerTemplate = newTemplate;
            return this;
        }

        /**
         * With template type.
         *
         * @param newTemplateType the new template type
         * @return the builder
         */
        public Builder withTemplateType(String newTemplateType) {
            innerTemplateType = newTemplateType;
            return this;
        }

        /**
         * With unit name.
         *
         * @param newUnitName the new unit name
         * @return the builder
         * @see GeneratorTarget#unitName
         */
        public Builder withUnitName(String newUnitName) {
            innerUnitName = newUnitName;
            return this;
        }

    }
}
