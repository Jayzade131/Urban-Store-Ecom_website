import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

@Component({
  selector: 'app-customer-nav',
  templateUrl: './customer-nav.component.html',
  styleUrls: ['./customer-nav.component.css']
})
export class CustomerNavComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  logOut()
  {
    UserStorageService.signOut();
    this.router.navigateByUrl('login');
  }
}
