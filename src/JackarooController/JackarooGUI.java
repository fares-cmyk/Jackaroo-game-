package JackarooController;

import JackarooView.BoardView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JackarooGUI  extends Application {
	@Override
	public void start(Stage primaryStage)  {
			Image image = new Image(getClass().getResource("BackGround.png").toExternalForm()); // or use getResource()
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(800);  
			imageView.setFitHeight(600); 
			imageView.setPreserveRatio(false); 
			imageView.fitWidthProperty().bind(primaryStage.widthProperty());
			imageView.fitHeightProperty().bind(primaryStage.heightProperty());       
			Label promptLabel = new Label("Fill in your name");
			promptLabel.setStyle(
	                "-fx-text-fill: #f0e68c;" + 
	                "-fx-font-family: 'System';" +
	                "-fx-font-size: 45px;" + 
	                "-fx-font-weight: bold;" +
	                "-fx-padding: 10px 20px;" +
	                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 6, 0, 2, 2)," + 
	                "innershadow(gaussian, rgba(255,255,255,0.3), 4, 0, 1, 1);"  
	                );
	        TextField nameField = new TextField();	        
	        nameField.setStyle(
	                "-fx-background-color: linear-gradient(#f5f5dc, #e0cab0);" + 
	                "-fx-border-color: #a0785a;" + 
	                "-fx-border-width: 1px;" +
	                "-fx-border-radius: 3px;" +
	                "-fx-padding: 5px;" +
	                "-fx-font-size: 32px;" + 
	                "-fx-font-weight: bold;"
	        );
	        nameField.setMinSize(600, 50);
	        nameField.setMaxSize(1000, 300);
	        Label resultLabel = new Label();
        	resultLabel.setStyle(
	                		"-fx-text-fill: #D8451F; -fx-border-width: 2px;" + 
	    	                "-fx-font-family: 'System';" +
	    	                "-fx-font-size: 45px;" + 
	    	                "-fx-font-weight: bold;" +
	    	                "-fx-padding: 10px 20px;" +  
	    	                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 6, 0, 2, 2)," + 
	    	                "innershadow(gaussian, rgba(255,255,255,0.3), 4, 0, 1, 1);"  
	    	                );
	        nameField.setOnKeyPressed(event -> {
	            if (event.getCode() == KeyCode.ENTER) {
		        	String name = nameField.getText();
		            if (name.trim().isEmpty()) {
		            	resultLabel.setText("Name is required!");
		            }
		            else{
		                primaryStage.close();
		            	BoardView board = new BoardView();
		            	board.intialize(name);
		            }
	            }
	        });
	        VBox layout = new VBox(10, promptLabel, nameField, resultLabel);
	        layout.setAlignment(Pos.CENTER);
	        layout.setStyle("-fx-padding: 20;");
	        layout.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
	        StackPane root = new StackPane(imageView,layout);
	        Scene scene = new Scene(root);
	        primaryStage.setTitle("Jackaroo");
	        primaryStage.setScene(scene);
	        primaryStage.setMaximized(true);
	        primaryStage.setFullScreen(true);
	        primaryStage.setFullScreenExitHint(""); 
	        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
