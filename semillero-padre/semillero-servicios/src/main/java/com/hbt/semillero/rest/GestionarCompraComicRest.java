/**
 * GestionarCompraComicRest.java
 */
package com.hbt.semillero.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.ejb.IGestionarCompraComicLocal;

/**
 * <b>Descripción:</b> Clase que determina
 * @author cataclas
 */
@Path("/gestionarCompraComic")
public class GestionarCompraComicRest {

	@EJB
	private IGestionarCompraComicLocal gestionarCompraComicLocal;

	@PATCH
	@Path("/venderComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO comprarComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarCompraComicLocal.comprarComic(comicDTO);
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion(e.getMessage());
		}
		return comicDTOResult;	
	}
}
