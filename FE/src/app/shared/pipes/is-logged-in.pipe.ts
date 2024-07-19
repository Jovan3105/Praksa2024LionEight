import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'isLoggedIn',
  standalone: true,
})
export class IsLoggedInPipe implements PipeTransform {
  transform(value: boolean): boolean {
    return value;
  }
}
