import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../service/admin.service';
import { Route, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-postproduct',
  templateUrl: './postproduct.component.html',
  styleUrls: ['./postproduct.component.css']
})
export class PostproductComponent implements OnInit {
  productForm :FormGroup;
  listOfCategories: any=[];
  selectedFile :File | null;
  imagePreview : string | ArrayBuffer | null;
  constructor(private fb:FormBuilder,
    private adminServive:AdminService,
    private router:Router
  ) { }

  onFileSelected(events: any)
  {
    this.selectedFile=events.target.files[0];
    this.previewimage();

  }

  previewimage(){
    const reader=new FileReader();
    reader.onload= () => {
      this.imagePreview=reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  ngOnInit(): void {
    this.productForm=this.fb.group({
      categoryId:[null,[Validators.required]],
      name:[null,[Validators.required]],
      description:[null,[Validators.required]], 
      price:[null,[Validators.required]]
    });


    this.getAllCategories();
  }

  getAllCategories(){
    this.adminServive.getAllCategory().subscribe(res=>{
       this.listOfCategories=res;
    })
  }

  addProduct():void{
    const formData : FormData= new FormData();
    formData.append('img',this.selectedFile);
    formData.append('categoryId',this.productForm.get('categoryId').value);
    formData.append('name',this.productForm.get('name').value);
    formData.append('description',this.productForm.get('description').value);
    formData.append('price',this.productForm.get('price').value);


    this.adminServive.addProduct(formData).subscribe((res)=>{
      if(res.id !=null)
        {
          Swal.fire({
            title: "Product Add Sucessfully..",
            icon: "success"
          });
        }
        else{
          Swal.fire("SomeThing went Wrong");
        }
    });
  }

}
