package employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AttendanceDAO {
    public void logAttendance(int employeeId, String loginTime, String logoutTime) throws SQLException {
        String query = "INSERT INTO Attendance (employee_id, login_time, logout_time) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, employeeId);
            stmt.setTime(2, java.sql.Time.valueOf(loginTime));
            stmt.setTime(3, java.sql.Time.valueOf(logoutTime));
            stmt.executeUpdate();
        }
    }

}
