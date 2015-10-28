package main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class UIChip implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */

	/**
	 * 
	 */

	private Color chipColor;

	public Color getChipColor() {
		return chipColor;
	}

	private Point currentPos;
	private int currentMatrixLocation;
	private int CHIP_SIZE = 20;
	Ellipse2D elipseObj;
	GamePlay gamePlayObj;

	public Point getCurrentPos() {
		return currentPos;
	}

	UIGameBoard gameBoardObj;
	boolean validDrop = false;
	final float dash1[] = { 5.0f, 5.0f };
	final Stroke stroke = new BasicStroke(5.0f, BasicStroke.JOIN_ROUND,
			BasicStroke.JOIN_BEVEL, 15.0f, dash1, 0.0f);

	UIChip(Color chipColor, Point p, int currentMatrxLoc, GamePlay gamePlay,
			UIGameBoard gameBrdObj) {
		this.chipColor = chipColor;
		this.currentPos = p;
		elipseObj = new Ellipse2D.Float((int) p.getX() - CHIP_SIZE / 2,
				(int) p.getY() - CHIP_SIZE / 2, CHIP_SIZE, CHIP_SIZE);
		gamePlayObj = gamePlay;
		gameBoardObj = gameBrdObj;
		this.currentMatrixLocation = currentMatrxLoc;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// this.elipseObj = new Ellipse2D.Float(e.getX(), e.getY(), chipSize,
		// chipSize);
		//
		// Point p=new Point(e.getX(),e.getY());
		// if (gameBoardObj.isChipInMatrix(p))
		// {
		// validDrop=true;
		// }
		//
	}

	// public boolean elipseThis(Point e){
	// //return elipseObj.contains(e);
	//
	// }
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// if(this.playable && onMe(e.getPoint())){
		// selected = true;
		// }

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// elipseObj = new Ellipse2D.Float(e.getX(), e.getY(), chipSize,
		// chipSize);
		// if (validDrop)
		// {
		// gameBoardObj.chipReleased(e);
		// validDrop=false;
		// //gamePlayObj.removeAll();
		// //gameBoardObj.repaint();
		// }
		// this.repaint();

	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setPaint(this.chipColor);
		g2d.setStroke(stroke);
		g2d.setBackground(this.chipColor);
		g2d.fill(elipseObj);

	}

}
