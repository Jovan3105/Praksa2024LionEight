import { Routes } from '@angular/router';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { isNotLoggedInGuard } from './shared/guards/is-not-logged-in.guard';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { isLoggedInGuard } from './shared/guards/is-logged-in.guard';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginPageComponent,
    canActivate: [isNotLoggedInGuard],
  },
  {
    path: 'register',
    component: RegisterPageComponent,
    canActivate: [isNotLoggedInGuard],
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [isLoggedInGuard],
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'dashboard',
  },
];
