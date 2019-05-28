package Classes.BankAccount;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        HashMap<Integer, BankAccount> myBankAccounts = new HashMap<>();
        while (!"End".equals(input)) {
            String[] data = input.split("\\s+");
            String command = data[0];
            String result = "";
            switch (command) {
                case "Create":
                    BankAccount account = new BankAccount();
                    myBankAccounts.put(account.getId(), account);
                    result = String.format("Account ID%d created", account.getId());
                    break;

                case "Deposit":
                    int accountId = Integer.parseInt(data[1]);
                    double amount = Double.parseDouble(data[2]);
                    if (myBankAccounts.containsKey(accountId)) {
                        myBankAccounts.get(accountId).setBalance(amount);
                        result = String.format("Deposited %.0f to ID%d", amount, accountId);
                    } else {
                        result = "Account does not exist";
                    }
                    break;

                case "SetInterest":
                    BankAccount.setInterest(Double.parseDouble(data[1]));
                    break;

                case "GetInterest":
                    accountId = Integer.parseInt(data[1]);
                    int years = Integer.parseInt(data[2]);
                    if (myBankAccounts.containsKey(accountId)) {
                        double interest = myBankAccounts.get(accountId).getInterest(years);
                        result = String.format("%.2f", interest);
                    } else {
                        result = "Account does not exist";
                    }
                    break;
            }
            if (!result.isEmpty()) {
                System.out.println(result);
            }
            input = sc.nextLine();
        }
    }
}
