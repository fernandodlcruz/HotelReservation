package com.hotelreservation.service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CompareDateRangeService {
	public static String strSd1;
	public static String strEd1;
	public static String strSd2;
	public static String strEd2;
	public static Date startD1;
	public static Date startD2;
	public static Date endD1;
	public static Date endD2;
	
	public static boolean checkForDateClash(Date sd1, Date ed1, Date sd2, Date ed2)
	//sd - start date, ed - end date
	{

		if(sd1.before(sd2)&&ed2.before(ed1)||
		   sd2.before(sd1)&&sd1.before(ed2)&&ed2.before(ed1)||
		   sd1.before(sd2)&&sd2.before(ed1)&&ed1.before(ed2)||
		   sd2.before(sd1)&&ed1.before(ed2))
		{
			System.out.println("Date intersection detected, room not available");

			return true;
		}

		else 
			System.out.println("No date intersection detected, room available");
			return false;
	}
	public static void main(String[] args) 
	{
		Scanner getDates = new Scanner(System.in);
		System.out.println("Dates should have the format dd/mm/yy");
		System.out.println("Start date 1: ");
		strSd1 = getDates.next();
		System.out.println("End date 1: ");
		strEd1 = getDates.next();
		System.out.println("Start date 2: ");
		strSd2 = getDates.next();
		System.out.println("End date 2: ");
		strEd2 = getDates.next();
		try {
			startD1=new SimpleDateFormat("dd/MM/yyyy").parse(strSd1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			endD1=new SimpleDateFormat("dd/MM/yyyy").parse(strEd1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			startD2=new SimpleDateFormat("dd/MM/yyyy").parse(strSd2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			endD2=new SimpleDateFormat("dd/MM/yyyy").parse(strEd2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		checkForDateClash(startD1, endD1, startD2, endD2);
		
	
	}

}
