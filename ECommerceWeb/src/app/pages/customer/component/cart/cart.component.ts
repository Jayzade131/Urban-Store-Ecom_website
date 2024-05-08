import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { MatDialog } from '@angular/material/dialog';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartItems: any[] = [];

  order:any;



  constructor(
    private snackbar:MatSnackBar,
    private fb:FormBuilder,
    private dilong: MatDialog,
    private customerService: CustomerService,
  ) { }

  ngOnInit(): void {
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

}
