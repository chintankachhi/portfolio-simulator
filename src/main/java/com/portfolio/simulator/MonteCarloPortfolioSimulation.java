package com.portfolio.simulator;

import java.util.Scanner;

/*
 * This is the main class to run the program.
 * It takes in the values through the user input and uses the MonteCarloSimulationUtil class for predicting and displaying the values of 
 * aggressive and very conservative portfolios based on those values.
 */
public class MonteCarloPortfolioSimulation {
	
	public static void main (String [] args)
	{
		double initialInvestment;
		int simulationPeriod;
		double aggressivePortfolioReturn;
        double aggressivePortfolioRisk;
        double veryConservativePortfolioReturn;
        double veryConservativePortfolioRisk;
        double inflationRate;
        int numberOfSimulations;
		
		Scanner input = new Scanner(System.in);
		 
	    System.out.print("Enter the initial amount: ");
	    initialInvestment = input.nextDouble();
	 
	    System.out.print("Enter the simulation period (years): ");
	    simulationPeriod = input.nextInt();
	    
	    System.out.print("Enter the aggressive portfolio return percentage: ");
	    aggressivePortfolioReturn = input.nextDouble();
	    
	    System.out.print("Enter the aggressive portfolio risk percentage: ");
	    aggressivePortfolioRisk = input.nextDouble();
	    
	    System.out.print("Enter the very conservative portfolio return percentage: ");
	    veryConservativePortfolioReturn = input.nextDouble();
	    
	    System.out.print("Enter the very conservative portfolio risk percentage: ");
	    veryConservativePortfolioRisk = input.nextDouble();
	    
	    System.out.print("Enter the percentage value for the inflation rate: ");
	    inflationRate = input.nextDouble();
	    
	    System.out.print("Enter the number of simulations: ");
	    numberOfSimulations = input.nextInt();
	    
	    input.close();
	    
	    System.out.println();
	    System.out.println("Monte Carlo Simulation Results:");
		System.out.println();
		
		System.out.println("For the aggressive portfolio:");
	    
	    try 
	    {
			MonteCarloSimulationUtil.printSimulationValues
			(initialInvestment, simulationPeriod, aggressivePortfolioReturn, aggressivePortfolioRisk, inflationRate, numberOfSimulations);
		} 
	    catch (InvalidSimulationException e) {
			e.printStackTrace();
		}
	    
	    System.out.println();
	    System.out.println("For the very conservative portfolio:");
	    
	    try 
	    {
			MonteCarloSimulationUtil.printSimulationValues
			(initialInvestment, simulationPeriod, veryConservativePortfolioReturn, veryConservativePortfolioRisk, inflationRate, numberOfSimulations);
		}
	    catch (InvalidSimulationException e) 
	    {
			e.printStackTrace();
		}
	}
}
