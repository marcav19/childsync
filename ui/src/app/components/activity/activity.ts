import { Component, inject, signal } from '@angular/core';
import { ActivityService } from '../../services/activity-service';
import { IActivity } from '../../models/iactivity';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../../services/user-service';
import { DatePipe } from '@angular/common';
import { IUser } from '../../models/iuser';

@Component({
  selector: 'app-activity',
  imports: [ReactiveFormsModule, DatePipe],
  templateUrl: './activity.html',
  styleUrl: './activity.css',
})
export class Activity {
  activityService = inject(ActivityService);
  userService = inject(UserService);
  activities = signal<Array<IActivity>>([]);
  users = signal<Array<IUser>>([]);
  selectedActivity = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  isSelected = signal(true);
  activityForm = new FormGroup({
    activityDateTime: new FormControl<string>(''),
    activityName: new FormControl<string>(''),
    userId: new FormControl<number>(1)
  });
  
  ngOnInit() {
    this.loadActivities();
    this.loadUsers();
  }

  findSelectedRow() {
    this.activityService.getActivityById(this.selectedActivity()!)
                        .subscribe(s => {
                          s.dateTime = s.dateTime.slice(0, 16);
                          this.activityForm.patchValue({
                            activityDateTime: s.dateTime,
                            activityName: s.name,
                            userId: s.userId
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

  loadActivities() {
    this.activityService.getAllActivities()
                        .subscribe(s => this.activities.set(s));
  }

  loadUsers() {
    this.userService.getAllUsers()
                    .subscribe(s => this.users.set(s));
  }

  create() {
    const activity = {
      'dateTime' : this.activityForm.value.activityDateTime + ':00',
      'name' : this.activityForm.value.activityName,
      'userId' : this.activityForm.value.userId
    };

    this.activityService.createActivity(activity)
                        .subscribe(s => {
                          console.log('Entry created:', s);
                          this.displayCreateForm.set(false);
                          this.loadActivities();
                        });
  }

  delete() {
    this.activityService.deleteActivity(this.selectedActivity()!)
                        .subscribe(s => {
                          console.log('Entry deleted');
                          this.displayDeleteConfirmation.set(false);
                          this.loadActivities();
                        });
  }

  update() {
    const activity = {
      'dateTime' : this.activityForm.value.activityDateTime + ':00',
      'name' : this.activityForm.value.activityName,
      'userId' : this.activityForm.value.userId
    };

    this.activityService.updateActivity(this.selectedActivity()!, activity)
                        .subscribe(s => {
                          console.log('Entry updated:', s)
                          this.displayUpdateForm.set(false);
                          this.loadActivities();
                        });
  }
}
