  import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/Auth/auth.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  SignupForm!: FormGroup;

  constructor(
   private fb: FormBuilder,
   private authservice : AuthService ,
   private router: Router
  ) {}

  

  ngOnInit(): void {
this.SignupForm=this.fb.group({
  name: [null,[Validators.required]],
  email:[null,[Validators.required, Validators.email]],
  password:[null,[Validators.required]]
})
}

onSubmit(): void{
  console.log(this.SignupForm.value)
  this.authservice.register(this.SignupForm.value).subscribe(
    (response) =>
    {
      Swal.fire({
        title: "Signup Successfull",
       
        icon: "success"
      });
      this.router.navigateByUrl("/login");
    },
    (error)=>{
      Swal.fire("Signup Failed Please Try Again.");
    }

  )
}


 
}
