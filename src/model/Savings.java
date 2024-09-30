package model;

public class Savings {
  private double balance;

  public Savings() {
    this.balance = 0;
  }

  public void deposit(double amount) {
    balance += amount;
  }

  public boolean withdraw(double amount) {
    if (balance >= amount) {
      balance -= amount;
      return true;
    }
    return false;
  }

  public double getBalance() {
    return balance;
  }
}
