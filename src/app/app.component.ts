import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './login/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'NeoSoft Technologies'; 
  isLoggedIn = false;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    console.log('menu ->' + this.isLoggedIn);
  }

  handleLogout() {
    this.authenticationService.logout();
  }

  checkIsLoggedIn(){
    if(this.authenticationService.isUserLoggedIn()==false){
      alert("Please Login!!");
      this.router.navigate(['login']);
    }
  }
}
