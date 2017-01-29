/*
 * BasicDescriptor.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.model;

import java.util.Enumeration;
import java.util.Properties;

/**
 * The Class BasicDescriptor.
 *
 * @author ffi
 */
public class BasicDescriptor implements Descriptor {

    /**
     * The properties.
     */
    private Properties properties;

    /**
     * The description.
     */
    protected String description;

    /**
     * The name.
     */
    protected String name;

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.model.Descriptor#getDescription()
     */
    public String getDescription() {
        return description != null ? description : "";
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.generator.model.Descriptor#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name upper case.
     *
     * @return the name upper case
     */
    public String getNameUpperCase() {
        if (null == name) {
            return null;
        }
        return name.toUpperCase();
    }

    /**
     * Sets the description.
     *
     * @param descrition the new description
     */
    private void setDescription(String descrition) {
        this.description = descrition;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the properties.
     *
     * @return the properties
     */
    public Properties getProperties() {
        if (null == properties) {
            properties = new Properties();
        }
        return properties;
    }

    /**
     * Sets the properties.
     *
     * @param properties the new properties
     */
    private void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Gets the propety.
     *
     * @param key the key
     * @return the propety
     */
    public String getPropety(String key) {
        return getProperties().getProperty(key);
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
     * The Class Builder.
     *
     * @param <T> the generic type
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    public static abstract class Builder<T extends Builder<T>> {

        /**
         * The inner desc.
         */
        private String innerDesc;

        /**
         * The inner name.
         */
        private String innerName;

        /**
         * The props.
         */
        private Properties innerProps;

        /**
         * Builds the.
         *
         * @return the basic descriptor
         */
        public BasicDescriptor build() {
            BasicDescriptor cd = new BasicDescriptor();
            cd.setName(innerName);
            cd.setDescription(innerDesc);
            if(null!=innerProps&&!innerProps.isEmpty())
            {
            	cd.setProperties(innerProps);
            }
            return cd;
        }

        /**
         * Enrich.
         *
         * @param obj the obj
         * @return the object
         */
        public Object enrich(Object obj) {
            if (null != obj && (obj instanceof BasicDescriptor)) {
                BasicDescriptor cd = (BasicDescriptor) obj;
                cd.setName(innerName);
                cd.setDescription(innerDesc);
                if(null!=innerProps&&!innerProps.isEmpty())
                {
                	cd.setProperties(innerProps);
                }
                return cd;
            }
            return null;
        }

        /**
         * With description.
         *
         * @param desc the desc
         * @return the t
         */
        public T withDescription(String desc) {

            innerDesc = desc;
            return self();
        }

        /**
         * With name.
         *
         * @param name the name
         * @return the t
         */
        public T withName(String name) {

            innerName = name;
            return self();
        }

        /**
         * With property.
         *
         * @param key the key
         * @param value the value
         * @return the t
         */
        public T withProperty(String key, String value) {

            if (null == innerProps) {
                innerProps = new Properties();
            }
            innerProps.setProperty(key, value);
            return self();
        }

        /**
         * With property.
         *
         * @param key the key
         * @param value the value
         * @return the t
         */
        public T withProperties(Properties additionalProperties) {

            if (null == innerProps) {
                innerProps = new Properties();
            }

            if (null != additionalProperties) {
                
            	Enumeration<Object> keys=additionalProperties.keys();
            	
            	while(keys.hasMoreElements()){

            		Object key = keys.nextElement();
                    innerProps.setProperty((String)key, additionalProperties.getProperty((String)key));
                }
            }

            return self();
        }

        /**
         * Self.
         *
         * @return the t
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
         * @see de.te2m.tools.generator.model.BasicDescriptor.Builder#self()
         */
        @Override
        protected Builder2 self() {
            return this;
        }
    }

}
