import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCreditcardsComponent } from './view-creditcards.component';

describe('ViewCreditcardsComponent', () => {
  let component: ViewCreditcardsComponent;
  let fixture: ComponentFixture<ViewCreditcardsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewCreditcardsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCreditcardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
