import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlacedOrderComponent } from './placed-order.component';

describe('PlacedOrderComponent', () => {
  let component: PlacedOrderComponent;
  let fixture: ComponentFixture<PlacedOrderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlacedOrderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlacedOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
