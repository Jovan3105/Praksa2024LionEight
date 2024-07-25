import { HttpInterceptorFn } from '@angular/common/http';

export const HttpInterceptor: HttpInterceptorFn = (request, next) => {
  // console.log('Interceptor');
  if (localStorage.getItem('JWT')) {
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${localStorage.getItem('JWT')}`,
        // 'Access-Control-Allow-Origin' : '*'
      },
    });
  }
  console.log('Interceptor sa tokenom', request);
  return next(request);
};
