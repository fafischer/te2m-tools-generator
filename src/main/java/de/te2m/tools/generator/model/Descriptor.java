/*
* Descriptor.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.model;

/**
 * The Interface Descriptor.
 *
 * @author ffi
 */
public interface Descriptor {
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName();
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription();
    
}
