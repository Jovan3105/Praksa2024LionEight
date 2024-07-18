import { Observable } from 'rxjs';
import { enviroment } from '../../enviroments/enviroment';

export interface IAuthService {
  baseUrl: string;
  register(...params: any[]): Observable<any>;
}
