import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from "./auth.service";
import {TokenService} from "./token.service";

@Injectable({
  providedIn: 'root'
})
export class GuardGuard implements CanActivate {
  constructor(private authService: AuthService,
              private router: Router,
              private token: TokenService) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree | any{
    if(this.token.getToken()){
      console.log(this.token.getToken());
      return true;
    } else {
      console.log('khong dang nhap')
      console.log('vao else khong');
      this.authService.login()
    }



  }


}
