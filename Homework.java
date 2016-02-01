// Ashish Naik
// COP 3330, Section 2
// Homework Allocation program: Homework class

// Import necessary Java functions
import java.util.*;
import java.io.*;

// Create Homework class as Public with Comparable Interface
public class Homework implements Comparable<Homework> {
	
	// Create a static integer to keep track of ID numbers
	protected static int tracker = 0;
	
	// Create an integer for the ID number
	// Create a Name class variable
	// Create an integer for the section number
	// Create a Files class variable
	// Create an integer for the date submitted
	protected int identity;
	protected Name ours;
	protected int section;
	protected Files study;
	protected int dateSubmitted;
	
	
	
	// Make a constructor for the Homework class to intake the information that will define it
	public Homework (int num, Name temp1, int sec, Files fil, int date) {
		
		// Assign value to afore mentioned variables
		identity = num;
		ours = temp1;
		section = sec;
		study = fil;
		dateSubmitted = date;
		
		// Keep track of the next ID number
		if (num > tracker) {
			tracker = num;
		}
	
	
	}
	
	// Create a Comparison class to compare Homework assignments
	public int compareTo(Homework called) {
		
		// If the current assignment is submitted ahead of the one compared, a negative value is returned
		// If the current assignment is submitted after the one compared, a positive value is returned
		// If they occurred at the same time, move on to the next comparison factor
		if ((dateSubmitted - called.dateSubmitted) != 0) {
			return (dateSubmitted-called.dateSubmitted);
		}
		
		// If the current assignment's files are greater than that of the one compared, a negative value is returned
		// If the current assignment's files are less than that of the one compared, a positive value is returned
		// If they have equivalent file values, move on to the next comparison factor
		if ((study.compareTo(called.study)) != 0) {
			return (study.compareTo(called.study));
		}
		
		// If the last name of the current assignment comes before that of the compared assignment, a negative value is returned
		// If the last name of the current assignment comes after that of the compared assignment, a positive value is returned
		// If the last names are equivalent, move on to the next comparison factor
		if (ours.lname.compareTo(called.ours.lname) != 0) {
			return (ours.lname.compareTo(called.ours.lname));
		}
		
		// If the first name of the current assignment comes before that of the compared assignment, a negative value is returned
		// If the first name of the current assignment comes after that of the compared assignment, a positive value is returned
		// If the first names are equivalent, then at this point the assignments are deemed equivalent
		if (ours.fname.compareTo(called.ours.fname) != 0) {
			return (ours.fname.compareTo(called.ours.fname));
		}
		
		// Where no differences can be detected, return 0 and indicate that the assignments are the same
		return 0;
	}
	
	// Using the static variable from before, indicate the next ID number for a Homework class
	public static int generateNextUniqueId() {
		return tracker+1;
	}
	
	// Calculate and return the days this assignment is late
	public int getDaysLate() {
		if (dateSubmitted != 15) {
			return dateSubmitted-15;
		}
		
		return 0;
	}
	
	// Provide the Files class from this assignment
	public Files getFiles() {
		return study;
	}
	
	// Provide the ID number of this assignment
	public int getId() {
		return identity;
	}
	
	// Provide the Name class of this assignment
	public Name getName() {
		return ours;
	}
	
	// Provide the Section number of this assignment
	public int getSection() {
		return section; 
	}
	
	// Generate a String of information about the current assignment
	public String toString() {
		return identity+" "+ours.toString()+" "+section+" "+study.toString()+" "+dateSubmitted+"\n";
	}
	


}