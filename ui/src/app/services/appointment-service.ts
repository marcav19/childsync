import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IAppointment } from '../models/iappointment';

@Injectable({
  providedIn: 'root',
})
export class AppointmentService {
  http = inject(HttpClient);
  url = 'http://localhost:8000/api/appointments';

  getAppointmentById(id: number) {
    return this.http.get<IAppointment>(this.url + '/' + id);
  }

  getAllAppointment() {
    return this.http.get<Array<IAppointment>>(this.url);
  }

  createAppointment(body: object) {
    return this.http.post<IAppointment>(this.url, body);
  }

  deleteAppointment(id: number) {
    return this.http.delete<IAppointment>(this.url + '/' + id);
  }

  updateAppointment(id: number, body: object) {
    return this.http.patch<IAppointment>(this.url + '/' + id, body);
  }
}
