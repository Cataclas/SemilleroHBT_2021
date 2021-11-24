/**
 * IGestionarVentaComicLocal.java
 */
package com.hbt.semillero.ejb;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;

@Local
public interface IGestionarVentaComicLocal {
	
	/**
	 * Metodo encargado de la venta de Comics
	 * @author cataclas
	 * @param comicDTO
	 * @return ComicDTO
	 * @throws Exception
	 */
	public ComicDTO venderComic(ComicDTO comicDTO) throws Exception;
}
