import java.awt.Color;


public class SettlementGridSpace {
	int value;
	Color color;
	
	public SettlementGridSpace(int value, Color color){
		this.value = value;
		this.color = color;
	}
	
	public void setValue(int newValue){
		value= newValue;
	}
	public int getValue(){
		return value;
	}
	
	public void setColor(Color newColor){
		color = newColor;
	}
	public Color getColor(){
		return color;
	}
}
