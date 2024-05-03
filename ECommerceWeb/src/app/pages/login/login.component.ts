import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/Auth/auth.service';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!:FormGroup;
  constructor(
    private fb: FormBuilder,
   private authservice : AuthService ,
   private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm=this.fb.group({
     
      email:[null,[Validators.required]],
      password:[null,[Validators.required]]
    })
  }
  onSubmit(): void{
    const username = this.loginForm.get('email')!.value;
    const password = this.loginForm.get('password')!.value;

    this.authservice.login(username,password).subscribe(
      (res)=>{
        if(UserStorageService.isCustomerLoggedIn())
          {
            this.router.navigateByUrl('customer/dashboard');
          }
          else if(UserStorageService.isAdminLoggedIn)
            {
              this.router.navigateByUrl('admin/dashboard');
            }
      },
      (error)=>{
        Swal.fire("Login Failed Please Try Again.");
      }
    )

  }

}
