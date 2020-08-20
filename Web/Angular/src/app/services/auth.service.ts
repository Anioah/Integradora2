import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  is_logged_in():boolean{
      return localStorage.getItem('token') !== null;
  }
}
