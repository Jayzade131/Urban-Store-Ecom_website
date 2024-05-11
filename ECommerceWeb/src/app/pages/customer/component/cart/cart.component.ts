import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { MatDialog } from '@angular/material/dialog';
import { CustomerService } from '../../services/customer.service';
import { PlacedOrderComponent } from '../placed-order/placed-order.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItems: any[] = [];

  order:any;

  couponForm!:FormGroup;


  constructor(
    private snackbar:MatSnackBar,
    private fb:FormBuilder,
    private dialong: MatDialog,
    private customerService: CustomerService,
  ) { }

  ngOnInit(): void {
   
    this.couponForm=this.fb.group({
      code :[null,[Validators.required]]
    })
    this.getCart();
  }

  getCart()
  {
    this.cartItems= [];
    this.customerService.getCartByUserId().subscribe(res =>{
      this.order =res;
      res.cartItems.forEach(element => {
        element.processedImg= 'data:image/jpeg;base64,' +element.returnImg;
        this.cartItems.push(element); 
      });
    })
  }

  applycoupon()
  {
    this.customerService.applyCoupon(this.couponForm.get(['code'])!.value).subscribe(res =>{
      this.snackbar.open("Coupon Applied Sucessfully",'close',{
        duration :5000
      });
      this.getCart();
    },error =>{
      this.snackbar.open(error.error,'close',{
        duration :5000
      });
    })
  }

  increaseProductQuan(productId :any)
  {
    console.log("product---"+productId)
    this.customerService.increaseProductQuantity(productId).subscribe(res =>{
      this.snackbar.open("Product +1",'close',{
        duration :5000
      });
      this.getCart();
    })
  }

  desProductQuan(productId :any)
  {
    console.log("product---"+productId)
    this.customerService.decrProductQuantity(productId).subscribe(res =>{
      this.snackbar.open("Product -1",'close',{
        duration :5000
      });
      this.getCart();
    })
  }

  placedbtn()
  {
    this.dialong.open(PlacedOrderComponent);
  }
  

}
