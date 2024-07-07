import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private double balance; // Stores the current balance
    private int pin; // Stores the current PIN
    private ArrayList<String> transactionHistory; // Stores transaction history

    public ATM(double initialBalance, int initialPin) {
        this.balance = initialBalance; // Initialize the balance
        this.pin = initialPin; // Initialize the PIN
        this.transactionHistory = new ArrayList<>(); // Initialize the transaction history list
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) { // Check if the deposit amount is positive
            balance += amount; // Add the deposit amount to the balance
            transactionHistory.add("Deposited: $" + amount); // Add a record to the transaction history
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid deposit amount!"); // Error message for invalid deposit amount
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) { // Check if the withdrawal amount is positive and does not exceed the
                                               // balance
            balance -= amount; // Subtract the withdrawal amount from the balance
            transactionHistory.add("Withdrew: $" + amount); // Add a record to the transaction history
            System.out.println("Withdrawal successful!");
        } else if (amount > balance) {
            System.out.println("Insufficient funds!"); // Error message for insufficient funds
        } else {
            System.out.println("Invalid withdrawal amount!"); // Error message for invalid withdrawal amount
        }
    }

    // Method to change the PIN
    public void changePin(int oldPin, int newPin) {
        if (this.pin == oldPin) { // Check if the old PIN is correct
            this.pin = newPin; // Update the PIN
            transactionHistory.add("PIN changed"); // Add a record to the transaction history
            System.out.println("PIN changed successfully!");
        } else {
            System.out.println("Incorrect old PIN!"); // Error message for incorrect old PIN
        }
    }

    // Method to print the transaction history
    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) { // Check if there are any transactions
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) { // Loop through and print each transaction
                System.out.println(transaction);
            }
        }
    }

    // Method to validate the PIN
    public boolean validatePin(int inputPin) {
        return this.pin == inputPin; // Return true if the input PIN matches the stored PIN
    }
}

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(1000.00, 1234); // Initialize the ATM with a balance of $1000 and a PIN of 1234

        System.out.print("Enter your PIN: ");
        int inputPin = scanner.nextInt();

        if (atm.validatePin(inputPin)) { // Check if the input PIN is correct
            int choice;
            do {
                // Display the ATM menu
                System.out.println("\nATM Menu:");
                System.out.println("1. Balance Inquiry");
                System.out.println("2. Cash Deposit");
                System.out.println("3. Cash Withdrawal");
                System.out.println("4. Change PIN");
                System.out.println("5. Transaction History");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Display the balance
                        System.out.println("Your balance is: $" + atm.getBalance());
                        break;
                    case 2:
                        // Handle deposit
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 3:
                        // Handle withdrawal
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        atm.withdraw(withdrawalAmount);
                        break;
                    case 4:
                        // Handle PIN change
                        System.out.print("Enter old PIN: ");
                        int oldPin = scanner.nextInt();
                        System.out.print("Enter new PIN: ");
                        int newPin = scanner.nextInt();
                        atm.changePin(oldPin, newPin);
                        break;
                    case 5:
                        // Print transaction history
                        atm.printTransactionHistory();
                        break;
                    case 6:
                        // Exit the program
                        System.out.println("Thank you for using the ATM!");
                        break;
                    default:
                        // Handle invalid options
                        System.out.println("Invalid option! Please try again.");
                }
            } while (choice != 6); // Repeat the menu until the user chooses to exit
        } else {
            // Handle incorrect PIN
            System.out.println("Incorrect PIN!");
        }

        scanner.close(); // Close the scanner
    }
}
