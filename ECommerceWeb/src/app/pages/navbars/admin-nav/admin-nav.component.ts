import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

@Component({
  selector: 'app-admin-nav',
  templateUrl: './admin-nav.component.html',
  styleUrls: ['./admin-nav.component.css']
})
export class AdminNavComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  logOut()
  {
    UserStorageService.signOut();
    this.router.navigateByUrl('login');
  }
}
