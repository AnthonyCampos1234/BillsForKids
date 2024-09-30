package main;
import controller.BillsForKidsController;
import view.BillsForKidsView;

public class Main {
  public static void main(String[] args) {
    BillsForKidsView view = new BillsForKidsView();
    BillsForKidsController controller = new BillsForKidsController(view);
    controller.run();
  }
}
