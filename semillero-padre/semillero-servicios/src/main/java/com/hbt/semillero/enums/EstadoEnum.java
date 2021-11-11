/**
 * EstadoEnum.java
 */
package com.hbt.semillero.enums;

/**
 * <b>Descripción:</b> Clase que determina la enumeración para representar los
 * estados aceptados por un comic
 * @author cataclas
 */
public enum EstadoEnum {
	ACTIVO("enum.estado.activo"),
	INACTIVO("enum.estado.inactivo");

	/**
	 * Atributo que representa el código del estado
	 */
	private String codigoEstado;
	
	/**
	 * Constructor que recibe como parametro el código del estado
	 * @param codigoEstado
	 */
	EstadoEnum(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * Metodo que retorna el valor del atributo codigoEstado
	 * @return cadena con el codigo del estado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * Metodo que establece el valor del atributo codigoEstado
	 * @param codigoEstado
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
}	