import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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
		OverworldInformationPanel infoPanel = new OverworldInformationPanel(300, 1000);

		Paint display = new Paint();
		display.setPreferredSize(new Dimension(screenWidth, screenHeight));

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;

		add(viewport, c);

		c.gridx = 1;
		add(infoPanel, c);

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

	public void mouseMoved(MouseEvent event){
		int mouseX = event.getX();
		int mouseY = event.getY();

		int distIntoViewportX = mouseX - viewport.getX();
		int distIntoViewportY = mouseY - viewport.getY();

		if(distIntoViewportX > 0 && distIntoViewportX < viewport.getWidth()){
			if(distIntoViewportY > 0 && distIntoViewportY < viewport.getHeight()){

				//mouse is inside the view port
				if(viewport.getAtLocation(distIntoViewportX, distIntoViewportY)){
					viewport.getSettlementIDAtLocation();
				}

			}
		}
	}

	public void mouseClicked(MouseEvent event){
		int distIntoViewportX = event.getX() - viewport.getX();
		int distIntoViewportY = event.getY() - viewport.getY();

		if(distIntoViewportX > 0 && distIntoViewportX < viewport.getWidth()){
			if(distIntoViewportY > 0 && distIntoViewportY < viewport.getHeight()){
				viewport.clicked();
				mouseMoved(event);
				if(viewport.getSettlementIDAtLocation() != 0 && viewport.getSettlementIDAtLocation() != viewport.getLastSelectedSettlement()){
					viewport.clicked();
				}
			}
		}
	}
	public void mouseDragged(MouseEvent event){
		int distIntoViewportX = event.getX() - viewport.getX();
		int distIntoViewportY = event.getY() - viewport.getY();

		if(distIntoViewportX > 0 && distIntoViewportX < viewport.getWidth()){
			if(distIntoViewportY > 0 && distIntoViewportY < viewport.getHeight()){
				viewport.clicked();
				mouseMoved(event);
				if(viewport.getSettlementIDAtLocation() != 0 && viewport.getSettlementIDAtLocation() != viewport.getLastSelectedSettlement()){
					viewport.clicked();
				}
			}
		}
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
