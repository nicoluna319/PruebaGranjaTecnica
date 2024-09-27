import { AnimalResp, PagedResponse } from './../models/animal-resp.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class AnimalService {

  private baseUrl = 'http://localhost:8080/api/v1/animales';

  constructor(private http: HttpClient) { }

  //Obtener todos los animalillos
  getAllAnimals(): Observable<PagedResponse<AnimalResp>>{
    return this.http.get<PagedResponse<AnimalResp>>(this.baseUrl);
  }
  

  //Crear un animalillo

  createAnimal(animal:any): Observable<AnimalResp>{
    return this.http.post<AnimalResp>(this.baseUrl,animal);
  }

  //Obtener un animalillo por su id

  getAnimalById(id:number): Observable<AnimalResp>{
    return this.http.get<AnimalResp>(`${this.baseUrl}/${id}`);
  }

  getAnimalResumen(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/resumen`);
  }
  
  // Eliminar un animalillo

  deleteAnimal(id:number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`)
  }

  getAnimalsByCorral(corralId: number, page: number, size: number, sortType: string): Observable<PagedResponse<AnimalResp>> {
    return this.http.get<PagedResponse<AnimalResp>>(`${this.baseUrl}/corrales/${corralId}`, {
      params: {
        page: page.toString(),
        size: size.toString(),
        sortType: sortType
      }
    });
  }

  getAverageAgeByCorral(corralId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/corral/${corralId}/promedio-edad`);
  }
  
}
