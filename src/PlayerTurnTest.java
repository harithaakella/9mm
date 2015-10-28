

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.Player;
import main.PlayerAction;
import main.PlayerTurn;
import main.UIChip;
import main.UIGameBoard;

import org.junit.Before;
import org.junit.Test;

public class PlayerTurnTest
{

	private PlayerTurn playerturnObj;
	private  String current_player="White";
	private  String current_player2="Black";

	@Before
	public void setUp() throws Exception
	{
		playerturnObj=new PlayerTurn();
	}
	
	@Test
	public void test_Who_Is_Current_Player()
	{
		assertEquals("The Current Player is WHITE",current_player,playerturnObj.getCurrentPlayer());
	}
	
	//Negative TestCase 
	
	@Test
	public void test_Who_Is_Current_Player2()
	{
		assertEquals("The Current Player is WHITE",current_player2,playerturnObj.getCurrentPlayer());
	}
	
	@Test
	public void test_What_Is_player_Action()
	{
		assertEquals("The Player Action is PLACE",PlayerAction.PLACE,playerturnObj.getPlayerAction());
	}
	
	//Negative TestCase 

	@Test
	public void test_What_Is_player_Action2()
	{
		assertEquals("The Player Action is PLACE",PlayerAction.FLY,playerturnObj.getPlayerAction());
	}
	
	//Negative TestCase 

	@Test
	public void test_What_Is_player_Action3()
	{
		assertEquals("The Player Action is PLACE",PlayerAction.MOVE,playerturnObj.getPlayerAction());
	}
}
