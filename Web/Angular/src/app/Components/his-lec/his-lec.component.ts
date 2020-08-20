import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';
import { HttpClientService } from 'src/app/services/http-client.service'
import { Lectura } from 'src/app/models/lectura';

@Component({
  selector: 'app-his-lec',
  templateUrl: './his-lec.component.html',
  styleUrls: ['./his-lec.component.css']
})
export class HisLecComponent {

  constructor(private router: Router, private http: HttpClientService) { }

  lectura_clase: Lectura = new Lectura();
  findLectura: Lectura = new Lectura();
  upLectura: Lectura = new Lectura();
  lecturas_array: Lectura [] = [];
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

  mostrarTemp(temp: any){

    if(this.findLectura.temperatura == null){
      alert("Ingresa la temperatura a verificar")
    }else{
      this.http.makeRequest('post', environment.api_url+'/perTemperature',{
        body:{
          temperatura: this.findLectura.temperatura
      }}).subscribe( temperatura => { this.lecturas_array = temperatura } );

      this.component = true;
    }
  }

  mostrarHumedad(hum: any){
    if(this.findLectura.humedad == null){
      alert("Ingresa la humedad a verificar")
    }else{
      this.http.makeRequest('post', environment.api_url+'/perHumedity',{
        body:{
          humedad: this.findLectura.humedad
      }
    }).subscribe( humedad => { this.lecturas_array = humedad } );

      this.component = true;
    }
  }

  deletedata(lectura: Lectura){
    this.http.makeRequest('delete', environment.api_url+'/deleteData',{
      body:{
        _id:lectura._id
    }}).subscribe();

    this.findLectura._id = lectura._id
    window.location.reload();
  }

  back(){
    this.component = true;
    this.detail = true;
  }

}
