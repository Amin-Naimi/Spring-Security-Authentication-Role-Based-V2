import { Component } from '@angular/core';
import { Form, NgForm } from '@angular/forms';
import { UserService } from '../services/user.service';
import { UserAuthService } from '../services/user-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private userService : UserService,
    private auth : UserAuthService,
    private router: Router){}

  login(loginForm : NgForm){
    this.userService.login(loginForm.value).subscribe(
      (response:any)=>{

        console.log(response.accesToken);
        console.log(response.users.roleSet)
        this.auth.setRoles(response.users.roleSet)
        this.auth.setToken(response.accesToken)

        const role  = response.users.roleSet[0];
        const roleName = role.roleName;
        console.log(roleName);

        if(roleName === 'Admin'){
          this.router.navigate(['/admin']);
        }else
        {
          this.router.navigate(['/user']);
        }




      },
      (error)=>{
        console.log(error);
      }
    );

  }

}
