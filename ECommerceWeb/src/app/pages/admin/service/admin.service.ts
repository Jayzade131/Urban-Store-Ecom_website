import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

const BASIC_URL = "http://localhost:8089/";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  addCategory(categoryDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/category', categoryDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllCategory(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/categories', {
      headers: this.createAuthorizationHeader(),
    })
  }

  addProduct(porductDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'api/admin/product', porductDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  updateProduct(productId: any, productDto: any): Observable<any> {
    return this.http.put(BASIC_URL + `api/admin/updateProduct/${productId}`, productDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllProduct(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/allProducts', {
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllProductByName(name: any): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/search/${name}`, {
      headers: this.createAuthorizationHeader(),
    })
  }

  deleteProduct(productId: any): Observable<any> {
    return this.http.delete(BASIC_URL + `api/admin/deleteProd/${productId}`, {
      headers: this.createAuthorizationHeader(),
    })
  }

  addCoupon(couponDto: any): Observable<any> {

    return this.http.post(BASIC_URL + `api/admin/createCoupon`, couponDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getCoupon(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/allCoupon', {
      headers: this.createAuthorizationHeader(),
    })
  }

  getPlacedOrders(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/admin/getplacedorder', {
      headers: this.createAuthorizationHeader(),
    })
  }

  changeOrderStatus(orderId: number, status: string): Observable<any> {
    const req = {
      orderId,
      status
    }
    return this.http.post
      (BASIC_URL + 'api/admin/orderStatusChange', req, {
        headers: this.createAuthorizationHeader(),
      })
  }

  insertFQA(productId: number, FAQDto: any): Observable<any> {
    return this.http.post
      (BASIC_URL + `api/admin/faq/${productId}`, FAQDto, {
        headers: this.createAuthorizationHeader(),
      })
  }

  getProductById(productId): Observable<any> {
    return this.http.get(BASIC_URL + `api/admin/getProductById/${productId}`, {
      headers: this.createAuthorizationHeader(),
    })
  }





  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      'Authorization', 'Bearer ' + UserStorageService.getToken()
    );
  }

}
