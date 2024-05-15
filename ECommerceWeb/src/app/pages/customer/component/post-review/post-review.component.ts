import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';
import Swal from 'sweetalert2';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-post-review',
  templateUrl: './post-review.component.html',
  styleUrls: ['./post-review.component.css']
})
export class PostReviewComponent implements OnInit {

  productId: any = this.actrt.snapshot.params['productId'];
  reviewFrom !: FormGroup;
  selectedFile: File | null;
  imagepreview: string | ArrayBuffer | null;
  constructor(
    private cs: CustomerService,
    private actrt: ActivatedRoute,
    private rt: Router,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.reviewFrom = this.fb.group({
      rating: [null, [Validators.required]],
      description: [null, [Validators.required]]
    })
  }

  onFileSelected(events: any) {
    this.selectedFile = events.target.files[0];
    this.previewimage();

  }

  previewimage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagepreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  submitForm() {
    const formData: FormData = new FormData();
    formData.append('img', this.selectedFile);
    formData.append('productId', this.productId.toString());
    formData.append('userId', UserStorageService.getUserId().toString());
    formData.append('description', this.reviewFrom.get('description').value);
    formData.append('rating', this.reviewFrom.get('rating').value);
    this.cs.addReview(formData).subscribe(res => {
      if (res.id != null) {
        Swal.fire({
          title: "Review Added",
          text: "You clicked the button!",
          icon: "success"
        });
        this.rt.navigateByUrl('/customer/orders');
      }
      else {
        Swal.fire("Something went wrong");
      }
    })
  }

}
