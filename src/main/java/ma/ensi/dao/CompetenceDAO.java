package ma.ensi.dao;

import ma.ensi.model.Competence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompetenceDAO {

    public void saveCompetence(Competence competence) throws SQLException {
        String sql = "INSERT INTO competence (id_portfolio, nom, niveau) VALUES (?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, competence.getIdPortfolio());
            stmt.setString(2, competence.getNom());
            stmt.setString(3, competence.getNiveau());

            stmt.executeUpdate();
        }
    }


    public void addCompetence(Competence competence) throws Exception {
        String query = "INSERT INTO competence (id_portfolio, nom, niveau) VALUES (?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, competence.getIdPortfolio());
            preparedStatement.setString(2, competence.getNom());
            preparedStatement.setString(3, competence.getNiveau());
            preparedStatement.executeUpdate();
        }
    }

}
