/*
* FileType.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.model;

/**
 * The Enum FileType.
 *
 * @author ffi
 */
public enum FileType {
    
    /**
     * The html.
     */
    HTML(".html"),
    
    /**
     * The xhtml.
     */
    XHTML(".xhtml"),
    
    /**
     * The xml.
     */
    XML(".xhtml"),
    
    /**
     * The java.
     */
    JAVA(".java"),
    
    /**
     * The custom.
     */
    CUSTOM(null);
    
    /**
     * The suffix.
     */
    private String suffix;
    
    /**
     * Instantiates a new file type.
     *
     * @param suffix the suffix
     */
    private FileType(String suffix)
    {
        this.suffix=suffix;
    }
    
    /**
     * Gets the suffix.
     *
     * @return the suffix
     */
    public String getSuffix()
    {
        return suffix;
    }
}
