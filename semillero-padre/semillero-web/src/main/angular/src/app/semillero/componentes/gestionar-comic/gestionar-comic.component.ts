import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ComicDTO } from 'src/app/semillero/dto/comic-dto';
import { GestionarComicService } from '../../servicios/gestionar-comic.service';

/**
 * @description Componente encargado de gestionar la logica para crear consultar actualizar y eliminar
 * un comic
 * @author cataclas
 */
@Component({
  selector: 'gestionar-comic',
  templateUrl: './gestionar-comic.component.html',
})
export class GestionarComicComponent implements OnInit {

  /**
   * Atributo que contiene los datos del Comic
   */
  public comicDTO: ComicDTO;

  /**
   * Atributo que contiene los datos del formulario
   */
  public gestionarComicForm: FormGroup;

  /**
   * Atributo que contiene la información de un comic
   */
  public comicDTOInfo: ComicDTO;

  /**
   * Atributo que contiene la lista de Comics
   */
  public listaComics: Array<ComicDTO>;

  /**
   * Atributo que permite mostrar/ocultar el modal de información
   */
  public mostrarItem: boolean;

  /**
   * Atributo que contiene el index en la tabla de un comic
   */
  public index: number;

  /**
   * Atributo que permite manejar el modal que contiene el formulario de Crear/Editar Comic
   */
  public modal: HTMLElement;

  /**
   * Atributo que activa y desactiva la validación del formulario
   */
  public submitted: Boolean;

  /**
   * Atributo que contiene el mensaje de respuesta por cada acción ejecutada sobre el Comic (CRUD)
   */
  public mensajeEjecucion: String;


  /**
   * Constructor de la clase
   * @param fb
   * @param router 
   * @param gestionComicService 
   */
  constructor(private fb: FormBuilder, private router: Router, private gestionComicService: GestionarComicService) {
    this.gestionarComicForm = this.fb.group({
      id: [null],
      nombre: [null, [Validators.required, Validators.maxLength(50)]],
      editorial: [null, [Validators.required, Validators.maxLength(50)]],
      tematicaEnum: [null, Validators.required],
      coleccion: [null, [Validators.required, Validators.maxLength(50)]],
      numeroPaginas: [null, [Validators.required, Validators.maxLength(5)]],
      precio: [null, [Validators.required, Validators.maxLength(4)]],
      autores: [null],
      color: [null],
      cantidad: [null, [Validators.required, Validators.maxLength(3)]],
    })
  }

  /**
   * Permite ejecutar y guardar valores predeterminados al cargar el componente
   */
  ngOnInit() {
    this.comicDTO = new ComicDTO();
    this.submitted = false;
    this.mostrarItem = false;
    this.listaComics = new Array<ComicDTO>();
    this.index = -1;
    this.modal = document.getElementById('myModal');
    this.listarComics("");
  }

  /**
 * Método encargado de Crear Comics
 * @returns 
 */
  public crearComic(): void {
    if (this.gestionarComicForm.invalid) {
      return;
    }

    this.comicDTO = new ComicDTO();
    this.comicDTO.nombre = this.gestionarComicForm.controls.nombre.value;
    this.comicDTO.editorial = this.gestionarComicForm.controls.editorial.value;
    this.comicDTO.tematicaEnum = this.gestionarComicForm.controls.tematicaEnum.value;
    this.comicDTO.coleccion = this.gestionarComicForm.controls.coleccion.value;
    this.comicDTO.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
    this.comicDTO.precio = this.gestionarComicForm.controls.precio.value;
    this.comicDTO.autores = this.gestionarComicForm.controls.autores.value;
    this.comicDTO.color = this.gestionarComicForm.controls.color.value;
    this.comicDTO.cantidad = this.gestionarComicForm.controls.cantidad.value;
    this.comicDTO.estadoEnum = "ACTIVO";

    this.gestionComicService.crearComics(this.comicDTO).subscribe(data => {
      if (data.exitoso) {
        this.mensajeEjecucion = data.mensajeEjecucion;
        this.listarComics("");
        this.submitted = false;
      } else {
        this.mensajeEjecucion = data.mensajeEjecucion;
      }
      alert(this.mensajeEjecucion);
      this.limpiarDatosComic();
    }, error => {
      console.log(error);
    })

  }

  /**
   * Método encargado de listar todos los comics
   * @param search
   */
  public listarComics(search: string) {
    this.gestionComicService.listarComics(search).subscribe(data => {
      if (data[0].exitoso) {
        this.listaComics = data;
      } else {
        console.log(data[0].mensajeEjecucion);
      }
    }, error => {
      console.log(error);
    })
  }

  /**
   * Metodo encargado de consultar un comic por su id
   * @param idComic
   */
  public consultarComic(idComic: Number) {
    this.gestionComicService.consultarComic(idComic).subscribe(data => {
      console.log(data);
      if (data.exitoso) {
        this.gestionarComicForm.controls.id.setValue(data.id);
        this.gestionarComicForm.controls.nombre.setValue(data.nombre);
        this.gestionarComicForm.controls.editorial.setValue(data.editorial);
        this.gestionarComicForm.controls.tematicaEnum.setValue(data.tematicaEnum);
        this.gestionarComicForm.controls.coleccion.setValue(data.coleccion);
        this.gestionarComicForm.controls.numeroPaginas.setValue(data.numeroPaginas);
        this.gestionarComicForm.controls.precio.setValue(data.precio);
        this.gestionarComicForm.controls.autores.setValue(data.autores);
        this.gestionarComicForm.controls.color.setValue(data.color);
        this.gestionarComicForm.controls.cantidad.setValue(data.cantidad);
      } else {
        console.log(data.mensajeEjecucion);
      }
    }, error => {
      console.log(error);
    })
  }

