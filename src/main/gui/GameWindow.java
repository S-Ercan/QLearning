package main.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.maze.Direction;
import main.maze.Maze;

/**
 * Main frame for graphical maze representation.
 */
public class GameWindow extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1903142717890981086L;

	private boolean simulationRunning;

	private JPanel mainPanel;
	private MazePanel mazePanel;
	private QValuePanel qValuePanel;

	private JLabel scoreLabel;
	private JButton goButton;
	private JButton pauseButton;

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

		int panelWidth = maze.getXSize() * MazePanel.tileWidth
				+ (maze.getXSize() + 1) * MazePanel.xMargin;
		int panelHeight = maze.getYSize() * MazePanel.tileHeight
				+ (maze.getYSize() + 1) * MazePanel.yMargin;

		mainPanel.add(createMazeAndControlsPanel(maze, panelWidth, panelHeight));
		mainPanel.add(createQValuePanel(maze, panelWidth, panelHeight));

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

	public JPanel createMazeAndControlsPanel(Maze maze, int panelWidth, int panelHeight)
	{
		JPanel mazeAndControlsPanel = new JPanel();
		mazeAndControlsPanel.setLayout(new BoxLayout(mazeAndControlsPanel, BoxLayout.Y_AXIS));

		mazePanel = new MazePanel(maze);
		mazePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mazePanel.setMinimumSize(new Dimension(panelWidth, panelHeight));
		mazePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		mazePanel.setMaximumSize(new Dimension(panelWidth, panelHeight));
		mazePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		mazeAndControlsPanel.add(mazePanel);

		JPanel controlsPanel = new JPanel();
		controlsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
		controlsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		scoreLabel = new JLabel();
		scoreLabel.setFont(new Font("", Font.BOLD, 16));
		updateScore(0);
		controlsPanel.add(scoreLabel);

		goButton = new JButton("Go");
		goButton.addActionListener(this);
		controlsPanel.add(goButton);

		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);
		controlsPanel.add(pauseButton);
		mazeAndControlsPanel.add(controlsPanel);

		return mazeAndControlsPanel;
	}

	public JPanel createQValuePanel(Maze maze, int panelWidth, int panelHeight)
	{
		qValuePanel = new QValuePanel(maze.getXSize(), maze.getYSize());
		qValuePanel.setMinimumSize(new Dimension(panelWidth, panelHeight));
		qValuePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		qValuePanel.setMaximumSize(new Dimension(panelWidth, panelHeight));
		qValuePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(qValuePanel);

		return qValuePanel;
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

	public void updateScore(int score)
	{
		scoreLabel.setText("Score: " + score);
	}

	public void updateQValue(int x, int y, Direction direction, double q)
	{
		qValuePanel.updateQValue(x, y, direction, q);
	}

	public boolean simulationIsRunning()
	{
		return simulationRunning;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == goButton)
		{
			simulationRunning = true;
		}
		else if (e.getSource() == pauseButton)
		{
			simulationRunning = false;
		}
	}
}
