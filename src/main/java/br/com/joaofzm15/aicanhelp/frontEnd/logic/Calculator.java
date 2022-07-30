package br.com.joaofzm15.aicanhelp.frontEnd.logic;


public class Calculator {

	public static double calculateWinRate(int wins, int losses) {
		double totalDuels = wins+losses;
		double oneHundredDividedByTotal = 100 / totalDuels;
		double toBeReturned = wins * oneHundredDividedByTotal;
		return toBeReturned;
	}
	
	public static double calculateAverage(int turns, int totalDuels) {
		double turnsToDouble = (double) turns;
		double totalToDouble = (double) totalDuels;
		return turnsToDouble/totalToDouble;
	}
	
}
