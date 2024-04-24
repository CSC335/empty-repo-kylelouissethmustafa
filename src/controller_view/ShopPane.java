package controller_view;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.AccountCollection;
import model.Accounts;
import model.shopCollection;
import model.shopItem;

/**
 * This class represents the shop pane in our MemoryGame. It'll allow users to
 * buy new game modes and powers with in-game currency so they may experience
 * more features and get a sense of progression.
 * 
 * @author Kyle Myint, Louis Romeo, Seth Jernigan, Mustafa Alnidawi
 */
public class ShopPane extends BorderPane {

	private BorderPane pane;

	private static ObservableList<shopItem> items = FXCollections.observableArrayList();
	private Label shopLabel;
	private TableView<shopItem> table;
	private TableColumn<shopItem, String> nameColumn;
	private TableColumn<shopItem, Integer> priceColumn;

	private Label balanceLabel;
	private Button buyButton;

	private shopCollection shopCollection;
	private shopCollection croppedCollection;

	public ShopPane(shopCollection shopCollection) {

		// Initialize UI components.
		pane = new BorderPane();
		shopLabel = new Label("Feature Shop");
		buyButton = new Button("Buy Selected Item");
		balanceLabel = new Label("User Balance: ");
		table = new TableView<>();
		nameColumn = new TableColumn<>("Item Name");
		priceColumn = new TableColumn<>("Price");

		setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		table.getStyleClass().add("row-hover-effect");

		this.shopCollection = shopCollection;
		this.shopCollection.add(new shopItem("Test", 100));

		nameColumn.setCellValueFactory(new PropertyValueFactory<shopItem, String>("itemName"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<shopItem, Integer>("Price"));

		croppedCollection = new shopCollection();

		for (int i = 0; i < shopCollection.getSize(); i++) {
			croppedCollection.add(shopCollection.getElement(i));
		}

		table.setItems(items);
		table.getColumns().addAll(nameColumn, priceColumn);

		addAllUsernames();
		resortTable();

		registerHandlers();
		layoutShop();
	}

	/**
	 * This method sets the default sort column to the price column and sorts the
	 * table in ascending order based on the prices.
	 */
	public void resortTable() {
		table.getSortOrder().add(priceColumn);
		priceColumn.setSortType(TableColumn.SortType.ASCENDING);
		table.sort();
	}

	/**
	 * This method sets the layout of the shop table within the pane
	 */
	public void layoutShop() {
		// Set preferred size for the table.
		table.setPrefSize(100, 100);

		// Set preferred widths for the columns.
		nameColumn.setPrefWidth(220);
		priceColumn.setPrefWidth(220);

		// Set padding for the pane and apply styling to the shop title.
		pane.setPadding(new Insets(80, 100, 100, 80));
		shopLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: black");

		// Set the title label at the top and the table at the center of the pane.
		pane.setTop(shopLabel);
		pane.setRight(balanceLabel);
		pane.setBottom(buyButton);
		pane.setCenter(table);

		// Set this pane as the center content of the parent container.
		this.setCenter(pane);
	}

	/**
	 * This method clears the shopItem list and then iterates through the shopItem
	 * collection, adding each item to the item list. This will help populate the
	 * table
	 */
	private void addAllUsernames() {
		items.clear();

		for (int i = 0; i < croppedCollection.getSize(); i++) {
			items.add(croppedCollection.getElement(i));
		}
	}

	// Registers event handlers
	private void registerHandlers() {
		// Handle buyButton based on selected entry in table
	}
}
