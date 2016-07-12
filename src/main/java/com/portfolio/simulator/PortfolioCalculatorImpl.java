package com.portfolio.simulator;
import java.util.Arrays;
import java.util.Random;

/*
 * This implementation is used to calculate the expected value and percentiles for a given portfolio.
 * It uses the Geometric Brownian motion (GBM) model in the calculation as follows:
 * Next year's portfolio value = current portfolio value + current portfolio value * ((return * time period) + standard deviation * random value * sqrt(time period))
 * In our case, since we are calculating iteratively every year, time period will be one year. We use the default value of 10,000 for the 
 * number of simulations.
 */
public class PortfolioCalculatorImpl implements PortfolioCalculator {
	
	private Portfolio portfolio;
	private int simulationPeriod;
	private static final int NUMBEROFSIMULATIONS = 10000;
	private double initialInvestment;
	private double[] values;
	
	// flag to indicate whether the simulation values have been created.
	private boolean createdSimulationValues = false;
	
	/*
	 * @param portfolio
	 *               The portfolio whose expected values are to be calculated.
	 * @param simulationPeriod 
	 *                    The desired simulation period in years for the given portfolio.
	 * @param initialInvestment
	 *                    The initial investment in the portfolio. 
	 */
	public PortfolioCalculatorImpl(Portfolio portfolio, int simulationPeriod, double initialInvestment)
	{
		this.portfolio = portfolio;
		this.simulationPeriod = simulationPeriod;
		this.initialInvestment = initialInvestment;
		values = new double[NUMBEROFSIMULATIONS];
	}
	
	/*
	 * @return The median value of the simulations.
	 */
	public double getMedian() throws InvalidSimulationException
	{
		if (!isCreatedSimulationValues())
		{	
		   createSimulationValues();
		}
		
		int middle = values.length/2;
		
	    if ((values.length % 2) == 1) 
	    {
	        return values[middle];
	    }
	    else 
	    {
	        return (values[middle-1] + values[middle]) / 2;
	    }
	}
	
	/*
	 * @param percentileValue
	 *                    The requested percentile value.
	 * @return The requested percentile value from the array of simulation values.
	 * For example, if 90 is passed in as the percentileValue, this method call will return the 90th percentile value. 
	 */
	public double getPercentile(int percentileValue) throws InvalidSimulationException
	{
		if ((percentileValue <= 0) || (percentileValue > 100))
		{
			throw new InvalidSimulationException("The percentile value must be between 1 and 100");
		}
		
		if (!isCreatedSimulationValues())
		{
		   createSimulationValues();
		}
		
		return values[((NUMBEROFSIMULATIONS*percentileValue/100) - 1)];
	}
	
	/*
	 * Creates an array of simulation values for the portfolio based on the number of simulations.
	 * Uses a random number generator to ensure Gaussian distribution of the random numbers.
	 * The generated array is sorted in ascending order.
	 */
	private void createSimulationValues() throws InvalidSimulationException
	{
		if (portfolio == null)
		{
			throw new InvalidSimulationException("The portfolio cannot be null!");
		}
		
		if (simulationPeriod <= 0)
		{
			throw new InvalidSimulationException("The simulation period should be greater than zero!");
		}
		
		if (initialInvestment <= 0)
		{
			throw new InvalidSimulationException("The initial investment should be greater than zero!");
		}
		
		double currentValue;
		Random random = new Random();
		double randomValue;
		
		for (int i = 0; i < NUMBEROFSIMULATIONS; i++)
		{
			currentValue = initialInvestment;
		
		    for (int j = 1; j <= simulationPeriod; j++)
		    {
		       randomValue = random.nextGaussian();
				
		       // Use the Geometric Brownian motion (GBM) model formula to calculate the portolio values and add an inflation adjusted rate of 3.5% for each year.
		       currentValue += currentValue * (portfolio.getMean() + portfolio.getStandardDeviation() * randomValue);
		       currentValue += currentValue* (0.035);
		    }
		   
		    values[i] = currentValue;
		}
		
		Arrays.sort(values);
		setCreatedSimulationValues(true);
	}

	private boolean isCreatedSimulationValues() {
		return createdSimulationValues;
	}

	private void setCreatedSimulationValues(boolean createdSimulationValues) {
		this.createdSimulationValues = createdSimulationValues;
	}
}