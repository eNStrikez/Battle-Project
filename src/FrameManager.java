import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class FrameManager implements MouseListener, MouseMotionListener, KeyListener{
	JFrame frame;
	EventManager eM;
	MapDisplay mD;
	InfoPanel iP;
	
	public FrameManager(int screenWidth, int screenHeight, EventManager eM, MapDisplay mD, InfoPanel iP){
		frame = new JFrame();
		frame.setSize(new Dimension(screenWidth, screenHeight));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		this.eM = eM;
		this.mD = mD;
		this.iP = iP;
		
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.addKeyListener(this);
		frame.setUndecorated(true);
		
		frame.add(BorderLayout.WEST, mD);
		frame.add(BorderLayout.EAST, iP);
		
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		 eM.keyPressed(event);
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		 
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		 
		
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		 eM.mouseDragged(event);
		
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		 
		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		 eM.mouseClicked(event);
		
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		 
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		 
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		 
		
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		 eM.mouseReleased(event);
		
	}
}