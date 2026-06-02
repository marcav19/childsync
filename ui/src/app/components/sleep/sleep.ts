import { Component, inject, signal } from '@angular/core';
import { SleepService } from '../../services/sleep-service';
import { ISleep } from '../../models/isleep';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { UserService } from '../../services/user-service';
import { IUser } from '../../models/iuser';

@Component({
  selector: 'app-sleep',
  imports: [ReactiveFormsModule, DatePipe],
  templateUrl: './sleep.html',
  styleUrl: './sleep.css',
})
export class Sleep {
  sleepService = inject(SleepService);
  userService = inject(UserService);
  sleeps = signal<Array<ISleep>>([]);
  users = signal<Array<IUser>>([]);
  selectedSleep = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  isSelected = signal(true);
  sleepForm = new FormGroup({
    sleepStart: new FormControl<string>(''),
    sleepEnd: new FormControl<string>(''),
    userId: new FormControl<number>(0)
  });
  
  ngOnInit() {
    this.loadSleeps();
    this.loadUsers();
  }

  findSelectedRow() {
    this.sleepService.getSleepById(this.selectedSleep()!)
                     .subscribe(s => {
                       s.start = s.start.slice(0, 16);
                       s.end = s.end.slice(0, 16);
                       this.sleepForm.patchValue({
                         sleepStart: s.start,
                         sleepEnd: s.end,
                         userId: s.userId
                       });
                     });
  }

  toggleCreateForm() {
    this.displayUpdateForm.set(false);
    this.displayDeleteConfirmation.set(false);
    this.sleepForm.reset();
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

  loadSleeps() {
    this.sleepService.getAllSleeps()
                     .subscribe(s => this.sleeps.set(s));
  }

  loadUsers() {
    this.userService.getAllUsers()
                    .subscribe(s => this.users.set(s));
  }

  create() {
    const sleep = {
      'start' : this.sleepForm.value.sleepStart + ':00',
      'end' : this.sleepForm.value.sleepEnd + ':00',
      'userId' : this.sleepForm.value.userId
    };

    this.sleepService.createSleep(sleep)
                     .subscribe(s => {
                       console.log('Entry created:', s);
                       this.displayCreateForm.set(false);
                       this.loadSleeps();
                     });
  }

  delete() {
    this.sleepService.deleteSleep(this.selectedSleep()!)
                     .subscribe(s => {
                       console.log('Entry deleted')
                       this.displayDeleteConfirmation.set(false);
                       this.loadSleeps();
                     });
  }

  update() {
    const sleep = {
      'start' : this.sleepForm.value.sleepStart + ':00',
      'end' : this.sleepForm.value.sleepEnd + ':00',
      'userId' : this.sleepForm.value.userId
    }

    this.sleepService.updateSleep(this.selectedSleep()!, sleep)
                     .subscribe(s => {
                       console.log('Entry updated:', s)
                       this.displayUpdateForm.set(false);
                       this.loadSleeps();
                     });
  }
}
