import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IActivity } from '../models/iactivity';

@Injectable({
  providedIn: 'root',
})
export class ActivityService {
  http = inject(HttpClient);
  endpoint = 'http://localhost:8000/api/activities';

  getActivityById(id: number) {
    return this.http.get<IActivity>(this.endpoint + '/' + id);
  }

  getAllActivities() {
    return this.http.get<Array<IActivity>>(this.endpoint);
  }

  createActivity(request: object) {
    return this.http.post<IActivity>(this.endpoint, request);
  }

  deleteActivity(id: number) {
    return this.http.delete<IActivity>(this.endpoint + '/' + id);
  }

  updateActivity(id: number, request: object) {
    return this.http.put<IActivity>(this.endpoint + '/' + id, request);
  }
}
