package JackarooView;

import java.io.IOException;
import java.util.ArrayList;








import model.Colour;
import model.card.Card;
import model.card.standard.Standard;
import model.card.standard.Suit;
import model.card.wild.Wild;
import model.player.Marble;
import engine.Game;
import engine.board.Board;
import exception.GameException;
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

public class Controls {
	private ArrayList<ImageView> track = new ArrayList<>();
	private ArrayList<ImageView> Safe_zones = new ArrayList<>();
	private ArrayList<Integer> green_marble = new ArrayList<>();
	private ArrayList<Integer> red_marble = new ArrayList<>();
	private ArrayList<Integer> yellow_marble = new ArrayList<>();
	private ArrayList<Integer> blue_marble = new ArrayList<>();
	private ArrayList<ImageView> marbles = new ArrayList<>();
	private ArrayList<ImageView> my_cards = new ArrayList<>();
	private Game game;
	private int Green;
	private int Yellow;
	private int Red;
	private int Blue;
	
    public Controls( ) {

    }
    public void setTrack(ArrayList<ImageView> track){
    	this.track = track;
    }
    public void setSafeZones(ArrayList<ImageView> Safe_zones){
    	this.Safe_zones = Safe_zones;
    }
    public void setGreen(ArrayList<Integer> green){
    	this.green_marble = green;
    }
    public void setRed(ArrayList<Integer> red){
    	this.red_marble = red;
    }
    public void setYellow(ArrayList<Integer> yellow){
    	this.yellow_marble = yellow;
    }
    public void setBlue(ArrayList<Integer> blue){
    	this.blue_marble = blue;
    }
    public void setMarbles(ArrayList<ImageView>marbles){
    	this.marbles = marbles;
    }
    public void setMyCards(ArrayList<ImageView> cards){
    	this.my_cards = cards;
    }
    public void setGame(Game game){
    	this.game = game;
    }
    
