import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCreditcardComponent } from './update-creditcard.component';

describe('UpdateCreditcardComponent', () => {
  let component: UpdateCreditcardComponent;
  let fixture: ComponentFixture<UpdateCreditcardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCreditcardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCreditcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
