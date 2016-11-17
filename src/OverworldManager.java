import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;


public class OverworldManager extends JPanel{

	MapDisplay mD;
	int screenWidth;
	int screenHeight;
	CardManager cM;

	public OverworldManager(MapDisplay mD, int screenWidth, int screenHeight){
		this.mD = mD;
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());

		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		
		Paint display = new Paint();
		display.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		c.fill = GridBagConstraints.BOTH;
		add(display, c);

	}

	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;

			g.setColor(Color.GREEN);
			g.fillRect(0, 0, screenWidth, screenHeight);
		}
	}
	
	public void giveCardManager(CardManager newCM){
		cM = newCM;
	}

	public void keyPressed(KeyEvent event){
		switch(event.getKeyCode()){
		case KeyEvent.VK_ESCAPE:
			cM.showCard("OverCard", "MenuCard");
			break;
		case KeyEvent.VK_S:
			cM.showCard("OverCard", "SettlementManager");
			break;
		default:
			break;
		}
	}
}
