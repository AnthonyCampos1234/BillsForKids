package main;

import controller.BillsForKidsController;
import view.BillsForKidsView;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			BillsForKidsView view = new BillsForKidsView();
			new BillsForKidsController(view);
		});
	}
}