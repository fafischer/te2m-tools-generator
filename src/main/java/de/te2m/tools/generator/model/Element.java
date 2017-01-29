/*
* Element.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.model;

import java.util.Hashtable;

/**
 * The Class Element.
 *
 * @author ffischer
 */
public class Element {
    
    /**
     * The name.
     */
    private String name;
    
    /**
     * The description.
     */
    private String description;
    
    /**
     * The icon.
     */
    private String icon;
    
    /**
     * The id.
     */
    private String id;
    
    /**
     * The attributes.
     */
    private Hashtable<String, String> attributes;

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the icon.
     *
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the icon.
     *
     * @param icon the new icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the attributes.
     *
     * @return the attributes
     */
    public Hashtable<String, String> getAttributes() {
        return attributes;
    }

    /**
     * Sets the attributes.
     *
     * @param attributes the attributes
     */
    public void setAttributes(Hashtable<String, String> attributes) {
        this.attributes = attributes;
    }
    
    /**
     * Adds the attribute.
     *
     * @param key the key
     * @param value the value
     */
    private void addAttribute(String key, String value)
    {
        if(null==this.attributes)
        {
            attributes=new Hashtable<String, String>();            
        }
        
        this.attributes.put(key, value);
    }
    
}