  /**
   * Metodo encargado de actualizar/modificar un comic
   */
  public actualizarComic() {
    this.comicDTO = new ComicDTO();
    this.comicDTO.id = this.gestionarComicForm.controls.id.value;
    this.comicDTO.nombre = this.gestionarComicForm.controls.nombre.value;
    this.comicDTO.editorial = this.gestionarComicForm.controls.editorial.value;
    this.comicDTO.tematicaEnum = this.gestionarComicForm.controls.tematicaEnum.value;
    this.comicDTO.coleccion = this.gestionarComicForm.controls.coleccion.value;
    this.comicDTO.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
    this.comicDTO.precio = this.gestionarComicForm.controls.precio.value;
    this.comicDTO.autores = this.gestionarComicForm.controls.autores.value;
    this.comicDTO.color = this.gestionarComicForm.controls.color.value;
    this.comicDTO.cantidad = this.gestionarComicForm.controls.cantidad.value;
    this.comicDTO.estadoEnum = "ACTIVO";

    this.gestionComicService.actualizarComic(this.comicDTO).subscribe(data => {
      if (data.exitoso) {
        this.listarComics("");
        alert(data.mensajeEjecucion);
      } else {
        console.log(data.mensajeEjecucion);
      }
    }, error => {
      console.log(error);
    })

    this.index = -1;
  }

  /**
   * Método encargado de Eliminar Comics
   * @param idComic 
   */
  public eliminarComic(idComic: Number): void {
    if(confirm("¿Desea eliminar este registro?")){
      this.gestionComicService.eliminarComic(idComic).subscribe(data => {
        if (data.exitoso) {
          this.listarComics("");
          alert(data.mensajeEjecucion);
        } else {
          console.log(data.mensajeEjecucion);
        }
      }, error => {
        console.log(error);
      })
    };
  }

  /**
   * Metodo encargado de abrir el Formulario, validando si es para crear o editar
   * @param posicion
   */
  public abrirForm(posicion: number): void {
    this.limpiarDatosComic();
    this.index = posicion;
    let comic = this.listaComics[posicion];
    let idComic = comic.id;
    
    if (this.index >= 0) {
      this.gestionarComicForm.controls.nombre;
      this.gestionarComicForm.controls.editorial.disable();
      this.gestionarComicForm.controls.tematicaEnum.disable();
      this.gestionarComicForm.controls.coleccion;
      this.gestionarComicForm.controls.numeroPaginas;
      this.gestionarComicForm.controls.precio;
      this.gestionarComicForm.controls.autores;
      this.consultarComic(idComic);
    } else {
      this.gestionarComicForm.controls.nombre.enable();
      this.gestionarComicForm.controls.editorial.enable();
      this.gestionarComicForm.controls.tematicaEnum.enable();
      this.gestionarComicForm.controls.coleccion.enable();
      this.gestionarComicForm.controls.numeroPaginas.enable();
      this.gestionarComicForm.controls.precio.enable();
      this.gestionarComicForm.controls.autores.enable();
      this.gestionarComicForm.controls.color.enable();
    }

    var inputNombreComic = document.getElementById('nombreComic')

    this.modal.addEventListener('show.bs.modal', function () {
      inputNombreComic.focus()
    })
  }

  /**
   * Metodo encargado de direccionar a la función de crear o actualizar, segun sea el caso
   */
  public guardarComic(): void {
    this.submitted = true;
    if (this.index >= 0) {
      this.actualizarComic();
    } else {
      this.crearComic();
    }
  }

  /**
   * Método encargado de Limpiar el formulario
   */
  private limpiarDatosComic(): void {
    this.submitted = false;
    this.gestionarComicForm.controls.id.setValue(null);
    this.gestionarComicForm.controls.nombre.setValue(null);
    this.gestionarComicForm.controls.coleccion.setValue(null);
    this.gestionarComicForm.controls.color.setValue(null);
    this.gestionarComicForm.controls.editorial.setValue(null);
    this.gestionarComicForm.controls.tematicaEnum.setValue(null);
    this.gestionarComicForm.controls.precio.setValue(null);
    this.gestionarComicForm.controls.numeroPaginas.setValue(null);
    this.gestionarComicForm.controls.autores.setValue(null);
    this.gestionarComicForm.controls.cantidad.setValue(null);
  }

  /**
   * Metodo encargado de cargar y mostrar información en modal de info
   * @param comic
   */
  public imprimirInfoComic(comic: ComicDTO): void {
    this.comicDTOInfo = comic;
    this.mostrarItem = true;
  }

  /**
   * Metodo encargado de cerrar el modal de info
   */
  public cerrar(): void {
    this.mostrarItem = false;
  }

  public irAComprar(comic : ComicDTO) : void {
    this.router.navigate(['gestionar-compra-comic', comic]);
  }

  get f() {
    return this.gestionarComicForm.controls
  }
}
