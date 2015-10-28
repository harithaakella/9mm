package main;

public class GameController {

	GamePlay playObj;
	GameController() {
		// load the game
		playObj=new GamePlay(this);
	}

	public void startScreen() {
		new GamePlay(this);
	}

	public void resetGame() {
		
		playObj.setVisible(false);
		playObj.dispose();
		playObj=new GamePlay(this);
		
		//playObj=new GamePlay();
	}

}
