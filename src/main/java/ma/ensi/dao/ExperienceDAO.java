package ma.ensi.dao;

import ma.ensi.model.Experience;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExperienceDAO {

    public void saveExperience(Experience experience) throws SQLException {
        String sql = "INSERT INTO experience (id_portfolio, titre, entreprise, date_debut, date_fin, description) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, experience.getIdPortfolio());
            stmt.setString(2, experience.getTitre());
            stmt.setString(3, experience.getEntreprise());
            stmt.setDate(4, experience.getDateDebut());
            stmt.setDate(5, experience.getDateFin());
            stmt.setString(6, experience.getDescription());

            stmt.executeUpdate();
        }
    }

    public void addExperience(Experience experience) throws Exception {
        String query = "INSERT INTO experience (id_portfolio, titre, entreprise, date_debut, date_fin, description) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, experience.getIdPortfolio());
            preparedStatement.setString(2, experience.getTitre());
            preparedStatement.setString(3, experience.getEntreprise());
            preparedStatement.setDate(4, experience.getDateDebut());
            preparedStatement.setDate(5, experience.getDateFin());
            preparedStatement.setString(6, experience.getDescription());
            preparedStatement.executeUpdate();
        }
    }

}
