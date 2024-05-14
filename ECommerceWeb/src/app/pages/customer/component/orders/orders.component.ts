import { Component, OnInit } from '@angular/core';
import { CustomerRoutingModule } from '../../customer-routing.module';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders: any[] = [];
  constructor(
    private cs: CustomerService
  ) { }

  ngOnInit(): void {
    this.getAllPlacedOrder();
  }

  getAllPlacedOrder() {
    this.orders = [];
    this.cs.getOrders().subscribe(res => {
      res.forEach(element => {
        this.orders.push(element);
      });
    })
  }

}
