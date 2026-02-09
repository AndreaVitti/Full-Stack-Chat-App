import { Routes } from '@angular/router';
import { ChatRoom } from './component/chat-room/chat-room';
import { Home } from './component/home/home';
import { Register } from './component/register/register';

export const routes: Routes = [
    {
        path: '', component: Home
    },
    {
        path: 'register', component: Register
    },
    {
        path: 'chat-room', component: ChatRoom
    }
];
