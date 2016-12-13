import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class MMCanvasUI extends JPanel implements MouseMotionListener, MouseListener{

	//ArrayList<Obstruction> mapBlockages;
	int mouseX;
	int mouseY;
	int brushHeight;
	int brushWidth;
	Color brushColor;
	int roughness;
	int tileType;

	Obstruction[][] obstructionMap;
	Obstruction selectedObstruction;
	MMEventManager eM;

	int gridSize;
	int squareSize;
	Boolean isSettlement;
	int settlementID;


	public MMCanvasUI(){
		setPreferredSize(new Dimension(1000, 1000));
		brushHeight = 2;
		brushWidth = 2;
		roughness = 1;
		tileType = 0;
		isSettlement = false;
		settlementID = 0;
		//mapBlockages = new ArrayList<Obstruction>();

		Paint display = new Paint();
		display.setPreferredSize(new Dimension(1000, 1000));

		add(display);
		addMouseListener(this);
		addMouseMotionListener(this);
		brushColor = Color.BLACK;

		gridSize = 100;
		squareSize = 10;

		obstructionMap = new Obstruction[gridSize][gridSize];
		resetMap();
		selectedObstruction = new Obstruction(0, 0, squareSize, squareSize, Color.GRAY, 1, 0, false, 0);
	}

	public void changeGridSize(int newSize){
		obstructionMap = new Obstruction[newSize][newSize];
		gridSize = newSize;

		squareSize = 1000 / gridSize;
		eM.updatePanel(roughness, brushColor, tileType, squareSize);
		resetMap();
		repaint();
	}

	public void giveEManager(MMEventManager eManager){
		eM = eManager;
	}

	public void createNewObstruction(int cX, int cY){
		for(int sizeX = 0; sizeX < brushWidth; sizeX ++){
			for(int sizeY = 0; sizeY < brushHeight; sizeY ++){
				if(cX + sizeX < gridSize && cX + sizeX > -1 && cY + sizeY < gridSize && cY + sizeY > -1){
					if(isSettlement){
						settlementID ++;
					}
					obstructionMap[cX + sizeX][cY  +sizeY] = new Obstruction((cX + sizeX) * squareSize, (cY + sizeY) * squareSize, squareSize, squareSize, brushColor, roughness, tileType, isSettlement, settlementID);
				}
			}
		}

	}
	public void copyObject(int cX, int cY){
		roughness = obstructionMap[cX][cY].getRoughness();
		brushColor = obstructionMap[cX][cY].getColor();
		tileType = obstructionMap[cX][cY].getTileType();
		eM.updatePanel(roughness, brushColor, tileType, squareSize);
	}
	public void removeObstruction(int cX, int cY){
		for(int sizeX = 0; sizeX < brushWidth; sizeX ++){
			for(int sizeY = 0; sizeY < brushHeight; sizeY ++){
				obstructionMap[cX + sizeX][cY  +sizeY] = new Obstruction((cX + sizeX) * squareSize, (cY + sizeY) * squareSize, squareSize, squareSize, Color.GRAY, 1, 0, false, 0);
			}
		}
	}

	public void updateBrushWidth(int newBrushWidth){
		brushWidth = newBrushWidth;
	}
	public void updateBrushHeight(int newBrushHeight){
		brushHeight = newBrushHeight;
	}
	public void updateRoughness(int newRoughness){
		roughness = newRoughness;
	}
	public void updateColor(Color newColor){
		brushColor = newColor;
	}
	public void updateTileType(int newTileType){
		tileType = newTileType;
	}

	public Obstruction[][] getObstructionMap(){
		return obstructionMap;

	}
	public void setObstructionMap(Obstruction[][] loadObstructionMap){
		obstructionMap = loadObstructionMap;
	}
	public void emptyMap(){
		resetMap();
		repaint();
	}
	public void resetMap(){
		for(int x = 0; x < gridSize; x ++){
			for(int y = 0; y < gridSize; y ++){
				obstructionMap[x][y] = new Obstruction(x * squareSize, y * squareSize, squareSize, squareSize, Color.GRAY, 1, 0, false, 0);
			}
		}
	}
	public void placingSettlements(Boolean isPlacing){
		isSettlement = isPlacing;
	}

	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, 1000, 1000);

			for(int x = 0; x < gridSize; x ++){
				for(int y = 0; y < gridSize; y ++){
					g.setColor(obstructionMap[x][y].getColor());
					g.fill(obstructionMap[x][y].getRect());
					if(obstructionMap[x][y].isSettlement()){
						g.setColor(Color.RED);
						g.draw(obstructionMap[x][y].getRect());
					}
				}
			}

			g.setColor(Color.WHITE);
			g.drawRect(selectedObstruction.getX(), selectedObstruction.getY(), brushWidth * squareSize, brushHeight * squareSize);
		}
	}


	@Override
	public void mouseDragged(MouseEvent event){
		mouseX = event.getX() - (brushWidth * squareSize)/2;
		mouseY = event.getY() - (brushHeight * squareSize)/2;

		if((int) (mouseX / squareSize) < gridSize && (int) (mouseX / squareSize) > -1 && (int) (mouseY / squareSize) < gridSize && (int) (mouseY / squareSize) > -1){
			selectedObstruction.setLocation(obstructionMap[(int)mouseX/squareSize][(int)mouseY/squareSize].getX(), obstructionMap[(int)mouseX/squareSize][(int)mouseY/squareSize].getY());
		}

		if(SwingUtilities.isLeftMouseButton(event)){
			createNewObstruction((int) (mouseX / squareSize), (int) (mouseY / squareSize));
		} else if(SwingUtilities.isRightMouseButton(event)){
			removeObstruction((int) (mouseX / squareSize), (int) (mouseY / squareSize));
		}
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent event) {
		mouseX = event.getX() - (brushWidth * squareSize)/2;
		mouseY = event.getY() - (brushHeight * squareSize)/2;
		if((int) (mouseX / squareSize) < gridSize && (int) (mouseX / squareSize) > -1 && (int) (mouseY / squareSize) < gridSize && (int) (mouseY / squareSize) > -1){
			selectedObstruction.setLocation(obstructionMap[(int)mouseX/squareSize][(int)mouseY/squareSize].getX(), obstructionMap[(int)mouseX/squareSize][(int)mouseY/squareSize].getY());
		}


		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		mouseX = event.getX() - (brushWidth * squareSize)/2;
		mouseY = event.getY() - (brushHeight * squareSize)/2;
		if((int) (mouseX / squareSize) < gridSize && (int) (mouseX / squareSize) > -1 && (int) (mouseY / squareSize) < gridSize && (int) (mouseY / squareSize) > -1){
			selectedObstruction.setLocation(obstructionMap[(int)mouseX/squareSize][(int)mouseY/squareSize].getX(), obstructionMap[(int)mouseX/squareSize][(int)mouseY/squareSize].getY());
		}


		switch(event.getButton()){
		case 1:
			createNewObstruction((int) (mouseX / squareSize), (int) (mouseY / squareSize));
			break;
		case 2:
			copyObject((int) (mouseX / squareSize), (int) (mouseY / squareSize));
			break;
		case 3:
			removeObstruction((int) (mouseX / squareSize), (int) (mouseY / squareSize));
			break;
		default:
			break;
		}
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent event) {

	}
	@Override
	public void mouseExited(MouseEvent event) {

	}
	@Override
	public void mousePressed(MouseEvent event) {

	}
	@Override
	public void mouseReleased(MouseEvent event) {

	}
}
