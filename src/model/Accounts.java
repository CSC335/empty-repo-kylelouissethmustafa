package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Accounts class allows for initialization of a user,
 * with username, password, and MemoryGame related statistics.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 *
 */
public class Accounts implements java.io.Serializable {
    private String username;
    private String password;
    private int gamesPlayed;
    
    private int best2Normal;
    private int best3Odd;
    private int best3ThreeKind;
    private int best4Normal;
    private int best5Odd;
    private int best6Normal;
    private int best6ThreeKind;

    /**
     * The constructor for accounts, which sets all best scores
     * to -1 by default, gamesPlayed to 0, and sets username and password.
     * 
     * @param username The Account's username.
     * @param password The Accounts password.
     */
    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;
        this.gamesPlayed = 0;
        // NOTE - if -1, not played that mode yet...  
        this.best2Normal = -1;
        this.best3Odd = -1;
        this.best3ThreeKind = -1;
        this.best4Normal = -1;
        this.best5Odd = -1;
        this.best6Normal = -1;
        this.best6ThreeKind = -1;
    }
    
    // TODO - recomment, add modes
    /**
     * Given a newScore and game mode, this method sets a new
     * best score for the account.
     * 
     * @param newScore The value of the newest score.
     * @param gameMode The specified game mode to update best score of.
     */
    public void setNewBestScore(int newScore, int dim, int gameMode) {
    	if(dim == 2) {
    		if(gameMode == 0) {
    			this.best2Normal = newScore;
    		}
    	} else if(dim == 3) {
    		if(gameMode == 1) {
    			this.best3Odd = newScore;
    		} else if(gameMode == 2) {
    			this.best3ThreeKind = newScore;
    		}
    	} else if(dim == 4) {
    		if(gameMode == 0) {
    			this.best4Normal = newScore;
    		}
    	} else if(dim == 5) {
    		if(gameMode == 1) {
    			this.best5Odd = newScore;
    		}
    	} else if(dim == 6) {
    		if(gameMode == 0) {
    			this.best6Normal = newScore;
    		} else if(gameMode == 2) {
    			this.best6ThreeKind = newScore;
    		}
    	}
    }
    
    /**
     * Increments games played for the account.
     */
    public void incrementGamesPlayed() {
    	this.gamesPlayed++;
    }
    
    /**
     * A getter for Account's username.
     * @return The account's username.
     */
    public String getUsername() {
    	return this.username;
    }
    
    /**
     * A getter for Account's password.
     * @return The account's password.
     */
    public String getPassword() {
    	return this.password;
    }
    
    public Integer get2Normal() {
    	return this.best2Normal;
    }
    
    public Integer get3Odd() {
    	return this.best3Odd;
    }
    
    public Integer get3ThreeKind() {
    	return this.best3ThreeKind;
    }
    
    public Integer get4Normal() {
    	return this.best4Normal;
    }
    
    public Integer get5Odd() {
    	return this.best5Odd;
    }
    
    public Integer get6Normal() {
    	return this.best6Normal;
    }
    
    public Integer get6ThreeKind() {
    	return this.best6ThreeKind;
    }
    
    // TODO - re comment, add game Modes
    /**
     * For specified game mode, a getter for Account's best score.
     * 
     * @param gameMode The game mode being queried for best user score.
     * @return The best score for the Account's specified game mode.
     */
    public int getBestScore(int dim, int gameMode) {
    	int score = -2;
    	if(dim == 2) {
    		if(gameMode == 0) {
    			score = this.best2Normal;
    		}
    	} else if(dim == 3) {
    		if(gameMode == 1) {
    			score = this.best3Odd;
    		} else if(gameMode == 2) {
    			score = this.best3ThreeKind;
    		}
    	} else if(dim == 4) {
    		if(gameMode == 0) {
    			score = this.best4Normal;
    		}
    	} else if(dim == 5) {
    		if(gameMode == 1) {
    			score = this.best5Odd;
    		}
    	} else if(dim == 6) {
    		if(gameMode == 0) {
    			score = this.best6Normal;
    		} else if(gameMode == 2) {
    			score = this.best6ThreeKind;
    		}
    	}
    	
    	if(score == -1) {
    		System.out.println("User has not played this mode");
    		return -1;
    	} else if(score == -2) {
    		System.out.println("Invalid game mode");
    		return score;
    	} else {
    		return score;
    	}
    }
    
    /**
     * A getter for number of games played by the Account.
     * 
     * @return The number of games played by the account.
     */
    public int getGamesPlayed() {
    	return this.gamesPlayed;
    }

    /**
     * An equals method that declares two accounts equal if they have the
     * same username and password.
     */
    public boolean equals(Object o) {
    	if(this.getUsername() == ((Accounts) o).getUsername() &&
    			this.getPassword() == ((Accounts) o).getPassword()) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
