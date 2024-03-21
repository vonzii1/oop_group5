MotorPH Payroll System Documentation
Prepared and Presented by:

Gillian Veronique Mondero
Kersey Summer Sta. Ines
Vonne Carlo Pediengco
Bachelor of Science in information Technology
Term 2 Academic Year 2023-2024

TABLE OF CONTENTS

Section

 1	 	Introduction
User Roles
Navigating The User Guide									    
 2	 	Getting Started
Hardware Prerequisites
Software Prerequisites
Accessing The System								

 3		Using the Payroll System
Secure Login Procedures
Salary and Deduction Calculations
Leave Applications

 4		Technical Information
		Use Case Diagram
		Class Diagram
		Testing

Introduction
The MotorPh Payroll System simplifies payroll management for businesses, offering a user-friendly interface and robust functionalities. This section provides users with a brief overview of the system's purpose, emphasizing its role in streamlining payroll processes and ensuring accuracy. The intended audience includes HR personnel, administrators, and employees involved in payroll-related tasks. To effectively utilize the system, users are encouraged to navigate this user guide, which provides comprehensive instructions and insights.

User Roles
The MotorPh Payroll System defines distinct user roles to streamline access and ensure security. These roles include employees, HR personnel, administrators, and IT staff. Each role is assigned specific permissions and responsibilities within the system to facilitate efficient payroll management and data security.

Navigating The User Guide
Navigating the MotorPh Payroll System user guide is simple and intuitive. Users can utilize the table of contents or search functionality to locate specific topics of interest. The guide is organized into sections, covering various aspects of system usage, including user roles, system access, and payroll functionalities. Clear headings and subheadings aid in easy navigation and comprehension of the content.

Getting Started
To begin using the MotorPh Payroll System, users need to ensure they meet the fundamental requirements outlined in this section. Hardware prerequisites may include a computer or mobile device with internet connectivity, while software prerequisites may involve a compatible web browser or dedicated application. Accessing the system can typically be done through a web link provided by the organization or via a dedicated application downloaded from an app store.



Hardware Prerequisites
Users need to ensure they have access to compatible hardware to use the MotorPh Payroll System effectively. This may include a desktop computer, laptop, tablet, or smartphone with internet connectivity. Adequate hardware ensures smooth system operation and optimal user experience.

Software Prerequisites
In addition to hardware requirements, users must also meet certain software prerequisites to access the MotorPh Payroll System. This may involve using a supported web browser, such as Google Chrome, Mozilla Firefox, or Safari. Alternatively, users may need to download and install a dedicated application from the relevant app store, depending on the system's configuration.

Accessing The System
Once users have met the necessary hardware and software prerequisites, accessing the MotorPh Payroll System is straightforward. Users can typically access the system through a web link provided by their organization or by downloading and installing a dedicated application. Upon accessing the system, users will be prompted to log in using their unique credentials, granting them access to the system's features based on their assigned role and permissions.

Using The Payroll System

Secure Login Procedures
The process of securely logging into the MotorPh Payroll System begins with the employee and admin opening the system and navigating to the login screen prompted to choose what role they are, either employee or admin. Here, they are prompted to enter their unique credentials, which include an email address and a password. These details are then submitted to the system via the Login class, which functions to verify the entered credentials against the stored user data in the system's database. If the credentials are found to be correct and match an existing user profile, the system grants access to the user, allowing them to proceed to the main dashboard of the payroll system. This process ensures that only authorized personnel can access sensitive payroll information and functionality, safeguarding employee data and company resources.

Salary and Deduction Calculations
The calculation of salaries and deductions within the MotorPh Payroll System is a comprehensive process that takes into account various factors to ensure accurate payroll management. Initially, the system calculates the monthly salary of each employee using the Finance composition class. This involves determining the gross salary based on the hourly rate multiplied by the hours worked, as recorded in the attendance system. Following this, the system computes the applicable deductions for each employee through the Deductions class. This includes calculating the contributions for SSS, PhilHealth, withholding tax, and PagIbig, based on the current rates and the employee's gross salary. Additionally, the Allowance class calculates any applicable allowances, such as rice subsidy, phone allowance, and clothing allowance, adding these to the gross salary. The net salary is then derived by subtracting the total deductions from the gross salary plus allowances, providing a clear and accurate reflection of the employee's take-home pay for the month.

Leave Applications
Applying for leave within the MotorPh Payroll System is designed to be a straightforward yet flexible process to accommodate the diverse needs of the workforce. An employee wishing to apply for leave starts by accessing the Request class interface, where they can select the type of request they wish to make, in this case, a leave request. Within the Leave class, the employee specifies the leave type (vacation, sick, maternity/paternity, bereavement, leave without pay) and inputs the required details such as the start date, end date, and reason for the leave. This information is then submitted for processing within the system. The request goes through a workflow where it can be reviewed, approved, or rejected by the HR personnel. Upon approval, the leave is recorded, and the employee's leave balance is updated accordingly. This system ensures a transparent and efficient process for managing leave applications, allowing for easy tracking and administration of employee leave entitlements.

Technical Information
<This section provides details and insights into the inner workings of the payroll system. It includes diagrams, such as use case and class diagrams, to illustrate key technical aspects of the system's design and structure. Additionally, this section references testing documentation, including test cases and results, to highlight the quality assurance processes undertaken during development.>

Use Case Diagram


Figure 1. MotorPH’s Payroll system

The MotorPH Payroll System's use case diagram outlines the interactions and functionalities of its four entities succinctly. Employees can log in with error display upon password verification, update personal details, clock in/out, request leave with error handling, request overtime, and view payslips. The HR entity manages employee data including additions, updates, and deletions, and approves leave requests. The Finance department handles payroll processing, including deductions, timesheet validation, overtime pay, bonuses, and payroll requests, along with audit trails and data cancellation. The IT department maintains system integrity through updates, backups, and scalability upgrades, ensuring data security with access controls, audits, and incident response planning.

Class Diagram


Figure 1.1 MotorPH’s Payroll System Class Diagram
The MotorPH class diagram outlines the structure and interactions within the system. It includes the Employee entity with attributes such as employee ID, name, contact details, status, position, and salary information, along with methods for retrieving employee details and accessing payslips. Employees can log in with email address and password verification. They can mark attendance, submit various requests (e.g., leave, overtime), and access different types of leave options. Employee inherits from HR, IT Department, and Finance Department, each providing relevant functionalities such as employee management, system maintenance, data security, payroll processing, and financial reporting. The system generates payroll reports and monthly summary reports, detailing gross and net incomes, contributions, taxes, allowances, and deductions. Salaries include hourly rates, gross and net salary calculations, and allowances, while deductions encompass contributions for SSS, PhilHealth, withholding tax, and Pag-IBIG, with methods for calculating monthly deductions.

Testing







