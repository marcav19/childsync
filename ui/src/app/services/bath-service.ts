import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IBath } from '../models/ibath';

@Injectable({
  providedIn: 'root',
})
export class BathService {
  http = inject(HttpClient);
  url = 'http://localhost:8000/api/baths';

  getBathById(id: number) {
    return this.http.get<IBath>(this.url + '/' + id);
  }

  getAllBath() {
    return this.http.get<Array<IBath>>(this.url);
  }

  createBath(body: object) {
    return this.http.post<IBath>(this.url, body);
  }

  deleteBath(id: number) {
    return this.http.delete<IBath>(this.url + '/' + id);
  }

  updateBath(id: number, body: object) {
    return this.http.patch<IBath>(this.url + '/' + id, body);
  }
}
