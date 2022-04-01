import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductComponent} from "./product/product.component";
import {RegisterComponent} from "./login/register/register.component";
import {LoginComponent} from "./login/login/login.component";
import {UserAccountComponent} from "./login/user-account/user-account.component";
import {HelloworldComponent} from "./helloworld/helloworld.component";
import {GuardGuard} from "./service/guard.guard";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {
    path:'product', component: ProductComponent,  canActivate: [GuardGuard]
  },
  {
    path:'signup', component: RegisterComponent
  },
  {
    path:'login', component: LoginComponent
  },
  {
    path:'user-account', component: UserAccountComponent, canActivate: [GuardGuard]
  },
  {
    path:'helloWorld', component: HelloworldComponent, canActivate: [GuardGuard]
  },
  {
    path:'home', component: HomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
