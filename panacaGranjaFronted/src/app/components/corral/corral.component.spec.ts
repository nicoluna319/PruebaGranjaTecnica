import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CorralComponent } from './corral.component';

describe('CorralComponent', () => {
  let component: CorralComponent;
  let fixture: ComponentFixture<CorralComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CorralComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CorralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
