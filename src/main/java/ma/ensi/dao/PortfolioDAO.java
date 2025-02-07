package ma.ensi.dao;

import ma.ensi.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Portfolio getPortfolioByUserId(int userId) {
        String sql = "SELECT * FROM portfolio WHERE id_utilisateur = ?";
        Portfolio portfolio = null;

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int portfolioId = resultSet.getInt("id_portfolio");
                String description = resultSet.getString("description");
                portfolio = new Portfolio(portfolioId, userId, description, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

                // Retrieve related competences, experiences, projects, and documents
                portfolio.setCompetences(getCompetencesByPortfolioId(portfolioId));
                portfolio.setExperiences(getExperiencesByPortfolioId(portfolioId));
                portfolio.setProjets(getProjetsByPortfolioId(portfolioId));
                portfolio.setDocuments(getDocumentsByPortfolioId(portfolioId)); // Set documents
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return portfolio;
    }



    private List<Competence> getCompetencesByPortfolioId(int portfolioId) {
        List<Competence> competences = new ArrayList<>();
        String sql = "SELECT * FROM competence WHERE id_portfolio = ?";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, portfolioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Competence competence = new Competence();
                competence.setIdPortfolio(portfolioId);
                competence.setNom(resultSet.getString("nom"));
                competence.setNiveau(resultSet.getString("niveau"));
                competences.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competences;
    }

    private List<Experience> getExperiencesByPortfolioId(int portfolioId) {
        List<Experience> experiences = new ArrayList<>();
        String sql = "SELECT * FROM experience WHERE id_portfolio = ?";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, portfolioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Experience experience = new Experience();
                experience.setIdPortfolio(portfolioId);
                experience.setTitre(resultSet.getString("titre"));
                experience.setEntreprise(resultSet.getString("entreprise"));
                experience.setDateDebut(resultSet.getDate("date_debut"));
                experience.setDateFin(resultSet.getDate("date_fin"));
                experience.setDescription(resultSet.getString("description"));
                experiences.add(experience);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experiences;
    }

    private List<Projet> getProjetsByPortfolioId(int portfolioId) {
        List<Projet> projets = new ArrayList<>();
        String sql = "SELECT * FROM projet WHERE id_portfolio = ?";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, portfolioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Projet projet = new Projet();
                projet.setIdPortfolio(portfolioId);
                projet.setTitre(resultSet.getString("titre"));
                projet.setDescription(resultSet.getString("description"));
                projet.setTechnologie(resultSet.getString("technologie"));
                projets.add(projet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projets;
    }



    public List<Document> getDocumentsByPortfolioId(int portfolioId) {
        List<Document> documents = new ArrayList<>();
        String sql = "SELECT * FROM document WHERE id_portfolio = ?";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, portfolioId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Document document = new Document();
                document.setIdPortfolio(resultSet.getInt("id_portfolio"));
                document.setType(resultSet.getString("type"));
                document.setFilePath(resultSet.getString("file_path"));
                documents.add(document);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

}
