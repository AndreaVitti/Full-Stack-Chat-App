import { Message } from "./message";

export interface ApiRes {
    httpCode: number;
    messageDTOS: Message[];
    username: string;
}
