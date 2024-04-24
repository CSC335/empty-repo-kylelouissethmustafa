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
    private int best2Streak;
    private int best3Odd;
    private int best3ThreeKind;
    private int best3Power;
    private int best3Streak;
    private int best4Normal;
    private int best4Power;
    private int best4Streak;
    private int best5Odd;
    private int best5Power;
    private int best5Streak;
    private int best6Normal;
    private int best6ThreeKind;
    private int best6Power;
    private int best6Streak;
    
    private MemoryGame currGame;

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
        this.best2Streak = -1;
        this.best3Odd = -1;
        this.best3ThreeKind = -1;
        this.best3Power = -1;
        this.best3Streak = -1;
        this.best4Normal = -1;
        this.best4Power = -1;
        this.best4Streak = -1;
        this.best5Odd = -1;
        this.best5Power = -1;
        this.best5Streak = -1;
        this.best6Normal = -1;
        this.best6ThreeKind = -1;
        this.best6Power = -1;
        this.best6Streak = -1;
        this.currGame = null;
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
    		} else if(gameMode == 3) {
    			this.best3Power = newScore;
    		}
    	} else if(dim == 4) {
    		if(gameMode == 0) {
    			this.best4Normal = newScore;
    		} else if(gameMode == 3) {
    			this.best4Power = newScore;
    		}
    	} else if(dim == 5) {
    		if(gameMode == 1) {
    			this.best5Odd = newScore;
    		} else if(gameMode == 3) {
    			this.best5Power = newScore;
    		}
    	} else if(dim == 6) {
    		if(gameMode == 0) {
    			this.best6Normal = newScore;
    		} else if(gameMode == 2) {
    			this.best6ThreeKind = newScore;
    		} else if(gameMode == 3) {
    			this.best6Power = newScore;
    		}
    	}
    }
    
    public void setNewStreak(int newStreak, int dim) {
    	if(dim == 2) {
    		if(newStreak > this.best2Streak) {
    			this.best2Streak = newStreak;
    		}
    	} else if(dim == 3) {
    		if(newStreak > this.best3Streak) {
    			this.best3Streak = newStreak;
    		}
    	} else if(dim == 4) {
    		if(newStreak > this.best4Streak) {
    			this.best4Streak = newStreak;
    		}
    	} else if(dim == 5) {
    		if(newStreak > this.best5Streak) {
    			this.best5Streak = newStreak;
    		}
    	} else if(dim == 6) {
    		if(newStreak > this.best6Streak) {
    			this.best6Streak = newStreak;
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
    
    public Integer get2Streak() {
    	return this.best2Streak;
    }
    
    public Integer get3Odd() {
    	return this.best3Odd;
    }
    
    public Integer get3ThreeKind() {
    	return this.best3ThreeKind;
    }
    
    public Integer get3Power() {
    	return this.best3Power;
    }
    
    public Integer get3Streak() {
    	return this.best3Streak;
    }
    
    public Integer get4Normal() {
    	return this.best4Normal;
    }
    
    public Integer get4Power() {
    	return this.best4Power;
    }
    
    public Integer get4Streak() {
    	return this.best4Streak;
    }
    
    public Integer get5Odd() {
    	return this.best5Odd;
    }
    
    public Integer get5Power() {
    	return this.best5Power;
    }
    
    public Integer get5Streak() {
    	return this.best5Streak;
    }
    
    public Integer get6Normal() {
    	return this.best6Normal;
    }
    
    public Integer get6ThreeKind() {
    	return this.best6ThreeKind;
    }
    
    public Integer get6Power() {
    	return this.best6Power;
    }
    
    public Integer get6Streak() {
    	return this.best6Streak;
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
    		} else if(gameMode == 3) {
    			score = this.best3Power;
    		}
    	} else if(dim == 4) {
    		if(gameMode == 0) {
    			score = this.best4Normal;
    		} else if(gameMode == 3) {
    			score = this.best4Power;
    		}
    	} else if(dim == 5) {
    		if(gameMode == 1) {
    			score = this.best5Odd;
    		} else if(gameMode == 3) {
    			score = this.best5Power;
    		}
    	} else if(dim == 6) {
    		if(gameMode == 0) {
    			score = this.best6Normal;
    		} else if(gameMode == 2) {
    			score = this.best6ThreeKind;
    		} else if(gameMode == 3) {
    			score = this.best6Power;
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
    
    public int getBestStreak(int dim) {
    	if(dim == 2) {
    		return this.best2Streak;
    	} else if(dim == 3) {
    		return this.best3Streak;
    	} else if(dim == 4) {
    		return this.best4Streak;
    	} else if(dim == 5) {
    		return this.best5Streak;
    	} else if(dim == 6) {
    		return this.best6Streak;
    	} else {
    		return -1;
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
    
    public void setNewGame(MemoryGame game) {
    	this.currGame = game;
    }
    
    public MemoryGame getCurrGame() {
    	return this.currGame;
    }
    
    public void endCurrGame() {
    	this.currGame = null;
    }
}
