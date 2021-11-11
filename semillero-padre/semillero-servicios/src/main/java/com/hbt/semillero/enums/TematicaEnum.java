/**
 * TematicaEnum.java
 */
package com.hbt.semillero.enums;

/**
 * <b>Descripción:</b> Clase que determina la enumeración para representar las
 * Temáticas aceptados por un comic
 * @author cataclas
 */
public enum TematicaEnum {
	AVENTURAS("enum.tematica.aventuras"), 
	BELICO("enum.tematica.belico"),
	DEPORTIVO("enum.tematica.deportivo"), 
	FANTASTICO("enum.tematica.fantastico"),  
	CIENCIA_FICCION("enum.tematica.cienciaficcion"),  
	HISTORICO("enum.tematica.historico"),  
	HORROR("enum.tematica.horror");
	
	/**
	 * Atributo que contiene la clave del mensaje
	 */
	private String codigoMensaje;

	/**
	 * Constructor que recibe como parametro el codigo del mensaje
	 * @param codigoMensaje
	 */
	TematicaEnum(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Metodo que retorna el valor del atributo
	 * @return cadena con el codigo del mensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * Metodo que establece el valor del atributo
	 *
	 * @param codigoMensaje
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}
}
