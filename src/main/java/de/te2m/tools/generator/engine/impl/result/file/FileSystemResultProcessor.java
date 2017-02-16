/*
 * FileSystemResultProcessor.java
 *   
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools 
 * (http://temtools.sf.net).
 * 
 */
package de.te2m.tools.generator.engine.impl.result.file;
 
import de.te2m.tools.generator.engine.ELUtils;
import de.te2m.tools.generator.engine.GeneratorResultProcessor;
import de.te2m.report.api.model.GeneratorTarget;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class ZipResultProcessor.
 * 
 * @author ffischer
 */
public class FileSystemResultProcessor implements GeneratorResultProcessor {

	/**
	 * The base dir.
	 */
	private String baseDir;

	/**
	 * The base directory.
	 */
	private File baseDirectory;

	/**
	 * Instantiates a new zip result processor.
	 * 
	 * @param base
	 *            the base
	 * @throws FileAlreadyExistsException
	 *             the file already exists exception
	 */
	public FileSystemResultProcessor(String base)
			throws FileAlreadyExistsException {

		baseDir = base;
		baseDirectory = new File(baseDir);
		if (baseDirectory.exists()) {
			if (!baseDirectory.isDirectory()) {
				throw new FileAlreadyExistsException(baseDir);
			}
		}
		baseDirectory.mkdirs();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.te2m.tools.generator.engine.GeneratorResultProcessor#
	 * processGeneratorResult(byte[],
	 * de.te2m.tools.generator.model.GeneratorTarget, java.util.HashMap)
	 */
	public Object processGeneratorResult(byte[] generated,
			GeneratorTarget target, Map<String, Object> params) {
		try {
			String name = target.getName();
			if (null == name || name.trim().length() == 0) {
				name = UUID.randomUUID().toString();

			}
			String fname = (String) ELUtils.evalExpression(params, name);

			System.out.println(name + " --> " + fname);

			Path path = FileSystems.getDefault().getPath(
					baseDir + File.separator + fname);

			if (Files.exists(path)) {
				Files.write(path, generated, StandardOpenOption.TRUNCATE_EXISTING);

			} else {

				Files.copy(new ByteArrayInputStream(generated), path);
			}

		} catch (IOException ex) {
			Logger.getLogger(FileSystemResultProcessor.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.te2m.tools.generator.engine.GeneratorResultProcessor#preProcess()
	 */
	@Override
	public void preProcess() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.te2m.tools.generator.engine.GeneratorResultProcessor#postProcess()
	 */
	@Override
	public void postProcess() {
		// TODO Auto-generated method stub

	}

}
