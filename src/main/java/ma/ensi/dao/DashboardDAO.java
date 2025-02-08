package ma.ensi.dao;

import ma.ensi.dao.DBConnection;
import java.sql.*;
import java.util.*;

public class DashboardDAO {

    public Map<String, Object> getDashboardStatistics() {
        Map<String, Object> stats = new HashMap<>();

        try (Connection conn = DBConnection.getConnection()) {

            stats.put("totalApplications", getCount(conn, "SELECT COUNT(*) FROM examinations"));
            stats.put("approvedApplications", getCount(conn, "SELECT COUNT(*) FROM examinations WHERE status='approved'"));
            stats.put("userLogins", getCount(conn, "SELECT COUNT(*) FROM users WHERE last_login IS NOT NULL"));
            stats.put("jobListingsViewed", getCount(conn, "SELECT SUM(views) FROM job_listings"));

            stats.put("recentUsers", getRecentUsers(conn));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stats;
    }

    private int getCount(Connection conn, String query) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    private List<Map<String, String>> getRecentUsers(Connection conn) throws SQLException {
        List<Map<String, String>> users = new ArrayList<>();
        String query = "SELECT name, registration_date, status FROM users ORDER BY registration_date DESC LIMIT 5";

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("name", rs.getString("name"));
                user.put("registrationDate", rs.getString("registration_date"));
                user.put("status", rs.getString("status"));
                users.add(user);
            }
        }
        return users;
    }
}
