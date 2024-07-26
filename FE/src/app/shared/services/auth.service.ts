import { Inject, Injectable } from '@angular/core';
import { IAuthService } from './IAuthService';
import { Observable } from 'rxjs';
import { enviroment } from '../../enviroments/enviroment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegisterUserDTS } from '../models/register-user-dts';
import { LoginUserDTS } from '../models/login-user-dts';
import { DOCUMENT } from '@angular/common';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService implements IAuthService {
  baseUrl: string = enviroment.BACK_END_URL + '/auth';
  localStorage?: Storage;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private http: HttpClient
  ) {
    this.localStorage = this.document.defaultView?.localStorage;
  }

  register(body: RegisterUserDTS): Observable<any> {
    return this.http.post(this.baseUrl + '/register', body, {
      responseType: 'text',
    });
  }

  login(body: LoginUserDTS): Observable<any> {
    return this.http.post(this.baseUrl + '/login', body, {
      responseType: 'text',
    });
  }

  logoutRequest(): Observable<any> {
    return this.http.get('http://localhost:8080/api/logout');
  }

  setLogin(obj: any): void {
    this.localStorage?.setItem('JWT', obj);
    this.localStorage?.setItem('User', JSON.stringify(jwtDecode(obj)));
  }

  isLoggedIn(): boolean {
    const JWT = this.localStorage ? this.localStorage.getItem('JWT') : null;
    if (JWT == null) return false;

    if (this.isJwtExpired(JWT)) {
      this.logout();
      this.logoutRequest();
      return false;
    }

    return true;
  }

  isJwtExpired(jwt: string) {
    const time = JSON.parse(atob(jwt.split('.')[1])).exp;
    return Math.floor(new Date().getTime() / 1000) >= time;
  }

  logout(): void {
    this.localStorage?.removeItem('User');
    this.localStorage?.removeItem('JWT');
  }
}
