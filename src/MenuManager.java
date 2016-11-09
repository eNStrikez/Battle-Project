import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuManager extends JPanel{
	int screenWidth;
	int screenHeight;
	CardManager cM;

	public MenuManager(int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JButton battleButton = new JButton("FightBattle");
		battleButton.setFocusable(false);
		battleButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				cM.showCard("BattleCard");
			}
			
		});
		
		Paint display = new Paint();
		display.setPreferredSize(new Dimension(screenWidth, screenHeight));
		add(display, c);
		add(battleButton, c);
	}

	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, screenWidth, screenHeight);
		}
	}
	
	public void giveCardManager(CardManager newCM){
		cM = newCM;
	}

	public void keyPressed(KeyEvent event){
		switch(event.getKeyCode()){
		case KeyEvent.VK_ESCAPE:
			System.exit(1);
			break;
		default:
			break;
		}
	}

}
