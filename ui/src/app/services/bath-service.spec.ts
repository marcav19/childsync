import { TestBed } from '@angular/core/testing';

import { BathService } from './bath-service';

describe('BathService', () => {
  let service: BathService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BathService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
