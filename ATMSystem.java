import java.util.Scanner;

// Class representing the user's bank account
class BankAccount {
    private double balance;


    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }


    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    
    public double getBalance() {
        return balance;
    }
}


class ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    
    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    
    public void showMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    // Process the user's choice
    public boolean processChoice(int choice) {
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                depositMoney();
                break;
            case 3:
                withdrawMoney();
                break;
            case 4:
                System.out.println("Exiting... Thank you for using the ATM!");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    // Check and display the balance
    private void checkBalance() {
        System.out.printf("Your balance is: $%.2f%n", bankAccount.getBalance());
    }

    // Handle deposit operation
    private void depositMoney() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        if (bankAccount.deposit(amount)) {
            System.out.printf("Successfully deposited $%.2f.%n", amount);
        } else {
            System.out.println("Deposit failed. Amount must be positive.");
        }
    }

    // Handle withdrawal operation
    private void withdrawMoney() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (bankAccount.withdraw(amount)) {
            System.out.printf("Successfully withdrew $%.2f.%n", amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }
}

// Main class to run the ATM program
public class ATMSystem {
    public static void main(String[] args) {
        // Create a BankAccount with an initial balance of 1000.0
        BankAccount account = new BankAccount(1000.0);

        
        ATM atm = new ATM(account);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        
        while (running) {
            atm.showMenu();
            System.out.print("Please enter your choice (1-4): ");
            try {
                int choice = scanner.nextInt();
                running = atm.processChoice(choice);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Clear the invalid input
            }
        }


        scanner.close();
    }
}
