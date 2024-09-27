import { TestBed } from '@angular/core/testing';

import { CorralService } from './corral.service';

describe('CorralService', () => {
  let service: CorralService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CorralService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
