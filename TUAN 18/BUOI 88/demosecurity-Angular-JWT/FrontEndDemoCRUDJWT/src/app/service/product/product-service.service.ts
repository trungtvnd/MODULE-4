import { Injectable } from '@angular/core';

import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../../model/product";
import {environment} from "../../../environments/environment";



const API_URL = `${environment.apiProduct}`;

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {
  private httpOption = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(private httpClient:HttpClient) { }

  public getAllProduct():Observable<Product[]> {

    return this.httpClient.get<Product[]>(API_URL + '/api/products')
  }

  getProductById(id:any):Observable<Product>{
    return this.httpClient.get<Product>(API_URL + '/api/products/' + id )
  }

  createProduct(product:Product):Observable<Product>{
    return this.httpClient.post(API_URL + '/api/products', product)
  }

  deleteProduct(id:any):Observable<Product>{
    return this.httpClient.delete(API_URL + '/api/products/'+ id )
  }

  putProduct(data:any, id: any){
    return this.httpClient.put(API_URL + '/api/products/' + id, data, this.httpOption )
  }



}
