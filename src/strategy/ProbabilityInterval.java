package strategy;

public class ProbabilityInterval
{
	private double lowerBound;
	private double upperBound;

	public ProbabilityInterval(double lowerBound, double upperBound)
	{
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public double getLowerBound()
	{
		return lowerBound;
	}

	public void setLowerBound(double lowerBound)
	{
		this.lowerBound = lowerBound;
	}

	public double getUpperBound()
	{
		return upperBound;
	}

	public void setUpperBound(double upperBound)
	{
		this.upperBound = upperBound;
	}
}
