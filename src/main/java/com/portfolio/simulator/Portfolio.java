package com.portfolio.simulator;

/*
 * This class represents a portfolio which consists of the return (mean) and the standard deviation for the portfolio.
 */
public class Portfolio {
	
	private double mean;
	private double standardDeviation;
	
	public Portfolio(double mean, double standardDeviation)
	{
		this.mean = mean;
		this.standardDeviation = standardDeviation;				
	}
	
	public double getMean()
	{
		return mean;		
	}
	
	public void setMean(double mean)
	{
		this.mean = mean;
	}
	
	public double getStandardDeviation()
	{
		return standardDeviation;
	}
	
	public void setStandardDeviation(double standardDeviation)
	{
		this.standardDeviation = standardDeviation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(mean);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(standardDeviation);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portfolio other = (Portfolio) obj;
		if (Double.doubleToLongBits(mean) != Double.doubleToLongBits(other.mean))
			return false;
		if (Double.doubleToLongBits(standardDeviation) != Double.doubleToLongBits(other.standardDeviation))
			return false;
		return true;
	}
}