import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


interface Corral{
  id: number;
  nombre:string;
  capacidad:number;
}

interface Animal{
  id: number;
  nombre: string;
  edad: number;
  peligroso: boolean;

}

interface ResumenAnimal {
  corral: string;
  animales: Animal[];
  numeroPeligrosos: number;
}

@Injectable({
  providedIn: 'root'
})
export class CorralService {

  private apiUrl = 'http://localhost:8080/corrales';

  constructor(private http: HttpClient) { }
}
  //////////////////Aqui quede//////////////////////