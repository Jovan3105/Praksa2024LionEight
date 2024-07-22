import { TestBed } from '@angular/core/testing';

import { SkyonicsService } from './skyonics.service';

describe('SkyonicsService', () => {
  let service: SkyonicsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SkyonicsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
