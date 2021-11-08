import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';

@Component({
  selector: 'app-crear-persona',
  templateUrl: './crear-persona.component.html',
  styleUrls: ['./crear-persona.component.css']
})
export class CrearPersonaComponent implements OnInit {

  private nombreInstructor: String;



  constructor() { }

  ngOnInit() {
    this.nombreInstructor = "Catalina Clavijo";
    // let edadInstructor = 28;
    let concatenar = "";
    for (let index = 0; index < this.nombreInstructor.length; index++) {
      let element = this.nombreInstructor[index];
      concatenar += element;

      // console.log(concatenar);
      // console.log(index);
    }
  }

}
