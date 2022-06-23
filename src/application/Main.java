package application;

import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;





public class Main extends Application {
	ArrayList<Card> deck = new ArrayList<Card>();
	ArrayList<Card> playerPile = new ArrayList<Card>();
	ArrayList<Card> dealerPile = new ArrayList<Card>();
	private FlowPane playerPanel;
	private FlowPane dealerPanel;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 500, 700);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("BlackJack");

			dealerPanel = new FlowPane();
			dealerPanel.setAlignment(Pos.CENTER);
			root.setTop(dealerPanel);
			playerPanel = new FlowPane();
			playerPanel.setAlignment(Pos.CENTER);
			root.setCenter(playerPanel);

			FlowPane buttonsPanel = new FlowPane();
			buttonsPanel.setAlignment(Pos.CENTER);
			Button hitButton = new Button("Hit");
			Button stayButton = new Button("Stay");
			buttonsPanel.getChildren().add(hitButton);
			buttonsPanel.getChildren().add(stayButton);
			root.setBottom(buttonsPanel);
			
			
			InitDeck();
			dealToDealer();
			dealToPlayer();
			dealToDealer();
			dealToPlayer();
			
			hitButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					dealToPlayer();
					if(PlayerIsBusted()){
						JOptionPane.showMessageDialog(null, "Over 21! Game over,player is busted");
						
						
					}
				}
			});

			stayButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					System.out.println("Staying");
					dealToDealer();
                                        
                                        if(DealerIsBusted()){
                                            JOptionPane.showMessageDialog(null, "Dealer is busted");
                                            
                                            
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Player wins");
                                            
                                            
                                        }
                                        if(winDealer()){
                                            JOptionPane.showMessageDialog(null, "Dealer is busted ");
                                            
                                        }
                                            else
                                            {
                                                    JOptionPane.showMessageDialog(null, "Player wins");
                                                    
                                                    
                                                    }                    
				}

				
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
        
	
	private boolean PlayerIsBusted() {
		int playerSum = 0;
                for(int i=0;i<playerPile.size();i++){
                    Card c=playerPile.get(i);
                    playerSum+=c.getvalue();
                }
		
		if (playerSum > 21) 
			return true;
		 
		return false;
	}
        private boolean DealerIsBusted(){
            int dealerSum=0;
            for(int i=0;i<dealerPile.size();i++){
                Card c=dealerPile.get(i);
                dealerSum+=c.getvalue();
            }
            if(dealerSum>21){
                return true;
            }
            return false;
        }
        private boolean winDealer(){
            int dealerSum=0;
            int playerSum=0;
            for(int i=0;i<playerPile.size();i++){
                Card c=playerPile.get(i);
                playerSum+=c.getvalue();
            }
            for(int i=0;i<dealerPile.size();i++){
                Card c=dealerPile.get(i);
                dealerSum+=c.getvalue();
            }
            if(playerSum>dealerSum)
                return true;
            
            return false;
        }

	private void InitDeck() {
		String[] suits = new String[] { "Hearts", "Diamonds", "Clubs", "Spades" };
		for (String s : suits) {
			for (int i = 1; i < 14; i++) {
				Card c = new Card(i, s);
				deck.add(c);
			}
		}
	}
	
	private void dealToDealer(){
		Random rand = new Random();
		int upperbound=14;
		Card c = deck.remove(rand.nextInt(upperbound));
		dealerPile.add(c);
		DisplayCard(c, dealerPanel);
	}
	
	private void dealToPlayer(){
		Random rand = new Random();
		int upperbound=14;
		Card c = deck.remove(rand.nextInt(upperbound));
		playerPile.add(c);
		DisplayCard(c, playerPanel);
	}
	
	private void DisplayCard(Card c, FlowPane panel) {
		Image img = new Image("cards/" + c.GetFileName());
		ImageView viewer = new ImageView(img);
		panel.getChildren().add(viewer);
		viewer.setPreserveRatio(true);
		viewer.setFitWidth(100);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
