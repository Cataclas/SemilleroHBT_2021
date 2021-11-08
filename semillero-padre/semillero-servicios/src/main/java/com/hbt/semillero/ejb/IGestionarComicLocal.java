package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.NombrePrecioComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.TamanioNombreComicDTO;

@Local
public interface IGestionarComicLocal {
	
	/**
	 * Método para crear/agregar Comic a la base de datos
	 * @param comicDTO
	 * @return ComicDTO
	 * @throws Exception
	 */
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception;
	
	/**
	 * Método para listar todos los Comics guardados en la base de datos
	 * @return List<ComicDTO>
	 */
	public List<ComicDTO> listarComics();

	/**
	 * Método para consultar un Comic por id, en la base de datos
	 * @param idComic
	 * @return ComicDTO
	 */
	public ComicDTO consultarComic(Long idComic);
	
	/**
	 * Método para actualizar/modificar un Comic en la base de datos
	 * @param comicDTO
	 * @return ResultadoDTO
	 */
	public ComicDTO actualizarComic(ComicDTO comicDTO);
	
	/**
	 * Método para eliminar un Comic de la base de datos
	 * @param idComic
	 * @return ResultadoDTO
	 */
	public ResultadoDTO eliminarComic(Long idComic);
	
	/**
	 * Método para consultar nombre y precio de un Comic por id en la base de datos
	 * @param idComic
	 * @return NombrePrecioComicDTO
	 */
	public NombrePrecioComicDTO consultarNombrePrecioComic(Long idComic);

	/**
	 * Método para listar nombre de comics, por los que superan el tamaño dado como parámetro y los que no
	 * @param tamanio
	 * @return ResultadoDTO
	 */
	public List<Object> consultarComicTamanioNombre (Short lengthComic);

}
