import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IBath } from '../models/ibath';

@Injectable({
  providedIn: 'root',
})
export class BathService {
  http = inject(HttpClient);
  endpoint = 'http://localhost:8000/api/baths';

  getBathById(id: number) {
    return this.http.get<IBath>(this.endpoint + '/' + id);
  }

  getAllBaths() {
    return this.http.get<Array<IBath>>(this.endpoint);
  }

  createBath(request: object) {
    return this.http.post<IBath>(this.endpoint, request);
  }

  deleteBath(id: number) {
    return this.http.delete<IBath>(this.endpoint + '/' + id);
  }

  updateBath(id: number, request: object) {
    return this.http.put<IBath>(this.endpoint + '/' + id, request);
  }
}
