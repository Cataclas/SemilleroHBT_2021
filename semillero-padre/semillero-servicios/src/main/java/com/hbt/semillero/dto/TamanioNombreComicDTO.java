package com.hbt.semillero.dto;

public class TamanioNombreComicDTO {
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	
	public TamanioNombreComicDTO() {
		//Constructor vacio
	}
	
	public TamanioNombreComicDTO(String nombre) {
		this.setNombre(nombre);
	}

	/**
	 * metodo para obtener el nombre
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * metodo para establecer el nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