    public void play_myCard(){
	    for (ImageView img : this.my_cards) {	
	            img.setDisable(false);  
	    }
    	final int[] clicks = {0};
    	for(int i=0;i<this.my_cards.size();i++){
        	DropShadow shadow = new DropShadow();
        	shadow.setRadius(10);
        	shadow.setOffsetX(0);
        	shadow.setOffsetY(5);
        	shadow.setColor(Color.rgb(0, 0, 0, 0.3));
        	InnerShadow innerShadow = new InnerShadow();
        	innerShadow.setColor(Color.rgb(0, 0, 0, 0.2));
        	innerShadow.setRadius(5);
        	shadow.setInput(innerShadow);
        	this.my_cards.get(i).setEffect(shadow);
        	this.my_cards.get(i).setFitWidth(300);
        	this.my_cards.get(i).setFitHeight(380);
        	    	
        	int y=i;
        	this.my_cards.get(i).setOnMouseClicked((MouseEvent event) -> {
        			clicks[0]++;
        		    for (ImageView img : this.my_cards) {	
        		        if (img != this.my_cards.get(y)) {
        		            img.setDisable(true);  
        		        }
        		    }
             		TranslateTransition transition = new TranslateTransition();
                    transition.setNode(this.my_cards.get(y));
                    transition.setDuration(Duration.seconds(0.5));
                    transition.setToY(-135+this.my_cards.get(y).getY());
                    transition.play();
                    
                    ScaleTransition scale = new ScaleTransition(Duration.seconds(0.5), this.my_cards.get(y));
                    scale.setToX(1.18);
                    scale.setToY(1.18);
                    scale.setCycleCount(1);
                    scale.setAutoReverse(false);
                    scale.play();
                    if(this.game.canPlayTurn()){
                    	int[]pos = {0};
                    	for(int k=0; k<100; k++){
            					int h = k;
            					this.track.get(k).setOnMouseClicked((MouseEvent move) -> {
            						System.out.println("yA77");
            						if(this.game.getBoard().getTrack().get(h).getMarble()!=null && this.game.getBoard().getTrack().get(h).getMarble().getColour() == this.game.getPlayers().get(0).getColour()){
            							try {
            								System.out.println("off");
            								this.game.selectMarble(game.getBoard().getTrack().get(h).getMarble());
            								this.game.getPlayers().get(0).selectCard(this.game.getPlayers().get(0).getHand().get(y));
            								this.game.playPlayerTurn();
            								this.game.endPlayerTurn();
            								pos[0] = h;
            								check_track_2(clicks[0],pos[0]);
            								System.out.println("GG");
            								
                	        				int g=0;
                	        				for(int f=0; f<y;f++){
                	        						g=g+308;			
                	        				}
            								
                	                 		TranslateTransition transition_4 = new TranslateTransition();
                	                 		transition_4.setNode(this.my_cards.get(y));
                	                 		transition_4.setDuration(Duration.seconds(0.5));
                	                 		transition_4.setToY(-821+this.my_cards.get(y).getY());
                	                 		transition_4.setToX(-306-g+this.my_cards.get(y).getX());
                	                 		transition_4.play();
                	                        
                	                        ScaleTransition scale_4 = new ScaleTransition(Duration.seconds(0.5), this.my_cards.get(y));
                	                        scale_4.setToX(0.67);
                	                        scale_4.setToY(0.67);
                	                        scale_4.setCycleCount(1);
                	                        scale_4.setAutoReverse(false);
                	                        scale_4.play();
                	                        
            							} catch (Exception e) {
            								e.printStackTrace();
            							}
            						}
            					});
                    	} 
                    }
                    
            		if(clicks[0]==2){
            			if(this.game.canPlayTurn()){
            				try {
                    		    for (ImageView img : this.my_cards) {	
                    		        if (img != this.my_cards.get(y)) {
                    		            img.setDisable(false);  
                    		        }
                    		    }
            					this.my_cards.get(y).setDisable(true);

            					clicks[0]=0;
            					this.game.getPlayers().get(0).selectCard(game.getPlayers().get(0).getHand().get(y));
            			    	this.game.playPlayerTurn();
								this.game.endPlayerTurn();

    	    	        				
    	        				int g=0;
    	        				for(int f=0; f<y;f++){
    	        						g=g+308;			
    	        				}
    	                 		TranslateTransition transition_3 = new TranslateTransition();
    	                 		transition_3.setNode(this.my_cards.get(y));
    	                 		transition_3.setDuration(Duration.seconds(0.5));
    	                 		transition_3.setToY(-821+this.my_cards.get(y).getY());
    	                 		transition_3.setToX(-306-g+this.my_cards.get(y).getX());
    	                 		transition_3.play();
    	                        
    	                        ScaleTransition scale_3 = new ScaleTransition(Duration.seconds(0.5), this.my_cards.get(y));
    	                        scale_3.setToX(0.67);
    	                        scale_3.setToY(0.67);
    	                        scale_3.setCycleCount(1);
    	                        scale_3.setAutoReverse(false);
    	                        scale_3.play();
    	                        check_track(0);
    	                        this.play_Bahgat();

    							
    						} catch (Exception e) {
    							
    							e.printStackTrace();
    						}

            			}

            		}
                    
       /*    		if(clicks[0]==2){
            			clicks[0]=0;
            		    for (ImageView img : this.my_cards) {
            		        if (img != this.my_cards.get(y)) {
            		            img.setDisable(false);  
            		        }
            		    }
                		TranslateTransition transition_2 = new TranslateTransition();
                		transition_2 .setNode(this.my_cards.get(y));
                		transition_2 .setDuration(Duration.seconds(0.5));
                		transition_2 .setToY(this.my_cards.get(y).getY());
                		transition_2 .play();
                        
                        ScaleTransition scale_2 = new ScaleTransition(Duration.seconds(0.5), this.my_cards.get(y));
                        scale_2.setToX(1);
                        scale_2.setToY(1);
                        scale_2.setCycleCount(1);
                        scale_2.setAutoReverse(false);
                        scale_2.play(); 
            		} */
            		

    		});
    	}
    	
    }
    
