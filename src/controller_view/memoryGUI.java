/**
 * This is the main GUI for the project
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */

package controller_view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class memoryGUI extends Application {
	
	private LoginPane loginPane;
	private BoardPane boardPane;

	public static void main(String[] args) {
		launch(args);
	}

	private BorderPane all;


	@Override
	public void start(Stage primaryStage) throws Exception {
		loginPane = new LoginPane();
		boardPane = new BoardPane();
		registerHandlers();
		LayoutGUI();

		Scene scene = new Scene(all, 850, 650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Memory Game");
		primaryStage.show();
	}

	/**
	 * Layout for the GUI
	 */
	private void LayoutGUI() {
		all = new BorderPane();
		
		all.setTop(boardPane);
		//all.setBottom(loginPane);
	}


	/**
	 * Event handlers
	 */
	private void registerHandlers() {

	}


}