package model;

public class Allowance {
  private double weeklyAmount;
  private int daysSinceLastAllowance;

  public Allowance(double weeklyAmount) {
    this.weeklyAmount = weeklyAmount;
    this.daysSinceLastAllowance = 0;
  }

  public double collectAllowance() {
    if (daysSinceLastAllowance >= 7) {
      daysSinceLastAllowance = 0;
      return weeklyAmount;
    }
    return 0;
  }

  public void passDay() {
    daysSinceLastAllowance++;
  }

  public int getDaysUntilAllowance() {
    return Math.max(0, 7 - daysSinceLastAllowance);
  }
}
