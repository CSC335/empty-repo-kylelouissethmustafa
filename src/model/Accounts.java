package model;
/*
 * Class responsible for creating, accessing, and 
 * deleting user accounts in the memory game. Stores the
 * username and password pairs in a Map data structure.
 * 
 * Author: Louis Romeo
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Accounts {
    
	private Map<String, String> accountMap;

    public Accounts() {
        accountMap = new HashMap<>();
    }

    public void createAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        // Check if the username already exists
        if (accountMap.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different one.");
            return;
        }

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        // Save the username and password
        accountMap.put(username, password);
        System.out.println("Account created successfully!");
    }

    public void deleteAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username to delete:");
        String username = scanner.nextLine();

        // Check if the username exists
        if (accountMap.containsKey(username)) {
            accountMap.remove(username);
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Username not found. Cannot delete account.");
        }
    }

    public static void main(String[] args) {
        Accounts accounts = new Accounts();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Exit");
            System.out.println("Choose an option:");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    accounts.createAccount();
                    break;
                case 2:
                    accounts.deleteAccount();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        System.out.println("Exiting...");
    }
}
