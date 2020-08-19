import { Injectable } from '@angular/core';
import { CanActivate , Router, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree} from '@angular/router';
import { AuthService } from "../services/auth.service";

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate{

    constructor(
        private data: AuthService,
        private router: Router){

    }
      canActivate():boolean {

        if(this.data.is_logged_in())
        {
          return true;
        }
        //this.router.navigate(['/login']);
        return false;
      }

}
