/*
* Report.java
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
import javax.xml.bind.annotation.XmlRootElement;


/**
 * A report consists of multiple generator targets.
 * <p>
 * The main purpose of this class is providing a way for handling a XML
 * configuration for code generators. This configuration includes:
 * <ul>
 * <li>The target template engine (by default Freemarker @link http://freemarker.sourceforge.net/ )</li>
 * <li>The configured set of templates including their target</li>
 * <li>Name and description of the report for documentation purposes</li>
 * </ul>
 * The calling code generator is responsible for passing the right parameters to
 * the templates.
 * </p>
 * <p>
 * At the current moment there is no validation planned. Later versions may
 * introduce a set of meta data for validating the passed parameters.
 * </p> @author ffi
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
@XmlRootElement
public class Report {

    /**
     * The name.
     */
    private String name;
    
    /**
     * The description.
     */
    private String description;
    
    /**
     * The targets.
     */
    private List<GeneratorTarget> targets;


    
    /**
     * The base path.
     */
    private String basePath;
    
    /**
     * The copyright info.
     */
    private String copyrightInfo;

    /**
     * Gets the base path.
     *
     * @return the base path
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Sets the base path.
     * Important: Method is public for compatibility reasons only and will become private soon.
     * @param basePath the new base path
     */
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    /**
     * Returns the copyright info for the report.
     *
     * @return the copyright info
     */
    public String getCopyrightInfo() {
        return copyrightInfo;
    }

    /**
     * Sets the copyright info for the report (not for the generated content).
     * Important: Method is public for compatibility reasons only and will become private soon.
     *
     * @param copyrightInfo the new copyright info
     */
    public void setCopyrightInfo(String copyrightInfo) {
        this.copyrightInfo = copyrightInfo;
    }

    /**
     * Returns a list of generator targets (templates including some meta data)
     * If no targets are defined then always an empty list will be returned.
     *
     * @return the defined generator targets
     */
    public List<GeneratorTarget> getTargets() {
        if (null == targets) {
            targets = new ArrayList<GeneratorTarget>();
        }
        return targets;
    }

    /**
     * Set a list of generator targets.
     * Important: Method is public for compatibility reasons only and will become private soon.
     *
     * @param targets the new targets
     */
    public void setTargets(List<GeneratorTarget> targets) {
        this.targets = targets;
    }

    /**
     * Add a new generator target.
     *
     * @param target the target
     * @deprecated
     */
    public void addTarget(GeneratorTarget target) {
        if (null == targets) {
            targets = new ArrayList<GeneratorTarget>();
        }
        targets.add(target);
    }

    /**
     * returns the name of the report.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the report.
     * Important: Method is public for compatibility reasons only and will become private soon.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the report.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the report.
     * Important: Method is public for compatibility reasons only and will become private soon.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Builder.
     *
     * @return the builder
     */
    public static Builder builder()
    {
    	return new Builder();
    }
	
	/**
     * The Class Builder.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    public static class Builder{
 
        /**
         * The name.
         */
        private String innerName;
        
        /**
         * The description.
         */
        private String innerDescription;
        
        /**
         * The targets.
         */
        private List<GeneratorTarget> innerTargets = new ArrayList<>();


        
        /**
         * The base path.
         */
        private String innerBasePath;
        
        /**
         * The copyright info.
         */
        private String innerCopyrightInfo;
        
        /**
         * With name.
         *
         * @param newName the new name
         * @return the builder
         */
        public Builder withName(String newName)
        {
        	innerName=newName;
        	return this;
        }
        
        /**
         * With description.
         *
         * @param newDesc the new desc
         * @return the builder
         */
        public Builder withDescription(String newDesc)
        {
        	innerDescription=newDesc;
        	return this;
        }
        
        /**
         * With base path.
         *
         * @param newPath the new path
         * @return the builder
         */
        public Builder withBasePath(String newPath)
        {
        	innerBasePath=newPath;
        	return this;
        }
        
        /**
         * With copyright info.
         *
         * @param newCI the new ci
         * @return the builder
         */
        public Builder withCopyrightInfo(String newCI)
        {
        	innerCopyrightInfo=newCI;
        	return this;
        }
        
        /**
         * With target.
         *
         * @param newTarget the new target
         * @return the builder
         */
        public Builder withTarget(GeneratorTarget newTarget)
        {
        	innerTargets.add(newTarget);
        	return this;
        }
        
        /**
         * Builds the.
         *
         * @return the report
         */
        public Report build()
        {
        	Report newReport = new Report();
        	newReport.setName(innerName);
        	newReport.setDescription(innerDescription);
        	newReport.setCopyrightInfo(innerCopyrightInfo);
        	newReport.setBasePath(innerBasePath);
        	newReport.setTargets(innerTargets);
        	return newReport;
        }

    }
    
}
