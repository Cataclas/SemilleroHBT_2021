import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionarVentaComicComponent } from './gestionar-venta-comic.component';

describe('GestionarVentaComicComponent', () => {
  let component: GestionarVentaComicComponent;
  let fixture: ComponentFixture<GestionarVentaComicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestionarVentaComicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionarVentaComicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
