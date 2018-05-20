package it.polimi.ingsw.controller.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class socketStartServer {
    public static void main(String[] args){
        try{
            ServerSocket server = new ServerSocket(10999);
            while(true){
                //Blocking call, waiting for a new connection
                Socket client = server.accept();

                //the new connection is handled by an indipendent thread, then the while loop can continue
                Connect nuovaConnessione = new Connect(client);
            }
        }
        catch (Exception e){}
        return;
    }
}


