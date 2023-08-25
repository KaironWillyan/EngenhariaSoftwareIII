import * as net from 'net';
import { Player } from './player';

const readline = require('readline-sync')

class Message {
    constructor(public action: string, public params: string[]) {}
}

const client: net.Socket = new net.Socket();

client.connect(3000, 'localhost', () => {
   console.log('Conectado ao servidor');
   const name = readline.question('Digite seu nome: ')

   client.write(`Login ${name}`);
});

client.on('data', (data: Buffer) => {
   const messageString = data.toString().trim();
   const messageParts = messageString.split(' ');
   const message = new Message(messageParts[0], messageParts.slice(1));

   console.log(messageString);

   try {
       handleMessage(message);
   } catch (error) {
       console.error(error.message);
   }

   if (messageString.endsWith('exit') || messageString.endsWith('vou desconectar')) {
       client.destroy();
   }
});

function handleMessage(message: Message) {
    switch (message.action) {
        case 'Login':
            process.stdin.once('data', (input) => {
                const name = input.toString().trim();
                client.write(`Login ${name}`);
            });
            break;
        case 'Aguarde':
            console.log(message.params.join(' '));
            break;
        case 'ShowHand':
            console.log(message.params.join(' '));
            const choice = readline.question('Do you want to hit or stand? (h/s): ');
            client.write(choice);
            break;
        case 'DealerHand':
            console.log(message.params.join(' '));
            break;
        case 'Você':
            console.log(message.params.join(' '));
            break;
        case 'stand':
            console.log(message.params.join(' '));
            break;
        default:
            throw new Error(`Ação desconhecida: ${message.action}`);
    }
}

client.on('end', () => {
   console.log(`Cliente ${Player.name} desconectado do servidor`);
});

client.on('error', (err) => { 
    console.error(err); 
});
