export interface AuthResponse {
  token: string;
  name: string;
  email: string;
  availabilityStatus: 'AVAILABLE' | 'UNAVAILABLE' | 'ON_LEAVE';
}

export interface EmployeeMe {
  id: number;
  name: string;
  email: string;
  availabilityStatus: 'AVAILABLE' | 'UNAVAILABLE' | 'ON_LEAVE';
}
