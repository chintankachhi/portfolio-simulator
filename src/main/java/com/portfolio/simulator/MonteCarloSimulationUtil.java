package com.portfolio.simulator;

import java.text.NumberFormat;

/*
 * This utility class assists in calculating and displaying future portfolio values based on the supplied input values for the portfolio.
 */
public final class MonteCarloSimulationUtil
{
    private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance();
   
	// Suppress default constructor for noninstantiability
	private MonteCarloSimulationUtil()
	{
	
	}
	
	/*
	 * @param initialInvestment
	 *                    The initial investment in the portfolio.
	 * @param simulationPeriod 
	 *                    The desired simulation period in years for the portfolio.
	 * @param portfolioReturn
	 *                    The percent return for the portfolio.
	 * @param portfolioRisk                   
	 *                    The percent risk for the portfolio. 
	 * @param inflationRate
	 *                   The percent inflation rate.
	 * @param numberOfSimulations
	 *                   The number of simulations to perform for the calculations.
	 * @throws InvalidSimulationException if the value(s) passed in are invalid.	                                          
	 */
	public static void printSimulationValues(double initialInvestment, int simulationPeriod, double portfolioReturn, double portfolioRisk, double inflationRate, int numberOfSimulations) throws InvalidSimulationException
	{
		// convert the percentage values to non-percentage values
		portfolioReturn /= 100;
		portfolioRisk /= 100;
		
		Portfolio portfolio = new Portfolio(portfolioReturn, portfolioRisk);
		
		// create the portfolio calculator to get its expected values.
		PortfolioCalculator portfolioCalculator= new PortfolioCalculatorImpl
				                                 (portfolio, simulationPeriod, initialInvestment, inflationRate, numberOfSimulations);
		
		double portfolioExpectedMedianReturn = portfolioCalculator.getMedian();
		double ninetiethPercentile = portfolioCalculator.getPercentile(90);
		double tenthPercentile = portfolioCalculator.getPercentile(10);
		
		System.out.println("The median return for the portfolio after 20 years is: " + FORMATTER.format(portfolioExpectedMedianReturn));
		System.out.println("The 10% best case for the portfolio after 20 years is: " + FORMATTER.format(ninetiethPercentile));
		System.out.println("The 10% worst case for the portfolio after 20 years is: " + FORMATTER.format(tenthPercentile));
	}
}