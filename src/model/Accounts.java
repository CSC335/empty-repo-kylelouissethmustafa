/*
 * Louis Romeo
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Accounts {
    
    private Map<String, String> accountMap;
    private Map<String, Integer> scoresMap;
    private Map<String, Integer> gamesPlayedMap;
    private Scanner scanner;

    public Accounts() {
        accountMap = new HashMap<>();
        scoresMap = new HashMap<>();
        gamesPlayedMap = new HashMap<>();
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
                scoresMap.put(username, 0); // Initialize score to 0
                gamesPlayedMap.put(username, 0); // Initialize games played to 0
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
            scoresMap.remove(username); // Remove score associated with the account
            gamesPlayedMap.remove(username); // Remove games played count associated with the account
            return true;
        }
        return false;
    }
    
    // Method to update scores and games played based on MemoryGame results
    public void updateScoresAndGamesPlayed(String username, int scoreIncrement) {
        if (scoresMap.containsKey(username) && gamesPlayedMap.containsKey(username)) {
            int currentScore = scoresMap.get(username);
            int currentGamesPlayed = gamesPlayedMap.get(username);
            scoresMap.put(username, currentScore + scoreIncrement); // Update score
            gamesPlayedMap.put(username, currentGamesPlayed + 1); // Increment games played
        }
    }
}
