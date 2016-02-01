// Ashish Naik
// COP 3330, Section 2
// Homework Allocation program: Name class

// Include necessary Java functions
import java.util.*;
import java.io.*;

// Create the Name class as public
public class Name {
	
	// Create strings to intake values of the first and last name
	protected String lname, fname;
	
	// Use a constructor to define these variables
	public Name (String fa, String la) {
		lname = la;
		fname = fa;
	
	}
	
	// Create a class to detect if the names are equivalent
	public Boolean equals(Name other) {
		
		if ((lname.equals(other.lname)) && (fname.equals(other.fname))) {
		
			return true;
		}
		
		return false;
	}
	
	// A class to return a String of the first and last name
	public String toString() {
		return fname+" "+lname;
	}



}