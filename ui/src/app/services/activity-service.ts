import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IActivity } from '../models/iactivity';

@Injectable({
  providedIn: 'root',
})
export class ActivityService {
  http = inject(HttpClient);
  url = 'http://localhost:8000/api/activities';

  getActivityById(id: number) {
    return this.http.get<IActivity>(this.url + '/' + id);
  }

  getAllActivity() {
    return this.http.get<Array<IActivity>>(this.url);
  }

  createActivity(body: object) {
    return this.http.post<IActivity>(this.url, body);
  }

  deleteActivity(id: number) {
    return this.http.delete<IActivity>(this.url + '/' + id);
  }

  updateActivity(id: number, body: object) {
    return this.http.put<IActivity>(this.url + '/' + id, body);
  }
}
