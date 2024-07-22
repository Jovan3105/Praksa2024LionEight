import { Injectable } from '@angular/core';
import { enviroment } from '../../enviroments/enviroment';
import { SkyonicsDTS } from '../models/skyonics-dts';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class SkyonicsService {
  baseUrl: string = enviroment.BACK_END_URL + '/api';

  constructor(private http: HttpClient) {}

  sendDeviceCommand(body: SkyonicsDTS): Observable<any> {
    return this.http.post(this.baseUrl + '/deviceCommand', body, {
      responseType: 'text',
    });
  }
}
