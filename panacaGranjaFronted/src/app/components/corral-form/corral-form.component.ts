// corral-form.component.ts
import { Component } from '@angular/core';
import { CorralService } from 'src/app/services/corral.service';

@Component({
  selector: 'app-corral-form',
  templateUrl: './corral-form.component.html',
  styleUrls: ['./corral-form.component.css']
})
export class CorralFormComponent {

  newCorral: any = {
    nombre: '',
    capacidad: null
  };

  constructor(private corralService: CorralService) { }

  onSubmit(): void {
    this.corralService.createCorral(this.newCorral).subscribe({
      next: (createdCorral) => {
        console.log('Corral creado con éxito', createdCorral);
        this.newCorral = { nombre: '', capacidad: null };
      },
      error: (err) => console.error(err)
    });
  }
}
