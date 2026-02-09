import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { RegisterReq } from '../model/register-req';
import { ApiRes } from '../model/api-res';
import { map, skip } from 'rxjs';
import { LoginReq } from '../model/login-req';

@Injectable({
  providedIn: 'root',
})
export class ChatApi {
  private baseUrl = 'http://localhost:8082/chatApp';

  http = inject(HttpClient);

  register(uri: string, registerReq: RegisterReq) {
    return this.http.post<ApiRes>(this.baseUrl + uri, registerReq, {
      headers: new HttpHeaders({
        'skip': ''
      })
    });
  }

  login(uri: string, loginReq: LoginReq) {
    return this.http.post<ApiRes>(this.baseUrl + uri, loginReq, {
      observe: 'response',
      headers: new HttpHeaders({
        'skip': ''
      })
    });
  }

  logout(uri: string) {
    return this.http.post<ApiRes>(this.baseUrl + uri, null, {
      headers: new HttpHeaders({
        'skip': ''
      })
    });
  }

  getAllMsg(uri: string) {
    return this.http.get<ApiRes>(this.baseUrl + uri).pipe(map(res => res.messageDTOS));
  }
}
