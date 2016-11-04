
public class Core{
	public static void main(String [] args){
		new Core();
	}
	
	public Core(){
		int screenHeight = 1000;
		int screenWidth = 1200;
		
		MapDisplay mD = new MapDisplay(screenWidth, screenHeight);
		EventManager eM = new EventManager(mD);
		InfoPanel iP = new InfoPanel(200, screenHeight, eM);
		FrameManager fM = new FrameManager(screenWidth, screenHeight, eM, mD, iP);
		
		mD.giveInfoPanel(iP);
	}

}