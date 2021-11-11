package com.hbt.semillero.dto;

import java.math.BigDecimal;

/**
 * <b>Descripción:</b> Clase que determina el dto a usar para la consulta del nombre y precio del Comic
 * @author cataclas
 */
public class NombrePrecioComicDTO extends ResultadoDTO {

		private static final long serialVersionUID = 1L;
		private String nombre;
		private BigDecimal precio;
		
		/**
		 * Constructor vacio de la clase.
		 */
		public NombrePrecioComicDTO() {
			//Constructor vacio
		}
		
		/**
		 * Constructor de la clase.
		 * @param nombre
		 * @param precio
		 */
		public NombrePrecioComicDTO(String nombre, BigDecimal precio) {
			this.nombre = nombre;
			this.precio = precio;
		}
		
		/**
		 * Metodo encargado de retornar el valor del atributo nombre
		 * 
		 * @return El nombre asociado a la clase
		 */
		public String getNombre() {
			return nombre;
		}
		
		/**
		 * Metodo encargado de modificar el valor del atributo nombre
		 * 
		 * @param nombre
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		/**
		 * Metodo encargado precioe asociado a la clase
		 */
		public BigDecimal getPrecio() {
			return precio;
		}
		
		/**
		 * Metodo encargado de modificar el valor del atributo precio
		 * 
		 * @param precio
		 */
		public void setPrecio(BigDecimal precio) {
			this.precio = precio;
		}
	
}