    public void check_track(int p){
        for(int k=0; k<100; k++){
			if(this.game.getBoard().getTrack().get(k).getMarble()!=null && this.game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(p).getColour()){
				
				ImageView swap_track = this.track.get(k);
				double X = this.track.get(k).getLayoutX();
				double Y = this.track.get(k).getLayoutY();
				
				if(this.game.getPlayers().get(p).getColour()==Colour.GREEN){
					ImageView swap_marble = this.marbles.get(green_marble.get(0));
					Integer marble_index = green_marble.get(0);
					double green_X = this.marbles.get(green_marble.get(0)).getLayoutX();
					double green_Y = this.marbles.get(green_marble.get(0)).getLayoutY();
					this.track.get(k).setLayoutX(green_X);
					this.track.get(k).setLayoutY(green_Y);
					this.marbles.get(green_marble.get(0)).setLayoutX(X);
					this.marbles.get(green_marble.get(0)).setLayoutY(Y);
					
					green_marble.remove(0);
					green_marble.add(marble_index);
					this.marbles.set(marble_index,swap_track);
					this.track.set(k,swap_marble);
							
				}
				if(this.game.getPlayers().get(p).getColour()==Colour.RED){
					ImageView swap_marble = this.marbles.get(red_marble.get(0));
					Integer marble_index = red_marble.get(0);
					double red_X = this.marbles.get(red_marble.get(0)).getLayoutX();
					double red_Y = this.marbles.get(red_marble.get(0)).getLayoutY();
					this.track.get(k).setLayoutX(red_X);
					this.track.get(k).setLayoutY(red_Y);
					this.marbles.get(red_marble.get(0)).setLayoutX(X);
					this.marbles.get(red_marble.get(0)).setLayoutY(Y);
					
					red_marble.remove(0);
					red_marble.add(marble_index);
					this.marbles.set(marble_index,swap_track);
					this.track.set(k,swap_marble);
					
				}
				if(this.game.getPlayers().get(p).getColour()==Colour.BLUE){
					ImageView swap_marble = this.marbles.get(blue_marble.get(0));
					Integer marble_index = blue_marble.get(0);
					double blue_X = this.marbles.get(blue_marble.get(0)).getLayoutX();
					double blue_Y = this.marbles.get(blue_marble.get(0)).getLayoutY();
					this.track.get(k).setLayoutX(blue_X);
					this.track.get(k).setLayoutY(blue_Y);
					this.marbles.get(blue_marble.get(0)).setLayoutX(X);
					this.marbles.get(blue_marble.get(0)).setLayoutY(Y);
					
					blue_marble.remove(0);
					blue_marble.add(marble_index);
					this.marbles.set(marble_index,swap_track);
					this.track.set(k,swap_marble);
				}
				if(this.game.getPlayers().get(p).getColour()==Colour.YELLOW){
					ImageView swap_marble = this.marbles.get(yellow_marble.get(0));
					Integer marble_index = yellow_marble.get(0);
					double yellow_X = this.marbles.get(yellow_marble.get(0)).getLayoutX();
					double yellow_Y = this.marbles.get(yellow_marble.get(0)).getLayoutY();
					this.track.get(k).setLayoutX(yellow_X);
					this.track.get(k).setLayoutY(yellow_Y);
					this.marbles.get(yellow_marble.get(0)).setLayoutX(X);
					this.marbles.get(yellow_marble.get(0)).setLayoutY(Y);
										
					yellow_marble.remove(0);
					yellow_marble.add(marble_index);
					this.marbles.set(marble_index,swap_track);
					this.track.set(k,swap_marble);
					
				}
				
			}

        }
    }
    
    public void check_track_2(int clicks,int pos){
        for(int k=0; k<100; k++){
        		
			ImageView swap_track = this.track.get(k);
			double X = this.track.get(k).getLayoutX();
			double Y = this.track.get(k).getLayoutY();
			
			if(this.game.getBoard().getTrack().get(k).getMarble()!=null && game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(0).getColour()){
				ImageView swap_marble = this.track.get(pos);
				Integer marble_index = pos;
				double green_X = this.track.get(pos).getLayoutX();
				double green_Y = this.track.get(pos).getLayoutY();
				this.track.get(k).setLayoutX(green_X);
				this.track.get(k).setLayoutY(green_Y);
				this.track.get(pos).setLayoutX(X);
				this.track.get(pos).setLayoutY(Y);
				
				this.track.remove(marble_index);
				this.track.add(marble_index,swap_track);
				this.track.remove(k);
				this.track.add(k,swap_marble); 							
			}
        }
        clicks = 0;
        
    }
    public void check_track_3(){

    }
    
