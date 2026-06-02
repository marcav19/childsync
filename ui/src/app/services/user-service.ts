import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { IUser } from '../models/iuser';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  http = inject(HttpClient);
  endpoint = 'http://localhost:8000/api/users'

  getAllUsers() {
    return this.http.get<Array<IUser>>(this.endpoint);
  }
}
