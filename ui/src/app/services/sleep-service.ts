import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ISleep } from '../models/isleep';

@Injectable({
  providedIn: 'root',
})
export class SleepService {
  http = inject(HttpClient);
  url = 'http://localhost:8000/api/sleep';

  getSleepById(id: number) {
    return this.http.get<ISleep>(this.url + '/' + id);
  }

  getAllSleep() {
    return this.http.get<Array<ISleep>>(this.url);
  }

  createSleep(body: object) {
    return this.http.post<ISleep>(this.url, body);
  }

  deleteSleep(id: number) {
    return this.http.delete<ISleep>(this.url + '/' + id);
  }

  updateSleep(id: number, body: object) {
    return this.http.patch<ISleep>(this.url + '/' + id, body);
  }
}
