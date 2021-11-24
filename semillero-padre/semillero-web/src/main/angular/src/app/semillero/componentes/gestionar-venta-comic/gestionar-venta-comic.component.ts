import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ComicDTO } from 'src/app/semillero/dto/comic-dto';
import { GestionarComicService } from '../../servicios/gestionar-comic.service';

@Component({
  selector: 'gestionar-venta-comic',
  templateUrl: './gestionar-venta-comic.component.html',
})
export class GestionarVentaComicComponent implements OnInit {

  /**
   * Atributo que contiene los datos del Comic
   */
  public comicDTO: ComicDTO;

  /**
   * Atributo que contiene los datos del formulario
   */
  public gestionarVentaComicForm: FormGroup;

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
   * Atributo que activa y desactiva la validación del formulario
   */
   public submitted: Boolean;

  /**
    * Constructor de la clase
    * @param fb
    * @param router 
    * @param gestionComicService 
    */
  constructor(private fb: FormBuilder, private router: Router, private gestionComicService: GestionarComicService, private activatedRoute: ActivatedRoute) {
    this.gestionarVentaComicForm = this.fb.group({
      id: [null],
      nombre: [null, Validators.required],
      cantidad: [null, [Validators.required, Validators.min(1)]],
    })
  }

  ngOnInit() {
    this.comicDTO = new ComicDTO();
    let comic: any = this.activatedRoute.snapshot.params;
    this.comicDTO = comic;
    this.gestionarVentaComicForm.controls.id.setValue(this.comicDTO.id);
    this.gestionarVentaComicForm.controls.nombre.setValue(this.comicDTO.nombre);
    this.exitoso = false;
    this.mostrarMensaje = false;
    this.submitted = false;
  }

  /**
   * Metodo para vender Comic
   */
  public venderComic() {
    if(this.gestionarVentaComicForm.valid){
      let comicVenta = new ComicDTO();
      comicVenta.id = parseInt(this.gestionarVentaComicForm.controls.id.value);
      comicVenta.cantidad = parseInt(this.gestionarVentaComicForm.controls.cantidad.value);
  
      this.gestionComicService.venderComic(comicVenta).subscribe(data => {
        if (data.exitoso) {
          this.exitoso = true;
          this.mensajeEjecucion = data.mensajeEjecucion;
          this.mostrarMensaje = true;
        } else {
          this.exitoso = false;
          this.mensajeEjecucion = data.mensajeEjecucion;
          this.mostrarMensaje = true;
        }
      }, error => {
        console.log(error);
      })
    } else{
      this.submitted = true;
    }
  }
  /**
   * Navega de vuelva a la gestion de comics
   * @param comic 
   */
  public volver(): void {
    this.router.navigate(['gestionar-comic']);
    this.submitted = false;
  }

  get f() {
    return this.gestionarVentaComicForm.controls
  }
}
