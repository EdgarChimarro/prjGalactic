/* 
 * RomanValidations.java 
 * 15/12/2017
 * Copyright 2017 TCS.
 * Todos los derechos reservados.
 */
package prjGalaxy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.EnumRomanNumbers;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * @author Usuario
 *
 * @version $1.0$
 */
public class RomanValidations {

	public boolean isValidRomanNumber(String fullRomanNumber) {
		boolean isRomanNumber= false;
		
		Pattern romanExpression = Pattern.compile("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
        if( fullRomanNumber == null || fullRomanNumber.length() == 0 ){
            isRomanNumber = false;
        }
        Matcher m_o = romanExpression.matcher( fullRomanNumber );
        if( m_o.matches() ){
            return true;
        }		
		return isRomanNumber;
	}
	
	public String convertToArabig(String fullRomanNumber)
	{
		int arabigNumber = 0;
        int lastArabig = 0;
        String[] individualNumbers = fullRomanNumber.split("");
        
		for(int i = 1; i < individualNumbers.length; i++){
			String letter = individualNumbers[i];
			arabigNumber = orderRomanNumber(EnumRomanNumbers.getIntValue(letter), lastArabig , arabigNumber);			
			lastArabig = EnumRomanNumbers.getIntValue(letter);
		}
		
		System.out.print("Numero: " + arabigNumber);
		
		return arabigNumber+"";
		
	}
	
	
private static int orderRomanNumber(int currentNumber, int LastRomanLetter, int lastArabig){
//		XLII
	
	int finalNumber = 0;
	
		if (currentNumber <= LastRomanLetter){			
			finalNumber = currentNumber + lastArabig;  
		} else if (currentNumber > LastRomanLetter){
			finalNumber = currentNumber - lastArabig;
		}
		return finalNumber;
		
		
        
    }

}
