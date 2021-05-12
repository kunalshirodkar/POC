import { Pipe, PipeTransform } from '@angular/core';
import{Employee} from './employee';
@Pipe({
  name: 'filterPipe'
})
export class FilterPipePipe implements PipeTransform {

  transform(ts: Employee[], searchText: string) {
    return ts.filter(t => t.firstName.indexOf(searchText) !== -1);
  }

}
