package ma.ensi.dao;

import ma.ensi.model.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DocumentDAO {

    public void saveDocument(Document document) throws SQLException {
        String sql = "INSERT INTO document (id_utilisateur, type, file_path) VALUES (?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, document.getIdUser());
            stmt.setString(2, document.getType());
            stmt.setString(3, document.getFilePath());

            stmt.executeUpdate();
        }
    }
}
