import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SignInForm} from "../model/sign-in-form";
import {JwtResponse} from "../model/jwt-response";
import {SignUp} from "../model/sign-up-form";
import {Router} from "@angular/router";

const API_PRODUCT = environment.apiProduct;
const API_AUTH = environment.apiAuth;
const TOKEN_KEY = 'Token_Key';
const NAME_KEY = 'Name_Key';
const ROLE_KEY = 'Role_Key';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient:HttpClient,
  private router:Router) { }

  public signUp(signUp:SignUp):Observable<any>{
    return this.httpClient.post<any>(API_AUTH + '/signup', signUp);
  }

  public signIn(signIn:SignInForm):Observable<JwtResponse>{
    return this.httpClient.post<JwtResponse>(API_AUTH + '/signin', signIn);
  }

  public loggined(){
    const token = sessionStorage.getItem(TOKEN_KEY);
    const username = sessionStorage.getItem(NAME_KEY);
    const authority = sessionStorage.getItem(ROLE_KEY);
    if(username && token && authority){
      return true
    }
    return false;
  }

  public login(): void {
    this.router.navigate(['/login']);
  }

}
