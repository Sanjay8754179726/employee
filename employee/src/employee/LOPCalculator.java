package employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LOPCalculator {
    public Map<Integer, Double> calculateLOP() throws SQLException {
        Map<Integer, Double> lopMap = new HashMap<>();

        String query = "SELECT employee_id, COUNT(*) AS late_early_count " +
                       "FROM Attendance " +
                       "WHERE (login_time > '09:00:00' OR logout_time < '18:00:00') " +
                       "GROUP BY employee_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                int count = rs.getInt("late_early_count");
                double lop = (count / 3) * 0.5; 
                lopMap.put(employeeId, lop);
            }
        }

        return lopMap;
    }
}
