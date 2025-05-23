package JackarooView;

import java.io.IOException;
import java.util.ArrayList;




import java.util.Collections;

import model.Colour;
import model.card.Card;
import model.card.standard.Standard;
import model.card.standard.Suit;
import model.card.wild.Wild;
import engine.Game;
import engine.board.Board;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BoardView {
    private AnchorPane root = new AnchorPane();
    private Controls control = new Controls();

    public BoardView() {
    		Image image = new Image(getClass().getResource("brown_border.png").toExternalForm());
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(800);  
			imageView.setFitHeight(600); 
			imageView.setPreserveRatio(false);
            root.getChildren().add(imageView);
	        Scene scene = new Scene(root);
			imageView.fitWidthProperty().bind(scene.widthProperty());
			imageView.fitHeightProperty().bind(scene.heightProperty());
			Stage stage = new Stage();
	        stage.setTitle("Jackaroo");
			stage.setScene(scene); 
	        stage.setMaximized(true);
	        stage.setFullScreen(true);
	        stage.setFullScreenExitHint(""); 
	        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);	        
	        stage.sizeToScene();
			stage.show();
    }
    
    public void intialize(String playerName) {
        try {
			Game game = new Game(playerName);
			control.setGame(game);
    		Image woodTexture = new Image(getClass().getResource("wooden_texture.png").toExternalForm());
	        ImagePattern pattern = new ImagePattern(woodTexture);
    		Image woodTexture_2 = new Image(getClass().getResource("wood_2.png").toExternalForm());
	        ImagePattern pattern_2 = new ImagePattern(woodTexture_2); 
    		Image woodTexture_3 = new Image(getClass().getResource("wood_dark.png").toExternalForm());
	        ImagePattern pattern_3 = new ImagePattern(woodTexture_3); 
	    	DropShadow shadow = new DropShadow();
	    	shadow.setRadius(10);
	    	shadow.setOffsetX(0);
	    	shadow.setOffsetY(5);
	    	shadow.setColor(Color.rgb(0, 0, 0, 0.3));
	    	InnerShadow innerShadow = new InnerShadow();
	    	innerShadow.setColor(Color.rgb(0, 0, 0, 0.2));
	    	innerShadow.setRadius(5);
	    	shadow.setInput(innerShadow);
	    	shadow.setInput(new GaussianBlur(3));
	        Circle firepit_1 = new Circle(200);
	        firepit_1.setLayoutX(1450);
	        firepit_1.setLayoutY(770);
	        firepit_1.setFill(pattern);
	        firepit_1.setEffect(shadow);
	        Circle firepit = new Circle(140);
	        firepit.setLayoutX(1450);
	        firepit.setLayoutY(770);
	        firepit.setFill(pattern_3);
	        firepit.setEffect(shadow);
	        
	        Rectangle square_1 = new Rectangle(745, 130, 1400, 1250); 
	        square_1.setFill(pattern_2);
	        square_1.setStroke(Color.BLACK); 
	        
	        Rectangle square = new Rectangle(695, 70, 1500, 1368); 
	        square.setFill(pattern);
	        square.setStroke(Color.BLACK);
	        
	        root.getChildren().add(square);
	        root.getChildren().add(square_1);
            root.getChildren().add(firepit_1);
            root.getChildren().add(firepit);

			this.create_track();


			ArrayList<Colour> players_colour = new ArrayList<>();
	       	for(int i=0; i<4; i++){	
	       		players_colour.add(game.getPlayers().get(i).getColour());
	       		
	       	}
			this.create_homezone(players_colour);       	
	       	
	        Circle circle = new Circle(175, 170, 172.3);
	        circle.setFill(Color.TRANSPARENT);
	        circle.setStroke(Color.GREEN);
	        circle.setStrokeWidth(5);
	        DropShadow glow = new DropShadow();
	        glow.setColor(Color.GREEN);
	        glow.setRadius(10);
	        circle.setEffect(glow);
	        Timeline timeline = new Timeline(
	            new KeyFrame(Duration.seconds(0), 
	                new KeyValue(glow.radiusProperty(), 10)),
	            new KeyFrame(Duration.seconds(1), 
	                new KeyValue(glow.radiusProperty(), 30))
	        );
	        timeline.setCycleCount(Animation.INDEFINITE);
	        timeline.setAutoReverse(true);
	        timeline.play();
	        	        
	        Circle circle_1 = new Circle(175, 170, 172.3);
	        circle_1.setFill(Color.TRANSPARENT);
	        circle_1.setStroke(Color.RED);
	        circle_1.setStrokeWidth(6);
	        DropShadow glow_1 = new DropShadow();
	        glow_1.setColor(Color.RED);
	        glow_1.setRadius(11);
	        circle_1.setEffect(glow);
	        Timeline timeline_1 = new Timeline(
	            new KeyFrame(Duration.seconds(0), 
	                new KeyValue(glow.radiusProperty(), 10)),
	            new KeyFrame(Duration.seconds(1), 
	                new KeyValue(glow.radiusProperty(), 30))
	        );
	        timeline_1.setCycleCount(Animation.INDEFINITE);
	        timeline_1.setAutoReverse(true);
	        timeline_1.play();
	        
	        
	        Circle circle_2 = new Circle(175, 170, 172.3);
	        circle_2.setFill(Color.TRANSPARENT);
	        circle_2.setStroke(Color.YELLOW);
	        circle_2.setStrokeWidth(6);
	        DropShadow glow_2 = new DropShadow();
	        glow_2.setColor(Color.YELLOW);
	        glow_2.setRadius(11);
	        circle_2.setEffect(glow);
	        Timeline timeline_2 = new Timeline(
	            new KeyFrame(Duration.seconds(0), 
	                new KeyValue(glow.radiusProperty(), 10)),
	            new KeyFrame(Duration.seconds(1), 
	                new KeyValue(glow.radiusProperty(), 30))
	        );
	        timeline_2.setCycleCount(Animation.INDEFINITE);
	        timeline_2.setAutoReverse(true);
	        timeline_2.play();
	        
	     
	        Circle circle_3 = new Circle(175, 170, 172.3);
	        circle_3.setFill(Color.TRANSPARENT);
	        circle_3.setStroke(Color.BLUE);
	        circle_3.setStrokeWidth(6);
	        DropShadow glow_3 = new DropShadow();
	        glow_3.setColor(Color.BLUE);
	        glow_3.setRadius(11);
	        circle_3.setEffect(glow);
	        Timeline timeline_3 = new Timeline(
	            new KeyFrame(Duration.seconds(0), 
	                new KeyValue(glow.radiusProperty(), 10)),
	            new KeyFrame(Duration.seconds(1), 
	                new KeyValue(glow.radiusProperty(), 30))
	        );
	        timeline_3.setCycleCount(Animation.INDEFINITE);
	        timeline_3.setAutoReverse(true);
	        timeline_3.play();
			
	        
	       	for(int i=0; i<4; i++){	
	       		
	       		if(players_colour.get(i) == Colour.BLUE){
	       			if(i==0){
	       				circle_3.setLayoutX(950);
	       				circle_3.setLayoutY(1170);
	       			}
	       			if(i==1){
	       				circle_3.setLayoutX(340);
	       				circle_3.setLayoutY(600);
	       			}
	       			if(i==2){
	       				circle_3.setLayoutX(970);
	       				circle_3.setLayoutY(30);
	       			}
	       			if(i==3){
	       				circle_3.setLayoutX(2200);
	       				circle_3.setLayoutY(600);
	       			}
	       			
	       		}
	       		
	       		if(players_colour.get(i) == Colour.GREEN){
	       			if(i==0){
	       				circle.setLayoutX(950);
	       				circle.setLayoutY(1170);
	       			}
	       			if(i==1){
	       				circle.setLayoutX(340);
	       				circle.setLayoutY(600);
	       			}
	       			if(i==2){
	       				circle.setLayoutX(970);
	       				circle.setLayoutY(30);
	       			}
	       			if(i==3){
	       				circle.setLayoutX(2200);
	       				circle.setLayoutY(600);
	       			}
	       		}
	       		
	       		if(players_colour.get(i) == Colour.RED){
	       			if(i==0){
	       				circle_1.setLayoutX(950);
	       				circle_1.setLayoutY(1170);
	       			}
	       			if(i==1){
	       				circle_1.setLayoutX(340);
	       				circle_1.setLayoutY(600);
	       			}
	       			if(i==2){
	       				circle_1.setLayoutX(970);
	       				circle_1.setLayoutY(30);
	       			}
	       			if(i==3){
	       				circle_1.setLayoutX(2200);
	       				circle_1.setLayoutY(600);
	       			}
	       		}
	       		
	       		if(players_colour.get(i) == Colour.YELLOW){
	       			if(i==0){
	       				circle_2.setLayoutX(950);
	       				circle_2.setLayoutY(1170);
	       			}
	       			if(i==1){
	       				circle_2.setLayoutX(340);
	       				circle_2.setLayoutY(600);
	       			}
	       			if(i==2){
	       				circle_2.setLayoutX(970);
	       				circle_2.setLayoutY(30);
	       			}
	       			if(i==3){
	       				circle_2.setLayoutX(2200);
	       				circle_2.setLayoutY(600);
	       			}
	       		}
	       		
	       	}

	        
	        
			Image image = new Image(getClass().getResource("Bahgat.png").toExternalForm());
			ImageView Bahgat = new ImageView(image);
			Bahgat.setFitWidth(345);
			Bahgat.setFitHeight(345);
			Bahgat.setPreserveRatio(true);
		    Bahgat.setSmooth(true);
			Bahgat.setLayoutX(340);
		    Bahgat.setLayoutY(600);
			
			Image image_1 = new Image(getClass().getResource("Mo7y.png").toExternalForm());
			ImageView Mo7y = new ImageView(image_1);
			Mo7y.setFitWidth(345);
			Mo7y.setFitHeight(345);
			Mo7y.setPreserveRatio(true);
			Mo7y.setSmooth(true);
			Mo7y.setLayoutX(2200);
			Mo7y.setLayoutY(600);
			
			Image image_2 = new Image(getClass().getResource("Ragol3enab.png").toExternalForm());
			ImageView Ragol3enab = new ImageView(image_2);
			Ragol3enab.setFitWidth(345);
			Ragol3enab.setFitHeight(345);
			Ragol3enab.setPreserveRatio(true);
			Ragol3enab.setSmooth(true);
			Ragol3enab.setLayoutX(970);
			Ragol3enab.setLayoutY(30);
			
			Image image_3 = new Image(getClass().getResource("bayoumi.png").toExternalForm());
			ImageView bayoumi = new ImageView(image_3);
			bayoumi.setFitWidth(345);
			bayoumi.setFitHeight(345);
			bayoumi.setPreserveRatio(true);
			bayoumi.setSmooth(true);
			bayoumi.setLayoutX(950);
			bayoumi.setLayoutY(1170);
			
			ArrayList<Label> players = new ArrayList<>();
        	for(int i=0; i<4; i++){	
        		Label name = new Label(game.getPlayers().get(i).getName());
        		name.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #ffcc00; " +
        	               	"-fx-effect: dropshadow(gaussian, black, 4, 0.5, 2, 2); " +
        	                "-fx-font-family: 'System';" +
        	                "-fx-font-size: 45px;" + 
        	                "-fx-font-weight: bold;" +
       	                	"-fx-padding: 10px 20px;" +
       	                	"-fx-background-color: rgba(0, 0, 0, 0.4); -fx-padding: 8 16 8 16; " +
        					"-fx-background-radius: 8;");
        		players.add(name);
        	}
        	players.get(0).setLayoutX(1360);
        	players.get(0).setLayoutY(1340);
        	
        	players.get(1).setRotate(-270);
        	players.get(1).setLayoutX(635);
        	players.get(1).setLayoutY(730);

        	players.get(2).setLayoutX(1332);
        	players.get(2).setLayoutY(90);
        	
        	players.get(3).setRotate(270);
        	players.get(3).setLayoutX(2050);
        	players.get(3).setLayoutY(730);
        	
        	Label current_player = new Label();
        	current_player.setFont(Font.font("Consolas", 28)); 
        	current_player.setStyle(
        		    		"-fx-font-size: 45px;" +
        		    	    "-fx-font-weight: bold;" +
        		    	    "-fx-text-fill: #FFD700; -fx-border-width: 2px;" + 
	    	                "-fx-font-family: 'System';" +
	    	                "-fx-font-weight: bold;" +
	    	                "-fx-padding: 10px 20px;" + 
	    	                "-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff);" + // Gradient
	    	                "-fx-background-radius: 12;"+
	    	                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 6, 0, 2, 2)," + 
	    	                "innershadow(gaussian, rgba(255,255,255,0.3), 4, 0, 1, 1);" 
        		    	);
        	current_player.setLayoutX(2200);
        	current_player.setLayoutY(50);
        	
        	Label next_player = new Label();
        	next_player.setFont(Font.font("Consolas", 28)); 
        	next_player.setStyle(
        		    		"-fx-font-size: 45px;" +
        		    	    "-fx-font-weight: bold;" +
        		    	    "-fx-text-fill: #FFD700; -fx-border-width: 2px;" + 
	    	                "-fx-font-family: 'System';" +
	    	                "-fx-font-weight: bold;" +
	    	                "-fx-padding: 10px 20px;" + 
	    	                "-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff);" + // Gradient
	    	                "-fx-background-radius: 12;"+
	    	                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 6, 0, 2, 2)," + 
	    	                "innershadow(gaussian, rgba(255,255,255,0.3), 4, 0, 1, 1);" 
        		    	);       	
        	next_player.setLayoutX(2200);
        	next_player.setLayoutY(145);
        	
	       	for(int i=0; i<4; i++){	
	       		if(game.getActivePlayerColour() == players_colour.get(i)){
	       			current_player.setText("Current Player: " + game.getPlayers().get(i).getName());
	       		}
	       		if(game.getNextPlayerColour() == players_colour.get(i)){
	       			next_player.setText("Next Player: " + game.getPlayers().get(i).getName());
	       		}

	       	}
        	for(int i=0; i<4; i++){	
                root.getChildren().add(players.get(i));
    		}
            root.getChildren().add(Bahgat);
            root.getChildren().add(Mo7y);
            root.getChildren().add(Ragol3enab);
            root.getChildren().add(bayoumi);
            root.getChildren().add(circle);
            root.getChildren().add(circle_1);
            root.getChildren().add(circle_2);
            root.getChildren().add(circle_3);
            root.getChildren().add(current_player);
            root.getChildren().add(next_player);
            this.Set_Cards(game.getPlayers().get(0).getHand());

            
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    
    public void create_track(){
    	ArrayList<ImageView> face_button = new ArrayList<>();
    	ArrayList<ImageView> Safe_zones = new ArrayList<>();
    	Image face = new Image(getClass().getResource("empty.png").toExternalForm());    		
    	DropShadow shadow = new DropShadow();
    	shadow.setRadius(10);
    	shadow.setOffsetX(0);
    	shadow.setOffsetY(5);
    	shadow.setColor(Color.rgb(0, 0, 0, 0.3));
    	InnerShadow innerShadow = new InnerShadow();
    	innerShadow.setColor(Color.rgb(0, 0, 0, 0.2));
    	innerShadow.setRadius(5);
    	shadow.setInput(innerShadow);
    	shadow.setInput(new GaussianBlur(3));
    	
    	int d = 46;
    	for(int i=0; i<117; i++){	
			ImageView button = new ImageView(face);
    		button.setEffect(shadow);
        	button.setFitWidth(41);
        	button.setFitHeight(41);
        	button.setPreserveRatio(true);
			face_button.add(button);
		}
    	int x = 1327;
    	int y = 1282;
    	for(int i=0; i<9; i++){	
    		if(i<3 || (i>6 && i<9)){
            	face_button.get(i).setLayoutY(1282);
            	face_button.get(i).setLayoutX(x);
            	x=x+d;
            	if(i==8){
            		x=x-d;
            	}
    		}
    		if(i>2 && i<7){
    			if(i==3){
                	y = y-d;
    			}
               	face_button.get(i).setLayoutX(1419);
            	face_button.get(i).setLayoutY(y);
            	y = y-d;
    		}

		}
    	
    	y = 1282-d;
    	for(int i=9; i<14; i++){	
           	face_button.get(i).setLayoutX(x);
        	face_button.get(i).setLayoutY(y);
        	y = y-d;
        	if(i==13){
        		y=y+d;
        	}
		}
    	
    	for(int i=14; i<20; i++){	
    			if(i==14){
    				x=x+d;
    			}
            	face_button.get(i).setLayoutY(y);
            	face_button.get(i).setLayoutX(x);
            	if(i == 19){
                	x=x-d;
                	y=y-d;
            	}
            	x=x+d;
		}

    	for(int i=20; i<25; i++){	
           	face_button.get(i).setLayoutX(x);
        	face_button.get(i).setLayoutY(y);
        	if(i==24){
        		y = y+d;
        		x = x+d;
        	}
        	y = y-d;
    	}
    	
    	for(int i=25; i<30; i++){	
        	face_button.get(i).setLayoutY(y);
        	face_button.get(i).setLayoutX(x);
        	x=x+d;
        	if( i == 29){
            	y = y-d;
            	x = x-d;
        	}
    	}
    	
    	int s = 0;
    	for(int i=30; i<38; i++){	
    		if(i<32 || (i>35 && i<38)){
            	face_button.get(i).setLayoutY(y);
            	face_button.get(i).setLayoutX(x);
            	y = y-d;
            	s = x;
    		}
    		if(i == 32){
               	y = y+d;
            	s=s-d;
    		}
    		if(i>31 && i<36){
               	face_button.get(i).setLayoutX(s);
            	face_button.get(i).setLayoutY(y);
            	s=s-d;
            	if(i == 35){
                	y = y-d;
            	}
    		}
    		if(i==37){
    			y = y+d;
    		}

		}
    	        	
    	for(int i=38; i<43; i++){	
    		if(i==38){
            	x=x-d;
    		}
        	face_button.get(i).setLayoutY(y);
        	face_button.get(i).setLayoutX(x);
        	x=x-d;
        	if( i == 42){
            	y = y-d;
            	x = x+d;
        	}
    	}        	
    	
    	for(int i=43; i<48; i++){	
           	face_button.get(i).setLayoutX(x);
        	face_button.get(i).setLayoutY(y);
        	if(i==47){
        		y = y+d;
        		x = x-d;
        	}
        	y = y-d;
    	}
    	
    	for(int i=48; i<54; i++){	

        	face_button.get(i).setLayoutY(y);
        	face_button.get(i).setLayoutX(x);
        	x=x-d;
        	if( i == 53){
            	y = y-d;
            	x = x+d;
        	}
    	}
    	
    	for(int i=54; i<59; i++){	
           	face_button.get(i).setLayoutX(x);
        	face_button.get(i).setLayoutY(y);
        	if(i==58){
        		y = y+d;
        		x = x-d;
        	}
        	y = y-d;
    	}
    	
    	s = 0;
    	for(int i=59; i<67; i++){	
    		if(i<61 || (i>64 && i<67)){
            	face_button.get(i).setLayoutY(y);
            	face_button.get(i).setLayoutX(x);
            	x=x-d;
            	s = y;
    		}
    		if(i == 61){
               	x=x+d;
            	s=s+d;
    		}
    		if(i>60 && i<65){
               	face_button.get(i).setLayoutX(x);
            	face_button.get(i).setLayoutY(s);
            	s=s+d;
            	if(i == 64){
                	x=x-d;
            	}
    		}
    		if(i==66){
    			x=x+d;
    		}

		}
    	
    	for(int i=67; i<73; i++){	

           	face_button.get(i).setLayoutX(x);
        	face_button.get(i).setLayoutY(y);
        	if(i==72){
        		y = y-d;
        		x = x-d;
        	}
        	y = y+d;
    	}
    	
    	for(int i=73; i<78; i++){	

        	face_button.get(i).setLayoutY(y);
        	face_button.get(i).setLayoutX(x);
        	x=x-d;
        	if( i == 77){
            	y = y+d;
            	x = x+d;
        	}
    	}
    	
    	for(int i=78; i<83; i++){	

           	face_button.get(i).setLayoutX(x);
        	face_button.get(i).setLayoutY(y);
        	if(i==82){
        		y = y-d;
        		x = x-d;
        	}
        	y = y+d;
    	}
    	
    	for(int i=83; i<89; i++){	

        	face_button.get(i).setLayoutY(y);
        	face_button.get(i).setLayoutX(x);
        	x=x-d;
        	if( i == 88){
            	y = y+d;
            	x = x+d;
        	}
    	}
    	
    	s = 0;
    	for(int i=89; i<97; i++){	
    		if(i<91 || (i>94 && i<97)){
            	face_button.get(i).setLayoutY(y);
            	face_button.get(i).setLayoutX(x);
            	y = y+d;
            	s = x;
    		}
    		if(i == 91){
               	y = y-d;
            	s=s+d;
    		}
    		if(i>90 && i<95){
               	face_button.get(i).setLayoutX(s);
            	face_button.get(i).setLayoutY(y);
            	s=s+d;
            	if(i == 94){
                	y = y+d;
            	}
    		}
    		if(i==96){
    			y = y-d;
    			x=x+d;
    		}
		}
    	
    	for(int i=97; i<103; i++){	
        	face_button.get(i).setLayoutY(y);
        	face_button.get(i).setLayoutX(x);
        	x=x+d;
        	if( i == 102){
            	y = y+d;
            	x = x-d;
        	}
    	}
    	
    	for(int i=103; i<108; i++){	
           	face_button.get(i).setLayoutX(x);
        	face_button.get(i).setLayoutY(y);
        	if(i==107){
        		y = y-d;
        		x = x+d;
        	}
        	y = y+d;
    	}
    	
    	for(int i=108; i<113; i++){	
        	face_button.get(i).setLayoutY(y);
        	face_button.get(i).setLayoutX(x);
        	x=x+46;
        	if( i == 112){
            	y = y+d;
            	x = x-d;
        	}
    	}
    	
    	for(int i=113; i<117; i++){	

           	face_button.get(i).setLayoutX(x);
        	face_button.get(i).setLayoutY(y);
        	y = y+d;
    	}
   	
    	for(int i=0; i<117; i++){	
            root.getChildren().add(face_button.get(i));
		}
 	
    	ImageView first = face_button.get(0);
    	face_button.remove(0);
    	Collections.reverse(face_button);
    	face_button.add(0,first);
    	
    	for(int h=0; h<4;h++){
    		ArrayList<ImageView> gg = new ArrayList<>();
    		if(h==0){
                for(int i=112; i<116; i++){
                	gg.add(face_button.get(i));
                }
    		}
    		if(h==1){
                for(int j=24; j<28; j++){
                	gg.add(face_button.get(j));  
                }
    		}
    		if(h==2){
                for(int k=54; k<58; k++){
                	gg.add(face_button.get(k));
                }
    		}
    		if(h==3){
                for(int m=83; m<87; m++){
                	gg.add(face_button.get(m));  
                }
    		}
            Safe_zones.add(gg.get(0));            
    	}
    	
    	for(int h=0; h<4;h++){
    		if(h==0){
                for(int m=0; m<4; m++){
                	face_button.remove(23);  
                }
    		}
    		if(h==1){
                for(int m=0; m<5; m++){
                	face_button.remove(48);  
                }
    		}
    		if(h==2){
                for(int m=0; m<4; m++){
                	face_button.remove(73);  
                }
    		}
    		if(h==3){
                for(int m=0; m<4; m++){
                	face_button.remove(98);  
                }
    		}    		
    	}
	
    	control.setTrack(face_button);
       	control.setSafeZones(Safe_zones);
    }
    
    public void create_homezone(ArrayList<Colour> players_colour){

    	ArrayList<ImageView> marble = new ArrayList<>();
    	Image red_marble_1 = new Image(getClass().getResource("red_marble.png").toExternalForm());    		
    	Image yellow_marble_1 = new Image(getClass().getResource("yellow_marble.png").toExternalForm());    		
    	Image green_marble_1 = new Image(getClass().getResource("green_marble.png").toExternalForm());    		
    	Image blue_marble_1 = new Image(getClass().getResource("blue_marble.png").toExternalForm());    		
    	DropShadow shadow = new DropShadow();
    	shadow.setRadius(10);
    	shadow.setOffsetX(0);
    	shadow.setOffsetY(5);
    	shadow.setColor(Color.rgb(0, 0, 0, 0.3));
    	InnerShadow innerShadow = new InnerShadow();
    	innerShadow.setColor(Color.rgb(0, 0, 0, 0.2));
    	innerShadow.setRadius(5);
    	shadow.setInput(innerShadow);
    	shadow.setInput(new GaussianBlur(3));
    	
    	ArrayList<Integer> red_marbles = new ArrayList<>();
    	ArrayList<Integer> green_marbles = new ArrayList<>();
    	ArrayList<Integer> blue_marbles = new ArrayList<>();
    	ArrayList<Integer> yellow_marbles = new ArrayList<>();
    	
    	for(int i=0; i<4; i++){
    		ImageView red_marble = new ImageView(red_marble_1);
    		red_marble.setEffect(shadow);
        	
    		red_marble.setFitWidth(41);
    		red_marble.setFitHeight(41);
    		red_marble.setPreserveRatio(true);
			marble.add(red_marble);
			red_marbles.add(i);
    	}
    	
    	for(int i=4; i<8; i++){
    		ImageView yellow_marble = new ImageView(yellow_marble_1);
    		yellow_marble.setEffect(shadow);
        	
    		yellow_marble.setFitWidth(41);
    		yellow_marble.setFitHeight(41);
    		yellow_marble.setPreserveRatio(true);
			marble.add(yellow_marble);
			yellow_marbles.add(i);
    	}
    	
    	for(int i=8; i<12; i++){
    		ImageView green_marble = new ImageView(green_marble_1);
    		green_marble.setEffect(shadow);
        	
    		green_marble.setFitWidth(41);
    		green_marble.setFitHeight(41);
    		green_marble.setPreserveRatio(true);
			marble.add(green_marble);
			green_marbles.add(i);
    	}
    	
    	for(int i=12; i<16; i++){
    		ImageView blue_marble = new ImageView(blue_marble_1);
    		blue_marble.setEffect(shadow);
      	
    		blue_marble.setFitWidth(41);
    		blue_marble.setFitHeight(41);
    		blue_marble.setPreserveRatio(true);
			marble.add(blue_marble);
			blue_marbles.add(i);
    	}
    	
       	for(int i=0; i<4; i++){	
       		
       		if(players_colour.get(i) == Colour.BLUE){
       			if(i==0){
					marble.get(12).setLayoutX(1623);
					marble.get(12).setLayoutY(1148);
					marble.get(13).setLayoutX(1588);
					marble.get(13).setLayoutY(1177);
					marble.get(14).setLayoutX(1656);
					marble.get(14).setLayoutY(1177);
					marble.get(15).setLayoutX(1623);
					marble.get(15).setLayoutY(1204.5);
       			}
       			if(i==1){
					marble.get(12).setLayoutX(962);
					marble.get(12).setLayoutY(900);
					marble.get(13).setLayoutX(927);
					marble.get(13).setLayoutY(929);
					marble.get(14).setLayoutX(995);
					marble.get(14).setLayoutY(929);
					marble.get(15).setLayoutX(962);
					marble.get(15).setLayoutY(956);
       			}
       			if(i==2){
					marble.get(12).setLayoutX(1600);
					marble.get(12).setLayoutY(260);
					marble.get(13).setLayoutX(1565);
					marble.get(13).setLayoutY(289);
					marble.get(14).setLayoutX(1633);
					marble.get(14).setLayoutY(289);
					marble.get(15).setLayoutX(1600);
					marble.get(15).setLayoutY(316);
       			}
       			if(i==3){
					marble.get(12).setLayoutX(1925);
					marble.get(12).setLayoutY(900);
					marble.get(13).setLayoutX(1890);
					marble.get(13).setLayoutY(929);
					marble.get(14).setLayoutX(1958);
					marble.get(14).setLayoutY(929);
					marble.get(15).setLayoutX(1925);
					marble.get(15).setLayoutY(956.5);
       			}
       			
       		}
       		
       		if(players_colour.get(i) == Colour.GREEN){
       			if(i==0){
					marble.get(8).setLayoutX(1623);
					marble.get(8).setLayoutY(1148);
					marble.get(9).setLayoutX(1588);
					marble.get(9).setLayoutY(1177);
					marble.get(10).setLayoutX(1656);
					marble.get(10).setLayoutY(1177);
					marble.get(11).setLayoutX(1623);
					marble.get(11).setLayoutY(1204.5);
       			}
       			if(i==1){
					marble.get(8).setLayoutX(962);
					marble.get(8).setLayoutY(900);
					marble.get(9).setLayoutX(927);
					marble.get(9).setLayoutY(929);
					marble.get(10).setLayoutX(995);
					marble.get(10).setLayoutY(929);
					marble.get(11).setLayoutX(962);
					marble.get(11).setLayoutY(956);
       			}
       			if(i==2){
					marble.get(8).setLayoutX(1600);
					marble.get(8).setLayoutY(260);
					marble.get(9).setLayoutX(1565);
					marble.get(9).setLayoutY(289);
					marble.get(10).setLayoutX(1633);
					marble.get(10).setLayoutY(289);
					marble.get(11).setLayoutX(1600);
					marble.get(11).setLayoutY(316);
       			}
       			if(i==3){
					marble.get(8).setLayoutX(1925);
					marble.get(8).setLayoutY(900);
					marble.get(9).setLayoutX(1890);
					marble.get(9).setLayoutY(929);
					marble.get(10).setLayoutX(1958);
					marble.get(10).setLayoutY(929);
					marble.get(11).setLayoutX(1925);
					marble.get(11).setLayoutY(956.5);
       			}

       		}
       		
       		if(players_colour.get(i) == Colour.RED){
       			if(i==0){
					marble.get(0).setLayoutX(1623);
					marble.get(0).setLayoutY(1148);
					marble.get(1).setLayoutX(1588);
					marble.get(1).setLayoutY(1177);
					marble.get(2).setLayoutX(1656);
					marble.get(2).setLayoutY(1177);
					marble.get(3).setLayoutX(1623);
					marble.get(3).setLayoutY(1204.5);
       			}
       			if(i==1){
					marble.get(0).setLayoutX(962);
					marble.get(0).setLayoutY(900);
					marble.get(1).setLayoutX(927);
					marble.get(1).setLayoutY(929);
					marble.get(2).setLayoutX(995);
					marble.get(2).setLayoutY(929);
					marble.get(3).setLayoutX(962);
					marble.get(3).setLayoutY(956);
       			}
       			if(i==2){
    				marble.get(0).setLayoutX(1600);
					marble.get(0).setLayoutY(260);
					marble.get(1).setLayoutX(1565);
					marble.get(1).setLayoutY(289);
					marble.get(2).setLayoutX(1633);
					marble.get(2).setLayoutY(289);
					marble.get(3).setLayoutX(1600);
					marble.get(3).setLayoutY(316);
       			}
       			if(i==3){
					marble.get(0).setLayoutX(1925);
					marble.get(0).setLayoutY(900);
					marble.get(1).setLayoutX(1890);
					marble.get(1).setLayoutY(929);
					marble.get(2).setLayoutX(1958);
					marble.get(2).setLayoutY(929);
					marble.get(3).setLayoutX(1925);
					marble.get(3).setLayoutY(956.5);
       			}

       		}
       		
       		if(players_colour.get(i) == Colour.YELLOW){
       			if(i==0){
					marble.get(4).setLayoutX(1623);
					marble.get(4).setLayoutY(1148);
					marble.get(5).setLayoutX(1588);
					marble.get(5).setLayoutY(1177);
					marble.get(6).setLayoutX(1656);
					marble.get(6).setLayoutY(1177);
					marble.get(7).setLayoutX(1623);
					marble.get(7).setLayoutY(1204.5);
       			}
       			if(i==1){
					marble.get(4).setLayoutX(962);
					marble.get(4).setLayoutY(900);
					marble.get(5).setLayoutX(927);
					marble.get(5).setLayoutY(929);
					marble.get(6).setLayoutX(995);
					marble.get(6).setLayoutY(929);
					marble.get(7).setLayoutX(962);
					marble.get(7).setLayoutY(956);
       			}
       			if(i==2){
					marble.get(4).setLayoutX(1600);
					marble.get(4).setLayoutY(260);
					marble.get(5).setLayoutX(1565);
					marble.get(5).setLayoutY(289);
					marble.get(6).setLayoutX(1633);
					marble.get(6).setLayoutY(289);
					marble.get(7).setLayoutX(1600);
					marble.get(7).setLayoutY(316);
       			}
       			if(i==3){
					marble.get(4).setLayoutX(1925);
					marble.get(4).setLayoutY(900);
					marble.get(5).setLayoutX(1890);
					marble.get(5).setLayoutY(929);
					marble.get(6).setLayoutX(1958);
					marble.get(6).setLayoutY(929);
					marble.get(7).setLayoutX(1925);
					marble.get(7).setLayoutY(956.5);
       			}

       		}
       		
       	}

        for(int i=0; i<16;i++){
        	root.getChildren().add(marble.get(i));
        }	
        control.setMarbles(marble);
        control.setBlue(blue_marbles);
        control.setGreen(green_marbles);
        control.setRed(red_marbles);
        control.setYellow(yellow_marbles);

    }
    
    public void Set_Cards (ArrayList<Card> cards){

    	ArrayList<ImageView> allcards = new ArrayList<>();
    	ArrayList<ImageView> mycards = new ArrayList<>();

    	//1
    	Image Ten_Clubs= new Image(getClass().getResource("10_of_clubs.png").toExternalForm());  
    	Image Ten_Diamonds= new Image(getClass().getResource("10_of_diamonds.png").toExternalForm());  
    	Image Ten_Hearts= new Image(getClass().getResource("10_of_hearts.png").toExternalForm());  
    	Image Ten_Spades= new Image(getClass().getResource("10_of_spades.png").toExternalForm());  
    	
    	//2
    	Image Nine_Clubs= new Image(getClass().getResource("9_of_clubs.png").toExternalForm());  
    	Image Nine_Diamonds= new Image(getClass().getResource("9_of_diamonds.png").toExternalForm());  
    	Image Nine_Hearts= new Image(getClass().getResource("9_of_hearts.png").toExternalForm());  
    	Image Nine_Spades= new Image(getClass().getResource("9_of_spades.png").toExternalForm());  
    	
    	//3
    	Image Eight_Clubs= new Image(getClass().getResource("8_of_clubs.png").toExternalForm());  
    	Image Eight_Diamonds= new Image(getClass().getResource("8_of_diamonds.png").toExternalForm());  
    	Image Eight_Hearts= new Image(getClass().getResource("8_of_hearts.png").toExternalForm());  
    	Image Eight_Spades= new Image(getClass().getResource("8_of_spades.png").toExternalForm());  
    	
    	//4
    	Image Seven_Clubs = new Image(getClass().getResource("7_of_clubs.png").toExternalForm());  
    	Image Seven_Diamonds = new Image(getClass().getResource("7_of_diamonds.png").toExternalForm());  
    	Image Seven_Hearts = new Image(getClass().getResource("7_of_hearts.png").toExternalForm());  
    	Image Seven_Spades = new Image(getClass().getResource("7_of_spades.png").toExternalForm());
    	
    	//5
    	Image Six_Clubs = new Image(getClass().getResource("6_of_clubs.png").toExternalForm());
    	Image Six_Diamonds = new Image(getClass().getResource("6_of_diamonds.png").toExternalForm());
    	Image Six_Hearts = new Image(getClass().getResource("6_of_hearts.png").toExternalForm());
    	Image Six_Spades = new Image(getClass().getResource("6_of_spades.png").toExternalForm());

    	//6
    	Image Five_Clubs = new Image(getClass().getResource("5_of_clubs.png").toExternalForm());
    	Image Five_Diamonds = new Image(getClass().getResource("5_of_diamonds.png").toExternalForm());
    	Image Five_Hearts = new Image(getClass().getResource("5_of_hearts.png").toExternalForm());
    	Image Five_Spades = new Image(getClass().getResource("5_of_spades.png").toExternalForm());

    	//7
    	Image Four_Clubs = new Image(getClass().getResource("4_of_clubs.png").toExternalForm());
    	Image Four_Diamonds = new Image(getClass().getResource("4_of_diamonds.png").toExternalForm());
    	Image Four_Hearts = new Image(getClass().getResource("4_of_hearts.png").toExternalForm());
    	Image Four_Spades = new Image(getClass().getResource("4_of_spades.png").toExternalForm());

    	//8
    	Image Three_Clubs = new Image(getClass().getResource("3_of_clubs.png").toExternalForm());
    	Image Three_Diamonds = new Image(getClass().getResource("3_of_diamonds.png").toExternalForm());
    	Image Three_Hearts = new Image(getClass().getResource("3_of_hearts.png").toExternalForm());
    	Image Three_Spades = new Image(getClass().getResource("3_of_spades.png").toExternalForm());

    	//9
    	Image Two_Clubs = new Image(getClass().getResource("2_of_clubs.png").toExternalForm());
    	Image Two_Diamonds = new Image(getClass().getResource("2_of_diamonds.png").toExternalForm());
    	Image Two_Hearts = new Image(getClass().getResource("2_of_hearts.png").toExternalForm());
    	Image Two_Spades = new Image(getClass().getResource("2_of_spades.png").toExternalForm());

    	//10
    	Image Ace_Clubs = new Image(getClass().getResource("ace_of_clubs.png").toExternalForm());
    	Image Ace_Diamonds = new Image(getClass().getResource("ace_of_diamonds.png").toExternalForm());
    	Image Ace_Hearts = new Image(getClass().getResource("ace_of_hearts.png").toExternalForm());
    	Image Ace_Spades = new Image(getClass().getResource("ace_of_spades.png").toExternalForm());

    	//11
    	Image Jack_Clubs = new Image(getClass().getResource("jack_of_clubs.png").toExternalForm());
    	Image Jack_Diamonds = new Image(getClass().getResource("jack_of_diamonds.png").toExternalForm());
    	Image Jack_Hearts = new Image(getClass().getResource("jack_of_hearts.png").toExternalForm());
    	Image Jack_Spades = new Image(getClass().getResource("jack_of_spades.png").toExternalForm());

    	//12
    	Image Queen_Clubs = new Image(getClass().getResource("queen_of_clubs.png").toExternalForm());
    	Image Queen_Diamonds = new Image(getClass().getResource("queen_of_diamonds.png").toExternalForm());
    	Image Queen_Hearts = new Image(getClass().getResource("queen_of_hearts.png").toExternalForm());
    	Image Queen_Spades = new Image(getClass().getResource("queen_of_spades.png").toExternalForm());
    	
    	//13
    	Image King_Clubs = new Image(getClass().getResource("king_of_clubs.png").toExternalForm());
    	Image King_Diamonds = new Image(getClass().getResource("king_of_diamonds.png").toExternalForm());
    	Image King_Hearts = new Image(getClass().getResource("king_of_hearts.png").toExternalForm());
    	Image King_Spades = new Image(getClass().getResource("king_of_spades.png").toExternalForm());
    	
    	Image burner = new Image(getClass().getResource("burner.png").toExternalForm());
    	Image saver = new Image(getClass().getResource("saver.png").toExternalForm());
    	
    	//1
   		allcards.add(new ImageView(Ace_Clubs));
   		allcards.add(new ImageView(Ace_Diamonds));
   		allcards.add(new ImageView(Ace_Hearts));
   		allcards.add(new ImageView(Ace_Spades));
   		
    	//2
   		allcards.add(new ImageView(Two_Clubs));
   		allcards.add(new ImageView(Two_Diamonds));
   		allcards.add(new ImageView(Two_Hearts));
   		allcards.add(new ImageView(Two_Spades));
   	
    	//3
   		allcards.add(new ImageView(Three_Clubs));
   		allcards.add(new ImageView(Three_Diamonds));
   		allcards.add(new ImageView(Three_Hearts));
   		allcards.add(new ImageView(Three_Spades));
   	
    	//4
   		allcards.add(new ImageView(Four_Clubs));
   		allcards.add(new ImageView(Four_Diamonds));
   		allcards.add(new ImageView(Four_Hearts));
   		allcards.add(new ImageView(Four_Spades));
   		
    	//5
   		allcards.add(new ImageView(Five_Clubs));
   		allcards.add(new ImageView(Five_Diamonds));
   		allcards.add(new ImageView(Five_Hearts));
   		allcards.add(new ImageView(Five_Spades));
  
    	//6
   		allcards.add(new ImageView(Six_Clubs));
   		allcards.add(new ImageView(Six_Diamonds));
   		allcards.add(new ImageView(Six_Hearts));
   		allcards.add(new ImageView(Six_Spades));
   	
    	//7
   		allcards.add(new ImageView(Seven_Clubs));
   		allcards.add(new ImageView(Seven_Diamonds));
   		allcards.add(new ImageView(Seven_Hearts));
   		allcards.add(new ImageView(Seven_Spades));
   
    	//8
   		allcards.add(new ImageView(Eight_Clubs));
   		allcards.add(new ImageView(Eight_Diamonds));
   		allcards.add(new ImageView(Eight_Hearts));
   		allcards.add(new ImageView(Eight_Spades));
   		
    	//9	
   		allcards.add(new ImageView(Nine_Clubs));
   		allcards.add(new ImageView(Nine_Diamonds));
   		allcards.add(new ImageView(Nine_Hearts));
   		allcards.add(new ImageView(Nine_Spades));
   	
    	//10
   		allcards.add(new ImageView(Ten_Clubs));
   		allcards.add(new ImageView(Ten_Diamonds));
   		allcards.add(new ImageView(Ten_Hearts));
   		allcards.add(new ImageView(Ten_Spades));
 
    	//11
   		allcards.add(new ImageView(Jack_Clubs));
   		allcards.add(new ImageView(Jack_Diamonds));
   		allcards.add(new ImageView(Jack_Hearts));
   		allcards.add(new ImageView(Jack_Spades));
   		
    	//12
   		allcards.add(new ImageView(Queen_Clubs));
   		allcards.add(new ImageView(Queen_Diamonds));
   		allcards.add(new ImageView(Queen_Hearts));
   		allcards.add(new ImageView(Queen_Spades));
   		
    	//13	
   		allcards.add(new ImageView(King_Clubs));
   		allcards.add(new ImageView(King_Diamonds));
   		allcards.add(new ImageView(King_Hearts));
   		allcards.add(new ImageView(King_Spades));
   		
  		allcards.add(new ImageView(burner));
   		allcards.add(new ImageView(saver));
   		
    	for (int i = 0; i < cards.size(); i++) {
    		int rank;   		
    		
        	if (cards.get(i) instanceof Standard) {
                Standard standardCard = (Standard) cards.get(i);
        		Suit s;
                rank = standardCard.getRank();
                s= standardCard.getSuit();
                
                if(rank==1){
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(0);
                		mycards.add(card);
					}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(1);
                		mycards.add(card);					
                	}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(2);
                		mycards.add(card);			
                	}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(3);
                		mycards.add(card);				
                	}
                	
            }
                if(rank==2){
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(4);
                		mycards.add(card);
					}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(5);
                		mycards.add(card);
					}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(6);
                		mycards.add(card);
					}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(7);
                		mycards.add(card);
					}
                	
            }
                if(rank==3){
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(8);
                		mycards.add(card);
					}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(9);
                		mycards.add(card);
					}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(10);
                		mycards.add(card);
					}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(11);
                		mycards.add(card);
					}
                	
            }
                if(rank==4){
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(12);
                		mycards.add(card);
					}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(13);
                		mycards.add(card);
					}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(14);
                		mycards.add(card);
					}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(15);
                		mycards.add(card);
					}
                }
                if (rank == 5) {
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(16);
                		mycards.add(card);
					}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(17);
                		mycards.add(card);
					}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(18);
                		mycards.add(card);
					}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(19);
                		mycards.add(card);
					}
                }
                if (rank == 6) {
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(20);
                		mycards.add(card);
					}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(21);
                		mycards.add(card);
					}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(22);
                		mycards.add(card);
					}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(23);
                		mycards.add(card);
					}
                }
                if (rank == 7) {
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(24);
                		mycards.add(card);
					}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(25);
                		mycards.add(card);
					}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(26);
                		mycards.add(card);
					}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(27);
                		mycards.add(card);
					}
                }
                if (rank == 8) {
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(28);
                		mycards.add(card);
					}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(29);
                		mycards.add(card);
					}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(30);
                		mycards.add(card);
					}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(31);
                		mycards.add(card);
					}
                }
                if (rank == 9) {
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(32);
                		mycards.add(card);
					}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(33);
                		mycards.add(card);
                	}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(34);
                		mycards.add(card);
                	}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(35);
                		mycards.add(card);
                	}
                }
                if (rank == 10) {
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(36);
                		mycards.add(card);
                	}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(37);
                		mycards.add(card);
                	}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(38);
                		mycards.add(card);
                	}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(39);
                		mycards.add(card);
                	}
                }
                if (rank == 11) {
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(40);
                		mycards.add(card);
                	}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(41);
                		mycards.add(card);
                	}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(42);
                		mycards.add(card);
                	}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(43);
                		mycards.add(card);
                	}
                }
                if (rank == 12) {
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(44);
                		mycards.add(card);
                	}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(45);
                		mycards.add(card);
                	}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(46);
                		mycards.add(card);
                	}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(47);
                		mycards.add(card);
                	}
                }
                if (rank == 13) {
                	if (s==Suit.CLUB) {
                		ImageView card = allcards.get(48);
                		mycards.add(card);
                	}
                	if (s==Suit.DIAMOND) {
                		ImageView card = allcards.get(49);
                		mycards.add(card);
                	}
                	if (s==Suit.HEART) {
                		ImageView card = allcards.get(50);
                		mycards.add(card);
                	}
                	if (s==Suit.SPADE) {
                		ImageView card = allcards.get(51);
                		mycards.add(card);
                	}	
                }
        	}

               	else{
                    Wild WildCard = (Wild) cards.get(i);
                    String name = WildCard.getName();
                    if (name.equals("MarbleBurner")) {
                		ImageView card = allcards.get(52);
                		mycards.add(card);
                	}
                    if (name.equals("MarbleSaver")) {
                		ImageView card = allcards.get(53);
                		mycards.add(card);
                	}
                } 
    }
    	
        	for(int i=0;i<cards.size();i++){
        		ImageView card = new ImageView(mycards.get(i).getImage());
            	root.getChildren().add(card); 
            	mycards.remove(i);
            	mycards.add(i,card);
        	}
        	
        	mycards.get(0).setLayoutX(1615);
        	mycards.get(0).setLayoutY(1400);
        	mycards.get(1).setLayoutX(1920);
        	mycards.get(1).setLayoutY(1400);
        	mycards.get(2).setLayoutX(2225);
        	mycards.get(2).setLayoutY(1400);
        	mycards.get(3).setLayoutX(2530);
        	mycards.get(3).setLayoutY(1400);  
        	control.setMyCards(mycards);
        	control.play();

    }
        
}
