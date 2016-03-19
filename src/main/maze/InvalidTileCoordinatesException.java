package main.maze;

public class InvalidTileCoordinatesException extends Exception
{
	private static final long serialVersionUID = 1L;

	public InvalidTileCoordinatesException(String message)
	{
		super(message);
	}
}
