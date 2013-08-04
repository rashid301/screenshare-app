package com.screenshare.client;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Client extends JFrame {

	private int screenWidth = 640;

	private int screenHeight = 480;
	// time between frames now time per screenshot is 500ms
	private final int fps = 10;
	private int interval = 0;

	// Jlabel to display the image on the panel
	private JLabel image;

	// variable to add the screenshot image
	private ImageIcon screen;

	// create a capture screen instance(only one per client app)
	private CaptureScreen captureScreen;

	public Client() throws IOException {
		// preparing the GUI for hosting

		super("Client Interface");

		// Default image
		BufferedImage bf = ImageIO.read(new File("hello.jpg"));
		// scaling the image
		Image scaledImage = bf.getScaledInstance(screenWidth, screenHeight,
				Image.SCALE_SMOOTH);
		// ImageIcon for JLabel
		screen = new ImageIcon(scaledImage);
		// Jlabel for showing the Image on screen
		image = new JLabel(screen);
		// adding the image to the screen
		add(image, BorderLayout.CENTER);
		// setting the Jframe properties
		setSize(screenWidth, screenHeight);

		interval = 1000 / fps;
		setVisible(true);

	}

	public void display(BufferedImage bf) {

		// scaling the image
		Image scaledImage = bf.getScaledInstance(screenWidth, screenHeight,
				Image.SCALE_SMOOTH);
		// ImageIcon for JLabel
		screen = new ImageIcon(scaledImage);
		// Jlabel for showing the Image on screen
		image.setVisible(false);
		image = new JLabel(screen);
		// adding the image to the screen
		add(image, BorderLayout.CENTER);
		// setting the Jframe properties
		setSize(screenWidth, screenHeight);
		setVisible(true);

	}

	public void clientRunner() {
		try {
			CaptureScreen cs = CaptureScreen.getInstance();
			while (true) {
				display(cs.getScreen());
				long starttime = System.currentTimeMillis();
				long delay = 0l;
				while (delay < interval) {
					delay = System.currentTimeMillis() - starttime;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(final boolean tof) {
		captureScreen = CaptureScreen.getInstance();
		try {

			// get the screenshot image
			BufferedImage screenShot = captureScreen.getScreen();
			// scaling the image
			Image scaledImage = screenShot.getScaledInstance(screenWidth,
					screenHeight, Image.SCALE_SMOOTH);
			screen.setImage(scaledImage);
		} catch (AWTException awtException) {
			awtException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
