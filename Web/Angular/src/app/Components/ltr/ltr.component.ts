import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
@Component({
  selector: 'app-ltr',
  templateUrl: './ltr.component.html',
  styleUrls: ['./ltr.component.css']
})
export class LTRComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  //Metodo Log Out
  public logout(): void {
   localStorage.setItem('token','');
   window.localStorage.removeItem('userToken');
   this.router.navigate(['/login']);
  /*   window.location.reload(); */
  }
  //Fin Metodo Log Out
}
