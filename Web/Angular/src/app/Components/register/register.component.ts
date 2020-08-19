import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../../services/http-client.service';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username: string;
  email: string;
  password: string;
  passwordError: boolean;

  constructor(private userService: HttpClientService, private router: Router) {}

  ngOnInit(): void{}

  register():void {
    this.userService.makeRequest('post',environment.api_url+'/register',{
      body:{
        username: this.username,
        email: this.email,
        password: this.password,
        passwordError: this.passwordError
      }
    }).subscribe(
      body => this.router.navigate(['/login'])
    );
  }
}
