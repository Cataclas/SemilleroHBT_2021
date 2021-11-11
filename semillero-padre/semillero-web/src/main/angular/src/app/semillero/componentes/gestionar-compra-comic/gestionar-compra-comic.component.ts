import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ComicDTO } from 'src/app/semillero/dto/comic-dto';
import { GestionarComicService } from '../../servicios/gestionar-comic.service';

@Component({
  selector: 'gestionar-compra-comic',
  templateUrl: './gestionar-compra-comic.component.html',
})
export class GestionarCompraComicComponent implements OnInit {

  /**
   * Atributo que contiene los datos del Comic
   */
  public comicDTO: ComicDTO;

  /**
   * Atributo que contiene los datos del formulario
   */
  public gestionarCompraComicForm: FormGroup;

  /**
   * Atributo que permite mostrar el mensaje de ejecución en danger o success
   */
  public exitoso: boolean;

  /**
   * Atributo que contiene el mensaje de respuesta por cada acción ejecutada
   */
  public mensajeEjecucion: String;

  /**
   * Atributo que permite mostrar/ocultar el mensaje de ejecución
   */
  public mostrarMensaje: boolean;

  /**
    * Constructor de la clase
    * @param fb
    * @param router 
    * @param gestionComicService 
    */
  constructor(private fb: FormBuilder, private router: Router, private gestionComicService: GestionarComicService, private activatedRoute: ActivatedRoute) {
    this.gestionarCompraComicForm = this.fb.group({
      nombre: [null, [Validators.required, Validators.maxLength(50)]],
      cantidad: [null, [Validators.required, Validators.maxLength(3)]],
    })
  }

  ngOnInit() {
    this.comicDTO = new ComicDTO();
    let comic: any = this.activatedRoute.snapshot.params;
    this.comicDTO = comic;
    this.gestionarCompraComicForm.controls.nombre.setValue(this.comicDTO.nombre);
    this.gestionarCompraComicForm.controls.cantidad.setValue(this.comicDTO.cantidad);
    this.exitoso = false;
    this.mostrarMensaje = false;
  }

  /**
   * Metodo para comprar Comic
   */
  public comprarComic() {
    this.comicDTO.cantidad = this.gestionarCompraComicForm.controls.cantidad.value;

    this.gestionComicService.comprarComic(this.comicDTO).subscribe(data => {
      if (data.exitoso) {
        this.exitoso = true;
        this.mensajeEjecucion = data.mensajeEjecucion;
        this.mostrarMensaje = true;
        this.router.navigate(['gestionar-comic']);
      } else {
        this.exitoso = false;
        this.mensajeEjecucion = data.mensajeEjecucion;
        this.mostrarMensaje = true;
      }
    }, error => {
      console.log(error);
    })
  }
  /**
   * Navega de vuelva a la gestion de comics
   * @param comic 
   */
  public volver(): void {
    this.router.navigate(['gestionar-comic']);
  }
  get f() {
    return this.gestionarCompraComicForm.controls
  }
}
