import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-coupon',
  templateUrl: './coupon.component.html',
  styleUrls: ['./coupon.component.css']
})
export class CouponComponent implements OnInit {
coupons : any[]=[]
  constructor(private adminService:AdminService) { }

  ngOnInit(): void {
    this.getCoupon();
  }
getCoupon()
{
  this.coupons=[];
  this.adminService.getCoupon().subscribe(res =>{
    res.forEach(element => {
      
      this.coupons.push(element);        
    });
  })
}
}
