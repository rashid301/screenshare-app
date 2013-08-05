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

import com.screenshare.share.ReceiveImage;

public class ClientRecieve extends JFrame {

	private int screenWidth = 640;

	private int screenHeight = 480;
	// time between frames now time per screenshot is 500ms
	private final int timePerFrame = 100;

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

	private ActionListener action = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// action to be performed
			captureScreen = CaptureScreen.getInstance();
			try {
				screenShot = captureScreen.getScreen();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateScreen(screenShot);
		}
	};
	
	private void updateScreen(BufferedImage cs){
		try {
			// get the screenshot image
			remove(image);
			// scaling the image
			scaledImage = cs.getScaledInstance(screenWidth,
					screenHeight, Image.SCALE_SMOOTH);
			screen.setImage(scaledImage);
			image.setIcon(screen);
			add(image, BorderLayout.CENTER);
			repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ClientRecieve() throws IOException {
		// preparing the GUI for hosting

		super("Client Interface");

		// Default image
		screenShot = ImageIO.read(new File("viewvc.png"));
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
			ReceiveImage ri = new ReceiveImage();
			
			/*Timer timer = new Timer(timePerFrame, action);
			timer.start();*/
			while(true){
				screenShot = ri.receive();
				updateScreen(screenShot);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
