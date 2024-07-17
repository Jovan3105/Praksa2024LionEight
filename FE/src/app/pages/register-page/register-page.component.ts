import { CommonModule, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { RegisterUserDTS } from '../../shared/models/register-user-dts';

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
    firstName: '',
    lastName: '',
    username: '',
    password: '',
  };

  constructor(private formBuilder: FormBuilder) {
    this.registerForm = this.formBuilder.group({
      firstName: [this.registerDTS.firstName, Validators.required],
      lastName: [this.registerDTS.lastName, [Validators.required]],
      username: [this.registerDTS.username, [Validators.required]],
      password: [
        this.registerDTS.password,
        [Validators.required, Validators.minLength(6)],
      ],
    });
  }

  onSubmit(): void {
    console.log(this.registerForm.value);
  }
}
