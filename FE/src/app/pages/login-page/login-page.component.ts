import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { LoginUserDTS } from '../../shared/models/login-user-dts';
import { AuthService } from '../../shared/services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css',
})
export class LoginPageComponent {
  loginForm: FormGroup;

  loginDTS: LoginUserDTS = {
    email: '',
    password: '',
  };

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      email: [this.loginDTS.email, [Validators.required, Validators.email]],
      password: [
        this.loginDTS.password,
        [Validators.required, Validators.minLength(6)],
      ],
    });
  }

  onSubmit(): void {
    this.authService.login(this.loginForm.value).subscribe({
      error: (e) => {
        console.log(this.loginForm.value);
        console.log(e);
      },
      next: () => {
        console.log('Succesfull login');
        this.authService.setLogin(this.loginForm.value);
        this.router.navigate(['/']);
      },
    });
  }
}
