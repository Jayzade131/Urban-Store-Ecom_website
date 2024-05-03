import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  products : any[]=[];
  searchProductForm!:FormGroup;
  
  constructor( private adminService: AdminService,
   private fb :FormBuilder
  ) { }

  ngOnInit(): void {
    this.getAllProducts();
    this.searchProductForm = this.fb.group({
      title :[null,[Validators.required]]
    })
  }
  getAllProducts()
  {
    this.products=[];
    this.adminService.getAllProduct().subscribe(res =>{
      res.forEach(element => {
        element.processedImg ='data:image/jpeg;base64,'+ element.byteimg;
        this.products.push(element);        
      });
    })
  }

  formSubmit(){
    this.products=[];
    const title=this.searchProductForm.get('title')!.value;
    this.adminService.getAllProductByName(title).subscribe(res =>{
      res.forEach(element => {
        element.processedImg ='data:image/jpeg;base64,'+ element.byteimg;
        this.products.push(element);        
      });
      console.log(this.products)
    })
  }

  deleteProduct(productId:any)
  {
    this.adminService.deleteProduct(productId).subscribe(res=>{
      if(res.body==null)
        {
          Swal.fire({
            title: "Product Delete Successfully",
            text: "You clicked the button!",
            icon: "success"
          });
          this.getAllProducts();
        }
        else{
          Swal.fire("Something went wrong");
        }
    })
  }
}
