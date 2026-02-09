import { Injectable } from '@angular/core';
import { Client } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocket {

  createClient(): Client {
    const stompClient = new Client({
      brokerURL: 'ws://localhost:8082/ws?jwtToken=' + localStorage.getItem('jwtToken'),
    })
    return stompClient;
  }
}
