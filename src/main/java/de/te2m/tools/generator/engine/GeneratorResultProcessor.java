/*
* GeneratorResultProcessor.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the te2m-tools-generator project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.generator.engine;

import java.util.Map;

import de.te2m.report.api.model.GeneratorTarget;

/**
 * Interface for providing post processing for created reports. Possible tasks
 * (provided by an possible implementation of this interface):
 * <ul>
 * <li>return the result as it is (eg. for presenting the result in a web
 * page)</li>
 * <li>store the result in the file system</li>
 * <li>store the result in a zip archive</li>
 * </ul>
 *
 * @author ffischer
 */
public interface GeneratorResultProcessor {

    /**
     * Preprocessing hook for the generated result. Will be called before
     * processing the first target. Will be used for initializing.
     */
    public void preProcess();

    /**
     * Postprocessing hook for the generated result Will be called after
     * processing the last target. Will be used for cleanup.
     */
    public void postProcess();

    /**
     * Provides post processing for the provided generator result and target.
     *
     * @param generated the generated
     * @param target the target
     * @param params the params
     * @return the object
     */
    public Object processGeneratorResult(byte[] generated, GeneratorTarget target, Map<String, Object> params);
}
