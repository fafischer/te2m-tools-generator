/*
 * ELUtils.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.engine;

import java.util.Map;
import javax.el.ELProcessor;

/**
 * The Class ELUtils.
 *
 * @author ffischer
 */
public class ELUtils {

    /**
     * Eval expression.
     *
     * @param params the params
     * @param expression the expression
     * @return the object
     */
    public static Object evalExpression(Map<String, Object> params, String expression) {

        if (null == expression || expression.trim().length() == 0) {
            return null;
        }
        ELProcessor elp = new ELProcessor();
        if (null != params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                elp.defineBean(entry.getKey(), entry.getValue());
            }
        }
        Object result = null;
        try {
            result = elp.eval(expression);
        } catch (javax.el.PropertyNotFoundException pnfe) {
            //
        }
        return result;
    }
}
