import { TestBed } from '@angular/core/testing';

import { GestionarComicService } from './gestionar-comic.service';

describe('GestionarComicService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GestionarComicService = TestBed.get(GestionarComicService);
    expect(service).toBeTruthy();
  });
});
