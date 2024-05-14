import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { PostcategoryComponent } from './component/postcategory/postcategory.component';
import { PostproductComponent } from './component/postproduct/postproduct.component';
import { PostcouponComponent } from './component/postcoupon/postcoupon.component';
import { CouponComponent } from './component/coupon/coupon.component';
import { OrdersComponent } from './component/orders/orders.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'category', component: PostcategoryComponent },
  { path: 'product', component: PostproductComponent },
  { path: 'post-coupon', component: PostcouponComponent},
  { path: 'coupons', component: CouponComponent},
  { path: 'orders', component: OrdersComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
