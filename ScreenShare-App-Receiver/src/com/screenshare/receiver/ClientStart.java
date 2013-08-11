package com.screenshare.receiver;

import java.io.IOException;

import javax.swing.JFrame;


public class ClientStart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// code to start the client.java file
		/*Client testServer;
		try {
			testServer = new Client();
			testServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			testServer.clientRunner();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//code to start the ClientRecieve.java file
		receiveClient testClientRecieve;
		try {
			testClientRecieve = new receiveClient("127.0.0.1");
			testClientRecieve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			testClientRecieve.clientRunner();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//code to start ClientSend.java file
		/*ClientSend testClientSend;
		try {
			testClientSend = new ClientSend();
			testClientSend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			testClientSend.clientRunner();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
