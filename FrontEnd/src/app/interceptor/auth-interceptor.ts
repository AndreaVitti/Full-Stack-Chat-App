import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  if (req.headers.has('skip')) {
    const headers = req.headers.delete('skip');
    const newReq = req.clone({ headers });
    return next(newReq);
  }

  const jwtToken = localStorage.getItem('jwtToken');
  const newReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ` + jwtToken
    }
  })
  return next(newReq);
};
