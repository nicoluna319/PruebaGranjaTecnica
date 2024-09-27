// corral-select.component.ts
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { CorralService } from 'src/app/services/corral.service';
import { CorralResp } from 'src/app/models/corral-resp.model';

@Component({
  selector: 'app-corral-select',
  templateUrl: './corral-select.component.html',
  styleUrls: ['./corral-select.component.css']
})
export class CorralSelectComponent implements OnInit {
  corrals: CorralResp[] = [];
  selectedCorralId: number | undefined = undefined;

  @Output() corralChange = new EventEmitter<number>();

  constructor(private corralService: CorralService) {}

  ngOnInit(): void {
    this.corralService.getAllCorrals().subscribe({
      next: (data) => {
        this.corrals = data.content;
      },
      error: (err) => console.error(err)
    });
  }

  onSelectCorral(): void {
    this.corralChange.emit(this.selectedCorralId);
  }
}
