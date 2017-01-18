import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class OverworldViewManager extends JPanel{
	int xOffset;
	int yOffset;
	Obstruction[][] map;
	JScrollPane viewport;
	float scaleSize;
	int scaledWidth;
	int squareSize;
	Boolean selectedFixed;
	ArrayList<Army> allArmies;
	Boolean armyHovering;
	Boolean settlementHovering;
	int hoveringID;
	Selectable selected;
	Army hoveringArmy;
	Settlement hoveringSettlement;

	public OverworldViewManager(int startX, int startY, Obstruction[][] worldMap){
		xOffset = startX;
		yOffset = startY;
		map = worldMap;
		scaleSize = 1f;
		scaledWidth = (int) (100 * scaleSize);
		squareSize = 1000/scaledWidth;
		selectedFixed = false;
		armyHovering = false;
		settlementHovering = false;

		setPreferredSize(new Dimension(1000, 1000));

		Paint display = new Paint();
		display.setPreferredSize(new Dimension(1000, 1000));
		add(display);
	}

	public void changeXOffset(int change){
		if(change < 0){
			if(xOffset > 0){
				xOffset += change;
			}
		} else {
			if(xOffset + scaledWidth < 200){
				xOffset += change;
			}
		}
		repaint();
	}
	public void changeYOffset(int change){
		if(change < 0){
			if(yOffset > 0){
				yOffset += change;
			}
		} else {
			if(yOffset + scaledWidth < 200){
				yOffset += change;
			}
		}
		repaint();
	}
	public Selectable getSelectedAtLocation(){
		return selected;
	}

	public Boolean isSomethingAtLocation(int x, int y){
		int[] coords = getGridLocation(x, y);
		if(map[coords[0] + xOffset][coords[1] + yOffset].getSettlementID() != 0){
			settlementHovering = true;
			hoveringID = map[coords[0] + xOffset][coords[1] + yOffset].getSettlementID();
			// get the settlement from the database and set the hovering settlement to that settlement
			
			if(!selectedFixed){
				selected = hoveringSettlement;
			}
			armyHovering = false;
			for(Army nextArmy: allArmies){
				if(nextArmy.isSelected()){
					nextArmy.setSelected(false);
				}
			}
		} else {
			settlementHovering = false;
			armyHovering = false;
			for(Army nextArmy: allArmies){
				if(nextArmy.getX() == coords[0] + xOffset && nextArmy.getY() == coords[1] + yOffset){
					nextArmy.setSelected(true);
					hoveringArmy = nextArmy;
					if(!selectedFixed){
						selected = hoveringArmy;
					}
					armyHovering = true;
					hoveringID = nextArmy.getID();
					settlementHovering = false;
				} else {
					nextArmy.setSelected(false);
				}
			}
		}
		repaint();
		if(armyHovering || settlementHovering){
			return true;
		} else {
			return false;
		}
	}

	public Selectable getSelected(){
		return selected;
	}

	public int[] getGridLocation(int x, int y){
		int[] coords = new int[2];

		coords[0] = x/squareSize;
		coords[1] = y/squareSize;

		return coords;
	}

	public void clicked(){
		if(!selectedFixed){
			if(settlementHovering || armyHovering){
				selectedFixed = true;
			}
		} else {
			if(!settlementHovering && !armyHovering){
				selectedFixed = false;
			} else if(settlementHovering){
				selected = hoveringSettlement;
			} else {
				selected = hoveringArmy;
			}
		}
	}

	public Boolean isMouseingOverSomething(){
		if(armyHovering || settlementHovering){
			System.out.println("Can double click1");
			return true;
		} else {
			return false;
		}
	}
	public Boolean isMouseingOverSelected(){
		if(settlementHovering){

		} else {
			//work out if the army that has the selected ID is still being hovered over

		}
		return true;
	}

	public void giveArmies(ArrayList<Army> givenAllArmies){
		allArmies = givenAllArmies;
	}

	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1000, 1000);

			//draw the map
			for(int xCount = 0; xCount < scaledWidth; xCount ++){
				for(int yCount = 0; yCount < scaledWidth; yCount ++){
					Obstruction currentSquare = map[xCount + xOffset][yCount + yOffset];
					g.setColor(currentSquare.getColor());
					g.fillRect(squareSize * xCount, squareSize * yCount, squareSize, squareSize);

					if(currentSquare.isSettlement()){
						if(currentSquare.getSettlementID() == hoveringID){
							g.setColor(Color.YELLOW);
							System.out.println(hoveringID);
							g.drawRect(squareSize * xCount, squareSize * yCount, squareSize, squareSize);

						}
					}
				}
			}

			//draw the armies
			for(Army nextArmy: allArmies){
				g.drawImage(nextArmy.getImage(), (nextArmy.getX() - xOffset) * squareSize, (nextArmy.getY() - yOffset) * squareSize, squareSize, squareSize, null);
				if(nextArmy.isSelected()){
					g.setColor(Color.YELLOW);
					g.drawRect((nextArmy.getX() - xOffset) * squareSize, (nextArmy.getY() - yOffset) * squareSize, squareSize, squareSize);
				}
			}
		}
	}
}
