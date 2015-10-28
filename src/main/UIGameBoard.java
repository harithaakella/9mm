/**
 * 
 */
package main;

import java.awt.Color;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author NITISH
 *
 */
public class UIGameBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static final String GAME_BOARD_IMAGE = "resources/gameboard.png";
	public static final Map<Integer, Point> BOARD_POINTER_MAPPER = new HashMap<Integer, Point>();
	public static final Integer BOARD_POINTER_THRESHOLD = 20;
	public PlayerTurn currentPlayer;
	public Point actualPoint;
	public int actualPosition;
	public Notifications notif;
	private Map<String, JLabel> dynamicLabels;
	public static final Map<Integer, UIChip> MAINBOARD = new HashMap<Integer, UIChip>();

	private List<UIChip> pieces = Collections
			.synchronizedList(new ArrayList<UIChip>());
	static {
		BOARD_POINTER_MAPPER.put(0, new Point(150, 149));
		BOARD_POINTER_MAPPER.put(1, new Point(299, 148));
		BOARD_POINTER_MAPPER.put(2, new Point(451, 148));

		BOARD_POINTER_MAPPER.put(3, new Point(201, 199));
		BOARD_POINTER_MAPPER.put(4, new Point(300, 196));
		BOARD_POINTER_MAPPER.put(5, new Point(399, 200));

		BOARD_POINTER_MAPPER.put(6, new Point(251, 248));
		BOARD_POINTER_MAPPER.put(7, new Point(299, 247));
		BOARD_POINTER_MAPPER.put(8, new Point(350, 250));

		BOARD_POINTER_MAPPER.put(9, new Point(150, 296));
		BOARD_POINTER_MAPPER.put(10, new Point(199, 300));
		BOARD_POINTER_MAPPER.put(11, new Point(252, 300));

		BOARD_POINTER_MAPPER.put(12, new Point(350, 300));
		BOARD_POINTER_MAPPER.put(13, new Point(401, 297));
		BOARD_POINTER_MAPPER.put(14, new Point(451, 301));

		BOARD_POINTER_MAPPER.put(15, new Point(249, 349));
		BOARD_POINTER_MAPPER.put(16, new Point(298, 349));
		BOARD_POINTER_MAPPER.put(17, new Point(352, 350));

		BOARD_POINTER_MAPPER.put(18, new Point(199, 397));
		BOARD_POINTER_MAPPER.put(19, new Point(299, 401));
		BOARD_POINTER_MAPPER.put(20, new Point(399, 399));

		BOARD_POINTER_MAPPER.put(21, new Point(150, 448));
		BOARD_POINTER_MAPPER.put(22, new Point(300, 446));
		BOARD_POINTER_MAPPER.put(23, new Point(451, 449));
	}
	GamePlay gamePlayUIObj;
	Player playerObj1;
	Player playerObj2;
	private static boolean Act = false;
	// to denote the action to move or not
	private static boolean isMove = false;
	// last chip choosen to move
	private static int lastChipChoosenToMove;
	GameController controllerObj;

	public UIGameBoard(GameController controllerObj, final GamePlay uiGame,
			PlayerTurn playerTurn, Player player1, Player player2) {
		// TODO Auto-generated constructor stub

		currentPlayer = playerTurn;
		gamePlayUIObj = uiGame;
		this.playerObj1 = player1;
		this.playerObj2 = player2;
		this.controllerObj = controllerObj;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				placePiece(e, uiGame);
			}
		});

		createGameLabels();
		generatePieceObjects(uiGame);
		this.setLayout(null);
		uiGame.getContentPane().removeAll();
		uiGame.getContentPane().repaint();
		uiGame.add(this);
		uiGame.revalidate();
		uiGame.repaint();

	}

	public void InvalidateLabels() {
		JLabel labelTurnName = dynamicLabels.get("labelTurnName");
		labelTurnName.setText(currentPlayer.getCurrentPlayer());
		labelTurnName.revalidate();

		JLabel totalCountsFst = dynamicLabels.get("totalCountsFst");
		totalCountsFst
				.setText(Integer.toString(playerObj1.getTotalChipCount()));
		totalCountsFst.revalidate();

		JLabel totalMillsFst = dynamicLabels.get("totalMillsFst");
		totalMillsFst.setText(Integer.toString(playerObj1.getNumberOfMills()));
		totalMillsFst.revalidate();

		JLabel totalCountsScnd = dynamicLabels.get("totalCountsScnd");
		totalCountsScnd
				.setText(Integer.toString(playerObj2.getTotalChipCount()));
		totalCountsScnd.revalidate();

		JLabel totalMillsScnd = dynamicLabels.get("totalMillsScnd");
		totalMillsScnd.setText(Integer.toString(playerObj2.getNumberOfMills()));
		totalMillsScnd.revalidate();

		JLabel labelActionName = dynamicLabels.get("labelActionName");
		labelActionName.setText("\""+currentPlayer.getPlayerAction().toString()+"\"");
		labelActionName.revalidate();

		// Remove notification
		if (dynamicLabels.get("labelNotification") != null) {
			JLabel labelNotifcation = dynamicLabels.get("labelNotification");
			this.remove(labelNotifcation);
			labelActionName.revalidate();
			dynamicLabels.remove(labelNotifcation);
		}

	}

	public void createGameLabels() {

		JLabel labelTurn = new JLabel("Current Turn :");
		labelTurn.setForeground(Color.blue);
		labelTurn.setFont(new Font("Serif", Font.BOLD, 14));
		// labelTurn.setFont(labelTurn.getFont().deriveFont(16.0f));
		Dimension size = labelTurn.getPreferredSize();
		labelTurn.setBounds(250, 10, size.width, size.height);
		this.add(labelTurn);

		JLabel labelTurnName = new JLabel(currentPlayer.getCurrentPlayer());
		labelTurnName.setBounds(250 + size.width + 10, 10, size.width,
				size.height);
		this.add(labelTurnName);

		JLabel labelAction = new JLabel("Action :");
		labelAction.setForeground(Color.blue);
		labelAction.setFont(new Font("Serif", Font.BOLD, 14));
		size = labelAction.getPreferredSize();
		labelAction.setBounds(260, 30, size.width, size.height);
		this.add(labelAction);
		PlayerAction action = currentPlayer.getPlayerAction();

		JLabel labelActionName = new JLabel("\"" + action.toString() + "\"");
		labelActionName.setBounds(260 + size.width + 10, 30, size.width + 20,
				size.height);
		this.add(labelActionName);

		JLabel labelPlayerNameFirst = new JLabel("White");
		labelPlayerNameFirst.setForeground(Color.red);
		labelPlayerNameFirst.setFont(new Font("Serif", Font.BOLD, 14));
		size = labelPlayerNameFirst.getPreferredSize();
		labelPlayerNameFirst.setBounds(10, 35, size.width, size.height);
		this.add(labelPlayerNameFirst);

		JLabel totalCountsFirst = new JLabel("Total Chips On Hand:");
		totalCountsFirst.setForeground(Color.blue);
		totalCountsFirst.setFont(new Font("Serif", Font.BOLD, 14));
		size = totalCountsFirst.getPreferredSize();
		totalCountsFirst.setBounds(10, 50, size.width, size.height);

		JLabel totalCountsFst = new JLabel(playerObj1.getPlayerChips()
				.toString());
		totalCountsFst.setBounds(10 + size.width + 10, 50, size.width,
				size.height);

		JLabel totalMillsFirst = new JLabel("Total Mills:");
		totalMillsFirst.setForeground(Color.blue);
		totalMillsFirst.setFont(new Font("Serif", Font.BOLD, 14));
		size = totalMillsFirst.getPreferredSize();
		totalMillsFirst.setBounds(10, 65, size.width, size.height);
		JLabel totalMillsFst = new JLabel(Integer.toString(playerObj1
				.getNumberOfMills()));
		totalMillsFst.setBounds(10 + size.width + 10, 65, size.width,
				size.height);

		JLabel labelPlayerNameSecond = new JLabel("Black");
		labelPlayerNameSecond.setForeground(Color.red);
		labelPlayerNameSecond.setFont(new Font("Serif", Font.BOLD, 14));
		Dimension blackSize = labelPlayerNameSecond.getPreferredSize();
		labelPlayerNameSecond.setBounds(600 - blackSize.width - 120, 35,
				size.width, size.height);

		JLabel totalCountsSecond = new JLabel("Total Chips On Hand:");
		totalCountsSecond.setForeground(Color.blue);
		totalCountsSecond.setFont(new Font("Serif", Font.BOLD, 14));
		size = totalCountsSecond.getPreferredSize();
		totalCountsSecond.setBounds(600 - blackSize.width - 120, 50,
				size.width, size.height);

		JLabel totalCountsScnd = new JLabel(playerObj2.getPlayerChips()
				.toString());
		totalCountsScnd.setBounds(
				600 - blackSize.width - 120 + size.width + 10, 50, size.width,
				size.height);
		JLabel totalMillsSecond = new JLabel("Total Mills:");
		totalMillsSecond.setForeground(Color.blue);
		totalMillsSecond.setFont(new Font("Serif", Font.BOLD, 14));
		size = totalMillsSecond.getPreferredSize();
		totalMillsSecond.setBounds(600 - blackSize.width - 120, 65, size.width,
				size.height);

		JLabel totalMillsScnd = new JLabel(Integer.toString(playerObj2
				.getNumberOfMills()));
		totalMillsScnd.setBounds(600 - blackSize.width - 120 + size.width + 10,
				65, size.width, size.height);

		dynamicLabels = new HashMap<String, JLabel>();
		dynamicLabels.put("labelTurnName", labelTurnName);
		dynamicLabels.put("totalCountsFst", totalCountsFst);
		dynamicLabels.put("totalMillsFst", totalMillsFst);
		dynamicLabels.put("totalCountsScnd", totalCountsScnd);
		dynamicLabels.put("totalMillsScnd", totalMillsScnd);
		dynamicLabels.put("labelActionName", labelActionName);

		this.add(labelPlayerNameFirst);
		this.add(totalCountsFirst);
		this.add(totalCountsFst);
		this.add(totalMillsFirst);
		this.add(totalMillsFst);

		this.add(labelPlayerNameSecond);
		this.add(totalCountsSecond);
		this.add(totalCountsScnd);
		this.add(totalMillsSecond);
		this.add(totalMillsScnd);

		this.invalidate();
		this.repaint();

	}

	public void clearPieceObjects() {

	}

	public void placePiece(MouseEvent e, GamePlay uiGame) {

		Point p = new Point(e.getX(), e.getY());
		String nextPlayer;

		switch (currentPlayer.getPlayerAction()) {

		case PLACE:
			if (currentPlayer.getCurrentPlayer() == "White") {
				nextPlayer = "Black";
				if (isChipInMatrix(p, currentPlayer)) {
					p = actualPoint;
					UIChip chipFirst = new UIChip(playerObj1.getChipColor(), p,
							actualPosition, uiGame, this);
					pieces.add(chipFirst);
					MAINBOARD.put(actualPosition, chipFirst);
					// check mills
					boolean isMill = checkAndUpdateMills(playerObj1,
							actualPosition);
					// if mill is occured
					if (isMill) {
						// give notification to remove opponent
						JLabel notificationText = new JLabel("Mill formed!!");
						this.add(notificationText);
						dynamicLabels
								.put("labelNotification", notificationText);
						this.invalidate();
						this.repaint();

					} else {
						playerObj1.setNumberOfMills(playerObj1
								.getNumberOfMills());
						currentPlayer.setCurrentPlayer(nextPlayer);
						currentPlayer
								.setPlayerAction(determineNextAction(playerObj2));
					}
					playerObj1.setPlayerChips(chipFirst);
					playerObj1.setChipInBoard(playerObj1.getChipInBoard() + 1);
					playerObj1
							.setTotalChipCount(playerObj1.getTotalChipCount() - 1);

					InvalidateLabels();
					uiGame.revalidate();
					uiGame.repaint();
				}
			} else {
				nextPlayer = "White";
				if (isChipInMatrix(p, currentPlayer)) {
					p = actualPoint;
					UIChip chipFirst = new UIChip(playerObj2.getChipColor(), p,
							actualPosition, uiGame, this);
					pieces.add(chipFirst);
					MAINBOARD.put(actualPosition, chipFirst);
					// todo check mills
					boolean isMill = checkAndUpdateMills(playerObj2,
							actualPosition);
					if (isMill) {
						// give notiicaion o remoe opponent
						// give notification to remove opponent
						JLabel notificationText = new JLabel("Mill formed!!");
						this.add(notificationText);
						dynamicLabels
								.put("labelNotification", notificationText);
						this.invalidate();
						this.repaint();

					} else {
						playerObj2.setNumberOfMills(playerObj2
								.getNumberOfMills());
						currentPlayer.setCurrentPlayer(nextPlayer);
						currentPlayer
								.setPlayerAction(determineNextAction(playerObj1));
					}
					playerObj2.setPlayerChips(chipFirst);
					playerObj2.setChipInBoard(playerObj2.getChipInBoard() + 1);

					playerObj2
							.setTotalChipCount(playerObj2.getTotalChipCount() - 1);

					InvalidateLabels();
					uiGame.revalidate();
					uiGame.repaint();
				}
			}
			break;
		case MOVE:
			// if placement is not possible other player wins
			// new UIGameCompletion(gamePlayUIObj,playerObj2);
			if (currentPlayer.getCurrentPlayer() == "White") {
				nextPlayer = "Black";
				if (isChipInMatrix(p, currentPlayer)) {
					if (isMove) {
						// Check if another white chip is selected after initial
						// selection
						if (MAINBOARD.get(actualPosition) != null) {
							if (MAINBOARD.get(actualPosition).getChipColor() == Color.WHITE) {
								lastChipChoosenToMove = actualPosition;
								break;
							}
						}
						// get chip position at the point
						UIChip chipObj = MAINBOARD.get(lastChipChoosenToMove);
						if (moveChip(playerObj1, p, uiGame, chipObj)) {
							UIChip chipFirst = new UIChip(
									playerObj1.getChipColor(), p,
									actualPosition, uiGame, this);
							// Remove old chip
							pieces.remove(chipObj);
							MAINBOARD.remove(lastChipChoosenToMove);
							pieces.add(chipFirst);
							MAINBOARD.put(actualPosition, chipFirst);

							// if mill is occurred
							boolean isMill = checkAndUpdateMills(playerObj1,
									actualPosition);
							if (isMill) {
								// give notification to remove opponent
								JLabel notificationText = new JLabel(
										"Mill formed!!");
								this.add(notificationText);
								dynamicLabels.put("labelNotification",
										notificationText);
								this.invalidate();
								this.repaint();

							} else {
								playerObj1.setNumberOfMills(playerObj1
										.getNumberOfMills());
								currentPlayer.setCurrentPlayer(nextPlayer);
								currentPlayer
										.setPlayerAction(determineNextAction(playerObj2));
							}
							// playerObj1.setPlayerChips(chipFirst);
							InvalidateLabels();
							uiGame.revalidate();
							uiGame.repaint();
							isMove = false;
						}
					} else {
						// check if the current clicked item is white
						if (MAINBOARD.get(actualPosition) != null) {
							if (MAINBOARD.get(actualPosition).getChipColor() == Color.WHITE) {
								isMove = true;
								lastChipChoosenToMove = actualPosition;
							}
						}
					}

				} else {
					notif.setNotification("The chip cannot be placed here");
					this.InvalidateLabels();
				}
			} else {
				nextPlayer = "White";
				if (isChipInMatrix(p, currentPlayer)) {
					if (isMove) {
						// Check if another white chip is selected after initial
						// selection
						if (MAINBOARD.get(actualPosition) != null) {
							if (MAINBOARD.get(actualPosition).getChipColor() == Color.BLACK) {
								lastChipChoosenToMove = actualPosition;
								break;
							}
						}
						// get chip position at the point
						UIChip chipObj = MAINBOARD.get(lastChipChoosenToMove);
						if (moveChip(playerObj2, p, uiGame, chipObj)) {
							UIChip chipFirst = new UIChip(
									playerObj2.getChipColor(), p,
									actualPosition, uiGame, this);
							// Remove old chip
							pieces.remove(chipObj);
							MAINBOARD.remove(lastChipChoosenToMove);
							pieces.add(chipFirst);
							MAINBOARD.put(actualPosition, chipFirst);
							// if mill is occurred
							boolean isMill = checkAndUpdateMills(playerObj2,
									actualPosition);
							if (isMill) {
								// give notification to remove opponent
								JLabel notificationText = new JLabel(
										"Mill formed!!");
								this.add(notificationText);
								dynamicLabels.put("labelNotification",
										notificationText);

							} else {
								playerObj2.setNumberOfMills(playerObj2
										.getNumberOfMills());
								currentPlayer.setCurrentPlayer(nextPlayer);
								currentPlayer
										.setPlayerAction(determineNextAction(playerObj1));
							}
							// playerObj2.setPlayerChips(chipFirst);
							InvalidateLabels();
							uiGame.revalidate();
							uiGame.repaint();
							isMove = false;
						}
					} else {
						// check if the current clicked item is white
						if (MAINBOARD.get(actualPosition) != null) {
							if (MAINBOARD.get(actualPosition).getChipColor() == Color.BLACK) {
								isMove = true;
								lastChipChoosenToMove = actualPosition;
							}
						}
					}

				} else {
					notif.setNotification("The chip cannot be placed here");
					this.InvalidateLabels();
				}

			}

			break;
		case REMOV:
			if (currentPlayer.getCurrentPlayer() == "White") {
				nextPlayer = "Black";
				if (isChipInMatrix(p, currentPlayer)) {
					// give notification on remove opponent
					RemoveOpponentAtThisLocation(actualPosition, actualPoint,
							playerObj2, playerObj1, uiGame, nextPlayer);

				}
			} else {
				nextPlayer = "White";

				if (isChipInMatrix(p, currentPlayer)) {
					// give notification on remove opponent
					RemoveOpponentAtThisLocation(actualPosition, actualPoint,
							playerObj1, playerObj2, uiGame, nextPlayer);

				}
			}

			break;
		case FLY:
			if (currentPlayer.getCurrentPlayer() == "White") {
				nextPlayer = "Black";
				if (isChipInMatrix(p, currentPlayer)) {
					if (isMove) {
						// Check if another white chip is selected after initial
						// selection
						if (MAINBOARD.get(actualPosition) != null) {
							if (MAINBOARD.get(actualPosition).getChipColor() == Color.WHITE) {
								lastChipChoosenToMove = actualPosition;
								break;
							}
						}
						// get chip position at the point
						UIChip chipObj = MAINBOARD.get(lastChipChoosenToMove);
						if (moveChip(playerObj1, p, uiGame, chipObj)) {
							UIChip chipFirst = new UIChip(
									playerObj1.getChipColor(), p,
									actualPosition, uiGame, this);
							// Remove old chip
							pieces.remove(chipObj);
							MAINBOARD.remove(lastChipChoosenToMove);
							pieces.add(chipFirst);
							MAINBOARD.put(actualPosition, chipFirst);

							// if mill is occurred
							boolean isMill = checkAndUpdateMills(playerObj1,
									actualPosition);
							if (isMill) {
								// give notification to remove opponent
								JLabel notificationText = new JLabel(
										"Mill formed!!");
								this.add(notificationText);
								dynamicLabels.put("labelNotification",
										notificationText);
								this.invalidate();
								this.repaint();

							} else {
								playerObj1.setNumberOfMills(playerObj1
										.getNumberOfMills());
								currentPlayer.setCurrentPlayer(nextPlayer);
								currentPlayer
										.setPlayerAction(determineNextAction(playerObj2));
							}
							// playerObj1.setPlayerChips(chipFirst);
							InvalidateLabels();
							uiGame.revalidate();
							uiGame.repaint();
							isMove = false;
						}
					} else {
						// check if the current clicked item is white
						if (MAINBOARD.get(actualPosition) != null) {
							if (MAINBOARD.get(actualPosition).getChipColor() == Color.WHITE) {
								isMove = true;
								lastChipChoosenToMove = actualPosition;
							}
						}
					}

				} else {
					notif.setNotification("The chip cannot be placed here");
					this.InvalidateLabels();
				}
			} else {
				nextPlayer = "White";
				if (isChipInMatrix(p, currentPlayer)) {
					if (isMove) {
						// Check if another white chip is selected after initial
						// selection
						if (MAINBOARD.get(actualPosition) != null) {
							if (MAINBOARD.get(actualPosition).getChipColor() == Color.BLACK) {
								lastChipChoosenToMove = actualPosition;
								break;
							}
						}
						// get chip position at the point
						UIChip chipObj = MAINBOARD.get(lastChipChoosenToMove);
						if (moveChip(playerObj2, p, uiGame, chipObj)) {
							UIChip chipFirst = new UIChip(
									playerObj2.getChipColor(), p,
									actualPosition, uiGame, this);
							// Remove old chip
							pieces.remove(chipObj);
							MAINBOARD.remove(lastChipChoosenToMove);
							pieces.add(chipFirst);
							MAINBOARD.put(actualPosition, chipFirst);
							// if mill is occurred
							boolean isMill = checkAndUpdateMills(playerObj2,
									actualPosition);
							if (isMill) {
								// give notification to remove opponent
								JLabel notificationText = new JLabel(
										"Mill formed!!");
								this.add(notificationText);
								dynamicLabels.put("labelNotification",
										notificationText);

							} else {
								playerObj2.setNumberOfMills(playerObj2
										.getNumberOfMills());
								currentPlayer.setCurrentPlayer(nextPlayer);
								currentPlayer
										.setPlayerAction(determineNextAction(playerObj1));
							}
							// playerObj2.setPlayerChips(chipFirst);
							InvalidateLabels();
							uiGame.revalidate();
							uiGame.repaint();
							isMove = false;
						}
					} else {
						// check if the current clicked item is white
						if (MAINBOARD.get(actualPosition) != null) {
							if (MAINBOARD.get(actualPosition).getChipColor() == Color.BLACK) {
								isMove = true;
								lastChipChoosenToMove = actualPosition;
							}
						}
					}

				} else {
					notif.setNotification("The chip cannot be placed here");
					this.InvalidateLabels();
				}
			}
			break;
		default:
			break;

		}

	}

	/*
	 * Get the player action The total count is the no of the chips that has
	 * been used from the 9 chips Chip In board is the total count of chip in
	 * the board
	 */
	public PlayerAction determineNextAction(Player playerObj) {
		if (playerObj.getTotalChipCount() == 0) {
			if (playerObj.getChipInBoard() == 3) {
				return PlayerAction.FLY;
			} else {
				return PlayerAction.MOVE;
			}
		} else {
			return PlayerAction.PLACE;
		}
	}

	/*
	 * Get the player object and check if the object is movable to the adjacent
	 * position based on the adjacent matrix. Returns true if the player action
	 * is fly
	 */
	public boolean moveChip(Player playerObj, Point p, GamePlay uiGame,
			UIChip playerChip) {
		// returns true if the player action is fly since we need not check the
		// adjacent positions.
		if (currentPlayer.getPlayerAction() == PlayerAction.FLY) {
			return true;
		}
		// check if the position chosen is from the adjacent matrix or not
		int[] adjacentMovingPositions = new int[LookUpMatrix.ADJACENCY_LOOKUP_MATRIX[lastChipChoosenToMove].length];
		// set the adjacent moving positions
		adjacentMovingPositions = LookUpMatrix.ADJACENCY_LOOKUP_MATRIX[lastChipChoosenToMove];
		boolean isMovable = false;
		for (int movCounts = 0; movCounts < adjacentMovingPositions.length; movCounts++) {
			if ((actualPosition == adjacentMovingPositions[movCounts])
					&& (MAINBOARD.get(actualPosition) == null)) {
				// is movable
				isMovable = true;
				break;
			}
		}

		// cannot be moved
		return isMovable;

	}

	/*
	 * Get the self player object and the opponent object if the chip color is
	 * of the player opponent then remove the opponent from the board matrix and
	 * set the board count and total count variables
	 */
	public void RemoveOpponentAtThisLocation(int actualPosition, Point p,
			Player playerOpponent, Player playerSelf, GamePlay uiGame,
			String nextPlayer) {
		UIChip opponentChip = MAINBOARD.get(actualPosition);
		// removal chip should not be of same color
		if (opponentChip.getChipColor() == playerOpponent.getChipColor()) {
		
			pieces.remove(opponentChip);
			MAINBOARD.remove(actualPosition);
			playerOpponent.setChipInBoard(playerOpponent.getChipInBoard() - 1);
			if (playerOpponent.getTotalChipCount() != 0)
				playerOpponent.setTotalChipCount(playerOpponent
						.getTotalChipCount() - 1);
			// set the current player action
			currentPlayer.setPlayerAction(determineNextAction(playerOpponent));
			currentPlayer.setCurrentPlayer(nextPlayer);
			checkGameEndCondition(playerSelf, playerOpponent);
			// repaint the board as whole
			InvalidateLabels();
			uiGame.revalidate();
			uiGame.repaint();

		}

	}

	/*
	 * This method checks if the mill has been formed at the particular locatton
	 * for the player object based upon the mill lookup matrix
	 */
	public boolean checkAndUpdateMills(Player playerObj, int position) {
		boolean isMill = false;
		int[] millCollectionMatrix = LookUpMatrix.MILL_MEMBERSHIP_LOOKUP[position];
		int[][] millPossibles = new int[2][];
		int counter = 0;
		for (int matrixElement : millCollectionMatrix) {
			int[] matrixPos = LookUpMatrix.MILL_MATRIX[matrixElement];
			millPossibles[counter] = matrixPos;
			// temp chips for mill check
			int millChipCount = 0;
			for (int i = 0; i < matrixPos.length; i++) {
				// check if piece contains the current matrix pos
				if (MAINBOARD.get(matrixPos[i]) != null) {
					if (MAINBOARD.get(matrixPos[i]).getChipColor() == playerObj
							.getChipColor()) {

						millChipCount++;
					}
				}

			}
			// check if mill formed
			if (millChipCount == 3) {
				// mill formed
				playerObj.setNumberOfMills(playerObj.getNumberOfMills() + 1);
				currentPlayer.setPlayerAction(PlayerAction.REMOV);
				// playerObj.setPlayerAction(PlayerAction.REMOV);
				isMill = true;
				return true;
			}

			counter++;
		}

		return false;

	}

	public PlayerAction getPlayerAction(Player playerObj) {
		if (playerObj.getTotalChipCount() < 1) {
			if (playerObj.getChipInBoard() == 3) {
				return PlayerAction.FLY;
			} else {
				return PlayerAction.MOVE;
			}
		} else {
			return currentPlayer.getPlayerAction();
		}
	}

	/*
	 * Generate the piece objects for the statring chips so that this could be
	 * dragged upon to the board
	 */
	public void generatePieceObjects(GamePlay uiGame) {
		// clearPieceObjects();
		// UIChip userChips;
		// Point p, p1;
		//
		// for (Integer i = 1; i < 10; i++) {
		// p = new Point(i * 25 + 10, 40);
		// p1 = new Point(i * 25 + 10 + 300, 40);
		// UIChip chipFirst = new UIChip(Color.BLACK, p, uiGame,this);
		// chipFirst.addMouseListener(chipFirst);
		// chipFirst.addMouseMotionListener(chipFirst);
		// UIChip chipSecond = new UIChip(Color.WHITE, p1, uiGame,this);
		// this.addMouseListener(chipSecond);
		// this.addMouseMotionListener(chipSecond);
		// pieces.add(chipFirst);
		// pieces.add(chipSecond);
		//
		// }

	}

	/*
	 * Returns the actual piece location in the matrix from the clicked point p
	 */
	public Integer getActualPieceLocationInMatrix(Point p) {
		Integer pos = 0;
		Iterator<Map.Entry<Integer, Point>> itr = BOARD_POINTER_MAPPER
				.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<Integer, Point> entry = itr.next();
			if (entry.getValue().distance(p) < BOARD_POINTER_THRESHOLD) {
				pos = entry.getKey();
				break;
			}

		}

		return pos;
	}

	/*
	 * Gets the mouse event and upon release of the chips from the starter chip
	 * to the gameboard creates the chip
	 */
	public void chipReleased(MouseEvent e) {
		// Point p = getActualPieceLocationInMatrix(e);
		// Color currentPlayerObj;
		// if (currentPlayer.getCurrentPlayer() == "White") {
		// currentPlayerObj = Color.WHITE;
		// } else {
		// currentPlayerObj = Color.BLACK;
		// }
		// UIChip chipFirst = new UIChip(currentPlayerObj, p, actualPosition,
		// gamePlayUIObj, this);
		// this.addMouseListener(chipFirst);
		// this.addMouseMotionListener(chipFirst);
		// this.repaint();

	}

	/*
	 * Paints the board components everytime on repaint. The initial states of
	 * the chips are retained by the pieces matrix
	 */
	public void paintComponent(Graphics g) {
		try {
			BufferedImage boardImage = ImageIO.read(new File(GAME_BOARD_IMAGE));
			g.drawImage(boardImage, 97, 97, null);
			UIChip userChips;
			Point p, p1;

			for (UIChip chipObj : pieces) {
				chipObj.draw((Graphics2D) g);
			}

		} catch (IOException e) {
			g.drawString("Unable to find image: " + GAME_BOARD_IMAGE, 10, 10);
		}

	}

	/*
	 * Important method to check if the current point clicked is the point
	 * clicked on the board Also checks if the clicked position is near to the
	 * threshold distance 20 .If so then it returns true.
	 */
	public boolean isChipInMatrix(Point p, PlayerTurn turnObj) {
		Iterator<Map.Entry<Integer, Point>> itr = BOARD_POINTER_MAPPER
				.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<Integer, Point> entry = itr.next();
			if (entry.getValue().distance(p) < BOARD_POINTER_THRESHOLD) {
				/*
				 * if the player action is remove send true after this if the
				 * action is remove or move just give away the coordinates and
				 * matrix position
				 */
				if ((turnObj.getPlayerAction() == PlayerAction.REMOV)
						|| (turnObj.getPlayerAction() == PlayerAction.MOVE)
						|| (turnObj.getPlayerAction() == PlayerAction.FLY)) {
					actualPoint = entry.getValue();
					actualPosition = entry.getKey();
					return true;
				} else {
					// If the current action is place then checks if the chip is
					// placeable to the place.
					if (isChipPlaceable(entry.getValue())) {
						actualPoint = entry.getValue();
						actualPosition = entry.getKey();
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}

	/*
	 * Returns true if the current point selected is the chip placeable point
	 */
	public boolean isChipPlaceable(Point p) {

		int chipLocation = getActualPieceLocationInMatrix(p);
		if (MAINBOARD.get(chipLocation) != null)
			return false;

		return true;
	}

	/*
	 * checks the game ending condition 1. IF chip in board is less than 3 2. If
	 * the chip is not movable
	 */
	public void checkGameEndCondition(Player PlayerObj, Player opponent) {

		// Condition 1:check if the total chip in board is less than 3
		if ((opponent.getChipInBoard() < 3)
				&& (opponent.getTotalChipCount() == 0)) {
			new UIGameCompletion(gamePlayUIObj, PlayerObj);
			controllerObj.resetGame();
		}

	}
}
