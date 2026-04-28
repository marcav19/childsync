import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Potty } from './potty';

describe('Potty', () => {
  let component: Potty;
  let fixture: ComponentFixture<Potty>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Potty]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Potty);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
