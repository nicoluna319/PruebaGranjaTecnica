import { TestBed } from '@angular/core/testing';

import { CorralSelectService } from './corral-select.service';

describe('CorralSelectService', () => {
  let service: CorralSelectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CorralSelectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
