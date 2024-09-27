import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestriccionFormComponent } from './restriccion-form.component';

describe('RestriccionFormComponent', () => {
  let component: RestriccionFormComponent;
  let fixture: ComponentFixture<RestriccionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestriccionFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestriccionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
