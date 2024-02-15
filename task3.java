import java.util.Scanner;

public class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("Welcome to the ATM.");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayOptions();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        if (userAccount.getBalance() < amount) {
            System.out.println("Insufficient balance.");
        } else {
            userAccount.withdraw(amount);
            System.out.println("Withdrawal successful. Remaining balance: $" + userAccount.getBalance());
        }
    }

    private void deposit() {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        userAccount.deposit(amount);
        System.out.println("Deposit successful. New balance: $" + userAccount.getBalance());
    }

    private void checkBalance() {
        System.out.println("Your balance: $" + userAccount.getBalance());
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(100); // Initialize account balance to $100
        ATM atm = new ATM(account);
        atm.run();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }
        if (balance < amount) {
            System.out.println("Insufficient balance for withdrawal.");
            return;
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}