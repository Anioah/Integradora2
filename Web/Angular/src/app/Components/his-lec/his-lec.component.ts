import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment.prod';
import { HttpClientService } from 'src/app/services/http-client.service'
import { Lectura } from 'src/app/models/lectura';
import { Fecha } from 'src/app/models/fecha';
import { Btdays } from 'src/app/models/btdays';

@Component({
  selector: 'app-his-lec',
  templateUrl: './his-lec.component.html',
  styleUrls: ['./his-lec.component.css']
})
export class HisLecComponent implements OnInit{

  constructor(private router: Router, private http: HttpClientService) { }

  public page:number;
  lectura_clase: Lectura = new Lectura();
  findLectura: Lectura = new Lectura();
  findBtw: Btdays = new Btdays();
  findDay: Fecha = new Fecha();
  upLectura: Lectura = new Lectura();
  lecturas_array: Lectura [] = [];
  public component;
  public detail = true;

  ngOnInit(){
    this.getData();
  }

  //Metodo Log Out
  public logout(): void {
   localStorage.setItem('token','');
   window.localStorage.removeItem('userToken');
   this.router.navigate(['/login']);
  /*   window.location.reload(); */
  }
  //Fin Metodo Log Out

  getData(){
    this.http.makeRequest('get', environment.api_url + "/showMysql",{
      body: {

      }}).subscribe((data)=>{ this.lecturas_array = data });

      return this.lecturas_array;
  }

  mostrarTemp(temp: any){

    if(this.findLectura.temperatura == null){
      alert("Ingresa la temperatura a verificar")
    }else{
      this.http.makeRequest('post', environment.api_url+'/mysqlPerTemperature',{
        body:{
          temperatura: this.findLectura.temperatura
      }}).subscribe( temperatura => { this.lecturas_array = temperatura } );

      this.component = true;
    }
    this.resetComponents();
    this.resetSearch();
  }

  mostrarHumedad(hum: any){
    if(this.findLectura.humedad == null){
      alert("Ingresa la humedad a verificar")
    }else{
      this.http.makeRequest('post', environment.api_url+'/mysqlPerHumedity',{
        body:{
          humedad: this.findLectura.humedad
      }
    }).subscribe( humedad => { this.lecturas_array = humedad } );

      this.component = true;
      this.resetSearch();
    }
  }

  mostrarDia(date: any){
    if(this.findDay.ano == null || this.findDay.mes == null || this.findDay.dia == null){
      alert("Ingresa la fecha a verificar")
    }else{
      this.http.makeRequest('post', environment.api_url+'/mysqlPerDate',{
        body:{
          year: this.findDay.ano,
          month:this.findDay.mes,
          day:this.findDay.dia
      }
    }).subscribe( fecha => { this.lecturas_array = fecha } );

      this.component = true;
      this.resetSearch();
    }
  }

  mostrarFecha(date: any){
    if(this.findBtw.fecha1 == null || this.findBtw.fecha2 == null){
      alert("Ingresa la fecha a verificar")
    }else{
      this.http.makeRequest('post', environment.api_url+'/mysqlBetweenDates',{
        body:{
          fecha1: this.findBtw.fecha1,
          fecha2: this.findBtw.fecha2
      }
    }).subscribe( fecha => { this.lecturas_array = fecha } );

      this.component = true;
      this.resetSearch();
    }
  }

  deletedata(lectura: Lectura){
    this.http.makeRequest('delete', environment.api_url+'/mysqlDelete',{
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

  showLectura(lectura: Lectura){
    this.upLectura = lectura;
    this.detail = false;
    this.component = true;
  }

  resetComponents(){
    this.component = null;
  }

  resetSearch(){
    this.findLectura.humedad = null,
    this.findLectura.temperatura = null;
    this.findLectura.created_at = null;
    this.findDay.ano = null;
    this.findDay.mes = null;
    this.findDay.dia = null;
    this.findBtw.fecha1 = null;
    this.findBtw.fecha2 = null;
  }

}
