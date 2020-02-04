package com.apps.swing_app_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Anatolii Melchenko
 *
 */
public class SinglePageApplication {

	private static final int MAX_NUMBER_VALUE = 1000;
	private static final int BORDER_VALUE = 31;

	private static JFrame jFrame = getFrame();
	private static JPanel jPanel = new JPanel();

	private SinglePageApplication() {

	}

	public static void main(String[] args) {

		createSwingApp();

	}

	private static void createSwingApp() {

		jPanel.setBackground(Color.WHITE);
		jFrame.add(jPanel);
		generateIntroPagePanel();

	}

	private static void generateIntroPagePanel() {

		final JLabel jLabel = new JLabel("How many numbers to display?");
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		jPanel.add(jLabel);

		final JTextField jTextField = new JTextField(15);
		jPanel.add(jTextField);

		final JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				jPanel.removeAll();
				jPanel.updateUI();
				String enteredString = jTextField.getText();
				int enteredAmountOfNumbers = 0;
				try {
					enteredAmountOfNumbers = Integer.parseInt(enteredString);
				} catch (Exception exception) {

				}
				generateSortPagePanel(enteredAmountOfNumbers);
				jPanel.repaint();

			}
		});
		jPanel.add(enterButton);

//		JPopupMenu jPopupMenu = new JPopupMenu();
//		jPopupMenu.add(new JMenuItem("test"));
//		jPanel.setComponentPopupMenu(jPopupMenu);

		jPanel.revalidate();

//		JScrollBar jScrollBar = new JScrollBar();
//		jPanel.add(jScrollBar);

	}

	private static void generateSortPagePanel(int amountOfNumbers) {

//		jPanel.setLayout(new BorderLayout());

		final Random random = new Random();
		List<Integer> numbers = new ArrayList<Integer>();
		final int indexOfNumberLessThanBorderValue = random.nextInt(amountOfNumbers);
		for (int i = 0; i < amountOfNumbers; i++) {
			if (i == indexOfNumberLessThanBorderValue) {
				numbers.add(generateRandomNumberButton(BORDER_VALUE));
			} else {
				numbers.add(generateRandomNumberButton(MAX_NUMBER_VALUE));
			}
		}

		final JButton sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel.removeAll();
				jPanel.updateUI();
//				Collections.sort(numbers);
//				Collections.reverse(numbers);
//				quickSort(
				sortNumbersOnSortPagePanel(numbers);
				jPanel.repaint();
			}
		});
		jPanel.add(sortButton);

		final JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel.removeAll();
				jPanel.updateUI();
				generateIntroPagePanel();
				jPanel.repaint();
			}
		});
		jPanel.add(resetButton);

		jPanel.revalidate();

	}
	
	private static void sortNumbersOnSortPagePanel(List<Integer> numberButtons) {
		
		for (Integer number : numberButtons) {
			createNumberButton(number);
		}

		final JButton sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel.removeAll();
				jPanel.updateUI();
				Collections.reverse(numberButtons);
				sortNumbersOnSortPagePanel(numberButtons);
				jPanel.repaint();
			}
		});
		jPanel.add(sortButton);

		final JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel.removeAll();
				jPanel.updateUI();
				generateIntroPagePanel();
				jPanel.repaint();
			}
		});
		jPanel.add(resetButton);

		jPanel.revalidate();
		
	}
	
	private static int generateRandomNumberButton(int randomSpreading) {
		
		final Random random = new Random();
		final int randomNumber = random.nextInt(randomSpreading);
		final JButton numberButton = new JButton(String.valueOf(randomNumber));
		numberButton.setBackground(new Color(200, 200, 200));
		jPanel.add(numberButton);
		return randomNumber;
		
	}
	
	private static void createNumberButton(int number) {
		
		final JButton numberButton = new JButton(String.valueOf(number));
		numberButton.setBackground(new Color(200, 200, 200));
		jPanel.add(numberButton);
		
	}
	
//	public static void quickSort(int[] array) {
//        int startIndex = 0;
//        int endIndex = ARRAY_LENGTH - 1;
//        doSort(startIndex, endIndex);
//    }
//
//    private static void doSort(int start, int end) {
//        if (start >= end)
//            return;
//        int i = start, j = end;
//        int cur = i - (i - j) / 2;
//        while (i < j) {
//            while (i < cur && (array[i] <= array[cur])) {
//                i++;
//            }
//            while (j > cur && (array[cur] <= array[j])) {
//                j--;
//            }
//            if (i < j) {
//                int temp = array[i];
//                array[i] = array[j];
//                array[j] = temp;
//                if (i == cur)
//                    cur = j;
//                else if (j == cur)
//                    cur = i;
//            }
//        }
//        doSort(start, cur);
//        doSort(cur+1, end);
//    }

	private static JFrame getFrame() {

		final JFrame jFrame = new JFrame();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension dimension = toolkit.getScreenSize();
		jFrame.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, 500, 300);
		jFrame.setTitle("Single Page Application");
		return jFrame;

	}

}
