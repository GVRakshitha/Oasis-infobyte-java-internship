import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String userId;
    private String userPin;
    private double balance;
    private ArrayList<String> transactions;

    public User(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String pin) {
        return userPin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposit: +" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawal: -" + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void transfer(User recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactions.add("Transfer to " + recipient.getUserId() + ": -" + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
        System.out.println("Current Balance: " + balance);
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize user
        User user = new User("123456", "7890", 1000.0);

        // ATM interface
        System.out.print("Enter User ID: ");
        String userIdInput = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pinInput = scanner.nextLine();

        if (userIdInput.equals(user.getUserId()) && user.validatePin(pinInput)) {
            System.out.println("Welcome, " + user.getUserId() + "!");

            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        user.printTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        user.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        user.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient's User ID: ");
                        String recipientId = scanner.next();
                        User recipient = new User(recipientId, "", 0);
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        user.transfer(recipient, transferAmount);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN. Exiting...");
        }
    }
}
