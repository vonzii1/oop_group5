/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

//This sub class is implementing INHERITANCE from the base class Employee.

import static classes.Allowance.calculateMonthlyAllowance;
import static classes.Deduction.calculateMonthlyDeduction;
import static classes.Earning.calculateGrossIncome;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;


public class Finance extends Employee {
    
    public int payslipNumber;
    public int emp_ID;
    public String emp_FullName;
    public Date periodStart;
    public Date periodEnd;
    public String emp_Department;
    public double dailyRate;
    public int daysWorked;
    public double overtimePay;
    public double riceSubsidy;
    public double phoneAllowance;
    public double clothingAllowance;
    public double sssContribution;
    public double philhealthContribution;
    public double pagibigContribution;
    public double withholdingContribution;
    public double earning = calculateGrossIncome( dailyRate, daysWorked, overtimePay);
    public double allowance = calculateMonthlyAllowance(riceSubsidy, phoneAllowance, clothingAllowance);
    public double deduction = calculateMonthlyDeduction(sssContribution, philhealthContribution, pagibigContribution, withholdingContribution);


    public Finance(double earning, double allowance, double deduction) {
        this.earning = earning;
        this.allowance = allowance;
        this.deduction = deduction;
    }

    Finance(int payslipNumber, int emp_ID, String emp_FullName, Date periodStart, Date periodEnd, String emp_Department) {
       
    }

    public Finance(int payslipNumber, int emp_ID, String emp_FullName, Date periodStart, Date periodEnd, String emp_Department, double dailyRate, int daysWorked, double overtimePay, double riceSubsidy, double phoneAllowance, double clothingAllowance, double sssContribution, double philhealthContribution, double pagibigContribution, double withholdingContribution) {
        this.payslipNumber = payslipNumber;
        this.emp_ID = emp_ID;
        this.emp_FullName = emp_FullName;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.emp_Department = emp_Department;
        this.dailyRate = dailyRate;
        this.daysWorked = daysWorked;
        this.overtimePay = overtimePay;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.sssContribution = sssContribution;
        this.philhealthContribution = philhealthContribution;
        this.pagibigContribution = pagibigContribution;
        this.withholdingContribution = withholdingContribution;
    }

    void calculateMonthlySalary(int payslipNumber, int emp_ID, String emp_FullName, Date periodStart, Date periodEnd, String emp_Department, double earning, double allowance, double deduction) {
        System.out.println("Payslip No.: " + payslipNumber + "\n" +
                "Employee ID: " + emp_ID + "\n" +
                "Employee Name: " + emp_FullName + "\n" +
                "Period Start Date: " + periodStart + "\n" +
                "Period End Date: " + periodEnd + "\n" +
                "Employee Department: " + emp_Department);
    }


}
    
    class Earning extends Finance{

    double monthlyRate;
    double dailyRate;
    int daysWorked;
    double overtimePay;

    public Earning(int payslipNumber, int emp_ID, String emp_FullName, Date periodStart, Date periodEnd, String emp_Department) {
        super(payslipNumber, emp_ID, emp_FullName, periodStart, periodEnd, emp_Department);
    }

    public Earning(int payslipNumber, int emp_ID, String emp_FullName, Date periodStart, Date periodEnd, String emp_Department, double monthlyRate, double dailyRate, int daysWorked, double overtimePay) {
        super(payslipNumber, emp_ID, emp_FullName, periodStart, periodEnd, emp_Department);
        this.monthlyRate = monthlyRate;
        this.dailyRate = dailyRate;
        this.daysWorked = daysWorked;
        this.overtimePay = overtimePay;
    }
    public static double calculateGrossIncome(double dailyRate, int daysWorked, double overtimePay){
        double grossIncome = ((dailyRate * daysWorked) + overtimePay);
        return grossIncome;
    }
}
class Allowance extends Finance{

    double riceSubsidy;
    double phoneAllowance;
    double clothingAllowance;

    public Allowance(int payslipNumber, int emp_ID, String emp_FullName, Date periodStart, Date periodEnd, String emp_Department) {
        super(payslipNumber, emp_ID, emp_FullName, periodStart, periodEnd, emp_Department);
    }

