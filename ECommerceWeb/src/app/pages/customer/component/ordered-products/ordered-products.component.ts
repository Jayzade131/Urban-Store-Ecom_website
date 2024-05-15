import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-ordered-products',
  templateUrl: './ordered-products.component.html',
  styleUrls: ['./ordered-products.component.css']
})
export class OrderedProductsComponent implements OnInit {

  orderId: any = this.actrot.snapshot.params['orderId'];
  orderedProductdetailsList = [];
  totalAmount: any;

  constructor(private custSer: CustomerService,
    private actrot: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.getOrderedProductByOrderId();
  }

  getOrderedProductByOrderId() {
    this.custSer.getOrderedProduct(this.orderId).subscribe(res => {
      res.productDtoList.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.byteimg;
        this.orderedProductdetailsList.push(element);

      });
      this.totalAmount = res.orderAmount;
    })
  }
}
