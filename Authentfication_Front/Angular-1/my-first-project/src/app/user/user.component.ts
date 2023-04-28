import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit{
  constructor(private user : UserService){}
  messages: string = "";
  ngOnInit(): void {
    this.forUser();
  }

  forUser(){
    this.user.forUser().subscribe(
      (response)=>{
        console.log(response);
        this.messages = response;
      },
      (error)=>{
        console.log(error);
      }
    )
  }


}
