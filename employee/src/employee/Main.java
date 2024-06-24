package employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        LOPCalculator lopCalculator = new LOPCalculator();

        try {
         
            List<Employee> employees = employeeDAO.getAllEmployees();
            for (Employee employee : employees) {
                System.out.println(employee.getName() + " - " + employee.getEmployeeType());
            }

            attendanceDAO.logAttendance(1, "09:15:00", "18:00:00");
            attendanceDAO.logAttendance(1, "09:05:00", "18:00:00");
            attendanceDAO.logAttendance(1, "09:10:00", "18:00:00");

          
            Map<Integer, Double> lopMap = lopCalculator.calculateLOP();
            for (Map.Entry<Integer, Double> entry : lopMap.entrySet()) {
                System.out.println("Employee ID: " + entry.getKey() + ", LOP: " + entry.getValue());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
