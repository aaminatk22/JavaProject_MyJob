package ma.ensi.dao;

import ma.ensi.model.VerificationRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VerificationRequestDAO {

    public void saveVerificationRequest(VerificationRequest request) throws SQLException {
        String query = "INSERT INTO verification_request (id_candidat, university_name, document_type, status, date_requested) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, request.getIdCandidat());
            ps.setString(2, request.getUniversityName());
            ps.setString(3, request.getDocumentType());
            ps.setString(4, request.getStatus());

            ps.executeUpdate();
        }
    }
}
