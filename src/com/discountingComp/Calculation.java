package com.discountingComp;

import java.io.*;
import java.util.*;
import java.text.*; //SimpleDateFormat

public class Calculation {

	public static void main(String[] args) {
		try {
			// config file which contains - ANNUAL_CI_RATE, DESIRED_AGE, CURRENT_AGE, MATURITY_AMOUNT
			FileReader fr = new FileReader("config-file");
			Properties props = new Properties();
			props.load(fr);
	
			double annualCIRate = Double.parseDouble(props.getProperty("ANNUAL_CI_RATE"));
			int desiredAge = Integer.parseInt(props.getProperty("DESIRED_AGE"));
			int duration = desiredAge - Integer.parseInt(props.getProperty("CURRENT_AGE"));
			double maturityAmount = Double.parseDouble(props.getProperty("MATURITY_AMOUNT"));
	
			double principalAmount = getPrincipalAmount(maturityAmount, annualCIRate / 100, duration);
			
			getData(maturityAmount, annualCIRate, principalAmount, duration);
		} 
		catch(Exception e) 
		{
			System.out.println(e.toString());
		}
	}

	public static double getPrincipalAmount(double maturityAmount, double annualCIRate, int duration) {
		return (maturityAmount / Math.pow((1 + annualCIRate), duration));
	}
	
	public static void getData(double maturityAmount, double interestRate, double principal, int duration) {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-YYYY");

		System.out.println("\nOutput:- \n");
		System.out.println(String.format("Amount Needed in %d years         : ", duration) + "\t" + String.format("%.2f", maturityAmount));
		System.out.println("Rate of interest                  : " + "\t" + String.format("%.2f", interestRate) + "%");
		System.out.println("Today’s Date                      : " + "\t" + ft.format(dNow));
		System.out.println("Amount to be invested today       : " + "\t" + String.format("%.2f", principal));
	}	
}