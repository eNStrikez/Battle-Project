import java.awt.Color;


public class MMEventManager{
	MMCanvasUI cUI;
	MMPanelManager MManager;
	public MMEventManager(MMCanvasUI cUI, MMPanelManager MManager){
		this.cUI = cUI;
		this.MManager = MManager;
	}
	
	public void updateBrushWidth(int newWidth){
		cUI.updateBrushWidth(newWidth);
	}
	public void updateBrushHeight(int newHeight){
		cUI.updateBrushHeight(newHeight);
	}
	public void updateColor(Color newColor){
		cUI.updateColor(newColor);
	}
	public void updateRoughness(int newRoughness){
		cUI.updateRoughness(newRoughness);
	}
	public void updateTileType(int newTileType){
		cUI.updateTileType(newTileType);
	}
	public void updatePanel(int roughness, Color color, int tileType){
		MManager.getCP().updateValues(roughness, color, tileType);
	}
}

