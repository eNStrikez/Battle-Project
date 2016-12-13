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

	public OverworldViewManager(int startX, int startY, Obstruction[][] worldMap){
		xOffset = startX;
		yOffset = startY;
		map = worldMap;
		scaleSize = 0.75f;
		scaledWidth = (int) (100 * scaleSize);

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
	public int getSettlementAtLocation(int mouseX, int mouseY){
		return 0;
	}

	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;

			int squareSize = 800/scaledWidth;

			for(int xCount = 0; xCount < scaledWidth; xCount ++){
				for(int yCount = 0; yCount < scaledWidth; yCount ++){
					Obstruction currentSquare = map[xCount + xOffset][yCount + yOffset];
					g.setColor(currentSquare.getColor());
					g.fillRect(squareSize * xCount, squareSize * yCount, squareSize, squareSize);
				}
			}
		}
	}
}
