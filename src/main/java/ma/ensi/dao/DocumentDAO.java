package ma.ensi.dao;

import ma.ensi.model.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DocumentDAO {

    public void saveDocument(Document document) throws SQLException {
        String query = "INSERT INTO document (id_portfolio, type, file_path) VALUES (?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, document.getIdPortfolio()); // Link the document to a portfolio
            ps.setString(2, document.getType());
            ps.setString(3, document.getFilePath());

            ps.executeUpdate();
            System.out.println("Document saved successfully.");
        }
    }

    public void addDocument(Document document) throws Exception {
        String query = "INSERT INTO document (id_portfolio, type_document, file_path) VALUES (?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, document.getIdPortfolio());
            preparedStatement.setString(2, document.getType());
            preparedStatement.setString(3, document.getFilePath());
            preparedStatement.executeUpdate();
        }
    }

}
