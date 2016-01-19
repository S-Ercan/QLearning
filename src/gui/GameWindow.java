package gui;

import javax.swing.JFrame;

import maze.Maze;

public class GameWindow extends JFrame
{
	private static final long serialVersionUID = 1903142717890981086L;

	private MazePanel mazePanel;

	/**
	 * Creates a new window with a graphical representation of maze.
	 * 
	 * @param maze maze to display
	 */
	public GameWindow(Maze maze)
	{
		setSize(500, 500);
		setResizable(false);
		setTitle("Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mazePanel = new MazePanel(maze);
		getContentPane().add(mazePanel);

		setVisible(true);
	}

	/**
	 * Calls mazePanel's move animation functionality.
	 * 
	 * @param x x coordinate of destination tile
	 * @param y y coordinate of destination tile
	 */
	public void showMoveAnimation(int x, int y)
	{
		mazePanel.showMoveAnimation(x, y);
	}
}
