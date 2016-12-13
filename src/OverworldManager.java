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
	JPanel informationHolder;
	JPanel battleHolder;
	JPanel controlHolder;

	public OverworldManager(int screenWidth, int screenHeight){
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		
		SaveManager sM = new SaveManager();
		
		map = sM.loadBlockages("OverworldMap.txt");

		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		
		viewport = new OverworldViewManager(0, 0, map);
		
		informationHolder = new JPanel();
		informationHolder.setPreferredSize(new Dimension(500, 800));
		battleHolder = new JPanel();
		battleHolder.setPreferredSize(new Dimension(800, 200));
		controlHolder = new JPanel();
		controlHolder.setPreferredSize(new Dimension(500, 200));
		
		Paint display = new Paint();
		display.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		c.fill = GridBagConstraints.BOTH;
		//add(display, c);
		c.gridx = 0;
		c.gridy = 0;
	
		add(viewport, c);
		
		/*
		c.gridx = 1;
		c.gridy = 0;
		add(informationHolder,c );
		c.gridx = 0;
		c.gridy = 1;
		add(battleHolder, c);
		c.gridx = 1;
		c.gridy = 1;
		add(controlHolder, c);
		*/
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
