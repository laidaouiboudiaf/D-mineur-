package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import model.Demineur;

public class DemineurFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private final Demineur demineur = new Demineur(5, 5);
	
	private final DemineurPanel panel;
	
	public DemineurFrame() {
		panel = new DemineurPanel(this);
		initDemineur();
		
		setSize(500, 500);
		setBackground(Color.PINK);
		setMinimumSize(new Dimension(300, 300));
		setTitle("D�mineur CCI 2020");

	
		add(panel);
		setJMenuBar(new DemineurMenuBar(this));
		
	}
	
	
	public Demineur getDemineur() {
		return demineur;
	}


	public void initDemineur() {
		demineur.init(15, 20);
		panel.repaint();
	}

}
