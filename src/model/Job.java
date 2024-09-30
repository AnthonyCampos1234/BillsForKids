package model;

public class Job {
    private String name;
    private double hourlyWage;
    private int hoursWorked;

    public Job(String name, double hourlyWage) {
        this.name = name;
        this.hourlyWage = hourlyWage;
        this.hoursWorked = 0;
    }
    
    public void work(int hours) {
        this.hoursWorked += hours;
    }

    public double calculateEarnings() {
        return hoursWorked * hourlyWage;
    }
    
    public String getName() {
        return name;
    }
    
    public double getHourlyWage() {
        return hourlyWage;
    }
    
    public int getHoursWorked() {
        return hoursWorked;
    }
}
