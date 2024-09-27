// animal-form.component.ts
import { Component } from '@angular/core';
import { AnimalService } from 'src/app/services/animal.service';

@Component({
  selector: 'app-animal-form',
  templateUrl: './animal-form.component.html',
  styleUrls: ['./animal-form.component.css']
})
export class AnimalFormComponent {

  newAnimal: any = {
    nombre: '',
    especie: '',
    edad: null,
    peligroso: false,
    corralId: null
  };

  constructor(private animalService: AnimalService) { }

  onSubmit(): void {
    this.animalService.createAnimal(this.newAnimal).subscribe({
      next: (createdAnimal) => {
        console.log('Animal creado con éxito', createdAnimal);
        this.newAnimal = { nombre: '', especie: '', edad: null, peligroso: false, corralId: null };
      },
      error: (err) => console.error(err)
    });
  }
}
