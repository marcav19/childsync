import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IMeal } from '../models/imeal';

@Injectable({
  providedIn: 'root',
})
export class MealService {
  http = inject(HttpClient);
  url = 'http://localhost:8000/api/meals';

  getMealById(id: number) {
    return this.http.get<IMeal>(this.url + '/' + id);
  }

  getAllMeal() {
    return this.http.get<Array<IMeal>>(this.url);
  }

  createMeal(body: object) {
    return this.http.post<IMeal>(this.url, body);
  }

  deleteMeal(id: number) {
    return this.http.delete<IMeal>(this.url + '/' + id);
  }

  updateMeal(id: number, body: object) {
    return this.http.patch<IMeal>(this.url + '/' + id, body);
  }
}
