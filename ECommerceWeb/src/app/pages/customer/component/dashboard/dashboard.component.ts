import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  products : any[]=[];
  searchProductForm!:FormGroup;

  constructor(private customerService : CustomerService,
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
    this.customerService.getAllProduct().subscribe(res =>{
      res.forEach(element => {
        element.processedImg ='data:image/jpeg;base64,'+ element.byteimg;
        this.products.push(element);        
      });
    })
  }

  formSubmit(){
    this.products=[];
    const title=this.searchProductForm.get('title')!.value;
    this.customerService.getAllProductByName(title).subscribe(res =>{
      res.forEach(element => {
        element.processedImg ='data:image/jpeg;base64,'+ element.byteimg;
        this.products.push(element);        
      });
      console.log(this.products)
    })
  }

  addcart()
  {
    
  }
}
