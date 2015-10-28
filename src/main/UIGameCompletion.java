package main;

import java.awt.Color;

import javax.swing.JOptionPane;


public class UIGameCompletion  {

	GamePlay gamePlayObj;
	public UIGameCompletion(GamePlay gamePlayObj,Player p) {
		this.gamePlayObj=gamePlayObj;
		String colorName="";
		if (Color.BLACK.equals(p.getChipColor())) 
		{
		  colorName = "BLACK";
		} 
		else if (Color.WHITE.equals(p.getChipColor())) 
		{
		  colorName = "WHITE";
		}
		JOptionPane.showMessageDialog(gamePlayObj,
			    "Player "+colorName + " wins!!",
			    "Player Wins",
			    JOptionPane.PLAIN_MESSAGE);
	}

}
