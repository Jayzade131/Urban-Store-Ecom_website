import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../service/admin.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-postcoupon',
  templateUrl: './postcoupon.component.html',
  styleUrls: ['./postcoupon.component.css']
})
export class PostcouponComponent implements OnInit {
  couponForm!:FormGroup;
  constructor(
    private fb:FormBuilder,
    private as:AdminService,
    private rt:Router,
    private snackbar:MatSnackBar
  ) { }

  ngOnInit(): void {
    this.couponForm= this.fb.group({
      name:[null,[Validators.required]],
      code:[null,[Validators.required]],
      discount:[null,[Validators.required]],
      expirationDate:[null,[Validators.required]],
    })
  }

  addCoupon()
  {
    if(this.couponForm.valid)
      {
        this.as.addCoupon(this.couponForm.value).subscribe(res =>{
          if(res.id !=null)
            {
              Swal.fire({
                title: "Coupon Added Successfully..",
                icon: "success"
              });
              this.rt.navigateByUrl('/admin/dashboard')
            }
            else{

              this.snackbar.open(res.message,'close',{
                duration:5000,
                panelClass:'error-snackbar'
              });

            }
        })
      }
      else{
        this.couponForm.markAllAsTouched();
      }
  }


}
