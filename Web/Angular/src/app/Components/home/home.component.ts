import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../../services/http-client.service';
import { AuthService } from '../../services/auth.service'
import { Router } from '@angular/router';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  public logout(): void {
   localStorage.setItem('token','');
   window.localStorage.removeItem('userToken');
   this.router.navigate(['/login']);
  /*   window.location.reload(); */
  }

}
