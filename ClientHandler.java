package sample.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    private Server server;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String str = input.readUTF(); // преобразуем инфу клиентского сокет в строки
                            // команда для выхода - если строка равно, то брейк
                            if (str.equals("/quit")) {
                                output.writeUTF("/serverClosed");
                                break;
                            }
                            System.out.println("Client: " + str);
                            server.broadcastMsg(str);
                          //  output.flush();
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMsg(String str){
        try {
            output.writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
