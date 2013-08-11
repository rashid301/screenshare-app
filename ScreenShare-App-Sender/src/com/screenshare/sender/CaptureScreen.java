package com.screenshare.sender;

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
	private static CaptureScreen cs = null;

	public static CaptureScreen getInstance() {
		if (cs == null) {
			cs = new CaptureScreen();
		}
		return cs;
	}

	public BufferedImage getScreen() throws AWTException {
		robot = new Robot();

		screenshot = robot.createScreenCapture(new Rectangle(Toolkit
				.getDefaultToolkit().getScreenSize()));
		return screenshot;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CaptureScreen();
	}

}
