package com.apps.swing_app_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author Anatolii Melchenko
 *
 */
public class SinglePageApplication {

	private static final int MAX_NUMBER_VALUE = 1000;
	private static final int BORDER_VALUE = 30;

	private static JFrame mainFrame = getFrame();
	private static JPanel mainPanel = new JPanel();
	private static JPanel leftPanel = new JPanel(new GridLayout(1, 0));
	private static JPanel rightPanel = new JPanel(new GridLayout(2, 1));
	private static List<Integer> numbers = new ArrayList<>();
	private static boolean isNumbersSorted = false;

	private SinglePageApplication() {

	}

	public static void main(String[] args) {

		createSwingApp();

	}

	private static void createSwingApp() {

		mainPanel.setBackground(Color.WHITE);
		mainFrame.add(mainPanel);
		showIntroPagePanel();

	}

	private static void showIntroPagePanel() {

		numbers.clear();
		isNumbersSorted = false;
		mainPanel.setLayout(new FlowLayout());
		clearPanels();

		final JLabel jLabel = new JLabel("How many numbers to display?");
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(jLabel);

		final JTextField jTextField = new JTextField(15);
		mainPanel.add(jTextField);

		final JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				mainPanel.removeAll();
				mainPanel.updateUI();
				String enteredString = jTextField.getText();
				int enteredAmountOfNumbers = 0;
				try {
					enteredAmountOfNumbers = Integer.parseInt(enteredString);
				} catch (Exception exception) {
					exception.printStackTrace(System.out);
				}
				showSortPagePanel(enteredAmountOfNumbers, false);
				mainPanel.repaint();

			}
		});
		mainPanel.add(enterButton);

		mainPanel.revalidate();

	}

	private static void showSortPagePanel(int amountOfNumbers, boolean isSortPageInitialized) {

		if (amountOfNumbers < 0) {
			showIntroPagePanel();
		}

		mainPanel.setLayout(new BorderLayout());

		if (!isSortPageInitialized) {
			if (amountOfNumbers > 0) {
				final Random random = new Random();
				final int indexOfNumberLessThanBorderValue = random.nextInt(amountOfNumbers);
				for (int i = 0; i < amountOfNumbers; i++) {
					if (i == indexOfNumberLessThanBorderValue) {
						numbers.add(generateRandomNumber(BORDER_VALUE + 1));
					} else {
						numbers.add(generateRandomNumber(MAX_NUMBER_VALUE + 1));
					}
				}
			}
		} else {

		}
		List<JPanel> columns = new ArrayList<>();
		int tens = getTens(numbers.size());
		int units = getUnits(numbers.size());
		for (int i = 0; i < tens; i++) {
			columns.add(new JPanel(new GridLayout(10, 1)));
		}
		for (int i = 0; i < tens; i++) {
			int iterationLimit = 10;
			if ((i + 1) == tens) {
				iterationLimit = units;
			}
			for (int j = 0; j < iterationLimit; j++) {
				JButton numberButton = createNumberButton(numbers.get(10 * i + j));
				columns.get(i).add(numberButton);
			}
		}
		for (JPanel column : columns) {
			leftPanel.add(column);
		}
		mainPanel.add(leftPanel, BorderLayout.WEST);
		
		final JButton sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				mainPanel.updateUI();
				clearPanels();
				if (!isNumbersSorted) {
					quickSort(numbers);
					Collections.reverse(numbers);
					isNumbersSorted = true;
				} else {
					Collections.reverse(numbers);
				}
				showSortPagePanel(0, true);
				mainPanel.repaint();
			}
		});
		rightPanel.add(sortButton, BorderLayout.NORTH);

		final JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.removeAll();
				mainPanel.updateUI();
				showIntroPagePanel();
				mainPanel.repaint();
			}
		});
		rightPanel.add(resetButton, BorderLayout.NORTH);
		mainPanel.add(rightPanel, BorderLayout.EAST);

		mainPanel.revalidate();

	}

	private static Integer generateRandomNumber(int randomSpreading) {

		final Random random = new Random();
		final int randomNumber = random.nextInt(randomSpreading);
		return randomNumber;

	}

	private static JButton createNumberButton(Integer number) {

		final JButton numberButton = new JButton(String.valueOf(number));
		numberButton.setBackground(new Color(200, 200, 200));
		addListenerToNumberButton(numberButton, number);
		return numberButton;

	}

	private static void addListenerToNumberButton(JButton button, int buttonNumber) {

		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (buttonNumber <= 30) {
					mainPanel.removeAll();
					mainPanel.updateUI();
					clearPanels();
					for (int i = 0; i < buttonNumber; i++) {
						numbers.add(generateRandomNumber(MAX_NUMBER_VALUE));
						isNumbersSorted = false;
					}
					showSortPagePanel(0, true);
					mainPanel.repaint();
				} else {
					final JPopupMenu popupMenu = new JPopupMenu();
					popupMenu.add(new JMenuItem("Please select a value smaller or equal to 30"));
					Component source = (Component) e.getSource();
					Point location = MouseInfo.getPointerInfo().getLocation();
					SwingUtilities.convertPointFromScreen(location, source);
					popupMenu.show(source, (int) location.getX(), (int) location.getY());
				}
			}

		});

	}

	private static void quickSort(List<Integer> list) {
		int startIndex = 0;
		int endIndex = list.size() - 1;
		doSort(list, startIndex, endIndex);
	}

	private static void doSort(List<Integer> list, int start, int end) {
		List<Integer> initialList = list;
		if (start >= end)
			return;
		int i = start, j = end;
		int cur = i - (i - j) / 2;
		while (i < j) {
			while (i < cur && (list.get(i) <= list.get(cur))) {
				i++;
			}
			while (j > cur && (list.get(cur) <= list.get(j))) {
				j--;
			}
			if (i < j) {
				int temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
				if (i == cur)
					cur = j;
				else if (j == cur)
					cur = i;
			}
		}
		doSort(initialList, start, cur);
		doSort(initialList, cur + 1, end);
	}

	private static int getTens(int number) {

		int tens = number / 10 + 1;
		return tens;

	}

	private static int getUnits(int number) {

		int units = number % 10;
		return units;

	}

	private static void clearPanels() {

		leftPanel.removeAll();
		leftPanel.revalidate();
		rightPanel.removeAll();
		rightPanel.revalidate();

	}

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
