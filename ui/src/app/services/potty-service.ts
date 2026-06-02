import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IPotty } from '../models/ipotty';

@Injectable({
  providedIn: 'root',
})
export class PottyService {
  http = inject(HttpClient);
  endpoint = 'http://localhost:8000/api/potties';

  getPottyById(id: number) {
    return this.http.get<IPotty>(this.endpoint + '/' + id);
  }

  getAllPotties() {
    return this.http.get<Array<IPotty>>(this.endpoint);
  }

  createPotty(request: object) {
    return this.http.post<IPotty>(this.endpoint, request);
  }

  deletePotty(id: number) {
    return this.http.delete<IPotty>(this.endpoint + '/' + id);
  }

  updatePotty(id: number, request: object) {
    return this.http.put<IPotty>(this.endpoint + '/' + id, request);
  }
}
