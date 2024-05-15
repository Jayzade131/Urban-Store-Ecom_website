import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FAQComponent implements OnInit {

  productId: number = this.actrt.snapshot.params["productId"];

  FAQform !: FormGroup;

  constructor(
    private as: AdminService,
    private rt: Router,
    private actrt: ActivatedRoute,
    private fb: FormBuilder

  ) { }

  ngOnInit(): void {
    this.FAQform = this.fb.group({
      que: [null, [Validators.required]],
      ans: [null, [Validators.required]]
    })
  }

  postFAQ() {
    this.as.insertFQA(this.productId, this.FAQform.value).subscribe(res => {
      if (res.id != null) {

        Swal.fire({
          title: "Added..!",
          text: "FAQ Added Successfully",
          icon: "success"
        });
        this.rt.navigateByUrl('/admin/dashboard');
      }
      else {
        Swal.fire("Something went wrong!");
      }
    })
  }



}
