import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import {EmployeeService} from '../employee.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  firstName : string;
  employees1 : any;
  exs: Employee[] = [];

  constructor( private service:EmployeeService) { }

  ngOnInit(): void {
    let resp=this.service.getEmployeesList();
      resp.subscribe((data)=>this.employees1=data);  
  }

  findEmployeeByFname(){
    let resp= this.service.getEmployeesByFirstName(this.firstName)
    resp.subscribe((data)=>this.employees1=data);
   }

}
