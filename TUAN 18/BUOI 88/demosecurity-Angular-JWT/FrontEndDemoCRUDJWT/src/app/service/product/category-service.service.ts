import { Injectable } from '@angular/core';

import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Category} from "../../model/category";



const API_URL = `${environment.apiProduct}`;
@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {

  constructor(private httpClient:HttpClient) { }

  public getAllCategory():Observable<Category[]> {
    return this.httpClient.get<Category[]>(API_URL + '/api/products/category' )
  }


}
