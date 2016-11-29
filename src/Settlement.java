import java.awt.Color;
import java.util.ArrayList;


public class Settlement {
	ArrayList<Building> avalibleBuildings;
	int gridSize;
	int x;
	int y;
	SettlementGridSpace[][] gridArray;
	ArrayList<Building> placedBuildings;
	
	public Settlement(int x, int y, int gridSize){
		avalibleBuildings = new ArrayList<Building>();
		this.gridSize = gridSize;
		this.x = x;
		this.y = y;
		
		placedBuildings = new ArrayList<Building>();
		gridArray = new SettlementGridSpace[gridSize][gridSize];
		
		for(int xCount = 0; xCount < gridSize; xCount ++){
			for(int yCount = 0; yCount < gridSize; yCount++){
				gridArray[xCount][yCount] = new SettlementGridSpace(0, Color.GRAY);
			}
		}
		
		
		Color block1Color = Color.ORANGE;
		ArrayList<int[]> blocks1 = new ArrayList<int[]>();
		blocks1.add(new int[] {0, -1});
		blocks1.add(new int[] {0, 0});
		blocks1.add(new int[] {0, 1});
		blocks1.add(new int[] {1, 0});
		avalibleBuildings.add(new Building("blueprint", blocks1, block1Color));
		
		Color block2Color = Color.MAGENTA;
		ArrayList<int[]> blocks2 = new ArrayList<int[]>();
		blocks2.add(new int[] {-1, -1});
		blocks2.add(new int[] {-1, 0});
		blocks2.add(new int[] {0, 0});
		blocks2.add(new int[] {1, 0});
		avalibleBuildings.add(new Building("blueprint2", blocks2, block2Color));
		
	}
	
	public int getGridSize(){
		return gridSize;
	}
	public SettlementGridSpace[][] getGrid(){
		return gridArray;
	}
	public void setGrid(int gridX, int gridY, int newValue){
		gridArray[gridX][gridY].setValue(newValue);
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public ArrayList<Building> getBuildings(){
		return avalibleBuildings;
	}
	public ArrayList<Building> getPlacedBuildings(){
		return placedBuildings;
	}
}
