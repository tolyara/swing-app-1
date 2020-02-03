package com.apps.swing_app_1;

import javax.swing.JFrame;

/**
 * @author Anatolii Melchenko
 *
 */
public class SinglePageApplication {
	
	public static void main(String[] args) {
		
		JFrame jFrame = new JFrame();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(500, 300);
		jFrame.setLocation(500, 400);
		jFrame.setTitle("Single Page Application");
		
	}
}
