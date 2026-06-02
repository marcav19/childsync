import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IMedicine } from '../models/imedicine';

@Injectable({
  providedIn: 'root',
})
export class MedicineService {
  http = inject(HttpClient);
  endpoint = 'http://localhost:8000/api/medicines';

  getMedicineById(id: number) {
    return this.http.get<IMedicine>(this.endpoint + '/' + id);
  }

  getAllMedicines() {
    return this.http.get<Array<IMedicine>>(this.endpoint);
  }

  createMedicine(request: object) {
    return this.http.post<IMedicine>(this.endpoint, request);
  }

  deleteMedicine(id: number) {
    return this.http.delete<IMedicine>(this.endpoint + '/' + id);
  }

  updateMedicine(id: number, request: object) {
    return this.http.put<IMedicine>(this.endpoint + '/' + id, request);
  }
}
