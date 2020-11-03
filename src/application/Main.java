package application;
	

import java.awt.Label;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// Note in Tab 3 File has "Import, export" 
// Note in Tab 3 print has "accounts, System by Date, System by lastName" 

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Transaction Manager");
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("View.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
