import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IPotty } from '../models/ipotty';

@Injectable({
  providedIn: 'root',
})
export class PottyService {
  http = inject(HttpClient);
  url = 'http://localhost:8000/api/potty';

  getPottyById(id: number) {
    return this.http.get<IPotty>(this.url + '/' + id);
  }

  getAllPotty() {
    return this.http.get<Array<IPotty>>(this.url);
  }

  createPotty(body: object) {
    return this.http.post<IPotty>(this.url, body);
  }

  deletePotty(id: number) {
    return this.http.delete<IPotty>(this.url + '/' + id);
  }

  updatePotty(id: number, body: object) {
    return this.http.patch<IPotty>(this.url + '/' + id, body);
  }
}
