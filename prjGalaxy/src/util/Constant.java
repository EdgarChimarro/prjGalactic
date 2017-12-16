/* 
 * Constant.java 
 * 15/12/2017
 * Copyright 2017 TCS.
 * Todos los derechos reservados.
 */
package util;

import java.util.HashMap;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * @author Usuario
 *
 * @version $1.0$
 */
public class Constant {
	
	/**
	 * Es la ruta del archivo de log4j.properties de pagos pichinha
	 */
	public static final String ARCHIVE_INPUT = "src/resources/InputFile.txt";
	
	public static final String DELIMITER_PRIMARY_VALUES="\\s+";
	public static final String DELIMITER_PRIMARY_CREDITS="credits";
	
	public static HashMap<String, String> PRIMARY_VALUES = new HashMap<String, String>();
	public static HashMap<String, Double> PRIMARY_CREDITS = new HashMap<String, Double>();
	
	public static final String QUESTION_HOW = "how much is";
	public static final String QUESTION_HOW_MANY = "how many";
	public static final String QUESTION_CREDITS = "how many credits is";
	
	public static final String INVALID_MESSAGE = "";
	    
//	    public static final String IS = " is ";
//	    public static final String CREDITS = " credits";

}
