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


public class SettlementManager extends JPanel{
	int screenWidth;
	int screenHeight;
	Settlement currentSettlement;
	CardManager cM;

	public SettlementManager(int screenWidth, int screenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;

		JButton returnButton = new JButton("Return");
		returnButton.setFocusable(false);
		returnButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				cM.showCard("SettlementManager", "OverCard");
			}

		});

		JPanel informationPanel = new JPanel();
		informationPanel.setPreferredSize(new Dimension(screenWidth, 200));
		informationPanel.setLayout(new GridBagLayout());
		informationPanel.add(returnButton, c);

		GridPaint display = new GridPaint();
		display.setPreferredSize(new Dimension(screenWidth - 500, screenHeight - 200));

		JPanel buildingSelection = new JPanel();
		buildingSelection.setLayout(new GridBagLayout());
		buildingSelection.setPreferredSize(new Dimension(500, screenHeight - 200));
		BuildingSelection buildingDisplay = new BuildingSelection();
		buildingDisplay.setPreferredSize(new Dimension(500, screenHeight - 200));
		buildingSelection.add(buildingDisplay, c);

		add(display, c);

		c.gridx = 1;
		add(buildingSelection, c);

		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 2;
		add(informationPanel, c);
	}

	public void giveCardManager(CardManager newCM){
		cM = newCM;
	}

	public void giveSettlement(Settlement newSettlement){
		currentSettlement = newSettlement;
	}

	public class GridPaint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;

			int gridWidth = screenWidth - 600;
			int gridHeight = screenHeight - 300;

			g.setColor(Color.GRAY);
			g.fillRect(0, 0, screenWidth - 300, screenHeight- 200);

			g.setColor(Color.BLACK);
			g.drawRect(50, 50, gridWidth, gridHeight);

			int gridSize = 10;
			int gapWidth = gridWidth/gridSize;

			for(int countX = 0; countX < gridSize; countX ++){
				for(int countY = 0; countY < gridSize; countY ++){
					g.drawRect(50 + countX*gapWidth, 50 + countY*gapWidth, gapWidth, gapWidth);
				}
			}
		}
	}

	public class BuildingSelection extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 500,  screenHeight - 200);
		}
	}
}
