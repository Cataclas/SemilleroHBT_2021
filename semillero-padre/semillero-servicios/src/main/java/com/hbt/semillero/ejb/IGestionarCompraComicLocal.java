/**
 * IGestionarCompraComicLocal.java
 */
package com.hbt.semillero.ejb;

import com.hbt.semillero.dto.ComicDTO;

/**
 * <b>Descripción:</b> Clase que determina
 * @author cataclas
 */
public interface IGestionarCompraComicLocal {
	public ComicDTO comprarComic(ComicDTO comicDTO) throws Exception;
}
