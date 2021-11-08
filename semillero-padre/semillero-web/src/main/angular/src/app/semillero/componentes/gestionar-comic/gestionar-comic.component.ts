import { Component, OnInit } from '@angular/core';
import { ComicDTO } from 'src/app/semillero/dto/comic-dto';

/**
 * @description Componente encargado de gestionar la logica para crear consultar actualizar y eliminar
 * un comic
 * @author dalvarez
 * @see SEMILLERO 2021
 */
@Component({
  selector: 'gestionar-comic',
  templateUrl: './gestionar-comic.component.html',
  styleUrls: ['./gestionar-comic.component.css'],
})
export class GestionarComicComponent implements OnInit {

  public comicDTO: ComicDTO;

  public comicDTOInfo: ComicDTO;

  public nombre: string;

  public listaComics: Array<ComicDTO>;

  public listaCopiaComics: Array<ComicDTO>;

  public mostrarItem: boolean;

  public index: number;

  constructor() { }

  ngOnInit() {
    this.mostrarItem = false;
    this.comicDTO = new ComicDTO();
    this.listaComics = [
      {
        id: 0,
        nombre: "Los Vengadores",
        tematicaEnum: "FANTASTICO",
        editorial: "Panini Comics",
        coleccion: "Marvel Gold",
        autores: "Jack Kirby y Stan Lee",
        numeroPaginas: 236,
        precio: 300,
        color: true
      }, {
        id: 1,
        nombre: "Batman",
        tematicaEnum: "BELICO",
        editorial: "DC Comics",
        coleccion: "Comics",
        autores: "DC Comics",
        numeroPaginas: 200,
        precio: 897,
        color: false
      }, {
        id: 2,
        nombre: "Flash",
        tematicaEnum: "CIENCIA_FICCION",
        editorial: "DC Comics",
        coleccion: "Comics",
        autores: "DC Comics",
        numeroPaginas: 170,
        precio: 379,
        color: true
      }

    ]
    this.index = -1;
    this.listaCopiaComics = this.listaComics;
  }
  public cambioSearch(searchVal: string): void {
    let datos = this.filtrarTabla(this.listaCopiaComics, searchVal);
    if (searchVal.length > 0 && datos.length >= 0) {
      this.listaComics = datos;
    } else {
      this.listaComics = this.listaCopiaComics;
    }

    // console.log(this.filtrarTabla(this.listaComics, searchVal));
  }

  public crearComic(): void {
    this.listaComics.push(this.comicDTO);
    this.comicDTO = new ComicDTO();
    //this.limpiarDatosComic(this.comicDTO);
  }

  public editarComic(): void {
    this.listaComics[this.index] = this.comicDTO;
    this.index = -1;
  }

  public eliminarComic(posicion: number): void {
    this.listaComics.splice(posicion, 1);
  }

  public guardarComic(): void {
    if (this.index >= 0) {
      this.editarComic();
    } else {
      this.crearComic();
    }
  }
  public filtrarTabla(data, s) {
    return data.filter(ComicDTO => ComicDTO.nombre.toLowerCase().includes(s.toLowerCase()) || ComicDTO.editorial.toLowerCase().includes(s.toLowerCase()) || ComicDTO.tematicaEnum.toLowerCase().includes(s.toLowerCase()) || ComicDTO.coleccion.toLowerCase().includes(s.toLowerCase()) || ComicDTO.autores.toLowerCase().includes(s.toLowerCase()))
    //  .sort((a,b) => a.nombre.toLowerCase().includes(s) && !b.nombre.toLowerCase().includes(s) ? -1 : b.nombre.toLowerCase().includes(s) && !a.nombre.toLowerCase().includes(s) ? 1 :0);

  }
  private limpiarDatosComic(comicDTO: ComicDTO): void {
    comicDTO.nombre = null;
    comicDTO.coleccion = null;
    comicDTO.color = null;
    comicDTO.editorial = null;
    comicDTO.id = null;
    comicDTO.tematicaEnum = null;
    comicDTO.precio = null;
    comicDTO.numeroPaginas = null;
    comicDTO.autores = null;
  }

  public imprimirInfoComic(posicion: number): void {
    this.mostrarItem = true;
    this.comicDTOInfo = this.listaComics[posicion];
  }

  public cerrar(): void {
    this.mostrarItem = false;
  }

  public abrirForm(posicion: number): void {
    if (posicion >= 0) {
      this.comicDTO = this.listaComics[posicion];
      this.index = posicion;
    } else {
      this.comicDTO = new ComicDTO;
    }

    var myModal = document.getElementById('myModal');
    var inputNombreComic = document.getElementById('nombreComic')

    myModal.addEventListener('shown.bs.modal', function () {
      inputNombreComic.focus()
    })
  }
}
