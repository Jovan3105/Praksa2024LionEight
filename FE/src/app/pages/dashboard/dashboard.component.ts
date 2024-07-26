import { Component, ElementRef, ViewChild } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { SkyonicsDTS } from '../../shared/models/skyonics-dts';
import { Router } from '@angular/router';
import { CommonModule, NgIf } from '@angular/common';
import { SkyonicsService } from '../../shared/services/skyonics.service';
import bootstrap from '../../../main.server';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule, NgIf],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {
  dashboardForm: FormGroup;

  skyonicsDTS: SkyonicsDTS = {
    APIKey: '',
    serialNumber: '',
    command: '',
  };

  validCommands: string[] = [
    'SHIPMODE',
    'DIAG CAN',
    'SETPARAMS 527=5',
    'SETPARAMS 527=4',
    'SETPARAMS 527=3',
    'SETPARAMS 527=2',
  ];

  isModalVisible: string = 'none';
  displayData: any;
  isLoaderVisible: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private skyonicsService: SkyonicsService
  ) {
    this.dashboardForm = this.formBuilder.group({
      APIKey: [this.skyonicsDTS.APIKey, Validators.required],
      serialNumber: [
        this.skyonicsDTS.serialNumber,
        [Validators.required, this.serialNumberValidator()],
      ],
      command: [
        this.skyonicsDTS.command,
        [Validators.required, this.commandValidator(this.validCommands)],
        ,
      ],
    });
  }

  commandValidator(commands: string[]): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const value: string = control.value;

      if (commands.includes(value)) {
        return null;
      } else {
        return { exactString: { value } };
      }
    };
  }

  serialNumberValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const value: string = control.value;

      if (value) {
        if (value.slice(0, 2) === '87' && value.charAt(2) === 'B') {
          return null;
        }
      }

      return { startsWith87B: { value } };
    };
  }

  formatCommand(command: string): string {
    return `"${command}"`;
  }

  closeModal(): void {
    this.isModalVisible = 'none';
  }

  openModal(): void {
    this.isModalVisible = 'block';
  }

  onSubmit(): void {
    this.dashboardForm.value.command = this.formatCommand(
      this.dashboardForm.value.command
    );
    console.log(this.dashboardForm.value);
    this.isLoaderVisible = true;
    this.skyonicsService.sendDeviceCommand(this.dashboardForm.value).subscribe({
      error: (e) => {
        console.log(e);
        this.dashboardForm.reset();
      },
      next: (result) => {
        //console.log(JSON.parse(result));
        //console.log(JSON.stringify(result));
        this.isLoaderVisible = false;
        this.displayData = JSON.parse(result);
        //console.log(this.displayData);
        this.openModal();
        this.dashboardForm.reset();
      },
    });
  }
}
