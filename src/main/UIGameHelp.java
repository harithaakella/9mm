package main;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class UIGameHelp extends JFrame{
	
	// keeps track of the instructions frame
	public static boolean isFrameOpen = false;
	
	public UIGameHelp(){
		// preventing from opening more
		// than one frame for instructions
		if(isFrameOpen) return;
		
		this.setResizable(true);
		this.setTitle("How To Play Nine Men's Morris");
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().add(addInstructions(), BorderLayout.CENTER);
		this.setSize(350, 400);
		this.setVisible(true);
		isFrameOpen = true;
		
		// set the isFrameOpen variable to false before closing
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				UIGameHelp.isFrameOpen = false;
			}
		});
	}
	
	private JScrollPane addInstructions(){
		JEditorPane instr = new JEditorPane();
		instr.setContentType("text/html");
		instr.setEditable(false);
		instr.setFont(new Font("Courier", Font.PLAIN, 14));
		instr.setText(UIGameHelp.instructions);	
		JScrollPane scroll = new JScrollPane(instr, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return scroll;
	}
	
	private static String instructions = 
			"<b>Game rules</b><p>The board consists of a grid with twenty-four intersections or points. "
			+ "Each player has nine pieces, or \"men\", usually coloured black and white. Players "
			+ "try to form 'mills'— three of their own men lined horizontally or vertically—allowing"
			+ " a player to remove an opponent's man from the game. A player wins by reducing the"
			+ " opponent to two pieces (where he could no longer form mills and thus be unable to win),"
			+ " or by leaving him without a legal move.</p><b>The game proceeds in three phases:</b><ul><li><b>Phase one: placing"
			+ " pieces</b></li><p>Nine Men's Morris starts on an empty board.<br>The game begins with an empty"
			+ " board. The players determine who plays first, then take turns placing their men one per"
			+ " play on empty points. If a player is able to place three of his pieces in a straight line,"
			+ " vertically or horizontally, he has formed a mill and may remove one of his opponent's pieces"
			+ " from the board and the game. Any piece can be chosen for the removal, but a piece not in an"
			+ " opponent's mill must be selected, if possible.</p><br><li><b>Phase two: moving pieces</b></li><p>Players continue"
			+ " to alternate moves, this time moving a man to an adjacent point. A piece may not \"jump\""
			+ " another piece. Players continue to try to form mills and remove their opponent's pieces"
			+ " in the same manner as in phase one. A player may \"break\" a mill by moving one of his"
			+ " pieces out of an existing mill, then moving the piece back to form the same mill a second"
			+ " time (or any number of times), each time removing one of his opponent's men. The act"
			+ " of removing an opponent's man is sometimes called \"pounding\" the opponent. When"
			+ " one player has been reduced to three men, phase three begins.</p><br><li><b>Phase three:"
			+ " \"flying\"</b></li><p>When a player is reduced to three pieces, there is no longer"
			+ " a limitation of moving to only adjacent points: The player's men may \"fly\","
			+ " \"hop\", or \"jump\" from any point to any vacant point.</p></ul></p>";
}
