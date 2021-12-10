import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class StudentTest {

	public static void main(String[] args) throws Exception {
		int num = 0; //number of students in the system
		int mainop, option; //mainop: number that the user choose in the main menu; option: number that the user choose in the submenu
		int i = 0; //count of students that are added to the student array
		int a = 0; //count of students that are added to the student_employee array
		int ID, courseID;
		String firstn, lastn, level, job, jobtype, coursen, line;
		boolean error, findID, foundcourse;
		
		File file1 = new File("d:\\StudentReport.txt"); //Mac: /Users/name/Desktop/StudentReport.txt
		File file2 = new File("d:\\Course.txt"); //Mac: /Users/name/Desktop/Course.txt
		File file3 = new File("d:\\CourseAssignment.txt"); //Mac: /Users/name/Desktop/CourseAssignment.txt
		file1.createNewFile();
		file2.createNewFile();
		file3.createNewFile();
		PrintWriter writer1 = new PrintWriter(file1); 
		PrintWriter writer2 = new PrintWriter(file2); 
		PrintWriter writer3 = new PrintWriter(file3); 
		Scanner input = new Scanner(System.in);
		
		//1 - Print the description of the main menu
		System.out.println("\tWelcome to Student and Course Management System!");
		do {
			try {
				System.out.print("\nThis system will allow you to manage students and courses. Let's start with the number of students this system will have: ");
				num = input.nextInt();
				error = false;
			} catch (Exception e) {
				System.out.println("\nException occured: " + e);
				input.next();
				error = true;
			}
		} while (error);
		System.out.println("");
		
		//2 - Create Student and StudentEmployee array
		Student[] studentArray = new StudentEmployee[num];
		for (int z = 0; z < num; z++) {
			studentArray[z] = new StudentEmployee();
		}		
		StudentEmployee[] studentEmployee = new StudentEmployee[num];
		for (int z = 0; z < num; z++) {
			studentEmployee[z] = new StudentEmployee();
		}
		
		//3 - This do-while loop will keep execute until the user choose to exit the main menu
		do {
			System.out.println("***Welcome to Student and Course Management System***"); //Print the main menu
			System.out.println("Press '1' for Student Management System (SMS)");
			System.out.println("Press '2' for Course Management System (CMS)");
			System.out.println("Press '0' to exit");
			mainop = input.nextInt();
			
			//4 - This nested if-else loop will match the input (option) to the corresponding submenu
			if (mainop == 1) {
				System.out.println("***Welcome to SMS***");
				//5 - This nested do-while loop will keep execute until the user choose to exit the SMS
				do {
					System.out.println("Press '1' to add a student"); //Print the SMS menu
					System.out.println("Press '2' to deactivate a student");
					System.out.println("Press '3' to display all students");
					System.out.println("Press '4' to search for a student by ID");
					System.out.println("Press '5' to assign on-campus job");
					System.out.println("Press '6' to display all students with on-campus jobs");
					System.out.println("Press '0' to exit SMS");
					option = input.nextInt();
					System.out.println("");
			
					//6 - This nested if-else loop will match the input (option) to the corresponding action
					if (option == 1) { //Add student (1): ask for student first name, last name, and level of a student. The student is then added to the Student array
						try {
							studentArray[i].randomID();
							
							System.out.print("Enter first name: ");
							firstn = input.next();
							studentArray[i].setFN(firstn);
				
							System.out.print("Enter last name: ");
							lastn = input.next();
							studentArray[i].setLN(lastn);
				
							do {
								System.out.print("Enter level of the student: ");
								level = input.next();
							} while (studentArray[i].setLVL(level));
							
							System.out.println("");
							studentArray[i].printadd();
					
							i++;
						}
						catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("System is full! Only " + num + " number of students allow in this system. \n");
						}
					}
					else if (option == 2) { //Deactivate student (2): ask for student ID and change their status to inactive
						System.out.print("Enter the ID for the student you want to deactivate: ");
						findID = false;
						
						try {
							ID = input.nextInt();
							System.out.println("");
							for (int j = 0; j < num; j++)  {
								if (studentArray[j].getID() == ID) {
									findID = true;
									studentArray[j].setStatus();
									break;
								}
							}
							if (findID != true) { 
								System.out.println("Student ID invalid! \n");
							}
						} catch (Exception e) {
							System.out.println("\nException occurred: " + e + "\n");
							input.next();
						}
					} 
					else if (option == 3) { //Display all students (3): print all students in the array and write all students in the Student txt file
						sort(studentArray,i);
	
						for (int z = 0; z < i; z++) {
							studentArray[z].print();
							
							writer1.write(studentArray[z].getFN() + " " + studentArray[z].getLN());
							writer1.write("\nID: " + studentArray[z].getID());
							writer1.write("\nLevel: " + studentArray[z].getLVL());
							
							if (studentArray[z].getStatus() != true) {
								writer1.write("\nStatus: Inactive \n\n");
							} else {
								writer1.write("\nStatus: Active \n\n");
							}
							writer1.flush();
						}
					}
					else if (option == 4) { //Search for student (4): ask for student ID and print the student's info
						System.out.print("Enter the student ID: ");
						ID = input.nextInt();
						System.out.println("");
						findID = false;
						
						for (int j = 0; j < num; j++) {
							if (studentArray[j].getID() == ID) {
								findID = true;
								studentArray[j].print();
								break;
							}
						}
							if (findID != true) {
								System.out.println("Student ID invalid! \n");
							}
					}
					else if (option == 5) { //Assign on campus job (5): ask for student ID and assign student to on-campus job. The student is then added to the StudentEmployee array
						System.out.print("Enter student ID: ");
						ID = input.nextInt();
						
						for (int j = 0; j < num; j++) {
							if (studentArray[j].getID() == ID) {
								studentEmployee[a] = (StudentEmployee) studentArray[j];
								
								do {
									System.out.print("Enter job: ");
									job = input.next();
								} while (studentEmployee[a].setJOB(job));
								
								input.nextLine();//clear buffer
								
								do {
									System.out.print("Enter job type: ");
									jobtype = input.nextLine();
								} while (studentEmployee[a].setET(jobtype));
								
								studentEmployee[a].printjobadd();
								a++;
							}
						}	
					}
					else if (option == 6) {
						for (int j = 0; j < num; j++) {
							if (studentEmployee[j].getet() != null) {
								studentEmployee[j].printjob();
							}
						}
					}
				} while (option != 0);
			}
			
			
			if (mainop == 2) {
				System.out.println("***Welcome to CMS***");
				//7 - This nested do-while loop will keep execute until the user choose to exit the CMS 
				do {
					System.out.println("Press '1' to add a new course"); //Print the CMS menu
					System.out.println("Press '2' to assign sudent a new course");
					System.out.println("Press '3' to display student with assigned courses");
					System.out.println("Press '0' to exit CMS");
					option = input.nextInt();
					System.out.println("");
					
					//8 - This nested if-else loop will match the input (option) to the corresponding action
					if (option == 1) { //Add new course (1): ask for course ID and name and gather the inputs and store it in the Course txt file 
						Scanner input2 = new Scanner(file2);
						foundcourse = false;
						
						System.out.print("Enter course ID: ");
						courseID = input.nextInt();
						System.out.print("Enter course name: ");
						coursen = input.next();
						
						if (file2.length() == 0) {
							writer2.println(Integer.toString(courseID) + " - " + coursen);
							writer2.flush();
							
							System.out.println("Confirmation: New course " + courseID + " has been added \n");
						} else {
							while (input2.hasNext()) {
								 line = input2.nextLine();
								 
								 if (line.contains(Integer.toString(courseID) + " - " + coursen)) {
									 foundcourse = true;
									 System.out.println("The course already exist. \n");
								 } 
							}
							if (foundcourse != true) {
								writer2.println(Integer.toString(courseID) + " - " + coursen);
								writer2.flush();
								
								System.out.println("Confirmation: New course " + courseID + " has been added \n");
							}
						}
					}
					if (option == 2) { //Assign student to a new course (2): ask for student ID and assign the course to the student. Gather those inputs and store it in CourseAssignment txt file
						findID = false;
						foundcourse = false;
						
						System.out.print("Enter student ID: ");
						ID = input.nextInt();
						
						for (int j = 0; j < num; j++) {
							if (studentArray[j].getID() == ID) {
								Scanner input2 = new Scanner(file2);
								findID = true;
								
								System.out.print("Enter course ID: ");
								courseID = input.nextInt();
								
								while (input2.hasNext()) {
									 line = input2.nextLine();
									 
									 if (line.contains(Integer.toString(courseID))) {
										 foundcourse = true;
										 writer3.println(studentArray[j].getFN() + " " + studentArray[j].getLN());
										 writer3.println("ID - " + studentArray[j].getID());
										 writer3.println("Courses: " + courseID + "\n");
										 writer3.flush();
										 
										 System.out.println("Confirmation: " + studentArray[j].getFN() + " " + studentArray[j].getLN() + " has been assigned to course " + courseID +"\n");
									 } 
								}
								if (foundcourse != true) {
									System.out.println("Course doesn't exist.\n");
								}
							}
						}
						if (findID != true) {
							System.out.println("Student ID invalid! \n");
						}
					}
					if (option == 3) { //Display students with assigned courses (3): read the CourseAssignment txt file and print the output. 
					    Scanner input2 = new Scanner(file3);
					    
					    while (input2.hasNextLine()) {
					      System.out.println(input2.nextLine());
					    }
					}
				} while (option != 0);
			}
		} while (mainop != 0); 
		
		System.out.println("Good Bye!!!");
	}
	
	//Method that will sort the student array
	public static void sort(Student[] x, int num) {
		Student temp = new Student();
		
		for (int j = 0; j < num; j++) {
			for (int k = j + 1; k < num; k++) {
				if (x[j].getFN().compareTo(x[k].getFN()) > 0) {
					temp = x[j];
					x[j] = x[k];
					x[k] = temp;
				}
			}
		}
	}
}