import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { CartComponent } from './component/cart/cart.component';
import { PlacedOrderComponent } from './component/placed-order/placed-order.component';
import { OrdersComponent } from '../customer/component/orders/orders.component';
import { OrderedProductsComponent } from './component/ordered-products/ordered-products.component';
import { PostReviewComponent } from './component/post-review/post-review.component';


const routes: Routes = [
  { path: '', component: CustomerComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'cart', component: CartComponent },
  { path: 'placed-order', component: PlacedOrderComponent },
  { path: 'orders', component: OrdersComponent },
  { path: 'orderedproduct/:orderId', component: OrderedProductsComponent },
  { path: 'postreview/:productId', component: PostReviewComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
