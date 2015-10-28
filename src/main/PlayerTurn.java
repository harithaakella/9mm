package main;


public class PlayerTurn {
	private  String current_player="White";
	private  PlayerAction playerAction=PlayerAction.PLACE;
	

	public PlayerAction getPlayerAction() {
		return playerAction;
	}
	public void setPlayerAction(PlayerAction playerAction) {
		this.playerAction = playerAction;
	}
	public String getCurrentPlayer() {
		return current_player;
	}
	public void setCurrentPlayer(String currentPlr) {
		current_player = currentPlr;
	}
	
}
	

