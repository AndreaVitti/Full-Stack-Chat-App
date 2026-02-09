import { Component, inject } from '@angular/core';
import { ChatApi } from '../../service/chat-api';
import { Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { tap } from 'rxjs';

@Component({
  selector: 'app-home',
  imports: [ReactiveFormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  router = inject(Router);
  chatApi = inject(ChatApi);
  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });

  login() {
    const email = this.loginForm.value?.email;
    const password = this.loginForm.value?.password;
    if (!email || !password) {
      return;
    }
    this.chatApi.login('/auth/login', {
      email: email,
      password: password
    }).pipe(tap(res => {
      const jwtTokenHeader = res.headers.get('Authorization');
      if (jwtTokenHeader) {
        localStorage.setItem('jwtToken', jwtTokenHeader.replace('Bearer ', ''));
        localStorage.setItem('username', res.body!.username);
      }
    })).subscribe({
      complete: () => {
        this.router.navigate(['/chat-room']);
      }
    });
  }

  navRegister(){
    this.router.navigate(['/register']);
  }
}
