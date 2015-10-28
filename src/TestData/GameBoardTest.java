package TestData;

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

public class GameBoardTest
{
	private UIGameBoard gameBoardObj;
	
	private Player playerObj1;
	private Player playerObj2;
	private PlayerTurn playerturnObj;
	

	@Before
	public void setUp() throws Exception
	{
		//List<UIChip> lstChips=new ArrayList<UIChip>();
		List<UIChip> lstChips=new ArrayList<UIChip>();
		playerturnObj=new PlayerTurn();
		playerObj1=new Player(9,Color.WHITE,0,0,lstChips,PlayerAction.FLY);
		playerObj2=new Player(9,Color.BLACK,0,0,lstChips,PlayerAction.PLACE);
		
		
		gameBoardObj=new UIGameBoard(playerturnObj,playerObj1,playerObj2);
	}
	
	@Test
	public void testChipInMatrix() 
	{
		assertTrue("the position in matrix is free", gameBoardObj.isChipInMatrix(new Point(150, 149),playerturnObj));
	}
	
	@Test
	public void testChipPlaceable() 
	{
		assertTrue("the Chip placeable is false",gameBoardObj.isChipPlaceable(new Point(299,148)));
	}
	
	@Test
	public void testChipPlaceable2() 
	{
		assertTrue("the Chip placeable is true",gameBoardObj.isChipPlaceable(new Point(350,300)));
	}

	
	@Test
	public void testPlayerAction() 
	{
		assertEquals("Player action is PLACE", PlayerAction.PLACE, gameBoardObj.getPlayerAction(playerObj1));
	}
	
	@Test
	public void testPlayerAction2()
	{
		assertEquals("Player action is PLACE", PlayerAction.PLACE, gameBoardObj.getPlayerAction(playerObj2));
	}

	@Test
	public void testgetActualPosinMatrix() 
	{
		// assertEquals("Actual position is 0", 0,
		// gameboardObj.getActualPieceLocationInMatrix(new Point(150, 149)));
		assertEquals("Actual position is 0", 0,
				gameBoardObj.getActualPieceLocationInMatrix(new Point(150, 149)).intValue());
	}

	@Test
	public void testcheckAndUpdateMill()
	{
		assertTrue("The mill should not be formed", !gameBoardObj.checkAndUpdateMills(playerObj1, 0));
	}
	
	@Test
	public void testcheckAndUpdateMill2() 
	{
		assertTrue("The mill should not be formed", !gameBoardObj.checkAndUpdateMills(playerObj2, 0));
	}
	
	//Negative Test
	
	@Test
	public void testcheckAndUpdateMill3()
	{
		assertTrue("The mill should not be formed", gameBoardObj.checkAndUpdateMills(playerObj1, 0));
	}
	
	//Negative Test
	
	@Test
	public void testcheckAndUpdateMill4()
	{
		assertTrue("The mill should not be formed", gameBoardObj.checkAndUpdateMills(playerObj2, 0));
	}

	@Test
	public void testDetermineNextAction() 
	{
		assertEquals("The next action is PLACE", PlayerAction.PLACE , gameBoardObj.determineNextAction(playerObj1));
	}

	@Test
	public void testDetermineNextAction2() 
	{
		assertEquals("The next action is PLACE", PlayerAction.PLACE , gameBoardObj.determineNextAction(playerObj2));
	}
	
	//Negative Test
	
	@Test
	public void testDetermineNextAction3() 
	{
		assertEquals("The next action is", PlayerAction.MOVE , gameBoardObj.determineNextAction(playerObj1));
	}
	
	//Negative Test

	@Test
	public void testDetermineNextAction4() 
	{
		assertEquals("The next action is", PlayerAction.MOVE , gameBoardObj.determineNextAction(playerObj2));
	}

	//Negative Test

	@Test
	public void testDetermineNextAction5() 
	{
		assertEquals("The next action is", PlayerAction.FLY , gameBoardObj.determineNextAction(playerObj1));
	}
	
	//Negative Test

	@Test
	public void testDetermineNextAction6() 
	{
		assertEquals("The next action is", PlayerAction.FLY, gameBoardObj.determineNextAction(playerObj2));
	}

	
	
	
	


}
