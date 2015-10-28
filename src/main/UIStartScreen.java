package main;

import java.awt.Graphics;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import javax.swing.ImageIcon;
 


import javax.imageio.ImageIO;
import javax.swing.*;


public class UIStartScreen extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String morrisStarterImage="resources/morrisImage.png";
	
	//ImageIcon P1Icon = createImageIcon("resources/firstPlayerIcon.png");
	  
	JRadioButton rb1,rb2;  
	UIStartScreen(final GameController controllerObj,final GamePlay uiGame,final PlayerTurn playerTurn,final Player player1,final Player player2){  
	JLabel gameLabel = new JLabel("Nine Men Morris");
	gameLabel.setBounds(250,180,100,30);
	
	//rb1=new JRadioButton("PLAYER 1",new ImageIcon("resources/firstPlayerIcon.png"),false); 
	//rb1=new JRadioButton("PLAYER 1",P1Icon); 
	rb1=new JRadioButton("PLAYER 1");
	rb1.setBounds(250,250,100,30);  
	rb1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			
			//todo add the logic for computer player
			//new UIGameBoard(uiGame,playerTurn);
		}
	});
	rb2=new JRadioButton("PLAYER 2");  
	rb2.setBounds(250,300,100,30);  
	rb2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			
			new UIGameBoard(controllerObj,uiGame,playerTurn,player1,player2);
		}
	});
	ButtonGroup bg=new ButtonGroup();  
	bg.add(rb1);
	bg.add(rb2);  
	add(rb1);
	add(rb2);
	setSize(300,300);  
	setLayout(null);  
	setVisible(true); 
	uiGame.add(gameLabel);
	uiGame.add(this);
	}
	
	  protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = UIStartScreen.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println("Couldn't find file: " + path);
	            return null;
	        }
	    }
	
	public void paintComponent(Graphics g) {
		try {
			BufferedImage boardImage = ImageIO.read(new File(morrisStarterImage));
			g.drawImage(boardImage, 0, 0, this.getWidth(), this.getHeight(), null);
			
			
		} catch (IOException e) {
			g.drawString("Unable to find image: " + morrisStarterImage, 10, 10);
		}

	}
}
