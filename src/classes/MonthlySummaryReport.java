package classes;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonthlySummaryReport {

    static void setConnection(Connection mockConn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int employeeId;
    public String employeeName;
    public String position;
    public String department;
    public double grossIncome;
    public String sssNumber;
    public double sssContribution;
    public String philhealthNumber;
    public double philhealthContribution;
    public String pagibigNumber;
    public double pagibigContribution;
    public String tinNumber;
    public double withholdingContribution;
    public double netPay;

    public MonthlySummaryReport(int employeeId, String employeeName, String position, String department, double grossIncome, String sssNumber, double sssContribution, String philhealthNumber, double philhealthContribution, String pagibigNumber, double pagibigContribution, String tinNumber, double withholdingContribution, double netPay) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.position = position;
        this.department = department;
        this.grossIncome = grossIncome;
        this.sssNumber = sssNumber;
        this.sssContribution = sssContribution;
        this.philhealthNumber = philhealthNumber;
        this.philhealthContribution = philhealthContribution;
        this.pagibigNumber = pagibigNumber;
        this.pagibigContribution = pagibigContribution;
        this.tinNumber = tinNumber;
        this.withholdingContribution = withholdingContribution;
        this.netPay = netPay;
    }

    public static List<MonthlySummaryReport> fetchEmployeeData() {
        List<MonthlySummaryReport> employeeDataList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payrollsystem_db", "root", "root");
            stmt = conn.createStatement();
            String query = "SELECT employeeId, employeeName, position, dailyRate, employeeSSSID, employeePhilhealthID, employeePagibigID, employeeBIRNumber FROM employee";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int employeeId = rs.getInt("employeeId");
                String employeeName = rs.getString("employeeName");
                String position = rs.getString("position");
                double dailyRate = rs.getDouble("dailyRate");
                double grossIncome = dailyRate * 22; // Assuming 22 working days in a month

                // Fetch contributions
                double sssContribution = getSSSContribution(conn, grossIncome);
                double philhealthContribution = getPhilHealthContribution(conn, grossIncome);
                double pagibigContribution = getPagIbigContribution(conn, grossIncome);
                double withholdingContribution = getWithholdingTax(conn, grossIncome);

                String sssNumber = rs.getString("employeeSSSID");
                String philhealthNumber = rs.getString("employeePhilhealthID");
                String pagibigNumber = rs.getString("employeePagibigID");
                String tinNumber = rs.getString("employeeBIRNumber");

                double netPay = grossIncome - (sssContribution + philhealthContribution + pagibigContribution + withholdingContribution);

                MonthlySummaryReport report = new MonthlySummaryReport(
                        employeeId, employeeName, position, "N/A", grossIncome, // Department set to "N/A"
                        sssNumber, sssContribution, philhealthNumber, philhealthContribution,
                        pagibigNumber, pagibigContribution, tinNumber, withholdingContribution, netPay);
                employeeDataList.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return employeeDataList;
    }

    private static double getSSSContribution(Connection conn, double grossIncome) throws SQLException {
        String query = "SELECT contribution FROM sss_matrix WHERE ? BETWEEN min_val AND max_val";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, grossIncome);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("contribution");
                }
            }
        }
        return 0;
    }

    private static double getPhilHealthContribution(Connection conn, double grossIncome) throws SQLException {
        String query = "SELECT premium_rate FROM philhealth_matrix WHERE ? BETWEEN monthly_salary_min AND monthly_salary_max";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, grossIncome);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("premium_rate");
                }
            }
        }
        return 0;
    }

    private static double getPagIbigContribution(Connection conn, double grossIncome) throws SQLException {
        String query = "SELECT total_rate FROM pagibig_matrix WHERE ? BETWEEN monthly_salary_min AND monthly_salary_max";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, grossIncome);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total_rate");
                }
            }
        }
        return 0;
    }

    private static double getWithholdingTax(Connection conn, double grossIncome) throws SQLException {
        String query = "SELECT tax_rate, excess_rate FROM withholding_matrix WHERE ? BETWEEN monthly_salary_min AND monthly_salary_max";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, grossIncome);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double taxRate = rs.getDouble("tax_rate");
                    double excessRate = rs.getDouble("excess_rate");
                    return taxRate + excessRate;
                }
            }
        }
        return 0;
    }

    public void generatePayrollReport(List<MonthlySummaryReport> reportData, String outputPath) {
        try {
            // Compile the JasperReport template
            JasperReport jasperReport = JasperCompileManager.compileReport("motorph_monthlysummarypayrollreport.jrxm");

            // Convert the list of MonthlySummaryReport instances to JRBeanCollectionDataSource
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);

            System.out.println("Report generated successfully.");
        } catch (JRException e) {
            e.printStackTrace();
            System.out.println("Error generating report: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Fetch employee data from the database
        List<MonthlySummaryReport> reportData = fetchEmployeeData();

        // Create an instance of MonthlySummaryReport and generate the report
        MonthlySummaryReport reportGenerator = new MonthlySummaryReport(0, null, null, null, 0, null, 0, null, 0, null, 0, null, 0, 0);
        reportGenerator.generatePayrollReport(reportData, "output/monthly_summary_report.pdf");
    }
}