    public Allowance(int payslipNumber, int emp_ID, String emp_FullName, Date periodStart, Date periodEnd, String emp_Department, double riceSubsidy, double phoneAllowance, double clothingAllowance) {
        super(payslipNumber, emp_ID, emp_FullName, periodStart, periodEnd, emp_Department);
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
    }
    public static double calculateMonthlyAllowance(double riceSubsidy, double phoneAllowance, double clothingAllowance){
        double monthlyAllowance = (riceSubsidy + phoneAllowance + clothingAllowance);
        return monthlyAllowance;
    }
}
class Deduction extends Finance {

	public static void main(String[] args) {
		
		String file = "src\\motorPH\\Employee Details.csv";
		BufferedReader reader = null;
		String line = "";
		int basicSalary; 
		double sssContribution, philhealthContribution, pagibigContibution, taxableIncome, withholdingTax;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null) {
				
				String[] row = line.split(",");
				
				System.out.println("Employee Number: " + row[0]);
				System.out.println("Last Name: " + row[1]);
				System.out.println("First Name: " + row[2]);
				
				basicSalary = Integer.parseInt(row[16]);
				
			    NumberFormat formatter = NumberFormat.getInstance();
			    String formattedNumber = formatter.format(basicSalary);
				
				System.out.println("Basic salary: ₱" + formattedNumber);
				
				
				sssContribution = SSSContribution(basicSalary);
				philhealthContribution = PhilHealthContribution(basicSalary);
				pagibigContibution = PagIbigContribution(basicSalary);
				
				taxableIncome = basicSalary - (sssContribution + philhealthContribution + pagibigContibution); 
				
				withholdingTax = WithholdingtaxCalculation(taxableIncome);
				
				System.out.println("SSS Contribution: ₱" + sssContribution);
				System.out.println("PhilHealth Contribution: ₱" + philhealthContribution);
				System.out.println("Pag Ibig Contribution: ₱" + pagibigContibution);
				System.out.println("Taxable Income: ₱" + taxableIncome);
				System.out.println("Withholding Tax: ₱" + withholdingTax);
				System.out.println("------------------------------");
				
				
			}
		}
		
		catch(IOException | NumberFormatException e) {
			
		}

	}

	
	static double SSSContribution(int arg1) {
	
		double bracketRange = 499.99, baseBracket = 3250, contributionIterate = 22.5;
		double maxBracket = baseBracket + bracketRange , minBracket = baseBracket;
		double contributionAmount = 135;
		
		for (int i = 1 ; i <= 42 ; i++) {
			if (arg1 <= baseBracket) {
				return 135;
			} else if (arg1 >= minBracket && arg1 <= maxBracket ) {
				contributionAmount = contributionAmount + contributionIterate;
				return contributionAmount;
			}
			minBracket = maxBracket;
			maxBracket = maxBracket + bracketRange;
		}
		return 1125;
	}
	
	static double PhilHealthContribution(int arg1) {
		
		return (arg1 * 0.03) * 0.5;
		
	}
	
	static double PagIbigContribution(int arg1) {
		
	    if (arg1 < 1000) {
	        	return 0;
	      } else if (arg1 >= 1000 && arg1 <= 1500) {
	    	  return arg1 * .01;
	      } else {
	    	  if(arg1 * .02 < 100) {
	    		  return arg1 * .02;
	    	  } else {
	    		  return 100;
	    	  }
	      }
		
	}
	
	static double WithholdingtaxCalculation(double arg1) {
		
		 if (arg1 <= 20832) {
		      return 0;
		    } else if (arg1 >= 20833 && arg1 < 33333) {
		    	return (arg1 - 20833) * 0.2;
		    } else if (arg1  >= 33333 && arg1 < 66667) {
		    	return (arg1 - 33333) * 0.25 + 2500;
		    } else if (arg1  >= 66667 && arg1 < 166667) {
		    	return (arg1 - 66667) * 0.3 + 10833;
		    } else if (arg1  >= 166667 && arg1 < 666667) {
		    	return (arg1 - 166667) * 0.32 + 40833.33;
		    } else {
		    	return (arg1 - 666667) * 0.35 + 200833.33;
		    }
		
	}
        
        public static double calculateMonthlyDeduction(double sssContribution, double philhealthContribution, double pagibigContribution, double withholdingContribution){
            
        double monthlyDeductions = (sssContribution + philhealthContribution + pagibigContribution + withholdingContribution);
        return monthlyDeductions;
    }

    public Deduction(int payslipNumber, int emp_ID, String emp_FullName, Date periodStart, Date periodEnd, String emp_Department) {
        super(payslipNumber, emp_ID, emp_FullName, periodStart, periodEnd, emp_Department);
    }
    
}
   

