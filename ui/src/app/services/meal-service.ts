import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IMeal } from '../models/imeal';

@Injectable({
  providedIn: 'root',
})
export class MealService {
  http = inject(HttpClient);
  endpoint = 'http://localhost:8000/api/meals';

  getMealById(id: number) {
    return this.http.get<IMeal>(this.endpoint + '/' + id);
  }

  getAllMeals() {
    return this.http.get<Array<IMeal>>(this.endpoint);
  }

  createMeal(request: object) {
    return this.http.post<IMeal>(this.endpoint, request);
  }

  deleteMeal(id: number) {
    return this.http.delete<IMeal>(this.endpoint + '/' + id);
  }

  updateMeal(id: number, request: object) {
    return this.http.put<IMeal>(this.endpoint + '/' + id, request);
  }
}
