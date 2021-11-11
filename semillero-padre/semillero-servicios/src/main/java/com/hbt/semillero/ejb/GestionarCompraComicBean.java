/**
 * GestionarCompraComicBean.java
 */
package com.hbt.semillero.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.enums.EstadoEnum;

/**
 * <b>Descripción:</b> Clase que determina
 * @author cataclas
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarCompraComicBean implements IGestionarCompraComicLocal {

	@PersistenceContext
	public EntityManager em;
	
	/** 
	 * @see com.hbt.semillero.ejb.IGestionarCompraComicLocal#comprarComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO comprarComic(ComicDTO comicDTO)  throws Exception {
		ComicDTO comicDTOResult = new ComicDTO();
		Comic comic = em.find(Comic.class, comicDTO.getId());
		
		if(comicDTO.getEstadoEnum().equals(EstadoEnum.INACTIVO)) {
			throw new Exception("El comic seleccionado no cuenta con stock en bodega");
		}
		
		if(comicDTO.getCantidad() < comic.getCantidad()) {
			Long stock = comic.getCantidad() - comicDTO.getCantidad();
			comicDTO.setCantidad(stock);
			Comic comicVenta = this.convertirComicDTOToComic(comicDTO);		
			em.merge(comicVenta);
			
			comicDTOResult = this.convertirComicToComicDTO(comicVenta);
			comicDTOResult.setExitoso(true);
			comicDTOResult.setMensajeEjecucion("La compra del comic "+ comicVenta.getNombre() + " fue exitosa");
		} else if(comicDTO.getCantidad() == comic.getCantidad()) {
			Long stock = comic.getCantidad() - comicDTO.getCantidad();
			comicDTO.setCantidad(stock);
			comicDTO.setEstadoEnum(EstadoEnum.INACTIVO);
			Comic comicVenta = this.convertirComicDTOToComic(comicDTO);		
			em.merge(comicVenta);
			
			comicDTOResult = this.convertirComicToComicDTO(comicVenta);
			comicDTOResult.setExitoso(true);
			comicDTOResult.setMensajeEjecucion("La compra del comic "+ comicVenta.getNombre() + " fue exitosa");
		} else if(comicDTO.getCantidad() > comic.getCantidad()) {
			throw new Exception("La cantidad solicitada supera la existencia de comics: " + comic.getCantidad());
		}
		
		return comicDTOResult;
	}
	

	/**
	 * Metodo encargado de convertir un objeto Comic a ComicDTO
	 * @param comic
	 * @return ComicDTO
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		comicDTO.setId(comic.getId());
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		return comicDTO;
	}

	/**
	 * Metodo encargado de convertir un objeto ComicDTO a Comic
	 * @param ComicDTO
	 * @return Comic
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		comic.setId(comicDTO.getId());
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}

}
