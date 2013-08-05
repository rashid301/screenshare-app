package com.screenshare.share;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

public class ReceiveImage {

	/**
	 * @param args
	 */
	int port = 5001;
	ServerSocket ss = null;
	Socket soc = null;
	BufferedReader br;
	InputStreamReader is;
	DataInputStream dis;
	
	public ReceiveImage(){
		try {
			ss = new ServerSocket(port);
			
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage receive() throws IOException{
		soc = ss.accept();
		
		br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		dis = new DataInputStream(soc.getInputStream());
		//byte[] buffer = new byte[65536];
		
		byte[] resultBuff = new byte[0];
	    byte[] buff = new byte[1024];
	    int k = -1;
	    while((k = dis.read(buff, 0, buff.length)) > -1) {
	        byte[] tbuff = new byte[resultBuff.length + k]; // temp buffer size = bytes already read + bytes last read
	        System.arraycopy(resultBuff, 0, tbuff, 0, resultBuff.length); // copy previous bytes
	        System.arraycopy(buff, 0, tbuff, resultBuff.length, k);  // copy current lot
	        resultBuff = tbuff; // call the temp buffer as your result buff
	    }
		return ImageIO.read(new ByteArrayInputStream(resultBuff));
	}
}
