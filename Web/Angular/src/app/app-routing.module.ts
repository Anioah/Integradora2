import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { HisLecComponent } from './Components/his-lec/his-lec.component';

const routes: Routes = [
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'registrar',
    component: RegisterComponent
  },
  {
    path:'his-lec',
    component: HisLecComponent
  },
  {
  path:'**',redirectTo:'/login'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
