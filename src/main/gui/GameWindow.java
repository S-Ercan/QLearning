package main.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	private QValuesPanel qValuesPanel;

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
		int panelWidth = maze.getXSize() * MazePanel.tileWidth
				+ (maze.getXSize() + 1) * MazePanel.xMargin;
		int panelHeight = maze.getYSize() * MazePanel.tileHeight
				+ (maze.getYSize() + 1) * MazePanel.yMargin;

		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(mainPanel);

		mainPanel.add(createMazeAndQValuesPanel(maze, panelWidth, panelHeight));
		mainPanel.add(createControlsPanel());

		// Adjust size to maze dimension
		int windowWidth = panelWidth * 2 + 20;
		int windowHeight = panelHeight + 200;
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

	public JPanel createMazeAndQValuesPanel(Maze maze, int panelWidth, int panelHeight)
	{
		JPanel mazeAndQValuesPanel = new JPanel();
		mazeAndQValuesPanel.setLayout(new BoxLayout(mazeAndQValuesPanel, BoxLayout.X_AXIS));

		mazePanel = new MazePanel(maze);
		mazePanel.setMinimumSize(new Dimension(panelWidth, panelHeight));
		mazePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		mazePanel.setMaximumSize(new Dimension(panelWidth, panelHeight));
		mazeAndQValuesPanel.add(mazePanel);

		qValuesPanel = new QValuesPanel(maze.getXSize(), maze.getYSize());
		qValuesPanel.setMinimumSize(new Dimension(panelWidth, panelHeight));
		qValuesPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
		qValuesPanel.setMaximumSize(new Dimension(panelWidth, panelHeight));
		mazeAndQValuesPanel.add(qValuesPanel);

		return mazeAndQValuesPanel;
	}

	public JPanel createControlsPanel()
	{
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));

		JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		scoreLabel = new JLabel();
		scoreLabel.setFont(new Font("", Font.BOLD, 16));
		updateScore(0);
		scorePanel.add(scoreLabel);

		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		goButton = new JButton("Go");
		goButton.setPreferredSize(new Dimension(70, 30));
		goButton.addActionListener(this);
		buttonsPanel.add(goButton);

		pauseButton = new JButton("Pause");
		pauseButton.setPreferredSize(new Dimension(70, 30));
		pauseButton.addActionListener(this);
		buttonsPanel.add(pauseButton);

		controlsPanel.add(scorePanel);
		controlsPanel.add(buttonsPanel);

		return controlsPanel;
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
		qValuesPanel.updateQValue(x, y, direction, q);
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
