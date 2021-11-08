import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionarComicComponent } from './gestionar-comic.component';

describe('GestionarComicComponent', () => {
  let component: GestionarComicComponent;
  let fixture: ComponentFixture<GestionarComicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestionarComicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionarComicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
