// animal-list.component.ts
import { Component, OnInit } from '@angular/core';
import { AnimalService } from 'src/app/services/animal.service';
import { AnimalResp } from 'src/app/models/animal-resp.model';

@Component({
  selector: 'app-animal-list',
  templateUrl: './animal-list.component.html',
  styleUrls: ['./animal-list.component.css']
})
export class AnimalListComponent implements OnInit {

  animals: AnimalResp[] = [];
  selectedCorralId: number | null = null;
  averageAge: number | null = null;

  constructor(private animalService: AnimalService) { }

  ngOnInit(): void {
    // Inicialmente sin animales hasta que se seleccione un corral
  }

  onCorralChange(corralId: number): void {
    this.selectedCorralId = corralId;

    // Obtener animales por corral con paginación
    this.animalService.getAnimalsByCorral(this.selectedCorralId, 0, 10, 'ASC').subscribe({
      next: (data) => {
        this.animals = data.content; // Asegúrate de manejar la paginación correctamente
      },
      error: (err) => console.error(err)
    });

    // Obtener el promedio de edad de los animales en el corral
    this.animalService.getAverageAgeByCorral(this.selectedCorralId).subscribe({
      next: (data) => {
        this.averageAge = data;
      },
      error: (err) => {
        console.error(err);
        this.averageAge = null; // Reiniciar en caso de error
      }
    });
  }
}
