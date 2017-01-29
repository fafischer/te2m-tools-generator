/*
* ElementCtnr.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.model;

import java.util.HashSet;
import java.util.Set;

/**
 * The Class ElementCtnr.
 *
 * @author ffischer
 */
public class ElementCtnr extends Element{
    
    /**
     * The elements.
     */
    private Set<Element> elements;

    /**
     * Gets the elements.
     *
     * @return the elements
     */
    public Set<Element> getElements() {
        return elements;
    }

    /**
     * Sets the elements.
     *
     * @param elements the new elements
     */
    public void setElements(Set<Element> elements) {
        this.elements = elements;
    }
    
    /**
     * Adds the element.
     *
     * @param e the e
     */
    public void addElement(Element e)
    {
        if(null==elements)elements=new HashSet<Element>();
    
        elements.add(e);
    
    }
    
    
    
}
