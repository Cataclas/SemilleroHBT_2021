package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.NombrePrecioComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.TamanioNombreComicDTO;
import com.hbt.semillero.ejb.IGestionarComicLocal;



//cd semillero-servicios & mvn clean install & cd.. & cd semillero-ear & mvn clean install & cd..

@Path("/gestionarComic")
public class GestionarComicRest {
	
	@EJB
	private IGestionarComicLocal gestionarComicLocal;
	
	@POST
	@Path("/crearComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO crearComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarComicLocal.crearComic(comicDTO);
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return comicDTOResult;
	}

	@GET
	@Path("/listarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> listarComics(){
		return gestionarComicLocal.listarComics();
	}

	@GET
	@Path("/consultarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ComicDTO consultarComic(@QueryParam("idComic") Long idComic) {
		ComicDTO comicDTOResult = new ComicDTO();
		if(idComic!=null) {
			comicDTOResult = this.gestionarComicLocal.consultarComic(idComic);
		}
		return comicDTOResult;
	}
	
	/**
	 * 
	 * Metodo encargado de modificar el nombre de un usuario
	 * @param idComic identificador del comic a buscar
	 * @param nombre nombre nuevo del comic
	 */
	@PATCH
	@Path("/actualizarComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO actualizarComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResult = new ComicDTO();
		if (comicDTO.getId() != null) {    /* ¿o se captura en el Front? */
			try {
				comicDTOResult = this.gestionarComicLocal.actualizarComic(comicDTO);		
			}  catch (Exception e) {
				comicDTOResult.setExitoso(false);
				comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
			}
		} else {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("El campo id es requerido para modificar el comic");
		}
		return comicDTOResult;
	}

	/**
	 * 
	 * Metodo encargado de eliminar un comic dado el id
	 * 
	 * @param idComic identificador del comic
	 */
	@DELETE
	@Path("/eliminarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO eliminarComic(@QueryParam("idComic") Long idComic) {
		ResultadoDTO resultadoDTO = new ResultadoDTO ();
		if(idComic!=null) {
			try {	
				resultadoDTO = this.gestionarComicLocal.eliminarComic(idComic);
			}catch (Exception e) {
				resultadoDTO.setExitoso(false);
				resultadoDTO.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
			}
		} else {
			resultadoDTO.setExitoso(false);
			resultadoDTO.setMensajeEjecucion("El campo id es requerido para modificar el comic");
		}
		return resultadoDTO;
	}
	
	@GET
	@Path("/consultarNombrePrecioComic")
	@Produces(MediaType.APPLICATION_JSON)
	public NombrePrecioComicDTO consultarNombrePrecioComic(@QueryParam("idComic") Long idComic) {
		return this.gestionarComicLocal.consultarNombrePrecioComic(idComic);
	}

	@GET
	@Path("/consultarComicTamanioNombre")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> consultarComicTamanioNombre  (@QueryParam("lengthComic") Short lengthComic) {
		return this.gestionarComicLocal.consultarComicTamanioNombre (lengthComic);
	}	
}