package ma.ensi.dao;

import ma.ensi.dao.DBConnection;
import ma.ensi.model.Experiences;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExperienceDAO {

    // SQL Queries
    private static final String SELECT_ALL_CANDIDATES = "SELECT DISTINCT candidate_name FROM experiences";
    private static final String SELECT_EXPERIENCES_BY_CANDIDATE = "SELECT * FROM experiences WHERE candidate_name=?";
    private static final String FILTER_EXPERIENCES = "SELECT * FROM experiences WHERE 1=1";

    // ✅ Get All Unique Candidates
    public List<String> getAllCandidates() {
        List<String> candidates = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_CANDIDATES);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                candidates.add(rs.getString("candidate_name"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    // ✅ Get Experiences for a Specific Candidate
    public List<Experiences> getExperiencesByCandidate(String candidateName) {
        List<Experiences> experiences = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_EXPERIENCES_BY_CANDIDATE)) {

            pstmt.setString(1, candidateName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    experiences.add(new Experiences(
                            rs.getInt("id"),
                            rs.getString("candidate_name"),
                            rs.getString("role"),
                            rs.getString("start_date"),
                            rs.getString("end_date"),
                            rs.getString("description")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return experiences;
    }

    // ✅ Get Filtered Experiences (Main Filter Function)
    public List<Experiences> getFilteredExperiences(String candidate, String role, String status, String startDate, String endDate) {
        List<Experiences> experiences = new ArrayList<>();
        StringBuilder query = new StringBuilder(FILTER_EXPERIENCES);
        List<Object> parameters = new ArrayList<>();

        // Add filters dynamically
        if (candidate != null && !candidate.isEmpty()) {
            query.append(" AND candidate_name = ?");
            parameters.add(candidate);
        }
        if (role != null && !role.isEmpty()) {
            query.append(" AND role = ?");
            parameters.add(role);
        }
        if (startDate != null && !startDate.isEmpty()) {
            query.append(" AND start_date >= ?");
            parameters.add(startDate);
        }
        if (endDate != null && !endDate.isEmpty()) {
            query.append(" AND end_date <= ?");
            parameters.add(endDate);
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query.toString())) {

            // Set parameters dynamically
            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    experiences.add(new Experiences(
                            rs.getInt("id"),
                            rs.getString("candidate_name"),
                            rs.getString("role"),
                            rs.getString("start_date"),
                            rs.getString("end_date"),
                            rs.getString("description")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return experiences;
    }
}
