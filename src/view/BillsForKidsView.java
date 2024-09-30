package view;

import model.Bill;

import java.util.List;
import java.util.Scanner;

public class BillsForKidsView {
  private Scanner scanner = new Scanner(System.in);

  public void displayMenu() {
    System.out.println("\n--- BillsForKids: Learn to Manage Your Money! ---");
    System.out.println("1. Collect Allowance");
    System.out.println("2. Pay Bills");
    System.out.println("3. Save Money");
    System.out.println("4. Spend Money");
    System.out.println("5. Check Finances");
    System.out.println("6. Pass a Day");
    System.out.println("7. Exit Game");
    System.out.println("What would you like to do?");
  }

  public int getMenuChoice() {
    return scanner.nextInt();
  }

  public void displayAllowance(double amount, int daysUntilNext) {
    System.out.printf("You received $%.2f as allowance!\n", amount);
    System.out.printf("Next allowance in %d days.\n", daysUntilNext);
  }

  public void displayBills(List<Bill> bills) {
    System.out.println("Your Bills:");
    for (int i = 0; i < bills.size(); i++) {
      System.out.printf("%d. %s\n", i + 1, bills.get(i));
    }
  }

  public int getBillChoice() {
    System.out.print("Which bill would you like to pay? (Enter the number) ");
    return scanner.nextInt() - 1;
  }

  public double getSavingsAmount() {
    System.out.print("How much would you like to save? $");
    return scanner.nextDouble();
  }

  public double getSpendingAmount() {
    System.out.print("How much would you like to spend? $");
    return scanner.nextDouble();
  }

  public void displayFinances(double savings, double piggyBank) {
    System.out.printf("Your savings: $%.2f\n", savings);
    System.out.printf("Your spending money: $%.2f\n", piggyBank);
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }
}
