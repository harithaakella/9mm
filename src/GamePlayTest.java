

import static org.junit.Assert.*;
import main.GamePlay;

import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {
	
	private GamePlay gameoPlayObj;
	
	@Before
	public void setUp() throws Exception
	{
		gameoPlayObj=new GamePlay();
	}
	

	@Test
	public void testPlayersCreated() {
		assertEquals("total number of players is 2",2,gameoPlayObj.getPlayers().size());
	}

}
