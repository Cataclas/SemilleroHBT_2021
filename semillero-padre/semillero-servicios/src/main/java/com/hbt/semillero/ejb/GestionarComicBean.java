package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.NombrePrecioComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.TamanioNombreComicDTO;
import com.hbt.semillero.entidad.Comic;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {

	@PersistenceContext
	public EntityManager em;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception {		
		if(comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido");
		}
		
		ComicDTO comicDTOResult = null;
		Comic comic = this.convertirComicDTOToComic(comicDTO);
		em.persist(comic);
		comicDTOResult = this.convertirComicToComicDTO(comic);
		comicDTOResult.setExitoso(true);
		comicDTOResult.setMensajeEjecucion("El comic ha sido creado exitosamente");
		return comicDTOResult;
	}	

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> listarComics() {
		List<ComicDTO> listaComicDTO = new ArrayList<>();
		List<Comic> resultado = em.createQuery("SELECT c FROM Comic c").getResultList();
		
		for (Comic comic : resultado) {
			listaComicDTO.add(convertirComicToComicDTO(comic));
		}
		return listaComicDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarComic(Long idComic) {
		Comic comic = em.find(Comic.class, idComic);
		ComicDTO comicDTO = convertirComicToComicDTO(comic);
		return comicDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ComicDTO actualizarComic(ComicDTO comicDTO) {	
		ComicDTO comicDTOResult = new ComicDTO();
		
		Comic comic = this.convertirComicDTOToComic(comicDTO);			
		em.merge(comic);
		
		comicDTOResult = this.convertirComicToComicDTO(comic);
		comicDTOResult.setExitoso(true);
		comicDTOResult.setMensajeEjecucion("El comic ha sido modificado exitosamente");
		return comicDTOResult;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ResultadoDTO eliminarComic(Long idComic) {
		ResultadoDTO resultadoDTO = new ResultadoDTO();
		
		Comic comic = em.find(Comic.class, idComic);
		em.remove(comic);
		
		resultadoDTO.setExitoso(true);
		resultadoDTO.setMensajeEjecucion("Comic eliminado con éxito!");
		
		return resultadoDTO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public NombrePrecioComicDTO consultarNombrePrecioComic(Long idComic) {
		String consulta = "SELECT new com.hbt.semillero.dto.NombrePrecioComicDTO(c.nombre, c.precio) "
						+ " FROM Comic c WHERE c.id = :idComic ";
		NombrePrecioComicDTO consultaNombrePrecioDTO = new NombrePrecioComicDTO();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("idComic", idComic);
			consultaNombrePrecioDTO = (NombrePrecioComicDTO) consultaNativa.getSingleResult();
			consultaNombrePrecioDTO.setExitoso(true);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");	
		} catch (Exception e) {
			consultaNombrePrecioDTO.setExitoso(false);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic "  + e.getMessage());
		}

		return consultaNombrePrecioDTO;
	}
	
	/**
	 * Metodo taller de servicios
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Object> consultarComicTamanioNombre (Short lengthComic) {
		ResultadoDTO resultadoDTO = new ResultadoDTO();
		ArrayList<Object> resultado = new ArrayList<>();
		ArrayList<String> listComicTamanioMenor = new ArrayList<>();
		ArrayList<String> listComicTamanioMayor = new ArrayList<>();
		
		try {
			if(lengthComic > 9) {
				throw new Exception("La longitud máxima permitida es de 9 caracteres");
			}
			
			String consulta = "SELECT new com.hbt.semillero.dto.TamanioNombreComicDTO(c.nombre) FROM Comic c";
			Query consultaNativa = em.createQuery(consulta);
			List<TamanioNombreComicDTO> listaNombres = consultaNativa.getResultList();
			
			for (TamanioNombreComicDTO item : listaNombres) {
				if(item.getNombre().length() > lengthComic) {				
					listComicTamanioMayor.add(item.getNombre());
				} else {
					listComicTamanioMenor.add(item.getNombre());
				}
			}		
			
			resultadoDTO.setExitoso(true);
			resultadoDTO.setMensajeEjecucion("Comics procesados exitosamente");
			resultado.add(resultadoDTO);
			resultado.add(listComicTamanioMayor);
			resultado.add(listComicTamanioMenor);
		} catch (Exception e) {
			resultadoDTO.setExitoso(false);
			resultadoDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar los comics, " + e.getMessage());
			resultado.add(resultadoDTO);
		}
		return resultado;
	}
	
	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
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
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
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
