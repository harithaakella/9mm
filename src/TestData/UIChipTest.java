package TestData;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.GamePlay;
import main.Player;
import main.PlayerAction;
import main.PlayerTurn;
import main.UIChip;
import main.UIGameBoard;

import org.junit.Before;
import org.junit.Test;

public class UIChipTest
{
	private UIChip chipObj;
	private UIChip chipObj2;

	
	private Player playerObj1;
	private Player playerObj2;
	private PlayerTurn playerturnObj;
	
	private Color color1;
	private Color color2;
	
	private GamePlay gameoPlayObj;
	
	private UIGameBoard gameBoardObj;
	

	@Before
	public void setUp() throws Exception
	{
		List<UIChip> lstChips=new ArrayList<UIChip>();
		playerturnObj=new PlayerTurn();
		playerObj1=new Player(9,Color.WHITE,0,0,lstChips,PlayerAction.FLY);
		playerObj2=new Player(9,Color.BLACK,0,0,lstChips,PlayerAction.PLACE);
		
		color1 = Color.BLACK;
		color2 = Color.WHITE;
		
		gameoPlayObj=new GamePlay();
		
		gameBoardObj = new UIGameBoard(playerturnObj, playerObj1, playerObj2);

		
		
		chipObj = new UIChip(color1,new Point(150,149), 0, gameoPlayObj, gameBoardObj);
		chipObj2 = new UIChip(color2,new Point(300,196), 4, gameoPlayObj, gameBoardObj);

	}
	
	@Test
	public void testCHIPSIZE() 
	{
		assertEquals("Calculating Chip Size",20,chipObj.getCHIP_SIZE());
	}
	
	//Negative Test
	
	@Test
	public void testCHIPSIZE2() 
	{
		assertEquals("Calculating Chip Size",10,chipObj.getCHIP_SIZE());
	}
	
	@Test
	public void testChipColor() 
	{
		assertEquals("Chip color is ",Color.BLACK,chipObj.getChipColor());
	}
	
	//Negative Test
	
	@Test
	public void testChipColor2() 
	{
		assertEquals("Chip color is ",Color.WHITE,chipObj.getChipColor());
	}
	
	@Test
	public void testChipColor3() 
	{
		assertEquals("Chip color is ",Color.WHITE,chipObj2.getChipColor());
	}
	
	//Negative Test
	@Test
	public void testChipColor4() 
	{
		assertEquals("Chip color is ",Color.BLACK,chipObj2.getChipColor());
	}
	
	@Test
	public void testMatrixLocation() 
	{
		assertEquals("Matrix Location  is ",0,chipObj.getCurrentMatrixLocation());
	}
	
	@Test
	public void testMatrixLocation2() 
	{
		assertEquals("Matrix Location is ",4,chipObj2.getCurrentMatrixLocation());
	}
	
	@Test
	public void testCurrentPosition() 
	{
		assertEquals("Chip current position is ",new Point(150,149),chipObj.getCurrentPos());
	}
	
	@Test
	public void testCurrentPosition2() 
	{
		assertEquals("Chip current position is ",new Point(300,196),chipObj2.getCurrentPos());
	}
	
	
}

