package com.screenshare.client;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.screenshare.gui.MainFrame;
import com.screenshare.share.ReceiveImage;

public class ClientMain implements Runnable {

	/**
	 * @param args
	 */
	MainFrame mf = null;
	ReceiveImage ri = null;
	Thread main = null;

	public ClientMain() {
		ri = new ReceiveImage();
		main = new Thread(this);
		main.start();
	}

	public void run() {
		BufferedImage bi;
		mf = new MainFrame();
		try {
			while (true) {
				bi = ri.receive();
				mf.display(bi);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new ClientMain();
	}

}
