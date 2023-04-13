import { TestBed } from '@angular/core/testing';

import { CreditcardServiceService } from './creditcard-service.service';

describe('CreditcardServiceService', () => {
  let service: CreditcardServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreditcardServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
