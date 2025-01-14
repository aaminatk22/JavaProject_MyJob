package ma.ensi.dao;

import ma.ensi.model.Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjetDAO {

    public void saveProjet(Projet projet) throws SQLException {
        String sql = "INSERT INTO projet (id_portfolio, titre, description, technologie) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, projet.getIdPortfolio());
            stmt.setString(2, projet.getTitre());
            stmt.setString(3, projet.getDescription());
            stmt.setString(4, projet.getTechnologie());

            stmt.executeUpdate();
        }
    }

    public void addProjet(Projet projet) throws Exception {
        String query = "INSERT INTO projet (id_portfolio, titre, description, technologie) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, projet.getIdPortfolio());
            preparedStatement.setString(2, projet.getTitre());
            preparedStatement.setString(3, projet.getDescription());
            preparedStatement.setString(4, projet.getTechnologie());
            preparedStatement.executeUpdate();
        }
    }

}
