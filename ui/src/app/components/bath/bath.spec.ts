import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bath } from './bath';

describe('Bath', () => {
  let component: Bath;
  let fixture: ComponentFixture<Bath>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Bath]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Bath);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
