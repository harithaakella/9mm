package main;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class UIGameMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameController controllerObj;

	// GameController controllerObj;
	public UIGameMenu(final GameController controllerObj) {
		JMenu file = new JMenu("File");
		JMenuItem reset = new JMenuItem("Reset");
		JMenuItem exit = new JMenuItem("Exit");
		this.controllerObj = controllerObj;
		file.add(reset);
		file.add(exit);

		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controllerObj.resetGame();
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		JMenu help = new JMenu("Help");
		JMenuItem howTo = new JMenuItem("Game Instructions");
		help.add(howTo);

		 howTo.addActionListener(new ActionListener(){
		 public void actionPerformed(ActionEvent arg0) {
		 new Dialog(new UIGameHelp(), "Alert", false);
		 }
		 });

		this.add(file);
		this.add(help);

	}

}