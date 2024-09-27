import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CorralSelectComponent } from './corral-select.component';

describe('CorralSelectComponent', () => {
  let component: CorralSelectComponent;
  let fixture: ComponentFixture<CorralSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CorralSelectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CorralSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
