
public class Agent
{
	private int[][] map;
	private int xPosition;
	private int yPosition;
	private int score;

	public Agent()
	{
		map = new int[5][5];
		xPosition = 0;
		yPosition = 0;
		score = 0;
	}
	
	public void chooseAction()
	{
		int x = 0;
		int y = 0;
		executeAction(x, y);
	}

	public void executeAction(int x, int y)
	{
		EnvironmentManager.executeMove(x, y);
	}
}
