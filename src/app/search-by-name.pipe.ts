import { Pipe, PipeTransform } from '@angular/core';
import { Employee } from './employee';
 
@Pipe({ name: 'searchByName' })
export class SearchByNamePipe implements PipeTransform {
  transform(employees: Employee[], searchText: string) {
    return employees.filter(employee =&amp;gt; employees.firstName.indexOf(searchText) !== -1);
  }
}