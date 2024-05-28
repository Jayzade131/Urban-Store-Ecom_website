import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "signup", component: SignupComponent },
  { path: 'admin', loadChildren: () => import('./pages/admin/admin.module').then(m => m.AdminModule) }, { path: 'customer', loadChildren: () => import('./pages/customer/customer.module').then(m => m.CustomerModule) },
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
