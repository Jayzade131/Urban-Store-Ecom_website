import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';
import Swal from 'sweetalert2';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-productdetail',
  templateUrl: './productdetail.component.html',
  styleUrls: ['./productdetail.component.css']
})
export class ProductdetailComponent implements OnInit {
  productId: any = this.actRt.snapshot.params["productId"];

  product: any;
  reviews: any[] = [];
  faqs: any[] = [];

  constructor(
    private cs: CustomerService,
    private actRt: ActivatedRoute,
    private sb: MatSnackBar
  ) { }

  ngOnInit(): void {

    this.getProductDeatilsById();
  }

  getProductDeatilsById() {
    this.cs.getProductDetail(this.productId).subscribe(res => {
      this.product = res.productDto;
      this.product.processedImg = 'data:image/jpeg;base64,' + res.productDto.byteimg;

      this.faqs = res.faqDtoList;

      res.reviewDtoList.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.reviews.push(element);
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


  addwishlist() {
    const wishlistDto = {
      productId: this.productId,
      userId: UserStorageService.getUserId(),
    }
    this.cs.addWishlist(wishlistDto).subscribe(res => {
      if (res.id != null) {
        this.sb.open('Add to Wishlist Successfully..!', 'close', {
          duration: 5000
        });
      }
      else {
        this.sb.open('Already In Wishlist.', 'ERROR', {
          duration: 5000
        });
      }
    })
  }
}
