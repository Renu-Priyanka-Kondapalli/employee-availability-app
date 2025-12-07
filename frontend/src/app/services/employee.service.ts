import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EmployeeMe } from '../models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getSelf(): Observable<EmployeeMe> {
    return this.http.get<EmployeeMe>(`${this.apiUrl}/employees/me`);
    }

  updateAvailability(status: 'AVAILABLE' | 'UNAVAILABLE' | 'ON_LEAVE'): Observable<EmployeeMe> {
    return this.http.put<EmployeeMe>(`${this.apiUrl}/employees/me/status`, {
      availabilityStatus: status
    });
  }
}
