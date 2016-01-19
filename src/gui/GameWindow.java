package gui;

import javax.swing.JFrame;

import maze.Maze;

public class GameWindow extends JFrame
{
	private static final long serialVersionUID = 1903142717890981086L;

	private MazePanel mazePanel;

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

	public void moveToSquare(int x, int y)
	{
		mazePanel.moveToSquare(x, y);
	}
}
