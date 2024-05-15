import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-updateproduct',
  templateUrl: './updateproduct.component.html',
  styleUrls: ['./updateproduct.component.css']
})
export class UpdateproductComponent implements OnInit {

  productId: number = this.actrt.snapshot.params['productId'];
  productForm: FormGroup;
  listOfCategories: any = [];
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  existingimg: string | null = null;
  imgChange = false;


  constructor(private fb: FormBuilder,
    private adminServive: AdminService,
    private router: Router,
    private actrt: ActivatedRoute,
  ) { }

  onFileSelected(events: any) {
    this.selectedFile = events.target.files[0];
    this.previewimage();
    this.imgChange = true;
    this.existingimg = null;

  }

  previewimage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  ngOnInit(): void {
    this.productForm = this.fb.group({
      categoryId: [null, [Validators.required]],
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
      price: [null, [Validators.required]]
    });


    this.getAllCategories();
    this.getproductById();
  }

  getAllCategories() {
    this.adminServive.getAllCategory().subscribe(res => {
      this.listOfCategories = res;
    })
  }



  getproductById() {
    this.adminServive.getProductById(this.productId).subscribe(res => {
      this.productForm.patchValue(res);
      this.existingimg = 'data:image/jpeg;base64,' + res.byteimg;
    })
  }

  updateProduct(): void {
    const formData: FormData = new FormData();

    if (this.imgChange && this.selectedFile) {
      formData.append('img', this.selectedFile);
    }


    formData.append('categoryId', this.productForm.get('categoryId').value);
    formData.append('name', this.productForm.get('name').value);
    formData.append('description', this.productForm.get('description').value);
    formData.append('price', this.productForm.get('price').value);


    this.adminServive.updateProduct(this.productId, formData).subscribe((res) => {
      if (res.id != null) {
        Swal.fire({
          title: "Product Updated Sucessfully..",
          icon: "success"
        });
        this.router.navigateByUrl("/admin/dashboard");
      }
      else {
        Swal.fire("SomeThing went Wrong");
      }
    });
  }

}
