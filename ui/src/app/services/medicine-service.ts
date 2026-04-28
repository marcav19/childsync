import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IMedicine } from '../models/imedicine';

@Injectable({
  providedIn: 'root',
})
export class MedicineService {
  http = inject(HttpClient);
  url = 'http://localhost:8000/api/medicine';

  getMedicineById(id: number) {
    return this.http.get<IMedicine>(this.url + '/' + id);
  }

  getAllMedicine() {
    return this.http.get<Array<IMedicine>>(this.url);
  }

  createMedicine(body: object) {
    return this.http.post<IMedicine>(this.url, body);
  }

  deleteMedicine(id: number) {
    return this.http.delete<IMedicine>(this.url + '/' + id);
  }

  updateMedicine(id: number, body: object) {
    return this.http.patch<IMedicine>(this.url + '/' + id, body);
  }
}
