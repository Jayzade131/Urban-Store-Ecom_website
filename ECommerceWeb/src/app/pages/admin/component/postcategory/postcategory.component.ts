import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {  Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-postcategory',
  templateUrl: './postcategory.component.html',
  styleUrls: ['./postcategory.component.css']
})
export class PostcategoryComponent implements OnInit {
  categoryForm!: FormGroup;

  constructor(private fb:FormBuilder,
   private adminServive:AdminService,
   private router: Router
  ) { }

  ngOnInit(): void {

    this.categoryForm =this.fb.group({
      name:[null,[Validators.required]],
      description:[null,[Validators.required]]
    })
  }

  addCategory():void{
    this.adminServive.addCategory(this.categoryForm.value).subscribe(
      (res) =>{
        if(res.id !=null)
          {
            Swal.fire({
              title: "Category Add Sucessfully..!",
              icon: "success"
            });
            this.router.navigateByUrl('/admin/dashboard');
          }
          else{
            Swal.fire("Something Wrong Please RETRY...");
          }
          
      }
    )
  }

}
