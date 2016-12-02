import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class OverworldManager extends JPanel{

	int screenWidth;
	int screenHeight;
	CardManager cM;
	OverworldViewManager viewport;
	Obstruction[][] map;

	public OverworldManager(int screenWidth, int screenHeight){
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		SaveManager sM = new SaveManager();
		
		map = sM.loadBlockages("OverworldMap.txt");

		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		
		viewport = new OverworldViewManager(0, 0, map);
		
		Paint display = new Paint();
		display.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		c.fill = GridBagConstraints.BOTH;
		//add(display, c);
		add(viewport, c);

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
			
		case KeyEvent.VK_RIGHT:
			viewport.changeXOffset(1);
			break;
		case KeyEvent.VK_LEFT:
			viewport.changeXOffset(-1);
			break;
		case KeyEvent.VK_UP:
			viewport.changeYOffset(-1);
			break;
		case KeyEvent.VK_DOWN:
			viewport.changeYOffset(1);
			break;
		default:
			break;
		}
	}
}
