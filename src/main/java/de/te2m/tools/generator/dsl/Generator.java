/*
 * Generator.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.dsl;

import de.te2m.tools.generator.engine.GeneratorResultProcessor;
import de.te2m.tools.generator.engine.ReportProcessor;
import de.te2m.tools.generator.engine.SimpleGenerator;
import de.te2m.tools.generator.engine.impl.ChildReportProcessor;
import de.te2m.tools.generator.engine.impl.ClassPathResourcerProcessor;
import de.te2m.tools.generator.engine.impl.RawContentProcessor;
import de.te2m.tools.generator.engine.impl.freemarker.FreeMarkerProcessor;
import de.te2m.tools.generator.model.Report;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class Generator.
 *
 * @author ffischer
 */
public class Generator {

    /**
     * Builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The Class Builder.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    public static class Builder {

        /**
         * The rep.
         */
        private Report rep;

        /**
         * The dict.
         */
        private Map<String, Object> dict;

        /**
         * The generator.
         */
        private SimpleGenerator generator;

        /**
         * The processors.
         */
        private List<ReportProcessor> processors;

        /**
         * The rp.
         */
        private GeneratorResultProcessor rp;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
            dict = new HashMap<>();
            processors = new ArrayList<>();
        }

        /**
         * With template.
         *
         * @param report the report
         * @return the builder
         */
        public Builder withTemplate(Report report) {
            if (null != report) {
                rep = report;
            }

            return this;
        }

        /**
         * With default processors.
         *
         * @return the builder
         */
        public Builder withDefaultProcessors() {
            processors.add(new FreeMarkerProcessor());
            processors.add(new ClassPathResourcerProcessor());
            processors.add(new RawContentProcessor());
            processors.add(new ChildReportProcessor());
            return this;
        }

        /**
         * With processor.
         *
         * @param repPro the rep pro
         * @return the builder
         */
        public Builder withProcessor(ReportProcessor repPro) {
            if (null != repPro) {
                processors.add(repPro);
            }

            return this;
        }

        /**
         * With result processor.
         *
         * @param resultProcessor the result processor
         * @return the builder
         */
        public Builder withResultProcessor(GeneratorResultProcessor resultProcessor) {
            if (null != resultProcessor) {
                rp = resultProcessor;
            }
            return this;
        }

        /**
         * With dictionary value.
         *
         * @param key the key
         * @param value the value
         * @return the builder
         */
        public Builder withDictionaryValue(String key, Object value) {
            if (null != key) {
                if (null != value) {
                    dict.put(key, value);
                } else {
                    if (dict.containsKey(key)) {
                        dict.remove(key);
                    }
                }
            }

            return this;
        }

        /**
         * Builds the.
         */
        public void build() {
            generator = new SimpleGenerator(rep, null, rp);

            generator.registerProcessors(processors);

            generator.generate(dict);
        }
    }
}
