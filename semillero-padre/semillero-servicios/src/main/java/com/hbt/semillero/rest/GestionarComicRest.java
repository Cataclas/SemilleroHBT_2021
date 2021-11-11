package com.hbt.semillero.rest;

import java.util.ArrayList;
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
import com.hbt.semillero.ejb.IGestionarComicLocal;

/**
 * <b>Descripción:</b> Clase que determina las condiciones de comunicación para acceder a los servicios
 * @author cataclas
 */
@Path("/gestionarComic")
public class GestionarComicRest {
	
	@EJB
	private IGestionarComicLocal gestionarComicLocal;
	
	/**
	 * Metodo encargado de crear Comics
	 * @author cataclas
	 * @param comicDTO
	 * @return ComicDTO
	 */
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

	/**
	 * Metodo encargado de Listar todos los comics
	 * @author cataclas
	 * @param search
	 * @return List<ComicDTO>
	 */
	@GET
	@Path("/listarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> listarComics(@QueryParam("search") String search) {
		List<ComicDTO> lista = new ArrayList<>();
		try {
			lista = this.gestionarComicLocal.listarComics(search);
		}  catch (Exception e) {
			System.out.println("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Metodo encargado de consultar un comic por su id
	 * @author cataclas
	 * @param idComic
	 * @return ComicDTO
	 */
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
	 * Metodo encargado de actualizar/modificar un comic
	 * @author cataclas
	 * @param comicDTO
	 * @return ComicDTO
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
	 * Metodo encargado de eliminar un comic
	 * @author cataclas
	 * @param idComic
	 * @return ResultadoDTO
	 */
	@POST
	@Path("/eliminarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO eliminarComic(Long idComic) {
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
	
	/**
	 * 
	 * Metodo encargado de consultar el nombre y precio de un comic
	 * @author cataclas
	 * @param idComic
	 * @return NombrePrecioComicDTO
	 */
	@GET
	@Path("/consultarNombrePrecioComic")
	@Produces(MediaType.APPLICATION_JSON)
	public NombrePrecioComicDTO consultarNombrePrecioComic(@QueryParam("idComic") Long idComic) {
		return this.gestionarComicLocal.consultarNombrePrecioComic(idComic);
	}

	/**
	 * Método para listar nombre de comics, por los que superan el tamaño dado como parámetro y los que no
	 * @author cataclas
	 * @param lengthComic
	 * @return List<Object>
	 */
	@GET
	@Path("/consultarComicTamanioNombre")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> consultarComicTamanioNombre  (@QueryParam("lengthComic") Short lengthComic) {
		return this.gestionarComicLocal.consultarComicTamanioNombre (lengthComic);
	}	
}