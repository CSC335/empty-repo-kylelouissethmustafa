package controller_view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Accounts;

public class StatsPane extends BorderPane {
    
	private Accounts accounts;
    private String username;
    private int totalGamesPlayed;
    private int totalScore;
    private Label usernameLabel;
    private Label gamesPlayedLabel;
    private Label averageScoreLabel;

    public StatsPane(Accounts accounts, String username) {
        this.accounts = accounts;
        this.username = username;
        layoutStatsPane();
    }

    private void layoutStatsPane() {
        usernameLabel = new Label("Username: " + username);
        gamesPlayedLabel = new Label("Games Played: ");
        averageScoreLabel = new Label("Average Score: ");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(usernameLabel, gamesPlayedLabel, averageScoreLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        setCenter(vbox);
    }

    // Method to update the score in StatsPane and Accounts
    public void updateScore(int scoreIncrement) {
        totalScore += scoreIncrement;
        totalGamesPlayed++;
        updateStats();
    }

    private void updateStats() {
        double averageScore = totalGamesPlayed != 0 ? (double) totalScore / totalGamesPlayed : 0;

        gamesPlayedLabel.setText("Games Played: " + totalGamesPlayed);
        averageScoreLabel.setText("Average Score: " + averageScore);
    }
}
