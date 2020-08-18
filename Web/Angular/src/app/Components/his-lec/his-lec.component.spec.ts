import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HisLecComponent } from './his-lec.component';

describe('HisLecComponent', () => {
  let component: HisLecComponent;
  let fixture: ComponentFixture<HisLecComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HisLecComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HisLecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
