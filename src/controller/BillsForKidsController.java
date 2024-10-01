package controller;

import model.*;
import view.BillsForKidsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BillsForKidsController {
  private Allowance allowance;
  private Savings savings;
  private Piggybank piggybank;
  private List<Bill> bills;
  private BillsForKidsView view;
  private Job job;

  public BillsForKidsController(BillsForKidsView view) {
    this.view = view;
    this.allowance = new Allowance(20);
    this.savings = new Savings();
    this.piggybank = new Piggybank();
    this.bills = new ArrayList<>();
    this.job = new Job("Paper Route", 10.0);
    initializeBills();
    setupListeners();
    updateView();
  }

  private void initializeBills() {
    bills.add(new Bill("Clash Of Clans Gold Plan", 5.0, 30));
    bills.add(new Bill("School Lunch", 10.0, 1));
    bills.add(new Bill("Netflix Subscription", 7.0, 30));
  }

  private void setupListeners() {
    view.setActionListener("collectAllowance", e -> collectAllowance());
    view.setActionListener("payBills", e -> payBills());
    view.setActionListener("saveMoney", e -> saveMoney());
    view.setActionListener("spendMoney", e -> spendMoney());
    view.setActionListener("checkFinances", e -> checkFinances());
    view.setActionListener("passDay", e -> passDay());
    view.setActionListener("workJob", e -> workJob());
  }

  private void collectAllowance() {
    double amount = allowance.collectAllowance();
    if (amount > 0) {
      piggybank.deposit(amount);
      view.displayMessage(String.format("You received $%.2f as allowance!", amount));
      view.displayMessage(String.format("Next allowance in %d days.", allowance.getDaysUntilAllowance()));
    } else {
      view.displayMessage("Sorry, it's not allowance day yet!");
      view.displayMessage("Days until next allowance: " + allowance.getDaysUntilAllowance());
    }
    updateView();
  }

  private void payBills() {
    if (bills.isEmpty()) {
      view.displayMessage("You have no bills to pay right now!");
      return;
    }
    StringBuilder billsMessage = new StringBuilder("Your Bills:\n");
    for (int i = 0; i < bills.size(); i++) {
      Bill bill = bills.get(i);
      billsMessage.append(String.format("%d. %s - $%.2f (Due in %d days)\n", i + 1, bill.getName(), bill.getAmount(), bill.getDaysUntilDue()));
    }
    view.showInputDialog(billsMessage + "\nEnter the number of the bill you want to pay:", new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          int choice = Integer.parseInt(e.getActionCommand()) - 1;
          if (choice >= 0 && choice < bills.size()) {
            Bill chosenBill = bills.get(choice);
            if (piggybank.withdraw(chosenBill.getAmount())) {
              view.displayMessage("You paid the " + chosenBill.getName() + " bill!");
              view.displayMessage("Great job managing your responsibilities!");
              bills.remove(choice);
            } else {
              view.displayMessage("Sorry, you don't have enough money to pay this bill.");
              view.displayMessage("Try saving more or waiting for your next allowance!");
            }
          } else {
            view.displayMessage("Invalid bill number. Please try again.");
          }
        } catch (NumberFormatException ex) {
          view.displayMessage("Invalid input. Please enter a number.");
        }
        updateView();
      }
    });
  }

  private void saveMoney() {
    view.showInputDialog("How much money do you want to save?", new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          double amount = Double.parseDouble(e.getActionCommand());
          if (piggybank.withdraw(amount)) {
            savings.deposit(amount);
            view.displayMessage("You saved $" + amount + "! Great job thinking about the future!");
          } else {
            view.displayMessage("Sorry, you don't have that much money in your piggy bank to save.");
          }
        } catch (NumberFormatException ex) {
          view.displayMessage("Invalid input. Please enter a number.");
        }
        updateView();
      }
    });
  }

  private void spendMoney() {
    view.showInputDialog("How much money do you want to spend?", new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          double amount = Double.parseDouble(e.getActionCommand());
          if (piggybank.withdraw(amount)) {
            view.displayMessage("You spent $" + amount + " on something fun!");
            view.displayMessage("Remember to balance spending with saving and paying bills!");
          } else {
            view.displayMessage("Sorry, you don't have that much money in your piggy bank to spend.");
          }
        } catch (NumberFormatException ex) {
          view.displayMessage("Invalid input. Please enter a number.");
        }
        updateView();
      }
    });
  }

  private void checkFinances() {
    view.clearOutput();
    view.displayFinances(savings.getBalance(), piggybank.getBalance());
    view.displayBills(bills);
    view.displayJobInfo(job.getName(), job.getHourlyWage());
    updateView();
  }

  private void passDay() {
    view.showConfirmDialog("Are you sure you want to pass a day?",
            e -> {
              allowance.passDay();
              for (Bill bill : bills) {
                bill.passDay();
              }
              view.displayMessage("A day has passed!");
              view.displayMessage("Days until next allowance: " + allowance.getDaysUntilAllowance());
              updateView();
            },
            e -> view.displayMessage("Okay, no day passed.")
    );
  }

  private void workJob() {
    view.showInputDialog("How many hours do you want to work?", new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          int hours = Integer.parseInt(e.getActionCommand());
          job.work(hours);
          double earnings = job.calculateEarnings();
          piggybank.deposit(earnings);
          view.displayJobEarnings(earnings);
        } catch (NumberFormatException ex) {
          view.displayMessage("Invalid input. Please enter a number.");
        }
        updateView();
      }
    });
  }

  private void updateView() {
    view.updateBalance(piggybank.getBalance(), savings.getBalance());
    view.updateSavingsGoal(savings.getBalance(), 1500.0);
  }
}