import { Component, inject, signal } from '@angular/core';
import { PottyService } from '../../services/potty-service';
import { IPotty } from '../../models/ipotty';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-potty',
  imports: [ReactiveFormsModule],
  templateUrl: './potty.html',
  styleUrl: './potty.css',
})
export class Potty {
  pottyService = inject(PottyService);
  pottyData = signal<Array<IPotty>>([]);
  selectedId = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  pottyForm = new FormGroup({
    pottyDateTime: new FormControl<string>(''),
    pottyDescription: new FormControl<string>(''),
    userId: new FormControl<number>(0)
  });
  
  ngOnInit() {
    this.loadPotty();
  }

  findSelectedRow() {
    this.pottyService.getPottyById(this.selectedId()!)
                     .subscribe(s => {
                        s.dateTime = s.dateTime.slice(0, 16);
                        this.pottyForm.patchValue({
                          pottyDateTime: s.dateTime,
                          pottyDescription: s.description,
                          userId: s.userId
                        });
                     });
  }

  toggleCreateForm() {
    this.displayUpdateForm.set(false);
    this.displayDeleteConfirmation.set(false);
    this.pottyForm.reset();
    this.displayCreateForm.set(true);
  }

  toggleUpdateForm() {
    this.displayCreateForm.set(false);
    this.displayDeleteConfirmation.set(false);
    this.findSelectedRow();
    this.displayUpdateForm.set(true);
  }

  toggleDeleteConfirmation() {
    this.displayCreateForm.set(false);
    this.displayUpdateForm.set(false);
    this.displayDeleteConfirmation.set(true);
  }

  loadPotty() {
    this.pottyService.getAllPotty()
                     .subscribe((s) => this.pottyData.set(s));
  }

  create() {
    const potty = {
      'potty_datetime' : (this.pottyForm.value.pottyDateTime!.replace('T', ' ') ?? '') + ':00',
      'potty_description' : this.pottyForm.value.pottyDescription,
      'user_id' : this.pottyForm.value.userId ?? 0
    };

    this.pottyService.createPotty(potty)
                     .subscribe(s => {
                       console.log('Entry created:', s);
                       this.displayCreateForm.set(false);
                       this.loadPotty();
                     });
  }

  delete() {
    this.pottyService.deletePotty(this.selectedId()!)
                     .subscribe(s => {
                       console.log(s, 'deleted')
                       this.loadPotty();
                     });
  }

  update() {
    const potty = {
      'potty_datetime' : (this.pottyForm.value.pottyDateTime!.replace('T', ' ') ?? '') + ':00',
      'potty_description' : this.pottyForm.value.pottyDescription,
      'user_id' : this.pottyForm.value.userId ?? 0
    };

    this.pottyService.updatePotty(this.selectedId()!, potty)
                     .subscribe(s => {
                       console.log('Entry updated:', s)
                       this.displayUpdateForm.set(false);
                       this.loadPotty();
                     });
  }
}
