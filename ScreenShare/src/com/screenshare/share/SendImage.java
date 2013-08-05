package com.screenshare.share;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import com.screenshare.client.CaptureScreen;

public class SendImage {

	/**
	 * @param args
	 */
	int send_port = 5001;
	int myport = 5000;
	Socket ss;
	DataOutputStream dos = null;
	

	public SendImage() {
		try {
			ss = new Socket(InetAddress.getByName("127.0.0.1"),send_port);
			dos = new DataOutputStream(ss.getOutputStream());

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(BufferedImage bImg) throws IOException {
		ByteArrayOutputStream writer = new ByteArrayOutputStream();

		ImageIO.write(bImg, "jpg", writer);
		byte[] bytePhoto = writer.toByteArray();
		
		System.out.println(bytePhoto.length);
		dos.write(bytePhoto);
	
	}

	public static void main(String[] args) throws IOException, AWTException {
		// TODO Auto-generated method stub

		SendImage si = new SendImage();
		//si.send(ImageIO.read(new File("hello1.jpg")));
		CaptureScreen cs = CaptureScreen.getInstance();
		si.send(cs.getScreen());

	}

}
