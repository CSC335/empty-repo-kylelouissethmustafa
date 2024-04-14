package model;
/*
 * Louis Romeo
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Accounts {
    
	private Map<String, String> accountMap;
    private Scanner scanner;

    public Accounts() {
        accountMap = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    // Method to create a new account with user input
    public void createAccountWithInput() {
        System.out.println("Enter a username:");
        String username = scanner.nextLine();

        System.out.println("Enter a password:");
        String password = scanner.nextLine();

        if (createAccount(username, password)) {
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Failed to create account. Username already exists or fields are empty.");
        }
    }

    // Method to create a new account
    private boolean createAccount(String username, String password) {
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            if (!accountMap.containsKey(username)) {
                accountMap.put(username, password);
                return true;
            }
        }
        return false;
    }

    // Method to check if an account exists
    public boolean accountExists(String username) {
        return accountMap.containsKey(username);
    }

    // Method to authenticate a user with user input
    public boolean authenticateWithInput() {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        return authenticate(username, password);
    }

    // Method to authenticate a user
    private boolean authenticate(String username, String password) {
        return accountMap.getOrDefault(username, "").equals(password);
    }

    // Method to delete an account with user input
    public void deleteAccountWithInput() {
        System.out.println("Enter the username of the account to delete:");
        String username = scanner.nextLine();

        if (deleteAccount(username)) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Failed to delete account. Username not found.");
        }
    }

    // Method to delete an account
    private boolean deleteAccount(String username) {
        if (accountMap.containsKey(username)) {
            accountMap.remove(username);
            return true;
        }
        return false;
    }
}
