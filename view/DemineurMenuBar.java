package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DemineurMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private final DemineurFrame frame;

	public DemineurMenuBar(DemineurFrame frame) {
		this.frame = frame;
		
		
		JMenu options = new JMenu("Options");

		JMenuItem quitter = new JMenuItem("Quitter");
		JMenuItem recommencer = new JMenuItem("Recommencer");
		JMenuItem aide = new JMenuItem("Aide");

		options.add(quitter);
		options.add(recommencer);
		options.add(aide);

		add(options);

		recommencer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.initDemineur();			
			}
		});

		aide.addActionListener(new AideAction());
		quitter.addActionListener(new QuitterAction());

	}

}
