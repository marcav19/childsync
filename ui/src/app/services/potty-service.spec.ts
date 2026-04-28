import { TestBed } from '@angular/core/testing';

import { PottyService } from './potty-service';

describe('PottyService', () => {
  let service: PottyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PottyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
