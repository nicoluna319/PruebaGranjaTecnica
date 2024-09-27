import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CorralResp } from '../models/corral-resp.model';
import { PagedResponse } from '../models/animal-resp.model';

@Injectable({
  providedIn: 'root'
})
export class CorralService {

  private baseUrl = 'http://localhost:8080/api/v1/corrales';

  constructor(private http: HttpClient) { }

  getAllCorrals(): Observable<PagedResponse<CorralResp>> {
    return this.http.get<PagedResponse<CorralResp>>(this.baseUrl);
  }

  createCorral(corral: any): Observable<CorralResp> {
    return this.http.post<CorralResp>(this.baseUrl, corral);
  }
  
}
