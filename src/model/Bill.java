package model;

public class Bill {
  private String name;
  private double amount;
  private int dueEveryXDays;
  private int daysUntilDue;

  public Bill(String name, double amount, int dueEveryXDays) {
    this.name = name;
    this.amount = amount;
    this.dueEveryXDays = dueEveryXDays;
    this.daysUntilDue = dueEveryXDays;
  }

  public void passDay() {
    daysUntilDue--;
    if (daysUntilDue <= 0) {
      daysUntilDue = dueEveryXDays;
    }
  }

  public boolean isDue() {
    return daysUntilDue == 0;
  }

  public String getName() { return name; }
  public double getAmount() { return amount; }
  public int getDaysUntilDue() { return daysUntilDue; }

  @Override
  public String toString() {
    return String.format("%s: $%.2f (Due in %d days)", name, amount, daysUntilDue);
  }
}