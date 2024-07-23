import { inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (
    (route.routeConfig?.path === 'login' ||
      route.routeConfig?.path === 'register') &&
    authService.isLoggedIn()
  ) {
    router.navigate(['/dashboard']);
    return false;
  }

  if (
    !authService.isLoggedIn() &&
    route.routeConfig?.path !== 'login' &&
    route.routeConfig?.path !== 'register'
  ) {
    router.navigate(['/login']);
    return false;
  }

  return true;
};
