package controller_view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class LoginPane extends BorderPane {
	private GridPane loginGrid;
	
	
	public LoginPane() {
		layoutPane();

		this.setPadding(new Insets(10,20,20,150));
		this.setCenter(loginGrid);
		this.registerHandlers();
	}
	
	public void layoutPane() {
		loginGrid = new GridPane();
		loginGrid.setPrefWidth(300);
		loginGrid.setPrefHeight(300);
		loginGrid.setHgap(8);
		loginGrid.setVgap(8);
		
		Label accountName = new Label("Username:");
		TextField accountNameField = new TextField("");

		Label password = new Label("Password:");
		PasswordField passwordField = new PasswordField();
		
		Button loginButton = new Button("Login");
		Button createAccount = new Button("Create new Account");
		
		Label loginStatus = new Label("Login or Create an Account!");
		
		loginGrid.add(loginStatus, 1, 0);
		loginGrid.add(accountName, 0, 1);
		loginGrid.add(accountNameField, 1, 1);
		loginGrid.add(password, 0, 2);
		loginGrid.add(passwordField, 1, 2);
		loginGrid.add(loginButton, 0, 3);
		loginGrid.add(createAccount, 1, 3);
		
	}
	
	public void registerHandlers() {
		
	}
	
	/**
	 *  Other Potential Methods Here:
	 *  
	 *  getCurrUser();
	 * 
	 * 
	 * 
	 */
}
