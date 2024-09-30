package model;

public class Piggybank {
  private double balance;

  public Piggybank() {
    this.balance = 0;
  }

  public void deposit(double amount) {
    this.balance += amount;
  }

  public boolean withdraw(double amount) {
    if (this.balance >= amount) {
      this.balance -= amount;
      return true;
    }
    return false;
  }

  public double getBalance() {
    return this.balance;
  }
}
