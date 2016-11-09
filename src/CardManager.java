import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;


public class CardManager extends JPanel{
	CardLayout cl;
	String currentCard;
	
	public CardManager(JPanel battleCard, JPanel menuCard, int screenWidth, int screenHeight){
		cl = new CardLayout();
		
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		setLayout(cl);
		
		add(battleCard, "BattleCard");
		//deck.add(overCard, "OverCard");
		add(menuCard, "MenuCard");
		
		cl.show(this, "MenuCard");
		currentCard = "MenuCard";
		
	}
	
	public void showCard(String cardName){
		cl.show(this, cardName);
		currentCard = cardName;
	}
	public String getCurrentCard(){
		return currentCard;
	}
	
}
