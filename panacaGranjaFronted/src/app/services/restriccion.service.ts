import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RestriccionResp } from '../models/restriccion-resp.model';

@Injectable({
  providedIn: 'root'
})
export class RestriccionService {

  private baseUrl = 'http://localhost:8080/api/v1/restricciones';

  constructor(private http: HttpClient) { }

  // Método para crear una nueva restricción
  createRestriccion(restriccion: any): Observable<RestriccionResp> {
    return this.http.post<RestriccionResp>(this.baseUrl, restriccion);
  }

  // Método para obtener todas las restricciones
  getAllRestricciones(): Observable<RestriccionResp[]> {
    return this.http.get<RestriccionResp[]>(this.baseUrl);
  }
  
}
