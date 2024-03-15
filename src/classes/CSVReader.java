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
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

	public static void main(String[] args) {
		
		String file = "src\\classes\\Employee Details.csv";
		BufferedReader reader = null;
		String line = "";
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null) {
				
				String[] row = line.split(",");
				
				System.out.println("--------EMPLOYEE INFORMATION-------- ");
				System.out.println("Employee Number: " + row[0]);
				System.out.println("Last Name: " + row[1]);
				System.out.println("First Name: " + row[2]);
				System.out.println("Birth Date: " + row[3]);
				System.out.println("Address: " + row[4]);
				System.out.println("Position: " + row[11]);
				System.out.println("Basic Salary: ₱" + row[13]);
			
				
			
				System.out.println("------------------------------");
				
				
			}
		}
		
		catch(IOException e) {
			
		}

	}

}