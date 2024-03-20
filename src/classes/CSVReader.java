/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CSVReader {
    
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("Choose an action:");
        System.out.println("1. Add Employee");
        System.out.println("2. Delete Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        try {
            switch (choice) {
                case "1":
                    addEmployee();
                    break;
                case "2":
                    System.out.println("Enter the Employee ID to delete:");
                    String idToDelete = scanner.nextLine();
                    deleteEmployee(idToDelete);
                    break;
                case "3":
                    updateEmployee();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}


//	public static void main(String[] args) {
//		
//		String file = "src\\classes\\Employee Details.csv";
//		BufferedReader reader = null;
//		String line = "";
//		
//		try {
//			reader = new BufferedReader(new FileReader(file));
//			while((line = reader.readLine()) != null) {
//				
//				String[] row = line.split(",");
//				
//				System.out.println("--------EMPLOYEE INFORMATION-------- ");
//				System.out.println("Employee Number: " + row[0]);
//				System.out.println("Last Name: " + row[1]);
//				System.out.println("First Name: " + row[2]);
//				System.out.println("Birth Date: " + row[3]);
//				System.out.println("Address: " + row[4]);
//				System.out.println("Position: " + row[11]);
//				System.out.println("Basic Salary: ₱" + row[13]);
//			
//				
//			
//				System.out.println("------------------------------");
//				
//				
//			}
//		}
//		
//		catch(IOException e) {
//			
//		}
//
//	}
        
        private static void input_data() throws IOException {
        try (// for adding data to our csv file
            Scanner details = new Scanner(System.in)) {
            System.out.println("Enter Employee ID");
            String emp_ID = details.nextLine();

            System.out.println("Enter Last Name");
            String emp_LastName = details.nextLine();
            
            System.out.println("Enter First Name");
            String emp_FirstName = details.nextLine();

            System.out.println("Enter Employee Birthdate");
            String emp_DOB = details.nextLine();

            System.out.println("Enter Employee Address");
            String emp_Address = details.nextLine();

            System.out.println("Enter Position");
            String position = details.nextLine();
            
            System.out.println("Enter Basic Salary");
            String basicSalary = details.nextLine();


            //Instantiating the CSVWriter class
            CSVWriter adwriter = new CSVWriter(new FileWriter("src\\classes\\Employee Details.csv",true), ',');
            //Writing data to a csv file
            String line1[] = {emp_ID, emp_LastName, emp_FirstName, emp_DOB, emp_Address, position, basicSalary};
            //Writing data to the csv file
            adwriter.writeNext(line1);
            //Flushing data from writer to file
            adwriter.flush();
        }

        System.out.println("Data saved");
        
        
   }
        private static void addEmployee() throws IOException {
            Scanner details = new Scanner(System.in);
            System.out.println("Enter Employee Details (ID, Last Name, First Name, Birthdate, Address, Position, Basic Salary) separated by commas:");
            String employeeDetails = details.nextLine();

            try (FileWriter fw = new FileWriter("src\\classes\\Employee Details.csv", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(employeeDetails);
            } catch (IOException e) {
                // Exception handling
            }

    System.out.println("Employee added successfully.");
   }
        private static void deleteEmployee(String employeeID) throws IOException {
            File inputFile = new File("src\\classes\\Employee Details.csv");
            File tempFile = new File("src\\classes\\myTempFile.csv");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    // Assuming the ID is the first value in the CSV
                    if (currentLine.startsWith(employeeID + ",")) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }

            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file.");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename the updated file.");
            } else {
                System.out.println("Employee deleted successfully.");
            }
   }
        private static void updateEmployee() throws IOException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Employee ID of the record you want to update:");
            String employeeID = scanner.nextLine();

            // Delete the employee
            deleteEmployee(employeeID);

            // Add a new employee record
            System.out.println("Enter the new information for the employee:");
            addEmployee();
       }
        
}