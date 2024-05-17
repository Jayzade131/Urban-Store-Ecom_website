import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  products: any[] = [];

  constructor(private cs: CustomerService,

  ) { }

  ngOnInit(): void {

    this.getwishlist();
  }

  getwishlist() {
    this.cs.getWishlist().subscribe(res => {
      res.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.products.push(element);
      });
    })
  }

  addcart(productId: any) {

    console.log(productId)
    this.cs.addToCart(productId).subscribe(res => {
      if (res.body != null) {


        Swal.fire({
          title: "Added",
          text: "Product Add To Cart Successfully.. ",
          icon: "success"
        });
      }
    }
    )
  }



}
