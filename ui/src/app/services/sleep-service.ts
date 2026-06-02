import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ISleep } from '../models/isleep';

@Injectable({
  providedIn: 'root',
})
export class SleepService {
  http = inject(HttpClient);
  endpoint = 'http://localhost:8000/api/sleeps';

  getSleepById(id: number) {
    return this.http.get<ISleep>(this.endpoint + '/' + id);
  }

  getAllSleeps() {
    return this.http.get<Array<ISleep>>(this.endpoint);
  }

  createSleep(request: object) {
    return this.http.post<ISleep>(this.endpoint, request);
  }

  deleteSleep(id: number) {
    return this.http.delete<ISleep>(this.endpoint + '/' + id);
  }

  updateSleep(id: number, request: object) {
    return this.http.put<ISleep>(this.endpoint + '/' + id, request);
  }
}
