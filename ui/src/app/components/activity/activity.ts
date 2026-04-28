import { Component, inject, signal } from '@angular/core';
import { ActivityService } from '../../services/activity-service';
import { IActivity } from '../../models/iactivity';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-activity',
  imports: [ReactiveFormsModule],
  templateUrl: './activity.html',
  styleUrl: './activity.css',
})
export class Activity {
  activityService = inject(ActivityService);
  activityData = signal<Array<IActivity>>([]);
  selectedId = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  activityForm = new FormGroup({
    activityDateTime: new FormControl<string>(''),
    activityName: new FormControl<string>(''),
    userId: new FormControl<number>(1)
  });
  
  ngOnInit() {
    this.loadActivity();
  }

  findSelectedRow() {
    this.activityService.getActivityById(this.selectedId()!)
                     .subscribe(s => {
                        s.datetime = s.datetime.slice(0, 16);
                        this.activityForm.patchValue({
                          activityDateTime: s.datetime,
                          activityName: s.name,
                          userId: s.userid
                        });
                     });
  }

  toggleCreateForm() {
    this.displayUpdateForm.set(false);
    this.displayDeleteConfirmation.set(false);
    this.activityForm.reset();
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

  loadActivity() {
    this.activityService.getAllActivity()
                     .subscribe((s) => this.activityData.set(s));
  }

  create() {
    const activity = {
      'datetime' : this.activityForm.value.activityDateTime + ':00',
      'name' : this.activityForm.value.activityName,
      'userid' : this.activityForm.value.userId
    };

    this.activityService.createActivity(activity)
                     .subscribe(s => {
                       console.log('Entry created:', s);
                       this.displayCreateForm.set(false);
                       this.loadActivity();
                     });
  }

  delete() {
    this.activityService.deleteActivity(this.selectedId()!)
                     .subscribe(s => {
                       console.log('deleted');
                       this.displayDeleteConfirmation.set(false);
                       this.loadActivity();
                     });
  }

  update() {
    const activity = {
      'datetime' : this.activityForm.value.activityDateTime + ':00',
      'name' : this.activityForm.value.activityName,
      'userid' : this.activityForm.value.userId
    };

    this.activityService.updateActivity(this.selectedId()!, activity)
                     .subscribe(s => {
                       console.log('Entry updated:', s)
                       this.displayUpdateForm.set(false);
                       this.loadActivity();
                     });
  }
}
