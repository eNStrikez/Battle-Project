import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Building {
	String name;
	ArrayList<int[]> size;
	Color color;
	
	public Building(String name, ArrayList<int[]> size, Color color){
		this.name = name;
		this.size = size;
		this.color = color;
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
}
