import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class OverworldInformationPanel extends JPanel{
	
	int width;
	int height;
	
	public OverworldInformationPanel(int width, int height){
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
		
		Paint display = new Paint();
		display.setPreferredSize(new Dimension(width, height));
		add(display);
		
	}
	
	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;
			
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, width, height);
		}
	}
}
