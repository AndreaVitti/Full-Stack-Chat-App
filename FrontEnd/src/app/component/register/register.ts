import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ChatApi } from '../../service/chat-api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  router = inject(Router);
  chatApi = inject(ChatApi);
  loginForm = new FormGroup({
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl('')
  });

  register() {
    const username = this.loginForm.value?.username;
    const email = this.loginForm.value?.email;
    const password = this.loginForm.value?.password;
    if (!username || !email || !password) {
      return;
    }
    this.chatApi.register('/auth/register', {
      username: username,
      email: email,
      password: password
    }).subscribe({
      complete: () => {
        this.router.navigate(['/']);
      }
    });
  }

  navLogin(){
    this.router.navigate(['']);
  }
}
