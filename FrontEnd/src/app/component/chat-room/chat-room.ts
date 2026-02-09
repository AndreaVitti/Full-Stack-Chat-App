import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { WebSocket } from '../../service/web-socket';
import { Client } from '@stomp/stompjs';
import { ChatApi } from '../../service/chat-api';
import { tap } from 'rxjs';
import { Router } from '@angular/router';
import { Message } from '../../model/message';

@Component({
  selector: 'app-chat-room',
  imports: [ReactiveFormsModule],
  templateUrl: './chat-room.html',
  styleUrl: './chat-room.css',
})
export class ChatRoom implements OnInit {
  chgdet = inject(ChangeDetectorRef);
  stompClientServ = inject(WebSocket);
  router = inject(Router);
  chatApi = inject(ChatApi);
  stompClient!: Client;
  messages: Message[] = [];
  username: string = localStorage.getItem('username')!;
  sendMsgForm = new FormGroup({
    message: new FormControl('')
  });

  ngOnInit(): void {
    this.stompClient = this.stompClientServ.createClient();
    this.stompClient.onConnect = () => {
      this.stompClient.subscribe('/chat/public', (message) => {
        const messageJson = JSON.parse(message.body)
        this.showMsg(messageJson);
        const sender = messageJson.sender;
        const type = messageJson.type;
        if (type === 'DISC' && sender === this.username) {
          this.stompClient.deactivate();
          this.chatApi.logout('/auth/logout').subscribe({
            complete: () => {
              localStorage.removeItem('username');
              localStorage.removeItem('jwtToken');
              this.router.navigate(['/']);
            }
          });
        }
      });
      this.chatApi.getAllMsg('/message/all').pipe(tap(res => {
        res.forEach(res => {
          this.messageDisplayed(res.type, res.sender, res.payload);
        })
      })).subscribe({
        complete: () => this.send('', 'CONN')
      });
    }
    this.stompClient.activate();
  }

  showMsg(message: any) {
    const payload = message.payload;
    const sender = message.sender;
    const type = message.type;
    this.messageDisplayed(type, sender, payload);
    this.chgdet.markForCheck();
  }

  send(message: string, type: string): void {
    this.stompClient.publish({
      destination: '/chatApp/sendMessage',
      body: JSON.stringify({ type: type, sender: this.username, payload: message })
    })
  }

  disconnect() {
    this.send('', 'DISC');
  }

  messageDisplayed(type: string, sender: string, payload: string): void {
    if (type !== 'CHAT') {
      this.messages.push(this.generateMsgDisp(type, '', payload));
    } else {
      this.messages.push(this.generateMsgDisp(type, sender, ': ' + payload));
    }
  }

  generateMsgDisp(type: string, sender: string, payload: string): Message {
    return { type, sender, payload };
  }
}
