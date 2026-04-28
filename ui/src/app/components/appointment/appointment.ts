import { Component, inject, signal } from '@angular/core';
import { AppointmentService } from '../../services/appointment-service';
import { IAppointment } from '../../models/iappointment';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-appointment',
  imports: [ReactiveFormsModule],
  templateUrl: './appointment.html',
  styleUrl: './appointment.css',
})
export class Appointment {
  appointmentService = inject(AppointmentService);
  appointmentData = signal<Array<IAppointment>>([]);
  selectedId = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  appointmentForm = new FormGroup({
    appointmentDateTime: new FormControl<string>(''),
    appointmentReason: new FormControl<string>(''),
    appointmentResult: new FormControl<string>(''),
    userId: new FormControl<number>(0)
  });
  
  ngOnInit() {
    this.loadAppointment();
  }

  findSelectedRow() {
    this.appointmentService.getAppointmentById(this.selectedId()!)
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

  loadAppointment() {
    this.appointmentService.getAllAppointment()
                     .subscribe((s) => this.appointmentData.set(s));
  }

  create() {
    const appointment = {
      'appointment_datetime' : (this.appointmentForm.value.appointmentDateTime!.replace('T', ' ') ?? '') + ':00',
      'appointment_reason' : this.appointmentForm.value.appointmentReason,
      'appointment_result' : this.appointmentForm.value.appointmentResult,
      'user_id' : this.appointmentForm.value.userId ?? 0
    };

    this.appointmentService.createAppointment(appointment)
                     .subscribe(s => {
                       console.log('Entry created:', s);
                       this.displayCreateForm.set(false);
                       this.loadAppointment();
                     });
  }

  delete() {
    this.appointmentService.deleteAppointment(this.selectedId()!)
                     .subscribe(s => {
                       console.log(s, 'deleted')
                       this.loadAppointment();
                     });
  }

  update() {
    const appointment = {
      'appointment_datetime' : (this.appointmentForm.value.appointmentDateTime!.replace('T', ' ') ?? '') + ':00',
      'appointment_reason' : this.appointmentForm.value.appointmentReason,
      'appointment_result' : this.appointmentForm.value.appointmentResult,
      'user_id' : this.appointmentForm.value.userId ?? 0
    };

    this.appointmentService.updateAppointment(this.selectedId()!, appointment)
                     .subscribe(s => {
                       console.log('Entry updated:', s)
                       this.displayUpdateForm.set(false);
                       this.loadAppointment();
                     });
  }
}
