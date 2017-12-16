/* 
 * InputOutput.java 
 * 15/12/2017
 * Copyright 2017 TCS.
 * Todos los derechos reservados.
 */
package prjGalaxy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * This is the hash map that will contain the value for each identifier
 */








import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import util.Constant;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author Edgar Chimarro
 * @version $1.0$
 */
public class InputOutput {

	/**
	 * <b> Read the properties file. </b>
	 * <p>
	 * [Author Usuario, 14/12/2017]
	 * </p>
	 *
	 * @param ARCHIVE_INPUT
	 */
	public void readFile() {

		File archive = this.validateFile(Constant.ARCHIVE_INPUT);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		// List<PlantillasNotificacion> listaPlantillasNotificacion = null;
		HashMap<String, String> plantillaNotificacionMap = null;

		try {
			plantillaNotificacionMap = new HashMap<String, String>();
			fileReader = new FileReader(archive);
			bufferedReader = new BufferedReader(fileReader);

			String line;
			// listaPlantillasNotificacion = new ArrayList<PlantillasNotificacion>();
			while ((line = bufferedReader.readLine()) != null) {
				// PlantillasNotificacion plantillasNotificacion = loadLine(line, Constantes.SEPARADOR_URL);
				// listaPlantillasNotificacion.add(plantillasNotificacion);
				// plantillaNotificacionMap.put(plantillasNotificacion.getNombreDescriptivoPlantilla(),
				// plantillasNotificacion);

				String[] lineSplited = line.split("\\s");				
				if (lineSplited.length < 4 && line.contains("is")) {
					validateValues(line, Constant.DELIMITER_PRIMARY_VALUES);
				} else if (lineSplited.length > 4 && line.startsWith(Constant.QUESTION_HOW)) {
					String resultsQuestionHow = processHowMuchQuestion(line, Constant.QUESTION_HOW);
					System.out.print(resultsQuestionHow + "\n");
				} else if (lineSplited.length > 4 && line.trim().toLowerCase(new Locale("English")).endsWith(Constant.DELIMITER_PRIMARY_CREDITS)) {
					validateCredits(line, " is ", Constant.DELIMITER_PRIMARY_CREDITS);					
				} else if (lineSplited.length > 4 && line.startsWith(Constant.QUESTION_HOW_MANY)){
					String resultsQuestionHowMany = processHowManyQuestion(line, Constant.QUESTION_HOW_MANY);
					System.out.print(resultsQuestionHowMany + "\n");					
				}
				
				
			

			}

		} catch (Exception errorFinal) {
			System.out.println("Error reading file  " + Constant.ARCHIVE_INPUT + "  " + errorFinal);
		}

		finally {
			if (null != fileReader) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != bufferedReader) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public void validateCredits(String line, String delimiterIs, String delimiterPrimaryCredits) {
//		glob glob Silver is 34 Credits
		String arabigNumber = null;
		String[] removedIsTest = line.split(delimiterIs);
						
		String valueCredits = removedIsTest[1].trim().toLowerCase(new Locale("English")).split(delimiterPrimaryCredits)[0].trim();
		String galacticName = removedIsTest[0].split(" ")[removedIsTest.length].trim();
		
		RomanValidations romanValidation = new RomanValidations();
		String fullRomanNumber = getRomanNumberfromString(removedIsTest[0].replaceAll(galacticName, "").trim().split(" "));
		if (romanValidation.isValidRomanNumber(fullRomanNumber)) {
			arabigNumber = romanValidation.convertToArabig(fullRomanNumber);
		}
		
		double realCreditValue = Integer.valueOf(valueCredits) / Integer.valueOf(arabigNumber);
		
		Constant.PRIMARY_CREDITS.put(galacticName, realCreditValue);
		
		System.out.print(Constant.PRIMARY_CREDITS);
	}

	public String processHowMuchQuestion(String line, String questionHowMuch) {
		String[] textDelimited = line.split(" is ");
		String listValues = textDelimited[1].replace("?", "").trim();
		String[] onlyValues = listValues.split(" ");
		String fullRomanNumber = "";
		String arabigNumber = null;
		
		
		fullRomanNumber = getRomanNumberfromString(onlyValues);

		RomanValidations romanValidation = new RomanValidations();
		if (romanValidation.isValidRomanNumber(fullRomanNumber)) {
			arabigNumber = romanValidation.convertToArabig(fullRomanNumber);
		}

		return listValues.concat(" is " + arabigNumber);

	}
	
	public String processHowManyQuestion(String line, String questionHowMany) {
		String[] textDelimited = line.split(" is ");
		String listValues = textDelimited[1].replace("?", "").trim();					
		String[] onlyValues = listValues.split(" ");		
		String galacticName = onlyValues[onlyValues.length-1].trim();
		String fullRomanNumber = "";
		String arabigNumber = null;	
		
		fullRomanNumber = getRomanNumberfromString(listValues.replaceAll(galacticName, "").trim().split(" "));

		RomanValidations romanValidation = new RomanValidations();
		if (romanValidation.isValidRomanNumber(fullRomanNumber)) {
			arabigNumber = romanValidation.convertToArabig(fullRomanNumber);
		}
		
		Double totalCredits = Integer.valueOf(arabigNumber) * Constant.PRIMARY_CREDITS.get(galacticName);
		
		return listValues.concat(" is " + totalCredits + Constant.DELIMITER_PRIMARY_CREDITS);

	}

	private String getRomanNumberfromString(String[] onlyValues) {
		String fullRomanNumber = "";
		for (int i = 0; i < onlyValues.length; i++) {
			if (!Constant.PRIMARY_VALUES.get(onlyValues[i]).isEmpty()) {
				fullRomanNumber += Constant.PRIMARY_VALUES.get(onlyValues[i]);
			} else {				
				fullRomanNumber = "Invalid Roman Number " + onlyValues[i] ;
			}
		}
		return fullRomanNumber;
	}

	private void validateValues(String line, String delimiter) {

		String[] textDelimited = line.split(delimiter);
		Constant.PRIMARY_VALUES.put(textDelimited[0].trim(), textDelimited[2].trim());

	}

	/**
	 * <b> Validate extension file. </b>
	 * <p>
	 * [Author Usuario, 14/12/2017]
	 * </p>
	 *
	 * @param ARCHIVE_INPUT
	 * @return
	 */
	private File validateFile(String ARCHIVE_INPUT) {
		try {
			if (!ARCHIVE_INPUT.endsWith(".txt")) {
				throw new IllegalArgumentException("Invalid extension file :".concat(ARCHIVE_INPUT));
			}
			File archive = new File(ARCHIVE_INPUT);
			System.out.println("Attempting to read from file in: " + archive.getCanonicalPath());

			return archive;
		} catch (Exception error) {
			System.out.println("Error processing file " + error);
		}
		return null;
	}

}
