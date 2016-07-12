package com.portfolio.simulator;

import java.text.NumberFormat;

/*
 * This is the main class to run the program.
 * It uses the Monte Carlo Simulation for predicting the values of aggressive and very conservative portfolios after 20 years.
 * It uses the test values for the risk and the return given in the java programming challenge for testing purposes.
 */
public class MonteCarloPortfolioSimulation {
	
	public static void main (String [] args) 
	{
		double initialInvestment = 100000.00;
		int simulationPeriod = 20;
		double aggressivePortfolioReturn = 0.094324;
        double aggressivePortfolioRisk = 0.15675;
		double veryConservativePortfolioReturn = 0.06189;
		double veryConservativePortfolioRisk = 0.063438;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		System.out.println("Monte Carlo Simulation Results:");
		System.out.println();
				
		Portfolio aggressivePortfolio = new Portfolio(aggressivePortfolioReturn, aggressivePortfolioRisk);
		
		// create the portfolio calculator for the aggressive portfolio to get its expected values.
		PortfolioCalculator portfolioCalculator= new PortfolioCalculatorImpl
				                                 (aggressivePortfolio, simulationPeriod, initialInvestment);
		
		try
		{
		   double portfolioExpectedMedianReturn = portfolioCalculator.getMedian();
		   double ninetiethPercentile = portfolioCalculator.getPercentile(90);
		   double tenthPercentile = portfolioCalculator.getPercentile(10);
		
		   System.out.println("The median return for the aggressive portfolio after 20 years is: " + formatter.format(portfolioExpectedMedianReturn));
		   System.out.println("The 10% best case for the aggressive portfolio after 20 years is: " + formatter.format(ninetiethPercentile));
		   System.out.println("The 10% worst case for the aggressive portfolio after 20 years is: " + formatter.format(tenthPercentile));
		
		   Portfolio veryConservativePortfolio = new Portfolio(veryConservativePortfolioReturn, veryConservativePortfolioRisk);
		
		   // create the portfolio calculator for the very conservative portfolio to get its expected values.
		   portfolioCalculator= new PortfolioCalculatorImpl
                   (veryConservativePortfolio,simulationPeriod, initialInvestment);
		
		   portfolioExpectedMedianReturn = portfolioCalculator.getMedian();
		   ninetiethPercentile = portfolioCalculator.getPercentile(90);
		   tenthPercentile = portfolioCalculator.getPercentile(10);
		
		   System.out.println();
		   System.out.println("The median return for the very conservative portfolio after 20 years is: " + formatter.format(portfolioExpectedMedianReturn));		
		   System.out.println("The 10% best case for the very conservative portfolio after 20 years is: " + formatter.format(ninetiethPercentile));
		   System.out.println("The 10% worst case for the very conservative portfolio after 20 years is: " + formatter.format(tenthPercentile));
		}
		catch (InvalidSimulationException e)
		{
			e.printStackTrace();
		}
	}
}