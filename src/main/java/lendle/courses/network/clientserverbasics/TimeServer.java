/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lendle.courses.network.clientserverbasics;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class TimeServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            ServerSocket server = new ServerSocket(13);
            System.out.println("connection open");
            while (true) {
                System.out.println("waiting for connection");
                Socket client = server.accept();
                Thread t = new Thread() {
                    public void run() {
                        OutputStream outputStream = null;
                        try {
                            outputStream = client.getOutputStream();
                            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "ascii");
                            Date date = new Date();
                            writer.write(date.toString());
                            writer.flush();
                            writer.close();
                            Thread.sleep(60000);
                            client.close();
                        } catch (IOException ex) {
                            Logger.getLogger(TimeServer.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(TimeServer.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            try {
                                outputStream.close();
                            } catch (IOException ex) {
                                Logger.getLogger(TimeServer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                };
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(TimeServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TimeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
