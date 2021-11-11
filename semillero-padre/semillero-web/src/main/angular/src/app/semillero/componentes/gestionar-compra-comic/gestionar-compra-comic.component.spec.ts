import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionarCompraComicComponent } from './gestionar-compra-comic.component';

describe('GestionarCompraComicComponent', () => {
  let component: GestionarCompraComicComponent;
  let fixture: ComponentFixture<GestionarCompraComicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestionarCompraComicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionarCompraComicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
