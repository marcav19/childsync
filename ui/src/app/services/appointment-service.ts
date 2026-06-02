import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IAppointment } from '../models/iappointment';

@Injectable({
  providedIn: 'root',
})
export class AppointmentService {
  http = inject(HttpClient);
  endpoint = 'http://localhost:8000/api/appointments';

  getAppointmentById(id: number) {
    return this.http.get<IAppointment>(this.endpoint + '/' + id);
  }

  getAllAppointments() {
    return this.http.get<Array<IAppointment>>(this.endpoint);
  }

  createAppointment(request: object) {
    return this.http.post<IAppointment>(this.endpoint, request);
  }

  deleteAppointment(id: number) {
    return this.http.delete<IAppointment>(this.endpoint + '/' + id);
  }

  updateAppointment(id: number, request: object) {
    return this.http.put<IAppointment>(this.endpoint + '/' + id, request);
  }
}
