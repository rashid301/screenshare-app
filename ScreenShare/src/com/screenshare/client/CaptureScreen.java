package com.screenshare.client;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class CaptureScreen {

	/**
	 * @param args
	 */
	Robot robot;
	BufferedImage screenshot;
	public CaptureScreen(){
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			System.err.println("Robot error");
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage getScreen(){
		screenshot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		return screenshot;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CaptureScreen();
	}

}
