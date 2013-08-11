package com.screenshare.sender;



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
		sendClient testClientSender;
		try {
			testClientSender = new sendClient();
			testClientSender.clientRunner();
		} catch (Exception e) {
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