    public void play_Bahgat( ){
    	int marbles=0;

		if(this.game.getPlayers().get(1).getColour() == Colour.BLUE){
			 if(this.game.canPlayTurn()){
					try {
						System.out.println("GG");

						this.game.playPlayerTurn();
				    	System.out.println(this.game.getPlayers().get(1).getSelectedCard());

				    	this.game.endPlayerTurn();

						 for(int k=0; k<100; k++){

								if(this.game.getBoard().getTrack().get(k).getMarble()!=null && this.game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(1).getColour()){

									marbles++;
								}
						 }
						if(marbles > Blue){

							check_track(1);
							Blue = marbles;

						}
						this.play_Ragel3enab();

					} catch (GameException e) {
						e.printStackTrace();
					}
			 }
		}
		if(this.game.getPlayers().get(1).getColour() == Colour.RED){
			 if(this.game.canPlayTurn()){
					try {
						System.out.println("GG");

						this.game.playPlayerTurn();
				    	System.out.println(this.game.getPlayers().get(1).getSelectedCard());

				    	this.game.endPlayerTurn();

						 for(int k=0; k<100; k++){
								if(this.game.getBoard().getTrack().get(k).getMarble()!=null && game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(1).getColour()){

									marbles++;
								}
						 }
						if(marbles > Red){

							check_track(1);
							Red = marbles;


						}
						this.play_Ragel3enab();

					} catch (GameException e) {
						e.printStackTrace();
					}
			 	}
		}
		if(this.game.getPlayers().get(1).getColour() == Colour.GREEN){
	        if(this.game.canPlayTurn()){
				try {
					System.out.println("GG");

					this.game.playPlayerTurn();
			    	System.out.println(this.game.getPlayers().get(1).getSelectedCard());

			    	this.game.endPlayerTurn();

					for(int k=0; k<100; k++){

							if(this.game.getBoard().getTrack().get(k).getMarble()!=null && game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(1).getColour()){

								marbles++;
							}
					 }
					if(marbles > Green){

						check_track(1);
						Green = marbles;


					}
					this.play_Ragel3enab();

				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
		}
		if(this.game.getPlayers().get(1).getColour() == Colour.YELLOW){
	        if(this.game.canPlayTurn()){
				try {
					System.out.println("GG");

					this.game.playPlayerTurn();
			    	System.out.println(this.game.getPlayers().get(1).getSelectedCard());

			    	this.game.endPlayerTurn();

					for(int k=0; k<100; k++){

							if(this.game.getBoard().getTrack().get(k).getMarble()!=null && game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(1).getColour()){

								marbles++;
							}
					 }
					if(marbles > Yellow){

						check_track(1);
						Yellow  = marbles;
					}
					this.play_Ragel3enab();

				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
		}
    }
    	
    public void play(){
    	this.play_myCard();
    	this.play_myCard();
    }
    
    
    
    
    
    
    public void play_Ragel3enab(){
     	int marbles=0;
		if(this.game.getPlayers().get(2).getColour() == Colour.BLUE){
			 if(this.game.canPlayTurn()){
				try {
					System.out.println("GG");

					this.game.playPlayerTurn();
			    	System.out.println(this.game.getPlayers().get(2).getSelectedCard());

			    	this.game.endPlayerTurn();

					 for(int k=0; k<100; k++){

							if(this.game.getBoard().getTrack().get(k).getMarble()!=null && this.game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(2).getColour()){

								marbles++;
							}
					 }
					if(marbles > Blue){

						check_track(2);
						Blue = marbles;

					}
					this.play_Mo7yy();

				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
		}
		if(this.game.getPlayers().get(2).getColour() == Colour.RED){
			
	        if(this.game.canPlayTurn()){
				try {
					System.out.println("GG");

					this.game.playPlayerTurn();
			    	System.out.println(this.game.getPlayers().get(2).getSelectedCard());

			    	this.game.endPlayerTurn();

					 for(int k=0; k<100; k++){
							if(this.game.getBoard().getTrack().get(k).getMarble()!=null && game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(2).getColour()){

								marbles++;
							}
					 }
					if(marbles > Red){

						check_track(2);
						Red = marbles;

					}
					this.play_Mo7yy();

				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
		}
		if(this.game.getPlayers().get(2).getColour() == Colour.GREEN){
	        if(this.game.canPlayTurn()){
				try {
					System.out.println("GG");

					this.game.playPlayerTurn();
			    	System.out.println(this.game.getPlayers().get(2).getSelectedCard());

			    	this.game.endPlayerTurn();

					for(int k=0; k<100; k++){

							if(this.game.getBoard().getTrack().get(k).getMarble()!=null && game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(2).getColour()){

								marbles++;
							}
					 }
					if(marbles > Green){

						check_track(2);
						Green = marbles;

					}
					this.play_Mo7yy();

				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
		}
		if(this.game.getPlayers().get(2).getColour() == Colour.YELLOW){
	        if(this.game.canPlayTurn()){
				try {
					System.out.println("GG");

					this.game.playPlayerTurn();
			    	System.out.println(this.game.getPlayers().get(2).getSelectedCard());

			    	this.game.endPlayerTurn();

					for(int k=0; k<100; k++){

							if(this.game.getBoard().getTrack().get(k).getMarble()!=null && game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(2).getColour()){

								marbles++;
							}
					 }
					if(marbles > Yellow){

						check_track(2);
						Yellow  = marbles;
					}
					this.play_Mo7yy();

				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
		}
    }
    
    public void play_Mo7yy(){
     	int marbles=0;
		if(this.game.getPlayers().get(3).getColour() == Colour.BLUE){
			 if(this.game.canPlayTurn()){
				try {
					System.out.println("GG");

					this.game.playPlayerTurn();
			    	System.out.println(this.game.getPlayers().get(3).getSelectedCard());

			    	this.game.endPlayerTurn();

					 for(int k=0; k<100; k++){

							if(this.game.getBoard().getTrack().get(k).getMarble()!=null && this.game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(3).getColour()){

								marbles++;
							}
					 }
					if(marbles > Blue){

						check_track(3);
						Blue = marbles;
					}
				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
		}
		if(this.game.getPlayers().get(3).getColour() == Colour.RED){
	        if(this.game.canPlayTurn()){
				try {
					System.out.println("GG");

					this.game.playPlayerTurn();
			    	System.out.println(this.game.getPlayers().get(3).getSelectedCard());

			    	this.game.endPlayerTurn();

					 for(int k=0; k<100; k++){
							if(this.game.getBoard().getTrack().get(k).getMarble()!=null && game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(3).getColour()){

								marbles++;
							}
					 }
					if(marbles > Red){

						check_track(3);
						Red = marbles;
					}
				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
		}
		if(this.game.getPlayers().get(3).getColour() == Colour.GREEN){
	        if(this.game.canPlayTurn()){
				try {
					System.out.println("GG");

					this.game.playPlayerTurn();
			    	System.out.println(this.game.getPlayers().get(3).getSelectedCard());

			    	this.game.endPlayerTurn();

					for(int k=0; k<100; k++){

							if(this.game.getBoard().getTrack().get(k).getMarble()!=null && game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(3).getColour()){

								marbles++;
							}
					 }
					if(marbles > Green){

						check_track(3);
						Green = marbles;
					}
				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
		}
		if(this.game.getPlayers().get(3).getColour() == Colour.YELLOW){
	        if(this.game.canPlayTurn()){
				try {
					System.out.println("GG");

					this.game.playPlayerTurn();
			    	System.out.println(this.game.getPlayers().get(3).getSelectedCard());

			    	this.game.endPlayerTurn();

					for(int k=0; k<100; k++){

							if(this.game.getBoard().getTrack().get(k).getMarble()!=null && game.getBoard().getTrack().get(k).getMarble().getColour() == this.game.getPlayers().get(3).getColour()){

								marbles++;
							}
					 }
					if(marbles > Yellow){

						check_track(3);
						Yellow  = marbles;
					}
				} catch (GameException e) {
					e.printStackTrace();
				}
	        }
		}
    }
    
    

}
