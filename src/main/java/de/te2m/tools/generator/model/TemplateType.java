/*
* TemplateType.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.model;

/**
 * The Enum TemplateType.
 *
 * @author ffi
 */
public enum TemplateType {

    /**
     * The freemarker.
     */
    FREEMARKER,
    
    /**
     * The download.
     */
    DOWNLOAD,
    
    /**
     * The filereader.
     */
    FILEREADER,
    
    /**
     * The classpathreader.
     */
    CLASSPATHREADER,
    
    /**
     * The raw content.
     */
    RAW_CONTENT,
    
    /**
     * The image renderer.
     */
    IMAGE_RENDERER,

    /**
     * Triggers the rendering of a child report.
     */
    CHILD_REPORT,
    
    /**
     * The xml import.
     */
    XML_IMPORT;

    /**
     * Instantiates a new template type.
     */
    private TemplateType() {
    }
}
