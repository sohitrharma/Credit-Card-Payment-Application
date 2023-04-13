import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnBilledComponent } from './un-billed.component';

describe('UnBilledComponent', () => {
  let component: UnBilledComponent;
  let fixture: ComponentFixture<UnBilledComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnBilledComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnBilledComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
