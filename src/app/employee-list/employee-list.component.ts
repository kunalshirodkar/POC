import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import  { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { AuthenticationService } from '../login/auth.service';  //

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees:Employee[];
  searchText = '';
  employees1:any;

  constructor(private employeeService:EmployeeService,private router :Router,
    private route: ActivatedRoute,private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.getEmployees();

    //
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    console.log('menu ->' + this.isLoggedIn);
  }

private getEmployees(){
  this.employeeService.getEmployeesList().subscribe(data =>{
    this.employees=data;
  });
}

updateEmployee(id:number){
  this.router.navigate(['update-employee',id]);
}

deleteEmployee(id:number){
  this.employeeService.deleteEmployee(id).subscribe(data=>{
   console.log(data); 
    this.getEmployees();
  })
}
search(value: string): void {
  this.employees1 = this.employees1.filter((val) => val.name.toLowerCase().includes(value));
}

employeeDetails(id:number){
  this.router.navigate(['employee-details',id]);
}

//
isLoggedIn = false;
handleLogout() {
  this.authenticationService.logout();
}
}
