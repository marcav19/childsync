import { Component, inject, signal } from '@angular/core';
import { SleepService } from '../../services/sleep-service';
import { ISleep } from '../../models/isleep';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-sleep',
  imports: [ReactiveFormsModule],
  templateUrl: './sleep.html',
  styleUrl: './sleep.css',
})
export class Sleep {
  sleepService = inject(SleepService);
  sleepData = signal<Array<ISleep>>([]);
  selectedId = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  sleepForm = new FormGroup({
    sleepStart: new FormControl<string>(''),
    sleepEnd: new FormControl<string>(''),
    userId: new FormControl<number>(0)
  });
  
  ngOnInit() {
    this.loadSleep();
  }

  findSelectedRow() {
    this.sleepService.getSleepById(this.selectedId()!)
                     .subscribe(s => {
                        s.start = s.start.slice(0, 16);
                        s.end = s.end.slice(0, 16);
                        this.sleepForm.patchValue({
                          sleepStart: s.start,
                          sleepEnd: s.end,
                          userId : s.userId
                        });
                     });
  }

  toggleCreateForm() {
    this.displayUpdateForm.set(false);
    this.sleepForm.reset();
    this.displayCreateForm.set(true);
  }

  toggleUpdateForm() {
    this.displayCreateForm.set(false);
    this.findSelectedRow();
    this.displayUpdateForm.set(true);
  }

  loadSleep() {
    this.sleepService.getAllSleep()
                     .subscribe((s) => this.sleepData.set(s));
  }

  create() {
    const sleep = {
      'id' : 0,
      'sleep_start' : (this.sleepForm.value.sleepStart!.replace('T', ' ') ?? '') + ':00',
      'sleep_end' : (this.sleepForm.value.sleepEnd!.replace('T', ' ') ?? '') + ':00',
      'user_id' : this.sleepForm.value.userId ?? 0
    };

    this.sleepService.createSleep(sleep)
                     .subscribe(s => {
                       console.log('Entry created:', s);
                       this.displayCreateForm.set(false);
                       this.loadSleep();
                     });
  }

  delete() {
    this.sleepService.deleteSleep(this.selectedId()!)
                     .subscribe(s => {
                       console.log(s, 'deleted')
                       this.loadSleep();
                     });
  }

  update() {
    const sleep = {
      'sleep_start' : (this.sleepForm.value.sleepStart!.replace('T', ' ') ?? '') + ':00',
      'sleep_end' : (this.sleepForm.value.sleepEnd!.replace('T', ' ') ?? '') + ':00',
      'user_id' : this.sleepForm.value.userId ?? 0
    }

    this.sleepService.updateSleep(this.selectedId()!, sleep)
                     .subscribe(s => {
                       console.log('Entry updated:', s)
                       this.displayUpdateForm.set(false);
                       this.loadSleep();
                     });
  }
}
