package ma.ensi.dao;

import ma.ensi.model.*;

import java.sql.*;

public class PortfolioDAO {

    public int createPortfolio(Portfolio portfolio) {
        String sql = "INSERT INTO portfolio (id_utilisateur, description) VALUES (?, ?) RETURNING id_portfolio";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, portfolio.getIdUtilisateur());
            preparedStatement.setString(2, portfolio.getDescription());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_portfolio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void addCompetence(Competence competence) {
        String sql = "INSERT INTO competence (id_portfolio, nom, niveau) VALUES (?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, competence.getIdPortfolio());
            preparedStatement.setString(2, competence.getNom());
            preparedStatement.setString(3, competence.getNiveau());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addExperience(Experience experience) {
        String sql = "INSERT INTO experience (id_portfolio, titre, entreprise, date_debut, date_fin, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, experience.getIdPortfolio());
            preparedStatement.setString(2, experience.getTitre());
            preparedStatement.setString(3, experience.getEntreprise());
            preparedStatement.setDate(4, experience.getDateDebut());
            preparedStatement.setDate(5, experience.getDateFin());
            preparedStatement.setString(6, experience.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProjet(Projet projet) {
        String sql = "INSERT INTO projet (id_portfolio, titre, description, technologie) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, projet.getIdPortfolio());
            preparedStatement.setString(2, projet.getTitre());
            preparedStatement.setString(3, projet.getDescription());
            preparedStatement.setString(4, projet.getTechnologie());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
