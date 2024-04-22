package controller_view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class SettingsPane extends BorderPane {
	private ComboBox<String> dimensions;
	private ComboBox<String> gameMode;
	private memoryGUI gui;
	private GridPane grid;
	
	private Label dimensionLbl;
	private Label gameModeLbl;
	private Label title;
	
	private Button saveSettingsBtn;
	
	public SettingsPane(memoryGUI gui) {
		this.gui = gui;
		
		layoutPane();
		
		registerHandlers();
		
		this.setTop(title);
		
		this.setCenter(grid);
		
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		
		this.setPadding(new Insets(20, 0, 0, 270));
		// top, x, x, left
	}
	
	private void layoutPane() {
		title = new Label("Game Settings");
		title.setStyle("-fx-font-size: 40; -fx-font-weight: bold");
		title.setPadding(new Insets(0, 10, 10, 0));
		
		grid = new GridPane();
		grid.setPadding(new Insets(0, 0, 0, 50));
		
		grid.setHgap(8);
		grid.setVgap(8);
		
		dimensions = new ComboBox<>();
		dimensions.getItems().addAll("2x2", "3x3", "4x4", "5x5", "6x6");
		dimensions.setValue("2x2");
		dimensionLbl = new Label("Dimension");
		dimensionLbl.setStyle("-fx-font-size: 15;");
		
		gameMode = new ComboBox<>();
		gameMode.getItems().addAll("Normal");
		gameMode.setValue("Normal");
		gameModeLbl = new Label("Game Mode");
		gameModeLbl.setStyle("-fx-font-size: 15;");
		
		grid.add(dimensionLbl, 0, 0);
		grid.add(dimensions, 1, 0);
		grid.add(gameModeLbl, 0, 1);
		grid.add(gameMode, 1, 1);
		
		saveSettingsBtn = new Button("Save Settings");
		grid.add(saveSettingsBtn, 0, 3);
	}
	
	private void registerHandlers() {
		dimensions.setOnAction(event -> {
			String newValue = dimensions.getValue();
			
			gameMode.getItems().clear();
			
			// TODO - add Power game mode...
			if(newValue.equals("2x2")) {
				gameMode.getItems().addAll("Normal");
				gameMode.setValue("Normal");
			} else if(newValue.equals("3x3")) {
				gameMode.getItems().addAll("Odd Card Out", "3 of a Kind");
				gameMode.setValue("Odd Card Out");
			} else if(newValue.equals("4x4")) {
				gameMode.getItems().addAll("Normal");
				gameMode.setValue("Normal");
			} else if(newValue.equals("5x5")) {
				gameMode.getItems().addAll("Odd Card Out");
				gameMode.setValue("Odd Card Out");
			} else if(newValue.equals("6x6")) {
				gameMode.getItems().addAll("Normal", "3 of a Kind");
				gameMode.setValue("Normal");
			}
			
			System.out.println("Changed dimension: " + newValue);
		});
		
		saveSettingsBtn.setOnAction(event -> {
			String dim = dimensions.getValue();
			String mode = gameMode.getValue();
			
			System.out.println("New Game Settings: ");
			System.out.println("Dimensions: " + dim);
			System.out.println("Game Mode: " + mode);
			
			int dimNum;
			int modeNum;
			
			if(dim.equals("2x2")) {
				dimNum = 2;
			} else if(dim.equals("3x3")) {
				dimNum = 3;
			} else if(dim.equals("4x4")) {
				dimNum = 4;
			} else if(dim.equals("5x5")) {
				dimNum = 5;
			} else if(dim.equals("6x6")) {
				dimNum = 6;
			} else {
				dimNum = 2;
			}
			
			if(mode.equals("Normal")) {
				modeNum = 0;
			} else if(mode.equals("Odd Card Out")) {
				modeNum = 1;
			} else if(mode.equals("3 of a Kind")) {
				modeNum = 2;
			} else {
				modeNum = 0;
			}
			
			gui.setupGameSettings(dimNum, modeNum);
		});
	}
	
}
