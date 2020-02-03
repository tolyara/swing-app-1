package com.apps.swing_app_1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 * @author Anatolii Melchenko
 *
 */
public class SinglePageApplication {
	
	private static final int MAX_NUMBER_VALUE = 1000;

	public static void main(String[] args) {

		createSwingApp();

	}

	private static void createSwingApp() {

		JFrame jFrame = getFrame();
		JPanel jPanel = new JPanel();
		jFrame.add(jPanel);
	
		JLabel jLabel = new JLabel("How many numbers to display?");
		jPanel.add(jLabel);
		
		JTextField jTextField = new JTextField(20);
		jPanel.add(jTextField);

		JButton jButton = new JButton("Enter");
//		jButton.setBackground(Color.MAGENTA);
//		jButton.setOpaque(true);
//		jButton.setBorderPainted(false);
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				jPanel.setBackground(Color.BLUE);
				System.out.println(jTextField.getText());
			}
		});
		jPanel.add(jButton);
		
		JPopupMenu jPopupMenu = new JPopupMenu();
		jPopupMenu.add(new JMenuItem("test"));
		jPanel.setComponentPopupMenu(jPopupMenu);
		
		jPanel.revalidate();

//		JScrollBar jScrollBar = new JScrollBar();
//		jPanel.add(jScrollBar);


	}

	private static JFrame getFrame() {

		JFrame jFrame = new JFrame();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		jFrame.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, 500, 300);
		jFrame.setTitle("Single Page Application");
		return jFrame;

	}

}
