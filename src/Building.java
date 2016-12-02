import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Building implements Cloneable{
	String name;
	ArrayList<int[]> size;
	Color color;
	int placedIndex;
	int cost;
	int identifier;
	
	public Building(String name, ArrayList<int[]> size, Color color, int cost, int identifier){
		this.name = name;
		this.size = size;
		this.color = color;
		placedIndex = 0;
		this.cost = cost;
		this.identifier = identifier;
	}
	
	public Building(Building copyBuilding){
		name = copyBuilding.getName();
		color = copyBuilding.getColor();
		placedIndex = copyBuilding.getPlacedIndex();
		size = new ArrayList<int[]>(copyBuilding.getTakenBlocks());
	}
	
	public int getCost(){
		return cost;
	}
	
	public String getName(){
		return name;
	}
	public ArrayList<int[]> getTakenBlocks(){
		return size;
	}
	public Image getImage(){
		return new ImageIcon(name + ".jpg").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
	}
	public Color getColor(){
		return color;
	}
	public Image getPlacedImage(){
		return new ImageIcon(name + "Placed.jpg").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
	}
	public int getPlacedIndex(){
		return placedIndex;
	}
	public void setPlacedIndex(int newIndex){
		placedIndex = newIndex;
	}
}
