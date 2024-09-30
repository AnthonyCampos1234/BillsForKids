package controller;
import java.util.ArrayList;
import java.util.List;
import model.Allowance;
import model.Bill;
import model.Job;
import model.Piggybank;
import model.Savings;
import view.BillsForKidsView;

public class BillsForKidsController {
  private Allowance allowance;
  private Savings savings;
  private Piggybank piggybank;
  private List<Bill> bills;
  private BillsForKidsView view;
  private Job job; // Add this line

  public BillsForKidsController(BillsForKidsView view) {
    this.view = view;
    this.allowance = new Allowance(20); // I think $20 a week is sufficient
    this.savings = new Savings();
    this.piggybank = new Piggybank();
    this.bills = new ArrayList<>();
    this.job = new Job("Paper Route", 10.0); // default job
    initializeBills();
  }

  private void initializeBills() {
    bills.add(new Bill("Clash Of Clans Gold Plan", 5.0, 30));
    bills.add(new Bill("School Lunch", 10.0, 1));
    bills.add(new Bill("Netflix Subscription", 7.0, 30));
  }

  public void run() {
    while (true) {
      view.displayMenu();
      int choice = view.getMenuChoice();

      switch (choice) {
        case 1:
          collectAllowance();
          break;
        case 2:
          payBills();
          break;
        case 3:
          saveMoney();
          break;
        case 4:
          spendMoney();
          break;
        case 5:
          checkFinances();
          break;
        case 6:
          passDay();
          break;
        case 7:
          workJob();
          break;
        case 8:
          view.displayMessage("Thanks for playing BillsForKids! Goodbye!");
          return;
        default:
          view.displayMessage("Invalid choice!");
      }
    }
  }

  private void collectAllowance() {
    double amount = allowance.collectAllowance();
    if (amount > 0) {
      piggybank.deposit(amount);
      view.displayAllowance(amount, allowance.getDaysUntilAllowance());
    } else {
      view.displayMessage("Sorry, it's not allowance day yet!");
      view.displayMessage("Days until next allowance: " + allowance.getDaysUntilAllowance());
    }
  }

  private void payBills() {
    view.displayBills(bills);
    int choice = view.getBillChoice();
    Bill chosenBill = bills.get(choice);
    if (chosenBill.isDue()) {
      if (piggybank.withdraw(chosenBill.getAmount())) {
        view.displayMessage("You paid the " + chosenBill.getName() + " bill!");
        view.displayMessage("Great job managing your responsibilities!");
      } else {
        view.displayMessage("Sorry, you don't have enough money to pay this bill.");
        view.displayMessage("Try saving more or waiting for your next allowance!");
      }
    } else {
      view.displayMessage("This bill is not due yet. You can wait to pay it!");
    }
  }

  private void saveMoney() {
    double amount = view.getSavingsAmount();
    if (piggybank.withdraw(amount)) {
      savings.deposit(amount);
      view.displayMessage("You saved $" + amount + "! Great job thinking about the future!");
    } else {
      view.displayMessage("Sorry, you don't have that much money in your piggy bank to save.");
    }
  }

  private void spendMoney() {
    double amount = view.getSpendingAmount();
    if (piggybank.withdraw(amount)) {
      view.displayMessage("You spent $" + amount + " on something fun!");
      view.displayMessage("Remember to balance spending with saving and paying bills!");
    } else {
      view.displayMessage("Sorry, you don't have that much money in your piggy bank to spend.");
    }
  }

  private void checkFinances() {
    view.displayFinances(savings.getBalance(), piggybank.getBalance());
    view.displayBills(bills);
    view.displayJobInfo(job.getName(), job.getHourlyWage());
  }

  private void passDay() {
    allowance.passDay();
    for (Bill bill : bills) {
      bill.passDay();
    }
    view.displayMessage("A day has passed!");
    view.displayMessage("Days until next allowance: " + allowance.getDaysUntilAllowance());
  }

  private void workJob() {
    int hours = view.getWorkHours();
    job.work(hours);
    double earnings = job.calculateEarnings();
    piggybank.deposit(earnings);
    view.displayJobEarnings(earnings);
  }

  private void changeJob() {
    String name = view.getJobName();
    double wage = view.getHourlyWage();
    job = new Job(name, wage);
    view.displayMessage("Your job has been updated!");
  }
}
