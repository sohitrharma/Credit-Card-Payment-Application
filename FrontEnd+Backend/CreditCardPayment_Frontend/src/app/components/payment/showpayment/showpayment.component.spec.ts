import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowpaymentComponent } from './showpayment.component';

describe('ShowpaymentComponent', () => {
  let component: ShowpaymentComponent;
  let fixture: ComponentFixture<ShowpaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowpaymentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowpaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
