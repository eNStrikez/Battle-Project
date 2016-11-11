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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class MenuManager extends JPanel{
	int screenWidth;
	int screenHeight;
	CardManager cM;
	String origin;

	public MenuManager(int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		GridBagConstraints c = new GridBagConstraints();

		origin = "BattleCard";
		
		JLayeredPane lPane = new JLayeredPane();
		lPane.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.setBounds(500,  400,  300,  500);
		
		JButton buttonOne = new JButton("1");
		buttonOne.setFocusable(false);
		JButton buttonTwo = new JButton("2");
		buttonTwo.setFocusable(false);
		JButton buttonThree = new JButton("3");
		buttonThree.setFocusable(false);
		
		JButton battleButton = new JButton("Fight");
		battleButton.setFocusable(false);
		battleButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				cM.showCard(origin);
			}
			
		});
		
		Paint display = new Paint(); 
		display.setBounds(0, 0, screenWidth, screenHeight);
		
		c.weightx = 1.0;
		
		buttonPanel.add(battleButton, c);
		buttonPanel.add(buttonOne, c);
		buttonPanel.add(buttonTwo, c);
		buttonPanel.add(buttonThree, c);
		
		
		lPane.add(display, new Integer(0));
		lPane.add(buttonPanel, new Integer(1));
		
		add(lPane);
		setVisible(true);
	}

	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, screenWidth, screenHeight);
		}
	}
	
	public void giveOrigin(String newOrigin){
		origin = newOrigin;
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
