package com.screenshare.client;

import java.io.IOException;

import javax.swing.JFrame;


public class ClientStart {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Client testServer;
        try {
            testServer = new Client();
            
            testServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            testServer.clientRunner();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //starts the server
        //testServer.Runner();

    }

}
