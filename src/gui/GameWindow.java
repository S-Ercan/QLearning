package gui;

import javax.swing.JFrame;

import maze.Maze;

public class GameWindow extends JFrame
{
	private static final long serialVersionUID = 1903142717890981086L;

	public GameWindow(Maze maze)
	{
		setSize(500, 500);
		setResizable(false);
		setTitle("Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(new MazePanel(maze.getXSize(), maze.getYSize()));
		setVisible(true);
	}
}
