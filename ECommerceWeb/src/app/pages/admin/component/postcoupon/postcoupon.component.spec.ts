import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostcouponComponent } from './postcoupon.component';

describe('PostcouponComponent', () => {
  let component: PostcouponComponent;
  let fixture: ComponentFixture<PostcouponComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostcouponComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostcouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
