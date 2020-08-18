import { Injectable } from '@angular/core';
import { CanActivate , Router, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree} from '@angular/router';
import { TokenAuthService } from "../services/token-auth.service"


@Injectable({
    providedIn: 'root'
})
export class tokenAuthGuard implements CanActivate{

    constructor(
        private data: TokenAuthService,
        private router: Router){
    }

    canActivate(): boolean{

        if(this.data.validation())
        {
            return true
        } 
            this.router.navigate(['/login']);
            return false;

    }

    

}