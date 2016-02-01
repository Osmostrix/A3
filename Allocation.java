// Ashish Naik
// COP 3330, Section 2
// Homework Allocation program: Allocation class

// Import necessary Java functions
import java.util.*;
import java.io.*;

// Import necesssary Java class files


// Create public class based on filename
public class Allocation {

		// Create a static method to be implemented
		public static void main(String[] args) {
			
			// Create temporary variables to store information from the HW_List.in file
			// Use strings for the last and first name
			// Use integers for the section number, date, and number of files
			// Use a string array to store the file names
			String temp1, temp2; 
			int call1, call2, call3;
			String[] temp3 = new String[100];
			
			// Create a try-catch region for dealing with files
			try {
				// Start Listing Homework Assignments
				
				// Create a new Scanner to intake information about homework assignments
				// Create a new PrintStream to send the information to the proper output file
				Scanner stuffing = new Scanner(new File("HW_List.in"));
				PrintStream fout = new PrintStream(new File("HW_Allocation.out"));
				
				// Create a Name variable to be filled later
				// Create a File variable to be filled later
				Name ours;
				Files study;
				
				// Use a Boolean variable to track successful addition of information to HomeworkQueue
				Boolean mark;
				
				// Create a new HomeworkQueue variable to store the assignments
				HomeworkQueue card = new HomeworkQueue();
				
				// Use an integer variable to keep track of the id numbers
				int i = 0;
				
				// While there is still information to scan, intake and store the informaton to a Homework assignment
				while (stuffing.hasNext()) {
					
					// Take in the last and first name and use it to define the Name variable
					temp1 = stuffing.next();
					temp2 = stuffing.next();
					ours = new Name(temp2, temp1);
					
					// Take in the section number and date
					call1 = stuffing.nextInt();
					call2 = stuffing.nextInt();
					
					// Take in the number of Files for this assignment
					// Construct a new Files variable
					call3 = stuffing.nextInt();
					study = new Files();
					
					// Intake all Files for this assignment
					for (int j = 0; j<call3; j++) {
						study.addFile(stuffing.next()); 
					}
					
					// Create a new Homework variable and define it using the information just taken in
					Homework fungus = new Homework(i+1, ours, call1, study, call2);
					
					// Attempt to add the Homework assignment to the HomeworkQueue and record the result of the attempt
					mark = card.addHomework(fungus);
					
					// Display the information from the new Homework assignment
					// Display the result of the attempt
					if (mark == true) {
						fout.printf("Homework %d: %d files(s) ", (i+1), call3);
						fout.printf("%s submitted by %s ", study.toString(), ours.toString());
						if (fungus.getDaysLate() < 0) {
							fout.printf("%d day(s) early for section %d added to the queue.", (fungus.getDaysLate()*(-1)), call1);
							fout.println("");
						}
						else if (fungus.getDaysLate() > 0) {
							fout.printf("%d days(s) late for section %d added to the queue.", (fungus.getDaysLate()), call1);
							fout.println("");
						}
						else if (fungus.getDaysLate() == 0) {
							fout.printf("on time for section %d added to the queue.", call1);
							fout.println("");
						}
						
					}
					else {
						fout.printf("Homework %d: %d files(s) ", (i+1), call3);
						fout.printf("%s submitted by %s ", study.toString(), ours.toString());
						if (fungus.getDaysLate() < 0) {
							fout.printf("%d day(s) early for section %d already in queue. Not added.", (fungus.getDaysLate()*(-1)), call1);
							fout.println("");
						}
						else if (fungus.getDaysLate() > 0) {
							fout.printf("%d days(s) late for section %d already in queue. Not added.", (fungus.getDaysLate()), call1);
							fout.println("");
						}
						else if (fungus.getDaysLate() == 0) {
							fout.printf("on time for section %d already in queue. Not added.", call1);
							fout.println("");
						}
					}
					
					// Create next ID number when finished with current Homework assignment
					i = i+1;
						
					
					
				}
				
				// When finished adding assignments to the HomeworkQueue, display completion statement
				// Start new line;
				fout.println("Finished processing homeworks!");
				fout.println("");
				
				// Start TA Queries
				
				// Create a new Scanner variable to intake information from TA_Queries.in file
				Scanner assign = new Scanner(new File("TA_Queries.in"));
				
				// Create integer variables to store the TA ID number, the TA's section, and the TA's number of assignment requests
				// Create a new Homework variable to take in the assignments from the HomeworkQueue
				int ta, section, requests;
				Homework sort;
				
				// While there is information to process, continue processing
				while (assign.hasNext() == true) {
					
					// Define the TA ID, section number, and requests variables
					ta = assign.nextInt();
					section = assign.nextInt();
					requests = assign.nextInt();
					
					// Use an integer to keep track of the files that are obtained 
					int counter = 0;
					
					// First, check if the section being accessed has any files to give.
					// If none are available, display an appropriate message to indicate this
					// If not process the requests
					if (card.sectionIsEmpty(section) == true) {
							fout.println("TA "+ta+" assigned no homeworks. Nothing in queue for section "+section+".");
					}
					else {
						
						// Process the number of assignments requested
						for (int j = 0; j < requests; j++) {
							
							// If the section runs out of assignments prematurely, immediately display the number of assignments obtained out of the total requested
							// Move on to next procedure
							if (card.sectionIsEmpty(section) == true) {
								fout.println("TA "+ta+" assigned "+counter+"/"+requests+" homework(s) from section "+section);
								break;
							}
							
							// Raise the counter to indicate that an assignment will be processed
							counter = counter+1;
						
							// Obtain an assignment from the specified section
							sort = card.getHomework(section);
							
							// Display TA ID and the assignment they obtained
							fout.printf("TA %d gets assigned homework %d: %d file(s) %s submitted by %s ", ta, sort.getId(), sort.getFiles().getNumberOfFile(), sort.getFiles().toString(), sort.getName().toString());
							
							// Display the number of days late the assignment is
							if (sort.getDaysLate() < 0) {
								fout.printf("%d day(s) early for section %d", (sort.getDaysLate()*(-1)), section);
								fout.println("");
							}
							else if (sort.getDaysLate() > 0) {
								fout.printf("%d days(s) late for section %d", (sort.getDaysLate()), section);
								fout.println("");
							}
							else if (sort.getDaysLate() == 0) {
								fout.printf("on time for section %d", section);
								fout.println("");
							}
						
				
						}
						
					}
					
					// If the requests are filled completely, display a statement to indicate this
					if (counter == requests) {
						fout.println("TA "+ta+" assigned all "+requests+" requested homework(s) from section "+section+".");
					}
					
					// Start new line
					fout.println("");
					
				}
				
				// When finished, display ending statement
				fout.println("Finished processing TA Queries! Exiting.");
				
				// Close files
				stuffing.close();
				assign.close();
				fout.close();
						
			}
			catch (FileNotFoundException ex) {}
			
			
		}
		
}