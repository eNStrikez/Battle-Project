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


	public MMCanvasUI(){
		setPreferredSize(new Dimension(1000, 1000));
		brushHeight = 2;
		brushWidth = 2;
		roughness = 1;
		tileType = 0;
		//mapBlockages = new ArrayList<Obstruction>();

		Paint display = new Paint();
		display.setPreferredSize(new Dimension(1000, 1000));

		add(display);
		addMouseListener(this);
		addMouseMotionListener(this);
		brushColor = Color.BLACK;

		obstructionMap = new Obstruction[100][100];
		resetMap();
		selectedObstruction = new Obstruction(0, 0, 10, 10, Color.GRAY, 1, 0);
	}
	
	public void giveEManager(MMEventManager eManager){
		eM = eManager;
	}

	public void createNewObstruction(int cX, int cY){
		for(int sizeX = 0; sizeX < brushWidth; sizeX ++){
			for(int sizeY = 0; sizeY < brushHeight; sizeY ++){
				if(cX + sizeX < 100 && cX + sizeX > -1 && cY + sizeY < 100 && cY + sizeY > -1){
					obstructionMap[cX + sizeX][cY  +sizeY] = new Obstruction((cX + sizeX) * 10, (cY + sizeY) * 10, 10, 10, brushColor, roughness, tileType);
				}
			}
		}

	}
	public void copyObject(int cX, int cY){
		roughness = obstructionMap[cX][cY].getRoughness();
		brushColor = obstructionMap[cX][cY].getColor();
		tileType = obstructionMap[cX][cY].getTileType();
		eM.updatePanel(roughness, brushColor, tileType);
	}
	public void removeObstruction(int cX, int cY){
		for(int sizeX = 0; sizeX < brushWidth; sizeX ++){
			for(int sizeY = 0; sizeY < brushHeight; sizeY ++){
				obstructionMap[cX + sizeX][cY  +sizeY] = new Obstruction((cX + sizeX) * 10, (cY + sizeY) * 10, 10, 10, Color.GRAY, 1, 0);
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
		for(int x = 0; x < 100; x ++){
			for(int y = 0; y < 100; y ++){
				obstructionMap[x][y] = new Obstruction(x * 10, y * 10, 10, 10, Color.GRAY, 1, 0);
			}
		}
	}

	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, 1000, 1000);

			for(int x = 0; x < 100; x ++){
				for(int y = 0; y < 100; y ++){
					g.setColor(obstructionMap[x][y].getColor());
					g.fill(obstructionMap[x][y].getRect());
				}
			}

			g.setColor(Color.WHITE);
			g.drawRect(selectedObstruction.getX(), selectedObstruction.getY(), brushWidth * 10, brushHeight * 10);
		}
	}


	@Override
	public void mouseDragged(MouseEvent event){
		mouseX = event.getX() - (brushWidth * 10)/2;
		mouseY = event.getY() - (brushHeight * 10)/2;

		if((int) (mouseX / 10) < 100 && (int) (mouseX / 10) > -1 && (int) (mouseY / 10) < 100 && (int) (mouseY / 10) > -1){
			selectedObstruction.setLocation(obstructionMap[(int)mouseX/10][(int)mouseY/10].getX(), obstructionMap[(int)mouseX/10][(int)mouseY/10].getY());
		}

		if(SwingUtilities.isLeftMouseButton(event)){
			createNewObstruction((int) (mouseX / 10), (int) (mouseY / 10));
		} else if(SwingUtilities.isRightMouseButton(event)){
			removeObstruction((int) (mouseX / 10), (int) (mouseY / 10));
		}
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent event) {
		mouseX = event.getX() - (brushWidth * 10)/2;
		mouseY = event.getY() - (brushHeight * 10)/2;
		if((int) (mouseX / 10) < 100 && (int) (mouseX / 10) > -1 && (int) (mouseY / 10) < 100 && (int) (mouseY / 10) > -1){
			selectedObstruction.setLocation(obstructionMap[(int)mouseX/10][(int)mouseY/10].getX(), obstructionMap[(int)mouseX/10][(int)mouseY/10].getY());
		}


		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		mouseX = event.getX() - (brushWidth * 10)/2;
		mouseY = event.getY() - (brushHeight * 10)/2;
		if((int) (mouseX / 10) < 100 && (int) (mouseX / 10) > -1 && (int) (mouseY / 10) < 100 && (int) (mouseY / 10) > -1){
			selectedObstruction.setLocation(obstructionMap[(int)mouseX/10][(int)mouseY/10].getX(), obstructionMap[(int)mouseX/10][(int)mouseY/10].getY());
		}


		switch(event.getButton()){
		case 1:
			createNewObstruction((int) (mouseX / 10), (int) (mouseY / 10));
			break;
		case 2:
			copyObject((int) (mouseX / 10), (int) (mouseY / 10));
			break;
		case 3:
			removeObstruction((int) (mouseX / 10), (int) (mouseY / 10));
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
