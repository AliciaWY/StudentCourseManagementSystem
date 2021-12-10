/**
 * Name: Alicia Lim
 * Course section: 002
 * Date: 11.16.2021
 * Project Description: Implement a Java program that create a ‘Student Management System’ and ‘Course Management System’.
 */

interface Curriculum {
	public int getCID();
	public String getCN();
	public void setCID(int cID);
	public void setCN(String cn);
	public void printcourse();
}

public class Course implements Curriculum {
	//Variables
	private int cID;
	private String cn;
	
	//Constructors
	Course() { }
	
	//Getter methods
	public int getCID() {
		return cID;
	}
	
	public String getCN() {
		return cn;
	}
	
	//Setter methods
	public void setCID(int cID) {
		this.cID = cID;
	}
	
	public void setCN(String cn) {
		this.cn = cn;
	}
	
	//Print method
	public void printcourse() {
		System.out.println("Confirmation: New course " + cID + " has been added. \n");
	}
}
