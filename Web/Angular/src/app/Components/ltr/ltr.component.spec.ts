import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LTRComponent } from './ltr.component';

describe('LTRComponent', () => {
  let component: LTRComponent;
  let fixture: ComponentFixture<LTRComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LTRComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LTRComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
