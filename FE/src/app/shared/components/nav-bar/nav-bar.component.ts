import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule, NgIf } from '@angular/common';
import { IsLoggedInPipe } from '../../../shared/pipes/is-logged-in.pipe';
import { error } from 'console';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [NgIf, CommonModule, IsLoggedInPipe],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css',
})
export class NavBarComponent implements OnInit {
  authService = inject(AuthService);
  isLoggedIn = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  logout(): void {
    this.authService.logoutRequest().subscribe({
      error: (e) => {
        console.log(e);
        //console.log('greska');
      },
      next: () => {
        console.log('Logout');
        this.authService.logout();
        this.router.navigate(['/login']);
      },
    });
  }
}
