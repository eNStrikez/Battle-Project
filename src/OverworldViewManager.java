import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
	int lastSettlementID;
	Boolean clickFixed;
	int lastSelectedSettlement;

	public OverworldViewManager(int startX, int startY, Obstruction[][] worldMap){
		xOffset = startX;
		yOffset = startY;
		map = worldMap;
		scaleSize = 1f;
		scaledWidth = (int) (100 * scaleSize);
		squareSize = 1000/scaledWidth;
		lastSettlementID = 0;
		clickFixed = false;
		lastSelectedSettlement = 0;

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
	public int getSettlementIDAtLocation(){
		return lastSettlementID;
	}

	public Boolean getAtLocation(int x, int y){
		int[] coords = getGridLocation(x, y);
		if(!clickFixed){
			lastSettlementID = map[coords[0] + xOffset][coords[1] + yOffset].getSettlementID();
		}
		repaint();
		return map[coords[0] + xOffset][coords[1] + yOffset].isSettlement();
	}

	public int[] getGridLocation(int x, int y){
		int[] coords = new int[2];

		coords[0] = x/squareSize;
		coords[1] = y/squareSize;

		return coords;
	}

	public void clicked(){
		if(lastSettlementID != 0){
			lastSelectedSettlement = lastSettlementID;
			clickFixed = !clickFixed;
		}
	}
	
	public int getLastSelectedSettlement(){
		return lastSelectedSettlement;
	}

	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1000, 1000);

			for(int xCount = 0; xCount < scaledWidth; xCount ++){
				for(int yCount = 0; yCount < scaledWidth; yCount ++){
					Obstruction currentSquare = map[xCount + xOffset][yCount + yOffset];
					g.setColor(currentSquare.getColor());
					g.fillRect(squareSize * xCount, squareSize * yCount, squareSize, squareSize);

					if(currentSquare.isSettlement()){
						if(currentSquare.getSettlementID() == lastSettlementID){
							g.setColor(Color.YELLOW);
							g.drawRect(squareSize * xCount, squareSize * yCount, squareSize, squareSize);

						}
					}
				}
			}
		}
	}
}
