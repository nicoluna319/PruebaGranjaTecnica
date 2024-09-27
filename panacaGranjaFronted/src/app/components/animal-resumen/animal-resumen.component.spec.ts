import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalResumenComponent } from './animal-resumen.component';

describe('AnimalResumenComponent', () => {
  let component: AnimalResumenComponent;
  let fixture: ComponentFixture<AnimalResumenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnimalResumenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnimalResumenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
