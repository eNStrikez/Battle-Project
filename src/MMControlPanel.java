import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MMControlPanel extends JPanel{

	JSlider widthSlider;
	MMEventManager eManager;
	JSlider heightSlider;
	JSlider roughnessSlider;
	JSlider typeSlider;
	JButton colorChooseButton;
	JButton modeToggleButton;
	Color brushColor;
	JColorChooser colorChooser;
	int roughness;
	int tileType;
	int brushSize;
	
	Boolean overMode = false;

	public MMControlPanel(MMEventManager newManager){
		eManager = newManager;
		brushColor = Color.BLACK;
		Paint cDisplay = new Paint();
		cDisplay.setPreferredSize(new Dimension(300, 1000));
		roughness = 1;
		tileType = 0;
		brushSize = 10;

		colorChooser = new JColorChooser();

		JLabel widthSliderLabel = new JLabel("Width");
		widthSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		widthSlider = new JSlider(JSlider.HORIZONTAL, 1, 15, 2);
		widthSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent event) {
				eManager.updateBrushWidth(widthSlider.getValue());
				repaint();
			}

		});
		widthSlider.setFocusable(false);
		widthSlider.setMajorTickSpacing(1);
		widthSlider.setPaintTicks(true);
		widthSlider.setPaintLabels(true);

		JLabel heightSliderLabel = new JLabel("Height");
		heightSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		heightSlider = new JSlider(JSlider.HORIZONTAL, 1, 15, 2);
		heightSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent event) {
				eManager.updateBrushHeight(heightSlider.getValue());
				repaint();
			}

		});
		heightSlider.setFocusable(false);
		heightSlider.setMajorTickSpacing(1);
		heightSlider.setPaintTicks(true);
		heightSlider.setPaintLabels(true);

		colorChooseButton = new JButton("Pick Colour");
		colorChooseButton.setFocusable(false);
		colorChooseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				brushColor = colorChooser.showDialog(null, "Color Picker", brushColor);
				eManager.updateColor(brushColor);
				repaint();
			}

		});

		JLabel roughnessLabel = new JLabel("Roughness (0 for wall)");

		roughnessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		roughnessSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 1);
		roughnessSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent event) {
				roughness = roughnessSlider.getValue();
				eManager.updateRoughness(roughness);
				repaint();
			}

		});
		roughnessSlider.setFocusable(false);
		roughnessSlider.setMinorTickSpacing(1);
		roughnessSlider.setMajorTickSpacing(5);
		roughnessSlider.setPaintTicks(true);
		roughnessSlider.setPaintLabels(true);

		JLabel typeLabel = new JLabel("Type(Land:0, Chasm:1, Water:2, Bridge:3)");

		typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		typeSlider = new JSlider(JSlider.HORIZONTAL, 0, 3, 0);
		typeSlider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent event) {
				tileType = typeSlider.getValue();
				eManager.updateTileType(tileType);
				repaint();
			}

		});
		typeSlider.setFocusable(false);
		typeSlider.setMinorTickSpacing(1);
		typeSlider.setMajorTickSpacing(1);
		typeSlider.setPaintTicks(true);
		typeSlider.setPaintLabels(true);
		
		modeToggleButton = new JButton("Toggle Mode");
		modeToggleButton.setFocusable(false);
		modeToggleButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				changeMode();
				repaint();
			}
			
		});

		cDisplay.add(widthSliderLabel);
		cDisplay.add(widthSlider);
		cDisplay.add(heightSliderLabel);
		cDisplay.add(heightSlider);
		cDisplay.add(colorChooseButton);
		cDisplay.add(roughnessLabel);
		cDisplay.add(roughnessSlider);
		cDisplay.add(typeLabel);
		cDisplay.add(typeSlider);
		cDisplay.add(modeToggleButton);


		add(cDisplay);
	}
	
	public void changeMode(){
		overMode = !overMode;
		eManager.changeMode(overMode);
	}
	
	public void updateValues(int newRoughness, Color newColor, int newTileType, int newBrushSize){
		roughnessSlider.setValue(newRoughness);
		roughness = newRoughness;
		brushColor = newColor;
		typeSlider.setValue(newTileType);
		tileType = newTileType;
		brushSize = newBrushSize;
		repaint();
	}

	public class Paint extends JPanel{
		public void paintComponent(Graphics gr){
			Graphics2D g = (Graphics2D) gr;
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, 300, 1000);

			g.setColor(brushColor);
			g.fillRect(50, 300, widthSlider.getValue()*brushSize, heightSlider.getValue()*brushSize);
		}
	}
}
