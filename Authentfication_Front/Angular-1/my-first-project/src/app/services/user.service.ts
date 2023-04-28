import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private http: HttpClient,
    private auth: UserAuthService) { }

  APP_URL = 'http://localhost:8086';
  requestHeader = new HttpHeaders(
    { "No-Auth": "True" }
  )

  public login(LoginData: any) {
    return this.http.post(`${this.APP_URL}/login`, LoginData, { headers: this.requestHeader })
  }

  public roleVerification(roles: any): boolean {
    let isValid: boolean = false;
    const userRoles: any = this.auth.getRoles();

    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < roles.length; j++) {
          if (userRoles[i].roleName === roles[j]) {
            isValid = true;
            return isValid;
          }
        }
      }
    }
    // return false if no matching role found
    return isValid;
  }




}
