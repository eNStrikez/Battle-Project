import java.awt.Image;
import java.util.ArrayList;


public class Army extends Selectable{
	
	ArrayList<Unit> containedUnits;
	Image coverImage;
	Boolean selected;
	
	public Army(ArrayList<Unit> containedUnits, Image coverImage){
		this.ID = ID;
		this.containedUnits = containedUnits;
		this.coverImage = coverImage;
		selected = false;
	}
	
	public Image getImage(){
		return coverImage;
	}
	
	public Boolean isSelected(){
		return selected;
	}
	
	public void setSelected(Boolean isSelected){
		selected = isSelected;
	}
}
