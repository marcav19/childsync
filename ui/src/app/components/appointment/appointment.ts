import { Component, inject, signal } from '@angular/core';
import { AppointmentService } from '../../services/appointment-service';
import { IAppointment } from '../../models/iappointment';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { UserService } from '../../services/user-service';
import { IUser } from '../../models/iuser';

@Component({
  selector: 'app-appointment',
  imports: [ReactiveFormsModule, DatePipe],
  templateUrl: './appointment.html',
  styleUrl: './appointment.css',
})
export class Appointment {
  appointmentService = inject(AppointmentService);
  userService = inject(UserService);
  appointments = signal<Array<IAppointment>>([]);
  users = signal<Array<IUser>>([]);
  selectedAppointment = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  isSelected = signal(true);
  appointmentForm = new FormGroup({
    appointmentDateTime: new FormControl<string>(''),
    appointmentReason: new FormControl<string>(''),
    appointmentResult: new FormControl<string>(''),
    userId: new FormControl<number>(1)
  });
  
  ngOnInit() {
    this.loadAppointments();
    this.loadUsers();
  }

  findSelectedRow() {
    this.appointmentService.getAppointmentById(this.selectedAppointment()!)
                           .subscribe(s => {
                             s.dateTime = s.dateTime.slice(0, 16);
                             this.appointmentForm.patchValue({
                               appointmentDateTime: s.dateTime,
                               appointmentReason: s.reason,
                               appointmentResult: s.result,
                               userId: s.userId
                             });
                           });
  }

  toggleCreateForm() {
    this.displayUpdateForm.set(false);
    this.displayDeleteConfirmation.set(false);
    this.appointmentForm.reset();
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

  loadAppointments() {
    this.appointmentService.getAllAppointments()
                     .subscribe(s => this.appointments.set(s));
  }

  loadUsers() {
    this.userService.getAllUsers()
                    .subscribe(s => this.users.set(s));
  }

  create() {
    const appointment = {
      'dateTime' : this.appointmentForm.value.appointmentDateTime + ':00',
      'reason' : this.appointmentForm.value.appointmentReason,
      'result' : this.appointmentForm.value.appointmentResult,
      'userId' : this.appointmentForm.value.userId
    };

    this.appointmentService.createAppointment(appointment)
                           .subscribe(s => {
                             console.log('Entry created:', s);
                             this.displayCreateForm.set(false);
                             this.loadAppointments();
                           });
  }

  delete() {
    this.appointmentService.deleteAppointment(this.selectedAppointment()!)
                     .subscribe(s => {
                       console.log('Entry deleted')
                       this.displayDeleteConfirmation.set(false);
                       this.loadAppointments();
                     });
  }

  update() {
    const appointment = {
      'dateTime' : this.appointmentForm.value.appointmentDateTime + ':00',
      'reason' : this.appointmentForm.value.appointmentReason,
      'result' : this.appointmentForm.value.appointmentResult,
      'userId' : this.appointmentForm.value.userId
    };

    this.appointmentService.updateAppointment(this.selectedAppointment()!, appointment)
                           .subscribe(s => {
                             console.log('Entry updated:', s)
                             this.displayUpdateForm.set(false);
                             this.loadAppointments();
                           });
  }
}
