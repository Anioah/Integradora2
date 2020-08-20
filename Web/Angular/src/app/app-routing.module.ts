import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { HisLecComponent } from './Components/his-lec/his-lec.component';
import { LTRComponent } from './Components/ltr/ltr.component';
import { HomeComponent } from './Components/home/home.component';
import { AuthGuard } from './guards/auth.guard';
import { tokenAuthGuard } from './guards/tokenAuth.guard';

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
    path:'home',
    component: HomeComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard,tokenAuthGuard]
  },
  {
    path:'his-lec',
    component: HisLecComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard,tokenAuthGuard]
  },
  {
    path:'ltr',
    component: LTRComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard,tokenAuthGuard]
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
