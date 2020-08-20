import { Component, OnInit } from '@angular/core';
import { Lectura } from 'src/app/models/lectura';
import { environment } from 'src/environments/environment';
import { HttpClientService } from 'src/app/services/http-client.service';

import { Router } from '@angular/router';
@Component({
  selector: 'app-ltr',
  templateUrl: './ltr.component.html',
  styleUrls: ['./ltr.component.css']
})
export class LTRComponent implements OnInit {

  constructor(private http: HttpClientService, private router: Router) { }

  ngOnInit(): void {
    this.getAll();
  }



  //Metodo Log Out
  public logout(): void {
   localStorage.setItem('token','');
   window.localStorage.removeItem('userToken');
   this.router.navigate(['/login']);
  /*   window.location.reload(); */
  }
  //Fin Metodo Log Out

  //Array de Datos
  datos:Lectura[] = [];
  //Fin Array de Datos

  //Array promedio
  promedios:any = [];
  //Fin Array promedio

  //Variables Timer
  timerDelay: number = 7;
  status: string = 'Datos Actualizados';
  timeleft: number = 10;
  interval;
  //Fin Variables Timer

  //Iniciar Refresh Automatico
  startTimer(){
    this.interval = setInterval(() =>{
      if (this.timeleft > 0) {
        this.timeleft--;
        this.status = 'Actualizando Datos';
        if (this.timeleft == 0) {
          window.location.reload()
        }
      }else{
        this.timeleft = 10;
      }
    },1000)
  }
  //Fin del evento

  //Pausar Refresh Manual
  pauseTimer() {
      clearInterval(this.interval);
    }
  //Fin del evento

  //Refresh mediante un Boton
  refreshWindow(){
    this.interval = setInterval(() =>{
      if (this.timerDelay >0) {
        this.timerDelay--;
        this.status = 'Actualizando Datos'
        if (this.timerDelay == 0) {
          window.location.reload()
        }
      }else{this.timerDelay = 7}
    },1000)
  }
  //Fin del evento

  getData(){
    this.http.makeRequest('get', environment.api_url + "/lecture",{
      body: {

      }}).subscribe((data)=>{ this.datos = data });

      return this.datos;
  }

  getAverage(){
    this.http.makeRequest('get', environment.api_url + "/average",{
      body: {

      }}).subscribe((data)=>{ this.promedios = data });

      return this.promedios;
  }

  getAll(){
    this.getData();
    this.getAverage();
  }
}