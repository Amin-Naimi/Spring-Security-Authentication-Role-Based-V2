import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { UserAuthService } from '../services/user-auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private auth: UserAuthService,
    private router: Router) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (request.headers.get('No-Auth') === 'True') {
      return next.handle(request.clone());
    }
    const token = this.auth.getToken();
    request = this.addToken(request, token);

    return next.handle(request).pipe(
      catchError(
        (error: HttpErrorResponse) => {
          console.log(error.status);
          if (error.status === 401) {
            this.router.navigate(['/login']);
          } else if (error.status === 403) {
            this.router.navigate(['/forbidden'])
          }
          return throwError("Something went wrong");
        }
      )
  );
  }

  private addToken(request: HttpRequest<any>, token: string) {
    return request.clone(
      {
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      }
    );

  }



}
