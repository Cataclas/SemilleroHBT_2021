package com.hbt.semillero.dto;

import java.math.BigDecimal;

public class NombrePrecioComicDTO extends ResultadoDTO {

		private static final long serialVersionUID = 1L;
		private String nombre;
		private BigDecimal precio;
		
		public NombrePrecioComicDTO() {
			//Constructor vacio
		}
		
		public NombrePrecioComicDTO(String nombre, BigDecimal precio) {
			this.nombre = nombre;
			this.precio = precio;
		}
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public BigDecimal getPrecio() {
			return precio;
		}
		public void setPrecio(BigDecimal precio) {
			this.precio = precio;
		}
	
}
