import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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

}
