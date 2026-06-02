import { Component, inject, signal } from '@angular/core';
import { PottyService } from '../../services/potty-service';
import { IPotty } from '../../models/ipotty';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { UserService } from '../../services/user-service';
import { IUser } from '../../models/iuser';

@Component({
  selector: 'app-potty',
  imports: [ReactiveFormsModule, DatePipe],
  templateUrl: './potty.html',
  styleUrl: './potty.css',
})
export class Potty {
  pottyService = inject(PottyService);
  userService = inject(UserService);
  potties = signal<Array<IPotty>>([]);
  users = signal<Array<IUser>>([]);
  selectedPotty = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  isSelected = signal(true);
  pottyForm = new FormGroup({
    pottyDateTime: new FormControl<string>(''),
    pottyDescription: new FormControl<string>(''),
    userId: new FormControl<number>(1)
  });
  
  ngOnInit() {
    this.loadPotties();
    this.loadUsers();
  }

  findSelectedRow() {
    this.pottyService.getPottyById(this.selectedPotty()!)
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

  loadPotties() {
    this.pottyService.getAllPotties()
                     .subscribe(s => this.potties.set(s));
  }

  loadUsers() {
    this.userService.getAllUsers()
                    .subscribe(s => this.users.set(s));
  }

  create() {
    const potty = {
      'dateTime' : this.pottyForm.value.pottyDateTime + ':00',
      'description' : this.pottyForm.value.pottyDescription,
      'userId' : this.pottyForm.value.userId
    };

    this.pottyService.createPotty(potty)
                     .subscribe(s => {
                       console.log('Entry created:', s);
                       this.displayCreateForm.set(false);
                       this.loadPotties();
                     });
  }

  delete() {
    this.pottyService.deletePotty(this.selectedPotty()!)
                     .subscribe(s => {
                       console.log('Entry deleted')
                       this.displayDeleteConfirmation.set(false);
                       this.loadPotties();
                     });
  }

  update() {
    const potty = {
      'dateTime' : this.pottyForm.value.pottyDateTime + ':00',
      'description' : this.pottyForm.value.pottyDescription,
      'userId' : this.pottyForm.value.userId
    };

    this.pottyService.updatePotty(this.selectedPotty()!, potty)
                     .subscribe(s => {
                       console.log('Entry updated:', s)
                       this.displayUpdateForm.set(false);
                       this.loadPotties();
                     });
  }
}
