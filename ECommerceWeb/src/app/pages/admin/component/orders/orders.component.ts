import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders:any;

  constructor(private as:AdminService,private snackbar:MatSnackBar) { }

  ngOnInit(): void {
    this.getAllPlacedOrder();
  }

  getAllPlacedOrder()
{
 this.as.getPlacedOrders().subscribe(res=>{
    this.orders=res;
 })
}

changeOrderStatus(orderId:number,status:string)
{
 
  this.as.changeOrderStatus(orderId,status).subscribe(res =>{
    if(res.id !=null)
      {
        this.snackbar.open("Order Status Change Successfully","Close",{
            duration :5000
        });
        this.getAllPlacedOrder();
      }
      else{
        this.snackbar.open("Something went wrong","Close",{
          duration :5000
      });
      }
  })
}

}
