/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.app.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;

public class ChatServer {
    
    // Server client capacity
    private static int SERVET_LIMIT = 20;
    // Server port number
    private static int SERVER_PORT_NO = 4999;
    // All client names, so we can check for duplicates upon registration.
    public static Set<String> names = new HashSet<>();
    // The set of all the print writers for all the clients, used for broadcast.
    public static Set<PrintWriter> writers = new HashSet<>();
    
    public static void main(String[] args) throws Exception{
        
        try (var listener = new ServerSocket(SERVER_PORT_NO)) {
            
            System.out.println("The chat server is running...");
            
            var pool = Executors.newFixedThreadPool(SERVET_LIMIT);

            while (true) {
                ServerTask task = new ServerTask(listener.accept()); 
                pool.execute(task);
            }
        }
    }
    
    public static void SendMessageToPerson(String userName, String message){

    }
    
    public static void SendMessageToGroup(String groupName, String message){

    }
    
    public static void SendMessageToAll(String message, String senderName, PrintWriter senderWriter){
        for (PrintWriter writer : writers) {
            if (writer == senderWriter){
               continue; 
            }
            writer.println(senderName + ": " + message);
         }
    }
}
