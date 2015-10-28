

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.GamePlay;
import main.Player;
import main.PlayerAction;
import main.UIChip;

import org.junit.Before;
import org.junit.Test;

public class PlayerDetailsTest
{

	private Player playerObj;
	private Player playerObj2;
	private Player playerObj3;
	private Player playerObj4;
	private Player playerObj5;
	private Player playerObj6;
	private Player playerObj7;
	private Player playerObj8;
	private Player playerObj9;




	@Before
	public void setUp() throws Exception
	{
		List<UIChip> lstChips=new ArrayList<UIChip>();
		playerObj=new Player(9,Color.WHITE,0,0,lstChips,PlayerAction.PLACE);
		playerObj2=new Player(3,Color.WHITE,0,0,lstChips,PlayerAction.PLACE);
		playerObj3=new Player(11,Color.WHITE,0,0,lstChips,PlayerAction.PLACE);
		playerObj4=new Player(9,Color.WHITE,0,0,lstChips,PlayerAction.PLACE);
		playerObj5=new Player(9,Color.BLACK,0,0,lstChips,PlayerAction.PLACE);
		playerObj6=new Player(9,Color.WHITE,0,0,lstChips,PlayerAction.PLACE);
		playerObj7=new Player(9,Color.WHITE,0,0,lstChips,PlayerAction.PLACE);
		playerObj8=new Player(9,Color.WHITE,0,0,lstChips,PlayerAction.PLACE);
		playerObj9=new Player(9,Color.WHITE,0,0,lstChips,PlayerAction.PLACE);

	}
	
	@Test
	public void testChipCount()
	{
		assertEquals("total number of chips is 9",9,playerObj.getTotalChipCount());
	}
	
	//Negative Test Case 

	@Test
	public void testChipCount_When_It_Is_Less_In_Number() 
	{
		assertEquals("total number of chips is 9",9,playerObj2.getTotalChipCount());
	}
	
	//Negative Test Case 

	@Test
	public void testChipCount_When_It_Is_More_In_Number()
	{
		assertEquals("total number of chips is 9",9,playerObj3.getTotalChipCount());
	}
	
	@Test
	public void testChipColor()
	{
		assertEquals("Chip color is White",Color.WHITE,playerObj4.getChipColor());

	}
	
	//Negative Test Case 
	
	@Test
	public void testChipColor2()
	{
		assertEquals("Chip color is White",Color.WHITE,playerObj5.getChipColor());

	}
	
	@Test
	public void testNumberOfMills()
	{
		assertEquals("Chip color is White",0,playerObj6.getNumberOfMills());

	}
	
	@Test
	public void testChipsOnBoard() 
	{
		assertEquals("Chip color is White",0,playerObj7.getChipInBoard());

	}
	
	@Test
	public void testPlayerChip8() 
	{
		//assertEquals("Chip color is White",,playerObj8.getPlayerChips());

	}
	
	@Test
	public void testPlayerAction()
	{
		assertEquals("Chip color is White",PlayerAction.PLACE,playerObj9.getPlayerAction());
	}
}
