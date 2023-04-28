import { Component, DoCheck, OnInit } from '@angular/core';
import { UserAuthService } from '../services/user-auth.service';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements DoCheck{

  constructor(private auth: UserAuthService,
    private router : Router,
    private user : UserService){}

  public hidenButton: boolean = false;
  public hidenlinks = false;

    ngDoCheck() {
      this.isLoggedIn();
    }

    public isLoggedIn(){
    if(this.auth.isLoggedIn()){
      this.hidenButton = true;
    }
    return this.hidenButton;
  }

  public logOut(){
    this.auth.clear();
    this.hidenButton = false;
    this.router.navigate(["/home"]);
  }

  public showingLinks(roles : string[]): boolean {
    let check : boolean = false;

    if(this.user.roleVerification(roles)){
      check = true;
      return check;
    }
    else
    return check

  }
}
