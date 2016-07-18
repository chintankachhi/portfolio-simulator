package com.portfolio.simulator;

import org.junit.Test;

/*
 * This class tests various exception scenarios that can occur due to invalid simulation values.
 */
public class PortfolioCalculatorTest {
	
	private static final Portfolio PORTFOLIO = new Portfolio(0.094324, 0.15675);
	private static final double INITIALINVESTMENT = 100000;
	private static final int SIMULATIONPERIOD = 20;
	private static final double INFLATIONRATE = 3.5;
	private static final int NUMBEROFSIMULATIONS = 10000;
	
	
	@Test (expected = InvalidSimulationException.class)
	public void testNullPortfolio() throws InvalidSimulationException
	{
		PortfolioCalculator portfolioCalculator = new PortfolioCalculatorImpl(null, SIMULATIONPERIOD, INITIALINVESTMENT, INFLATIONRATE, NUMBEROFSIMULATIONS);
		portfolioCalculator.getMedian();
	}
	
	@Test (expected = InvalidSimulationException.class)
	public void testInvalidSimulationPeriod() throws InvalidSimulationException
	{
		PortfolioCalculator portfolioCalculator = new PortfolioCalculatorImpl(PORTFOLIO, -10, INITIALINVESTMENT, INFLATIONRATE, NUMBEROFSIMULATIONS);
		portfolioCalculator.getMedian();
	}
	
	@Test (expected = InvalidSimulationException.class)
	public void testInvalidInitialInvestment() throws InvalidSimulationException
	{
		PortfolioCalculator portfolioCalculator = new PortfolioCalculatorImpl(PORTFOLIO, SIMULATIONPERIOD, -1000, INFLATIONRATE, NUMBEROFSIMULATIONS);
		portfolioCalculator.getMedian();
	}
	
	@Test (expected = InvalidSimulationException.class)
	public void testInvalidPercentile() throws InvalidSimulationException
	{
		PortfolioCalculator portfolioCalculator = new PortfolioCalculatorImpl(PORTFOLIO, SIMULATIONPERIOD, INITIALINVESTMENT, INFLATIONRATE, NUMBEROFSIMULATIONS);
		portfolioCalculator.getPercentile(-90);
	}
	
	@Test
	public void testNegativeNumberOfSimulations() throws InvalidSimulationException
	{
		PortfolioCalculator portfolioCalculator = new PortfolioCalculatorImpl(PORTFOLIO, SIMULATIONPERIOD, INITIALINVESTMENT, INFLATIONRATE, -10);
		portfolioCalculator.getMedian();
	}
}