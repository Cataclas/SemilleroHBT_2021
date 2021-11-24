/**
 * GestionarVentaComicRest.java
 */
package com.hbt.semillero.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.ejb.IGestionarVentaComicLocal;

/**
 * <b>Descripción:</b> Clase que determina las condiciones de comunicación para acceder al servicio de venta de comics
 * @author cataclas
 */
@Path("/gestionarVentaComic")
public class GestionarVentaComicRest {

	@EJB
	private IGestionarVentaComicLocal gestionarVentaComicLocal;

	/**
	 * Metodo encargado de la venta de comics
	 * @author cataclas
	 * @param comicDTO
	 * @return comicDTO
	 */
	@PATCH
	@Path("/venderComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO venderComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarVentaComicLocal.venderComic(comicDTO);
			comicDTOResult.setExitoso(true);
			comicDTOResult.setMensajeEjecucion("La venta del comic "+ comicDTOResult.getNombre() + " fue exitosa");
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion(e.getMessage());
		}
		return comicDTOResult;	
	}
}
