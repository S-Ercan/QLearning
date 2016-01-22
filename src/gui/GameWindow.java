package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.Direction;
import maze.Maze;

/**
 * Main frame for graphical maze representation.
 */
public class GameWindow extends JFrame
{
	private static final long serialVersionUID = 1903142717890981086L;

	private JPanel mainPanel;
	private MazePanel mazePanel;
	private QValuePanel qValuePanel;

	/**
	 * Creates a new window with a graphical representation of maze.
	 * 
	 * @param maze
	 *            maze to display
	 */
	public GameWindow(Maze maze)
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		getContentPane().add(mainPanel);

		mazePanel = new MazePanel(maze);
		mainPanel.add(mazePanel);

		qValuePanel = new QValuePanel(maze.getXSize(), maze.getYSize());
		mainPanel.add(qValuePanel);

		// Adjust size to maze dimension
		int windowWidth = (maze.getXSize() + 5) * mazePanel.getTileWidth() * 2;
		int windowHeight = (maze.getYSize() + 5) * mazePanel.getTileHeight();
		setSize(windowWidth, windowHeight);

		// Adjust location to size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		setLocation((int) screenWidth / 2 - windowWidth / 2,
				(int) screenHeight / 2 - windowHeight / 2);

		setResizable(false);
		setTitle("Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

	/**
	 * Calls mazePanel's move animation functionality.
	 * 
	 * @param x
	 *            x coordinate of destination tile
	 * @param y
	 *            y coordinate of destination tile
	 */
	public void showMoveAnimation(int x, int y)
	{
		mazePanel.showMoveAnimation(x, y);
	}

	public void updateQValue(int x, int y, Direction direction, double q)
	{
		qValuePanel.updateQValue(x, y, direction, q);
	}
}
