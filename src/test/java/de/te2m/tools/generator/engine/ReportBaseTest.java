/*
 * ReportBaseTest.java
 *
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.tools.generator.engine;

import de.te2m.report.api.model.dev.java.JavaClassDescriptor;
import de.te2m.report.api.model.dev.service.ServiceDescriptor;
import de.te2m.report.api.model.GeneratorTarget;
import de.te2m.report.api.model.Report;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * The Class ReportBaseTest.
 *
 * @author ffischer
 */
public abstract class ReportBaseTest {

    /**
     * The simple report.
     */
    protected Report simpleReport;

    /**
     * Setup simple report.
     */
    protected void setupSimpleReport() {
        simpleReport = new Report();
        GeneratorTarget t = new GeneratorTarget();
        t.setTarget(JavaClassDescriptor.class.getCanonicalName());
        simpleReport.addTarget(t);
        t = new GeneratorTarget();
        t.setTarget(JavaClassDescriptor.class.getCanonicalName());
        simpleReport.addTarget(t);
        t = new GeneratorTarget();
        t.setTarget(ServiceDescriptor.class.getCanonicalName());
        simpleReport.addTarget(t);
    }

    /**
     * Marshall report.
     *
     * @param r the r
     * @return the string
     */
    protected String marshallReport(Report r) {
        try {
            JAXBContext context = JAXBContext.newInstance(Report.class);
            Marshaller m = context.createMarshaller();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            m.marshal(r, baos);
            String result = baos.toString();
            return result;
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
