import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-placed-order',
  templateUrl: './placed-order.component.html',
  styleUrls: ['./placed-order.component.css']
})
export class PlacedOrderComponent implements OnInit {
  placedForm !: FormGroup;
  constructor(
    private customerService: CustomerService,
    private fb: FormBuilder,
    private rt: Router,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.placedForm = this.fb.group({
      address: [null, [Validators.required]],
      orderDesc: [null]
    })
  }

  placedOrder() {
    this.customerService.placedOrder(this.placedForm.value).subscribe(res => {
      if (res.id != null) {
        Swal.fire({
          title: "Order Placed",
          text: "Order Placed Sucessfully",
          icon: "success"
        });
        this.rt.navigateByUrl("/customer/placed-order");
      }
      else {
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: "Something went wrong!",

        });
      }
    })
  }

  closeForm() {
    this.dialog.closeAll();
  }

}
