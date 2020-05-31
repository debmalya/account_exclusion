import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BatchrunComponent } from './batchrun.component';

describe('BatchrunComponent', () => {
  let component: BatchrunComponent;
  let fixture: ComponentFixture<BatchrunComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BatchrunComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BatchrunComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
