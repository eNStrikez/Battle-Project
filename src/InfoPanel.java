import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class InfoPanel extends JPanel{
	int panelWidth;
	int panelHeight;
	ArrayList<Unit> selectedUnits;
	JPanel controlOptionPane;
	JSlider agroSlider;
	

	public InfoPanel(int panelWidth, int panelHeight){
		this.panelWidth = panelWidth;
		this.panelHeight = panelHeight;

		selectedUnits = new ArrayList<Unit>();

		Paint display = new Paint();
		display.setPreferredSize(new Dimension(panelWidth, panelHeight - 200));
		
		controlOptionPane = new JPanel();
		controlOptionPane.setPreferredSize(new Dimension(panelWidth, 200));
		
		JLabel agroSliderLabel = new JLabel("Agression Range");
		agroSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		agroSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 100);
		agroSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent event) {
				//update agro range on selected units
				repaint();
			}

		});
		agroSlider.setFocusable(false);
		agroSlider.setMajorTickSpacing(100);
		agroSlider.setMinorTickSpacing(20);
		agroSlider.setPaintTicks(true);
		agroSlider.setPaintLabels(true);
		
		controlOptionPane.add(agroSliderLabel);
		controlOptionPane.add(agroSlider);
		
		add(BorderLayout.CENTER, display);
		add(BorderLayout.SOUTH, controlOptionPane);
	}
	
	public void updateSelectedUnits(ArrayList<Unit> newSelectedUnits){
		selectedUnits.clear();
		selectedUnits.addAll(newSelectedUnits);
		repaint();
	}

	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, panelWidth, panelHeight);

			for(int i = 0; i < selectedUnits.size(); i ++){
				g.setColor(Color.BLACK);
				switch(selectedUnits.get(i).getType()){
				case 0:
					g.drawString("Type: Land", 20, (70 * i ) + 10);
					break;
				case 1:
					g.drawString("Type: Air", 20, (70 * i ) + 10);
					break;
				case 2:
					g.drawString("Type: Sea", 20, (70 * i ) + 10);
					break;
				default:
					break;
				}
				g.drawString("Agression range: " + selectedUnits.get(i).getAgroRange(), 20, (70 * i) + 29);
				g.setColor(Color.GREEN);
				g.drawRect(10, (70 * i ) + 49, 181, 11);
				g.setColor(Color.RED);
				g.fillRect(11, (70 * i ) + 50, 180, 10);
				g.setColor(Color.GREEN);
				g.fillRect(11, (70 * i ) + 50, (int)(180 * selectedUnits.get(i).getHealthRatio()), 10);
			}

		}
	}
}
