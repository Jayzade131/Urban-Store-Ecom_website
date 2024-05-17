import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerComponent } from './customer.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';
import { ReactiveFormsModule } from "@angular/forms";
import {MatSelectModule} from '@angular/material/select';
import {MatGridListModule} from '@angular/material/grid-list';
import { CartComponent } from './component/cart/cart.component';
import { PlacedOrderComponent } from './component/placed-order/placed-order.component';
import { OrdersComponent } from './component/orders/orders.component';
import { OrderedProductsComponent } from './component/ordered-products/ordered-products.component';
import { PostReviewComponent } from './component/post-review/post-review.component';
import { ProductdetailComponent } from './component/productdetail/productdetail.component';
import { WishlistComponent } from './component/wishlist/wishlist.component';


@NgModule({
  declarations: [
    CustomerComponent,
    DashboardComponent,
    CartComponent,
    PlacedOrderComponent,
    OrdersComponent,
    OrderedProductsComponent,
    PostReviewComponent,
    ProductdetailComponent,
    WishlistComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    HttpClientModule,
    MatCardModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatGridListModule
  ]
})
export class CustomerModule { }
