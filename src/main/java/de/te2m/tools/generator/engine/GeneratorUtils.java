/*
 * GeneratorUtils.java
 *   
 * Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-tools-generator project which is a sub project of temtools 
 * (http://temtools.sf.net).
 * 
 */
package de.te2m.tools.generator.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class GeneratorUtils.
 * 
 * @author ffischer
 */
public class GeneratorUtils {

	/**
	 * Read string.
	 * 
	 * @param in
	 *            the in
	 * @return the string
	 */
	public static String readString(InputStream in) {
		InputStreamReader is = new InputStreamReader(in);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(is);
		String read;
		try {
			read = br.readLine();

			while (read != null) {
				// System.out.println(read);
				sb.append(read);
				sb.append("\n");
				read = br.readLine();
			}
			return sb.toString();
		} catch (IOException ex) {
			Logger.getLogger(GeneratorUtils.class.getName()).log(Level.SEVERE,
					null, ex);
			return null;
		}

	}

	/**
	 * Transforms a arbitary String into a Java style class name.
	 * 
	 * @param val
	 *            the val
	 * @return the string
	 */
	public static String toJavaClassName(String val) {
		if (null == val || val.trim().length() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(val,
				" .,!?$&§%\"/\\^°=()[]{}<>|-_'#*+~");
		while (st.hasMoreTokens()) {
			String currentToken = st.nextToken();
			sb.append(currentToken.substring(0, 1).toUpperCase()
					+ currentToken.substring(1));
		}
		return sb.toString().replace("ß", "ss")
				.replace("@", "At")
				.replace("ä", "ae")
				.replace("Ä", "Ae")
				.replace("ö", "oe")
				.replace("Ö", "Oe")
				.replace("ü", "ue")
				.replace("Ü", "Ue");
		// (val.substring(0, 1).toUpperCase() + val.substring(1)).replace(" ",
		// "_");
	}

	/**
	 * Transforms a arbitary String into a Java style constant name.
	 * 
	 * @param val
	 *            the val
	 * @return the string
	 */
	public static String toJavaConstantName(String val) {
		return toJavaClassName(val).toUpperCase();
	}

}
