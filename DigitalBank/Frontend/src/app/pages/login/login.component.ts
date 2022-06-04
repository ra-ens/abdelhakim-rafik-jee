import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginFormGroup: FormGroup | undefined;
  token?: Observable<string>;
  errorMessage?: string;

  constructor(
    private fb: FormBuilder,
    private authentication: AuthenticationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginFormGroup = this.fb.group({
      keyword: this.fb.control('')
    });
  }

  loginHandler() {
    let username = this.loginFormGroup?.value.username;
    let password = this.loginFormGroup?.value.password;

    this.router.navigate(['/private/accounts']);

    // this.token = this.authentication.authenticate(username, password).pipe(
    //   catchError(err => {
    //     this.errorMessage = err.message;
    //     return throwError(err);
    //   })
    // );

    // this.token.subscribe(t => {
    //   console.log('Token: >>', t);
    // });
  }
}
