import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private http: HttpClient) {}

  public authenticate(username: string, password: string): Observable<string> {
    return this.http.post<string>(environment.backendHost + '/login', {
      username,
      password
    });
  }

  public getUserData(): Observable<User> {
    return this.http.get<User>(environment.backendHost + '/profile');
  }
}
