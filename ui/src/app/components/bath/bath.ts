import { Component, inject, signal } from '@angular/core';
import { BathService } from '../../services/bath-service';
import { IBath } from '../../models/ibath';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { UserService } from '../../services/user-service';
import { IUser } from '../../models/iuser';

@Component({
  selector: 'app-bath',
  imports: [ReactiveFormsModule, DatePipe],
  templateUrl: './bath.html',
  styleUrl: './bath.css',
})
export class Bath {
  bathService = inject(BathService);
  userService = inject(UserService);
  baths = signal<Array<IBath>>([]);
  users = signal<Array<IUser>>([]);
  selectedBath = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  isSelected = signal(true);
  bathForm = new FormGroup({
    bathDateTime: new FormControl<string>(''),
    userId: new FormControl<number>(1)
  });
    
  ngOnInit() {
    this.loadBaths();
    this.loadUsers();
  }
  
  findSelectedRow() {
    this.bathService.getBathById(this.selectedBath()!)
                    .subscribe(s => {
                      s.dateTime = s.dateTime.slice(0, 16);
                      this.bathForm.patchValue({
                        bathDateTime: s.dateTime,
                        userId: s.userId
                      });
                    });
  }
  
  toggleCreateForm() {
    this.displayUpdateForm.set(false);
    this.displayDeleteConfirmation.set(false);
    this.bathForm.reset();
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
  
  loadBaths() {
    this.bathService.getAllBaths()
                    .subscribe(s => this.baths.set(s));
  }

  loadUsers() {
    this.userService.getAllUsers()
                    .subscribe(s => this.users.set(s));
  }
  
  create() {
    const bath = {
      'dateTime' : this.bathForm.value.bathDateTime + ':00',
      'userId' : this.bathForm.value.userId
    };
  
    this.bathService.createBath(bath)
                    .subscribe(s => {
                      console.log('Entry created:', s);
                      this.displayCreateForm.set(false);
                      this.loadBaths();
                    });
  }
  
  delete() {
    this.bathService.deleteBath(this.selectedBath()!)
                    .subscribe(s => {
                      console.log('Entry deleted')
                      this.displayDeleteConfirmation.set(false);
                      this.loadBaths();
                    });
  }
  
  update() {
    const bath = {
      'dateTime' : this.bathForm.value.bathDateTime + ':00',
      'userId' : this.bathForm.value.userId
    };
  
    this.bathService.updateBath(this.selectedBath()!, bath)
                    .subscribe(s => {
                      console.log('Entry updated:', s)
                      this.displayUpdateForm.set(false);
                      this.loadBaths();
                    });
  }
}
