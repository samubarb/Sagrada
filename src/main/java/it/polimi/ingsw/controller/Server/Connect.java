package it.polimi.ingsw.controller.Server;

import java.io.*;
import java.net.Socket;

public class Connect extends Thread{
        // dichiarazione delle variabili socket e dei buffer
        Socket client;
        BufferedReader inputBuffer;
        PrintWriter outputBuffer;

        /**
         * @param client this is the client that request the connection
         *               This method automatically runs a new thread
         */
        public Connect(Socket client)
        {
            this.client = client;
            this.start();
        }

        public void run()
        {
            try
            {
                // In this two lines inizializes the input and output buffers
                inputBuffer = new BufferedReader(new InputStreamReader(client.getInputStream()));
                outputBuffer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);


                System.out.println("Sto servendo il client che ha indirizzo "+client.getInetAddress());

                // eventuali elaborazioni e scambi di informazioni con il client

                // ...

                //Closing input and output buffer
                inputBuffer.close();
                outputBuffer.close();
                //Closing client connection
                client.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

}
