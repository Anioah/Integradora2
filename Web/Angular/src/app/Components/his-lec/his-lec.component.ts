import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';
import { AuthService } from 'src/app/services/auth.service';
import { HttpClientService } from 'src/app/services/http-client.service'
import { Lectura } from 'src/app/models/lectura';

@Component({
  selector: 'app-his-lec',
  templateUrl: './his-lec.component.html',
  styleUrls: ['./his-lec.component.css']
})
export class HisLecComponent {

  constructor(private router: Router, private http: HttpClientService) { }

   lecturas_model: Lectura = new Lectura();

  public component;
  public detail = true;
  //Metodo Log Out
  public logout(): void {
   localStorage.setItem('token','');
   window.localStorage.removeItem('userToken');
   this.router.navigate(['/login']);
  /*   window.location.reload(); */
  }
  //Fin Metodo Log Out



}
