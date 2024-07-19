import { CommonModule, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { RegisterUserDTS } from '../../shared/models/register-user-dts';
import { AuthService } from '../../shared/services/auth.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [NgIf, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css',
})
export class RegisterPageComponent {
  registerForm: FormGroup;

  registerDTS: RegisterUserDTS = {
    name: '',
    surname: '',
    email: '',
    password: '',
  };

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.registerForm = this.formBuilder.group({
      name: [this.registerDTS.name, Validators.required],
      surname: [this.registerDTS.surname, [Validators.required]],
      email: [this.registerDTS.email, [Validators.required, Validators.email]],
      password: [
        this.registerDTS.password,
        [Validators.required, Validators.minLength(6)],
      ],
    });
  }

  onSubmit(): void {
    this.authService.register(this.registerForm.value).subscribe({
      error: (e) => {
        console.log(e);
      },
      next: () => {
        console.log('Succesfull registration');
        this.router.navigate(['/login']);
      },
    });
  }
}
