package com.portfolio.simulator;

/*
 * This interface is used to get the expected median value and percentiles for a given portfolio.
 */
public interface PortfolioCalculator {
	
	/*	
	 * @return The median value of the simulations.
	 * @throws InvalidSimulationException
	 */
	public double getMedian() throws InvalidSimulationException;
	
	/*
	 * @param percentileValue 
	 *                    The requested integer percentile value.
	 * @return The requested integer percentile value.
	 * @throws InvalidSimulationException
	 */
	public double getPercentile(int percentileValue) throws InvalidSimulationException;
}