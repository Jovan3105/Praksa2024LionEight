import { Inject, Injectable } from '@angular/core';
import { IAuthService } from './IAuthService';
import { Observable } from 'rxjs';
import { enviroment } from '../../enviroments/enviroment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegisterUserDTS } from '../models/register-user-dts';
import { LoginUserDTS } from '../models/login-user-dts';
import { DOCUMENT } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class AuthService implements IAuthService {
  baseUrl: string = enviroment.BACK_END_URL;
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

  setLogin(obj: any): void {
    this.localStorage?.setItem('User', JSON.stringify(obj));
  }

  isLoggedIn(): boolean {
    const user = this.localStorage ? this.localStorage.getItem('User') : null;
    if (user == null) return false;
    return true;
  }

  logout(): void {
    this.localStorage?.removeItem('User');
  }
}
