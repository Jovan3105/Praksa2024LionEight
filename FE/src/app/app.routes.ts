import { Routes } from '@angular/router';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { authGuard } from './shared/guards/auth.guard';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginPageComponent,
    canActivate: [authGuard],
  },
  {
    path: 'register',
    component: RegisterPageComponent,
    canActivate: [authGuard],
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard],
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'dashboard',
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: 'dashboard',
  },
];
