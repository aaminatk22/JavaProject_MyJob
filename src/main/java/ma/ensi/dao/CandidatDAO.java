package ma.ensi.dao;

import ma.ensi.model.Candidat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatDAO {
    public void addCandidat(Candidat candidat) {
        // Implementation
    }

    public Candidat getCandidat(int id) {
        // Implementation
        return null;
    }

    public List<Candidat> getAllCandidats() throws SQLException {
        List<Candidat> candidats = new ArrayList<>();
        String sql = "SELECT * FROM candidats";
        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) { Candidat candidat = new Candidat();
                    candidat.setId(rs.getInt("id"));
                    candidat.setFirstName(rs.getString("first_name"));
                    candidat.setLastName(rs.getString("last_name"));
                    candidat.setEmail(rs.getString("email"));
                    candidat.setPassword(rs.getString("password"));
                    candidat.setResume(rs.getString("resume"));
                    candidat.setProfilePicture(rs.getString("profile_picture"));
                    candidat.setBio(rs.getString("bio"));
                    candidat.setCreatedAt(rs.getTimestamp("created_at"));
                    candidats.add(candidat); } } } return candidats; }

    public void updateCandidat(Candidat candidat) throws SQLException {
        String sql =
                "UPDATE candidats SET first_name = ?, last_name = ?, email = ?, password = ?, resume = ?, profile_picture = ?, bio = ?, created_at = ? WHERE id = ?";
        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, candidat.getFirstName());
            pstmt.setString(2, candidat.getLastName());
            pstmt.setString(3, candidat.getEmail());
            pstmt.setString(4, candidat.getPassword());
            pstmt.setString(5, candidat.getResume());
            pstmt.setString(6, candidat.getProfilePicture());
            pstmt.setString(7, candidat.getBio());
            pstmt.setTimestamp(8, candidat.getCreatedAt());
            pstmt.setInt(9, candidat.getId()); pstmt.executeUpdate();
        } }

    public void deleteCandidat(int id) throws SQLException {
        String sql = "DELETE FROM candidats WHERE id = ?";
        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } }

}
