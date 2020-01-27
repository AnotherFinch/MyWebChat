package sample.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() {
    clients = new Vector<>();
    // объявляем потоки сервера и клиента (инициализация)
    ServerSocket server = null;
    Socket socket = null;

    // создаем серверсокет, оборачиваем в трайкетч и присваиваем порт (любой - 8080)

        try {

            server = new ServerSocket(8000);
            System.out.println("Server started");
            // !!! подключение клиента
            // !!! таким образом мы инициализировали 2 сокета - серверный и клиентский
            while (true){
                socket = server.accept();
                System.out.println("Client connected");
                clients.add(new ClientHandler(this, socket));
            }
            // считывание информации с сокета клиента, обычный system.in не подходит
            // сканер или буфер ридер необходимо обороачивать, как далее
         //   DataInputStream input = new DataInputStream(socket.getInputStream());
        //    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            // создаем бесконечный цикл


        } catch (IOException e) {
            e.printStackTrace();
        // файнали отвечате за то, чтобы при любом случае закрывались потоки, необходимо делать всегда
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(String msg){
        for (ClientHandler o: clients){
            o.sendMsg(msg);
        }
    }
}

