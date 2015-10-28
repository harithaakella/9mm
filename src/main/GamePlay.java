/**
 * 
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


/**
 * @author NITISH
 *
 */
public class GamePlay extends JFrame{ 
	 
	private static final long serialVersionUID = 1L;
	UIGameMenu menuObj;
	PlayerTurn playerChip=new PlayerTurn();
	GameController controllerObj;
	public GamePlay(GameController controllerObj){
		this.controllerObj=controllerObj;
		this.setResizable(false);
		this.setTitle("Nine Men's Morris");
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startScreen();
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	public void startScreen(){
		this.setJMenuBar(new UIGameMenu(controllerObj));
		playerChip.setCurrentPlayer("White");
		List<UIChip> lstChips=new ArrayList<UIChip>();
		Player firstPlayer=new Player(9,Color.WHITE,0,0,lstChips,PlayerAction.PLACE);
		Player secondPlayer=new Player(9,Color.BLACK,0,0,lstChips,PlayerAction.PLACE);
		new UIStartScreen(controllerObj,this,playerChip,firstPlayer,secondPlayer);
		
	}
	
	
	

}
