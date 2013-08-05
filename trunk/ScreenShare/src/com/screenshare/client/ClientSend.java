package com.screenshare.client;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import com.screenshare.share.SendImage;

public class ClientSend extends JFrame {

	private int screenWidth = 640;

	private int screenHeight = 480;
	// time between frames now time per screenshot is 500ms
	private final int timePerFrame = 1000;

	// Jlabel to display the image on the panel
	private JLabel image;

	// variable to add the screenshot image
	private ImageIcon screen;

	// BufferredImage to get the image
	private BufferedImage screenShot;

	// create a capture screen instance(only one per client app)
	private CaptureScreen captureScreen;

	// scaled Image
	private Image scaledImage;

	SendImage si = new SendImage();

	private ActionListener action = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// action to be performed
			captureScreen = CaptureScreen.getInstance();
			try {
				// get the screenshot image
				remove(image);
				screenShot = captureScreen.getScreen();
				// scaling the image
			
				si.send(screenShot);
				/*scaledImage = screenShot.getScaledInstance(screenWidth,
						screenHeight, Image.SCALE_SMOOTH);
				screen.setImage(scaledImage);
				image.setIcon(screen);
				add(image, BorderLayout.CENTER);
				repaint();*/
			} catch (AWTException awtException) {
				awtException.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	public ClientSend() throws IOException {
		// preparing the GUI for hosting

		super("Client Interface");

		// Default image
		screenShot = ImageIO.read(new File("hello.jpg"));
		// scaling the image
		scaledImage = screenShot.getScaledInstance(screenWidth, screenHeight,
				Image.SCALE_SMOOTH);
		// ImageIcon for JLabel
		screen = new ImageIcon(scaledImage);
		// Jlabel for showing the Image on screen
		image = new JLabel(screen);
		// adding the image to the screen
		add(image, BorderLayout.CENTER);
		// setting the Jframe properties
		setSize(screenWidth, screenHeight);
		setVisible(true);
	}

	public void clientRunner() {
		try{
			Timer timer = new Timer(timePerFrame, action);
			timer.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
