/**
 * Name: Alicia Lim
 * Course section: 002
 * Date: 12.07.2021
 * Project Description: Implement a Java program that create a ‘Student Management System’ and ‘Course Management System’.
 */

import java.util.Scanner;

interface Person {
	public int randomID();
	public int getID();
	public String getFN();
	public String getLN();
	public String getLVL();
	public Boolean getStatus();
	public void setFN(String fn);
	public void setLN(String ln);
	public Boolean setLVL(String lvl);
	public void setStatus();
	public void printadd();
	public void print();
}

public class Student implements Person {
	//Variables
	private int ID;
	private String fn,ln,lvl;
	private boolean status = true;
	
	//Constructors
	Student() { }
	
	Scanner input = new Scanner(System.in);
	
	//Method that assign a random ID (0-99) to a student 
	public int randomID() { 
		ID = (int)(Math.random()*100);
		return ID;
	}
	
	//Getter methods
	public int getID() {
		return ID;
	}
	
	public String getFN() {
		return fn;
	}
	
	public String getLN() {
		return ln;
	}
	
	public String getLVL() {
		return lvl;
	}
	
	public Boolean getStatus() {
		return status;
	}

	//Setter methods 
	public void setFN(String fn) {
		this.fn = fn; 
	}
	
	public void setLN(String ln) {
		this.ln = ln; 
	}
	
	public Boolean setLVL(String lvl) {
		if (lvl.equalsIgnoreCase("Freshman") || lvl.equalsIgnoreCase("Sophomore") || lvl.equalsIgnoreCase("Junior") || lvl.equalsIgnoreCase("Senior")) {
			this.lvl = lvl; 
			return false;
		}
		else {
			System.out.println("Please type in Freshman, Sophomore, Junior, or Senior.");
			return true;
		}
		
	}
	
	public void setStatus() {
		this.status = false; 
		System.out.println(fn + " " + ln + " has been deactivated \n");
	}
	
	//Print methods
	public void printadd() {
		System.out.println(fn + " " + ln + " has been added as a " + lvl + " with ID " + ID + "\n");
	}

	public void print() {
		System.out.println(fn + " " + ln);
		System.out.println("ID: " + ID);
		System.out.println("Level: " + lvl);
		if (status != true) {
			System.out.println("Status: Inactive \n");
		} else {
			System.out.println("Status: Active \n");
		}
	}
}

class StudentEmployee extends Student {
	//Variables
	private String employmenttype, job;
	
	//Constructors
	StudentEmployee() {
		super();
	}
	
	Scanner input = new Scanner(System.in);
	
	//Getter methods
	public String getet() {
		return employmenttype;
	}
	
	public String getjob() {
		return job;
	}
	
	//Setter methods
	public Boolean setET(String employmenttype) {
		if (employmenttype.equalsIgnoreCase("Full time") || employmenttype.equalsIgnoreCase("Part time")) {
			this.employmenttype = employmenttype;
			return false;
		}
		else {
			System.out.println("Please type in full time or part time.");
			return true;
		}
	}
	
	public Boolean setJOB(String job) {
		if (job.equalsIgnoreCase("TA") || job.equalsIgnoreCase("RA")) {
			this.job = job;
			return false;
		}
		else {
			System.out.println("Please type in TA or RA.");
			return true;
		}
	}
	
	//Print methods
	public void printjobadd() {
		System.out.println(super.getFN() + " " + super.getLN() + " has been assigned " + employmenttype + " " + job + " job. \n");
	}
	
	public void printjob() {
		System.out.println(super.getFN() + " " + super.getLN());
		System.out.println("ID - " + super.getID());
		System.out.println(employmenttype + " " + job + "\n");
	}
}
