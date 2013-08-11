package com.screenshare.receiver;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class receiveClient extends JFrame {

	private int screenWidth = 640;

	private int screenHeight = 480;

	// Jlabel to display the image on the panel
	private JLabel image;

	// variable to add the screenshot image
	private ImageIcon screen;

	// BufferredImage to get the image
	private BufferedImage screenShot;

	// scaled Image
	private Image scaledImage;

	// stream to receive message from the server
	private ImageInputStream input;

	// server IP who client is going to connect
	private String serverIP;

	// connection between any two computers
	private Socket connection;

	public receiveClient(String host) throws IOException {
		// preparing the GUI for hosting

		super("Client Interface");

		serverIP = host;

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
		try {
			connection = new Socket(InetAddress.getByName(serverIP), 9876);
			// input = (ImageInputStream)(connection.getInputStream());
			updateDisplay();
		} catch (ClassNotFoundException coeException) {
			coeException.printStackTrace();
		} catch (EOFException eofException) {
			eofException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			closeMethod();
		}
	}

	private void updateDisplay() throws IOException, ClassNotFoundException {
		while (true) {
			int i = 0;
			// screen = (ImageIcon) input.readObject();
			screenShot = ImageIO.read(connection.getInputStream());
			if (screenShot != null) {
				System.out.println(screenShot);
				updateScreen(screenShot);
			}
			/*
			 * Image tmpImg = (Image)screen.getImage(); File f = new
			 * File("input"+i); ImageIO.write((RenderedImage) tmpImg, "png", f);
			 * i++;
			 */
			/*
			 * screen = (ImageIcon) input.readObject(); updateScreen(screen);
			 */
		}
	}

	private void updateScreen(BufferedImage cs) {
		try {
			remove(image);
			// get the screenshot image
			scaledImage = cs.getScaledInstance(screenWidth, screenHeight,
					Image.SCALE_SMOOTH);
			screen = new ImageIcon(scaledImage);
			// Jlabel for showing the Image on screen
			image = new JLabel(screen);

			System.out.println("received");
			add(image, BorderLayout.CENTER);
			image.revalidate();
			repaint();
			//repaint();
			int i = 0;
			/*
			 * while(i< 25000000){ i++; }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// close the streams and shutting down
	private void closeMethod() {
		try {
			// close the input stream
			// input.close();
			// close the connection
			connection.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

}
