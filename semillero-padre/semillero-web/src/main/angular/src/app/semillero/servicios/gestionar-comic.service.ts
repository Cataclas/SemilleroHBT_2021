import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { ComicDTO } from '../dto/comic-dto';
import { AbstractService } from './template.service';

/**
 * Clase encargada de conectar el front con los servicios creados
 */
@Injectable({
  providedIn: 'root'
})
export class GestionarComicService extends AbstractService {

  /**
   * Constructor de la clase
   * @param injector 
   * @param httpClient 
   */
  constructor(private injector: Injector, private httpClient: HttpClient) {
    super(injector);
  }

  /**
   * Metodo encargado de crear Comics
   * @param comicDTO 
   * @returns 
   */
  public crearComics(comicDTO: ComicDTO): Observable<any> {
    return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/gestionarComic/crearComic', comicDTO);
  }

  /**
   * Metodo encargado de listar todos los comics
   * @param search
   * @returns 
   */
  public listarComics(search: String): Observable<any> {
    let params = new HttpParams().set('search', search.toString());
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/gestionarComic/listarComics',
      { params: params });
    // return this.httpClient.get(`http://localhost:8085/semillero-servicios/rest/gestionarComic/listarComics?search=${search}`);
  }

  /**
   * Metodo encargado de consultar un comic por su id
   * @param idComic
   * @returns 
   */
  public consultarComic(idComic: Number): Observable<any> {
    let params = new HttpParams().set('idComic', idComic.toString());
    return this.httpClient.get(`http://localhost:8085/semillero-servicios/rest/gestionarComic/consultarComic`,
      { params: params })
  }

  /**
   * Metodo encargado de actualizar/modificar comics
   * @param comicDTO
   * @returns 
   */
  public actualizarComic(comicDTO: ComicDTO): Observable<any> {
    return this.httpClient.patch('http://localhost:8085/semillero-servicios/rest/gestionarComic/actualizarComic', comicDTO);
  }

  /**
   * Metodo encargado de eliminar comics
   * @param idComic
   * @returns 
   */
  public eliminarComic(idComic: Number): Observable<any> {
    return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/gestionarComic/eliminarComic', idComic);
  }

  /**
   * Metodo encargado de gestionar la venta de comics
   * @param comicDTO
   * @returns 
   */
  public venderComic(comicDTO: ComicDTO): Observable<any> {
    return this.httpClient.patch('http://localhost:8085/semillero-servicios/rest/gestionarVentaComic/venderComic', comicDTO);
  }
}
