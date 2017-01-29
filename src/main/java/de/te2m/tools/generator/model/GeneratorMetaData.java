/*
 * GeneratorMetaData.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.model;

import java.util.Date;

/**
 * The Class GeneratorMetaData.
 *
 * @author ffischer
 */
public class GeneratorMetaData {

    /**
     * The copyright info.
     */
    private String copyrightInfo;

    /**
     * The author.
     */
    private String author;

    /**
     * The version.
     */
    private String version;

    private String deploymentUnit;

    private String basePackage;

    /**
     * Gets the author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     *
     * @param author the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the copyright info.
     *
     * @return the copyright info
     */
    public String getCopyrightInfo() {
        return copyrightInfo;
    }

    /**
     * Sets the copyright info.
     *
     * @param copyrightInfo the new copyright info
     */
    public void setCopyrightInfo(String copyrightInfo) {
        this.copyrightInfo = copyrightInfo;
    }

    /**
     * Sets the version.
     *
     * @param v the new version
     */
    public void setVersion(String v) {
        this.version = v;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Gets the time stamp.
     *
     * @return the time stamp
     */
    private String getTimeStamp() {
        return new Date().toString();
    }

    public String getDeploymentUnit() {
        return deploymentUnit;
    }

    public void setDeploymentUnit(String deploymentUnit) {
        this.deploymentUnit = deploymentUnit;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

}
