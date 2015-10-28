package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	private int totalChipCount;
	private Color chipColor;
	private int numberOfMills;
	private int chipInBoard;
	private PlayerAction action;
	public int getTotalChipCount() {
		return totalChipCount;
	}

	public Color getChipColor() {
		return chipColor;
	}

	public int getNumberOfMills() {
		return numberOfMills;
	}

	public int getChipInBoard() {
		return chipInBoard;
	}

	public List<UIChip> getPlayerChips() {
		return playerChips;
	}
	public PlayerAction getPlayerAction() {
		return this.action;
	}

	public void setTotalChipCount(int totalChipCount) {
		this.totalChipCount = totalChipCount;
	}

	public void setChipColor(Color chipColor) {
		this.chipColor = chipColor;
	}

	public void setNumberOfMills(int numberOfMills) {
		this.numberOfMills = numberOfMills;
	}

	public void setChipInBoard(int chipInBoard) {
		this.chipInBoard = chipInBoard;
	}
	
	public void setPlayerChips(UIChip playerChips) {
		this.playerChips.add(playerChips) ;
	}
	
	public void setPlayerAction(PlayerAction act) {
		this.action=act;
	}
	
	public void removePlayerChips(UIChip playerChips) {
		this.playerChips.remove(playerChips) ;
	}

	private List<UIChip> playerChips=new ArrayList<UIChip>();
	
	public Player(int totalChipCount,Color chipClr,int numberOfMills,int chipInBrd,List<UIChip> playerChips,PlayerAction playerAct) {
		// TODO Auto-generated constructor stub
		this.chipColor=chipClr;
		this.chipInBoard=chipInBrd;
		this.numberOfMills=numberOfMills;
		this.playerChips=playerChips;
		this.totalChipCount=totalChipCount;
		this.action=playerAct;
	}
	
	public Player()
	{
	
	}

}
