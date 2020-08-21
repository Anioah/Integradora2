import { Component, OnDestroy, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { HttpClientService } from '../../services/http-client.service';
import { AuthService } from '../../services/auth.service'
import { environment } from '../../../environments/environment.prod';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  implements OnInit{
  public email: string = '';
  public password: string = '';

  constructor(public userService: HttpClientService, private router: Router, private auth: AuthService, private _data: AuthService) {}

  ngOnInit():void{
    window.localStorage.removeItem('token');
  }

  password_keydown(e){
    if(e.keyCode == 13){
      e.preventDefault();
      this.log_in();
      return;
    }
  }

  log_in()
  {

    try{
      this.userService.makeRequest('post',environment.api_url + '/login',{
        body:{
            email: this.email,
            password: this.password
          }
        }).
        subscribe(data => {
            localStorage.setItem('token',data.token);
            localStorage.setItem('username',data);
            this.router.navigate(['/home']);
        });
    }
    catch(error){
      console.log(error)
    }
  }

}
