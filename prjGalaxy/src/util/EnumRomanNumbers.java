/* 
 * EnumRomanNumbers.java 
 * 15/12/2017
 * Copyright 2017 TCS.
 * Todos los derechos reservados.
 */
package util;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * @author Edgar Chimarro
 *
 * @version $1.0$
 */
public enum EnumRomanNumbers {
	
	I(1), 
	V(5), 
	X(10), 
	L(50), 
	C(100), 
	D(500), 
	M(1000);
	
	private int keyValue;

	/**
	 * Default constructor for Enum Int Type
	 * @param keyValue
	 */
	private EnumRomanNumbers(int keyValue) {
		this.keyValue = keyValue;
	}

	/**
	 * 
	 * <b> Incluir aqui la descripcion del metodo. </b>
	 * <p>
	 * [Author Usuario, 15/12/2017]
	 * </p>
	 *
	 * @param keyValue
	 * @return
	 */
	public static int getIntValue(String keyValue) {
		if (keyValue.length() == 0 || keyValue == null) {
			throw new IllegalArgumentException();
		}
		for (EnumRomanNumbers numberType : EnumRomanNumbers.values()) {
			if (numberType.toString().equals(keyValue)) {
				return Integer.valueOf(numberType.keyValue);
			}
		}
		throw new IllegalArgumentException();
	}
	
	

}
