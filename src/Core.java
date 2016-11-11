import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;


public class Core{
	public static void main(String [] args){
		new Core();
	}
	
	public Core(){
		int screenHeight = 1000;
		int screenWidth = 1300;
		
		MapDisplay mD = new MapDisplay(screenWidth, screenHeight);
		EventManager eM = new EventManager(mD);
		InfoPanel iP = new InfoPanel(300, screenHeight, eM);
		
		JPanel battlePanel = new JPanel();
		battlePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		battlePanel.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		battlePanel.add(mD, c);
		battlePanel.add(iP, c);

		MenuManager menuPanel = new MenuManager(screenWidth, screenHeight);
		CardManager cM = new CardManager(battlePanel, menuPanel, screenWidth, screenHeight);
		
		menuPanel.giveCardManager(cM);
		iP.giveCardManager(cM);

		FrameManager fM = new FrameManager(screenWidth, screenHeight, eM, cM, menuPanel);
		
		
		mD.giveInfoPanel(iP);
	}

}