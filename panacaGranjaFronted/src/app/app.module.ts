import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnimalListComponent } from './components/animal-list/animal-list.component';
import { FormsModule } from '@angular/forms';
import { AnimalFormComponent } from './components/animal-form/animal-form.component';
import { CorralFormComponent } from './components/corral-form/corral-form.component';
import { RestriccionFormComponent } from './components/restriccion-form/restriccion-form.component';
import { AnimalResumenComponent } from './components/animal-resumen/animal-resumen.component';
import { CorralSelectComponent } from './components/corral-select/corral-select.component';

@NgModule({
  declarations: [
    AppComponent,
    AnimalListComponent,
    AnimalFormComponent,
    CorralFormComponent,
    RestriccionFormComponent,
    AnimalResumenComponent,
    CorralSelectComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
