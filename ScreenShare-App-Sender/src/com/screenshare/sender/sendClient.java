package com.screenshare.sender;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class sendClient {

	private int screenWidth = 640;

	private int screenHeight = 480;

	// time between frames now time per screenshot is 500ms
	private final int timePerFrame = 2000;

	// Stream to send message to the server
	private ImageOutputStream output;

	// server setup (the socket, the port everything is in here)
	private ServerSocket server;

	// connection between any two computers
	private Socket connection;

	// BufferredImage to get the image
	private BufferedImage screenShot;

	// create a capture screen instance(only one per client app)
	private CaptureScreen captureScreen;

	// scaled Image
	private Image scaledImage;
	
	//ImageIcon
	private ImageIcon image = new ImageIcon();

	// Timer
	private Timer timer;
	
	//file to store the ImageIcon before sending
	private File file;

	private ActionListener action = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// action to be performed
			captureScreen = CaptureScreen.getInstance();
			try {
				screenShot = captureScreen.getScreen();
				/*scaledImage = screenShot.getScaledInstance(screenWidth,
						screenHeight, Image.SCALE_SMOOTH);*/
				
				//ImageIO.write((RenderedImage) screenShot, "png", file);
				//image = new ImageIcon(screenShot);
				send(screenShot);

			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	};

	public sendClient() {
	}

	// sendmessage
	private void send(BufferedImage cs) {
		try {
			// use the output stream to send the message
			ImageIO.write(cs, "png", connection.getOutputStream());
			//connection.getOutputStream().flush();
			//output.writeObject(cs);
			//output.flush();
			System.out.println("sent");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clientRunner() {
		try {
			// connect only to server, if server is running otherwise dont do
			// the rest
			server = new ServerSocket(9876, 100);
			while(true){
				connection = server.accept();
				//output =(connection.getOutputStream());
				timer = new Timer(timePerFrame, action);
				timer.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeMethod();
		}
	}

	// close the streams and shutting down
	private void closeMethod() {
		try {
			// stop the timer
			timer.stop();
			// close the output stream
			output.close();
			// close the connection
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

}
