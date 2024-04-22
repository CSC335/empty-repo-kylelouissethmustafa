package controller_view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SettingsPane extends BorderPane {
	private ComboBox<String> dimensions;
	private memoryGUI gui;
	private GridPane grid;
	
	private Label dimensionLbl;
	
	private Button saveSettingsBtn;
	
	public SettingsPane(memoryGUI gui) {
		this.gui = gui;
		
		layoutPane();
		
		registerHandlers();
		
		this.setCenter(grid);
		
		this.setPadding(new Insets(50, 0, 0, 200));
		// top, x, x, left
	}
	
	private void layoutPane() {
		grid = new GridPane();
		
		grid.setHgap(8);
		grid.setVgap(8);
		
		dimensions = new ComboBox<>();
		
		dimensions.getItems().addAll("2x2", "3x3", "4x4", "5x5", "6x6");
		
		dimensions.setValue("2x2");
		
		dimensionLbl = new Label("Dimension");
		
		grid.add(dimensionLbl, 0, 0);
		grid.add(dimensions, 1, 0);
		
		saveSettingsBtn = new Button("Save Settings");
		grid.add(saveSettingsBtn, 0, 1);
	}
	
	private void registerHandlers() {
		dimensions.setOnAction(event -> {
			String newValue = dimensions.getValue();
			
			System.out.println("Changed dimension: " + newValue);
		});
		
		saveSettingsBtn.setOnAction(event -> {
			String dim = dimensions.getValue();
			
			System.out.println("New Game Settings: ");
			System.out.println("Dimensions: " + dim);
			
			if(dim.equals("2x2")) {
				gui.setupGameSettings(2);
			} else if(dim.equals("3x3")) {
				gui.setupGameSettings(3);
			} else if(dim.equals("4x4")) {
				gui.setupGameSettings(4);
			} else if(dim.equals("5x5")) {
				gui.setupGameSettings(5);
			} else if(dim.equals("6x6")) {
				gui.setupGameSettings(6);
			} else {
				gui.setupGameSettings(2);
			}
		});
	}
	
}
