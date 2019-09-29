package Classes.BankAccount;

public class BankAccount {
    private int id;
    private double balance;


    private static int accountCounts = 0;
    private static double interestRate = 0.02;

    public BankAccount() {
        this.id = ++accountCounts;
        this.balance = 0;
    }

    public double getInterest(int years) {
        return interestRate * years * this.balance;
    }

    public static void setInterest(double interest) {
        interestRate = interest;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }
}
