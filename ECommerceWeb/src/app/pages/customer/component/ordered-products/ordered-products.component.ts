import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-ordered-products',
  templateUrl: './ordered-products.component.html',
  styleUrls: ['./ordered-products.component.css']
})
export class OrderedProductsComponent implements OnInit {

  constructor(private custSer: CustomerService) { }

  ngOnInit(): void {
  }

}
