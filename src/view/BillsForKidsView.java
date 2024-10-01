package view;

import model.Bill;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BillsForKidsView extends JFrame {
	private JButton collectAllowanceButton, payBillsButton, saveMoneyButton, spendMoneyButton;
	private JButton checkFinancesButton, passDayButton, workJobButton;
	private JTextArea outputArea;
	private JPanel mainPanel;
	private JLabel balanceLabel, savingsLabel;
	private JProgressBar savingsGoalProgress;
	private JLabel mascotLabel;
	private Timer animationTimer;

	private Color backgroundColor = new Color(230, 255, 253);
	private Color buttonColor = new Color(255, 217, 102);
	private Color textColor = new Color(70, 70, 70);

	public BillsForKidsView() {
		setTitle("BillsForKids: Your Fun Finance Adventure!");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel(new BorderLayout(15, 15));
		mainPanel.setBackground(backgroundColor);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setContentPane(mainPanel);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setOpaque(false);
		topPanel.add(createBalancePanel(), BorderLayout.WEST);
		topPanel.add(createMascotPanel(), BorderLayout.EAST);
		mainPanel.add(topPanel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel.setOpaque(false);
		centerPanel.add(createButtonPanel(), BorderLayout.WEST);
		centerPanel.add(createOutputPanel(), BorderLayout.CENTER);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
		bottomPanel.setOpaque(false);
		bottomPanel.add(createSavingsGoalPanel(), BorderLayout.SOUTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		initializeAnimationTimer();
		setVisible(true);
	}

	private JPanel createBalancePanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.setOpaque(false);
		balanceLabel = new JLabel("Your Treasure: $0.00");
		balanceLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		balanceLabel.setForeground(new Color(255, 83, 13));
		savingsLabel = new JLabel("Your Savings: $0.00");
		savingsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		savingsLabel.setForeground(new Color(46, 204, 113));
		panel.add(balanceLabel);
		panel.add(savingsLabel);
		return panel;
	}

	private JPanel createMascotPanel() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		mascotLabel = new JLabel(new ImageIcon("path/to/mascot.gif"));
		panel.add(mascotLabel);
		return panel;
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(7, 1, 0, 10));
		panel.setOpaque(false);
		panel.setBorder(createRoundedBorder("Money Actions"));

		collectAllowanceButton = createStyledButton("Collect Allowance", new ImageIcon("path/to/allowance_icon.png"));
		payBillsButton = createStyledButton("Pay Bills", new ImageIcon("path/to/bills_icon.png"));
		saveMoneyButton = createStyledButton("Save Money", new ImageIcon("path/to/save_icon.png"));
		spendMoneyButton = createStyledButton("Spend Money", new ImageIcon("path/to/spend_icon.png"));
		checkFinancesButton = createStyledButton("Check Finances", new ImageIcon("path/to/check_icon.png"));
		passDayButton = createStyledButton("Next Day", new ImageIcon("path/to/calendar_icon.png"));
		workJobButton = createStyledButton("Work Job", new ImageIcon("path/to/work_icon.png"));

		panel.add(collectAllowanceButton);
		panel.add(payBillsButton);
		panel.add(saveMoneyButton);
		panel.add(spendMoneyButton);
		panel.add(checkFinancesButton);
		panel.add(passDayButton);
		panel.add(workJobButton);

		return panel;
	}

	private JButton createStyledButton(String text, ImageIcon icon) {
		JButton button = new JButton(text, icon);
		button.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		button.setBackground(buttonColor);
		button.setForeground(textColor);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(true);
		button.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createRaisedBevelBorder(),
						new EmptyBorder(5, 10, 5, 10)
		));
		button.setToolTipText("Click to " + text.toLowerCase());
		return button;
	}

	private JPanel createOutputPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		outputArea.setBackground(new Color(255, 253, 208));
		outputArea.setForeground(textColor);
		JScrollPane scrollPane = new JScrollPane(outputArea);
		scrollPane.setBorder(createRoundedBorder("Your Money Story"));
		panel.add(scrollPane, BorderLayout.CENTER);
		return panel;
	}

	private JPanel createSavingsGoalPanel() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setOpaque(false);
		panel.setBorder(createRoundedBorder("Savings Goal"));

		savingsGoalProgress = new JProgressBar(0, 100);
		savingsGoalProgress.setStringPainted(true);
		savingsGoalProgress.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		savingsGoalProgress.setForeground(new Color(46, 204, 113));
		savingsGoalProgress.setBackground(new Color(236, 240, 241));

		panel.add(new JLabel("Progress:"), BorderLayout.WEST);
		panel.add(savingsGoalProgress, BorderLayout.CENTER);

		return panel;
	}

	private Border createRoundedBorder(String title) {
		return BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(new Color(255, 183, 77), 2, true),
						BorderFactory.createTitledBorder(
										BorderFactory.createEmptyBorder(5, 5, 5, 5),
										title,
										TitledBorder.CENTER,
										TitledBorder.TOP,
										new Font("Comic Sans MS", Font.BOLD, 16),
										new Color(255, 83, 13)
						)
		);
	}

	private void initializeAnimationTimer() {
		animationTimer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				animateMascot();
			}
		});
		animationTimer.start();
	}

	private void animateMascot() {
		// Change mascot image or position for animation effect
		// This is a placeholder - implement actual animation logic
		mascotLabel.setIcon(new ImageIcon("path/to/mascot_animated.gif"));
	}

	public void updateSavingsGoal(double current, double goal) {
		int percentage = (int) ((current / goal) * 100);
		savingsGoalProgress.setValue(percentage);
		savingsGoalProgress.setString(String.format("$%.2f / $%.2f", current, goal));
	}

	public void displayMessage(String message) {
		outputArea.append("🎈 " + message + "\n");
		outputArea.setCaretPosition(outputArea.getDocument().getLength());
	}

	public void clearOutput() {
		outputArea.setText("");
	}

	public void updateBalance(double balance, double savings) {
		balanceLabel.setText(String.format("Your Treasure: $%.2f", balance));
		savingsLabel.setText(String.format("Your Savings: $%.2f", savings));
	}

	public void setActionListener(String action, ActionListener listener) {
		switch (action) {
			case "collectAllowance":
				collectAllowanceButton.addActionListener(listener);
				break;
			case "payBills":
				payBillsButton.addActionListener(listener);
				break;
			case "saveMoney":
				saveMoneyButton.addActionListener(listener);
				break;
			case "spendMoney":
				spendMoneyButton.addActionListener(listener);
				break;
			case "checkFinances":
				checkFinancesButton.addActionListener(listener);
				break;
			case "passDay":
				passDayButton.addActionListener(listener);
				break;
			case "workJob":
				workJobButton.addActionListener(listener);
				break;
		}
	}

	public void showInputDialog(String message, ActionListener listener) {
		String input = JOptionPane.showInputDialog(this, message, "Input", JOptionPane.QUESTION_MESSAGE);
		if (input != null && !input.trim().isEmpty()) {
			listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, input));
		}
	}

	public void showConfirmDialog(String message, ActionListener yesListener, ActionListener noListener) {
		int result = JOptionPane.showConfirmDialog(this, message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			yesListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "yes"));
		} else {
			noListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "no"));
		}
	}

	public void showOptionsDialog(String title, String message, String[] options, ActionListener listener) {
		int choice = JOptionPane.showOptionDialog(this, message, title,
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[0]);
		if (choice >= 0) {
			listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(choice)));
		}
	}


	public void displayBills(List<Bill> bills) {
		StringBuilder billsMessage = new StringBuilder("Your Bills:\n");
		for (int i = 0; i < bills.size(); i++) {
			Bill bill = bills.get(i);
			billsMessage.append(String.format("%d. %s - $%.2f (Due in %d days)\n",
							i + 1, bill.getName(), bill.getAmount(), bill.getDaysUntilDue()));
		}
		displayMessage(billsMessage.toString());
	}

	public void displayFinances(double savings, double piggyBank) {
		displayMessage(String.format("Your savings: $%.2f", savings));
		displayMessage(String.format("Your spending money: $%.2f", piggyBank));
	}

	public void displayJobInfo(String name, double hourlyWage) {
		displayMessage(String.format("Your job: %s (Hourly wage: $%.2f)", name, hourlyWage));
	}

	public void displayJobEarnings(double earnings) {
		displayMessage(String.format("You earned $%.2f from your job!", earnings));
	}

	public void playSound(String soundFile) {
		// This is a placeholder - implement actual sound playing logic
		System.out.println("Playing sound: " + soundFile);
	}
}