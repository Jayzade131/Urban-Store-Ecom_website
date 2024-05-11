import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

const BASIC_URL="http://localhost:8089/";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    private http:HttpClient,

  ) {}


  getAllProduct():Observable<any>{
    return this.http.get(BASIC_URL + 'api/customer/allProducts',{
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllProductByName(name:any):Observable<any>{
    return this.http.get(BASIC_URL + `api/customer/search/${name}`,{
      headers: this.createAuthorizationHeader(),
    })
  }
  addToCart(productId:any):Observable<any>{
   const cartDto = {
      productId :productId,
      userId: UserStorageService.getUserId()
    }
    console.log(cartDto)
    return this.http.post(BASIC_URL + `api/customer/cart`, cartDto, {
      headers: this.createAuthorizationHeader(),
})
  }

  getCartByUserId():Observable<any>{
    const userId=UserStorageService.getUserId()
     
     return this.http.get(BASIC_URL + `api/customer/cart/${userId}`, {
       headers: this.createAuthorizationHeader(),
     })
   }

   applyCoupon(code:any):Observable<any>{
    const userId=UserStorageService.getUserId()
     return this.http.get(BASIC_URL + `api/customer/coupon/${userId}/${code}`, {
       headers: this.createAuthorizationHeader(),
     })
   }
   
   increaseProductQuantity(productId:any):Observable<any>{
    const cartDto = {
       productId :productId,
       userId: UserStorageService.getUserId()
     }
     console.log(cartDto)
     return this.http.post(BASIC_URL + `api/customer/addition`, cartDto, {
       headers: this.createAuthorizationHeader(),
 })
   }

   decrProductQuantity(productId:any):Observable<any>{
    const cartDto = {
       productId :productId,
       userId: UserStorageService.getUserId()
     }
     console.log(cartDto)
     return this.http.post(BASIC_URL + `api/customer/substraction`, cartDto, {
       headers: this.createAuthorizationHeader(),
 })
   }

   placedOrder(orderDto:any):Observable<any>{
    orderDto.userId=UserStorageService.getUserId()
     return this.http.post(BASIC_URL + `api/customer/placedOrder`,orderDto, {
       headers: this.createAuthorizationHeader(),
     })
   }


  private  createAuthorizationHeader(): HttpHeaders{
    return new HttpHeaders().set(
      'Authorization','Bearer ' + UserStorageService.getToken()
    );
  }
}
