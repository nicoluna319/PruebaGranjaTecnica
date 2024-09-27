// restriccion-form.component.ts
import { Component, OnInit } from '@angular/core';
import { RestriccionService } from 'src/app/services/restriccion.service';
import { AnimalService } from 'src/app/services/animal.service';
import { AnimalResp } from 'src/app/models/animal-resp.model';

@Component({
  selector: 'app-restriccion-form',
  templateUrl: './restriccion-form.component.html',
  styleUrls: ['./restriccion-form.component.css']
})
export class RestriccionFormComponent implements OnInit {

  newRestriccion: any = {
    animalId: null,
    animalRestringidoId: null
  };

  animals: AnimalResp[] = [];

  constructor(private restriccionService: RestriccionService, private animalService: AnimalService) { }

  ngOnInit(): void {
    this.animalService.getAllAnimals().subscribe({
      next: (data) => {
        this.animals = data.content; // Recuerda usar 'content' si estás usando paginación
      },
      error: (err) => console.error(err)
    });
  }

  onSubmit(): void {
    this.restriccionService.createRestriccion(this.newRestriccion).subscribe({
      next: (createdRestriccion) => {
        console.log('Restricción creada con éxito', createdRestriccion);
        this.newRestriccion = { animalId: null, animalRestringidoId: null };
      },
      error: (err) => console.error(err)
    });
  }
}
