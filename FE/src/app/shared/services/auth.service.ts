import { Injectable } from '@angular/core';
import { IAuthService } from './IAuthService';
import { Observable } from 'rxjs';
import { enviroment } from '../../enviroments/enviroment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegisterUserDTS } from '../models/register-user-dts';
import { LoginUserDTS } from '../models/login-user-dts';

@Injectable({
  providedIn: 'root',
})
export class AuthService implements IAuthService {
  baseUrl: string = enviroment.BACK_END_URL;

  constructor(private http: HttpClient) {}

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
    localStorage.setItem('User', JSON.stringify(obj));
  }

  isLoggedIn(): boolean {
    const user = localStorage ? localStorage.getItem('User') : null;
    if (user == null) return false;
    return true;
  }

  logout(): void {
    localStorage.removeItem('User');
  }
}
