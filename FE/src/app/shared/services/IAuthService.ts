import { Observable } from 'rxjs';
import { enviroment } from '../../enviroments/enviroment';

export interface IAuthService {
  baseUrl: string;
  register(...params: any[]): Observable<any>;
  login(...params: any[]): Observable<any>;
  setLogin(...params: any[]): void;
  isLoggedIn(): boolean;
  logout(): void;
}
