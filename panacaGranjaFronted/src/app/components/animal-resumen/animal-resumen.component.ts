// animal-resumen.component.ts
import { Component, OnInit } from '@angular/core';
import { AnimalService } from 'src/app/services/animal.service';

@Component({
  selector: 'app-animal-resumen',
  templateUrl: './animal-resumen.component.html',
  styleUrls: ['./animal-resumen.component.css']
})
export class AnimalResumenComponent implements OnInit {

  resumenAnimales: any[] = [];

  constructor(private animalService: AnimalService) { }

  ngOnInit(): void {
    this.animalService.getAnimalResumen().subscribe({
      next: (data) => {
        this.resumenAnimales = data;
      },
      error: (err) => console.error(err)
    });
  }
}
