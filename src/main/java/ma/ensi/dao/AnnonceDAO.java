package ma.ensi.dao;

import ma.ensi.model.Annonce;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnonceDAO {

    // Use the existing ConnexionBDD class to get a connection
    public List<Annonce> getAllAnnonces() {
        List<Annonce> annonces = new ArrayList<>();
        try (Connection connection = ConnexionBDD.getConnection()) {  // Auto-close connection with try-with-resources
            String sql = "SELECT * FROM annonce";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Annonce annonce = new Annonce();
                annonce.setIdAnnonce(resultSet.getInt("id_annonce"));
                annonce.setTitre(resultSet.getString("titre"));
                annonce.setTypeAnnonce(resultSet.getString("type_annonce"));
                annonce.setDescription(resultSet.getString("description"));
                annonce.setDatePublication(resultSet.getString("date_publication"));
                annonce.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                annonces.add(annonce);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching annonces: " + e.getMessage());
            e.printStackTrace();
        }
        return annonces;
    }

    // Fetch an annonce by its ID
    public Annonce getAnnonceById(int id) {
        Annonce annonce = null;
        try (Connection connection = ConnexionBDD.getConnection()) {
            String sql = "SELECT * FROM annonce WHERE id_annonce = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                annonce = new Annonce();
                annonce.setIdAnnonce(resultSet.getInt("id_annonce"));
                annonce.setTitre(resultSet.getString("titre"));
                annonce.setTypeAnnonce(resultSet.getString("type_annonce"));
                annonce.setDescription(resultSet.getString("description"));
                annonce.setDatePublication(resultSet.getString("date_publication"));
                annonce.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching annonce by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return annonce;
    }

    // Add a new annonce
    public boolean addAnnonce(Annonce annonce) {
        String query = "INSERT INTO annonce (titre, type_annonce, description, id_utilisateur) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, annonce.getTitre());
            preparedStatement.setString(2, annonce.getTypeAnnonce());
            preparedStatement.setString(3, annonce.getDescription());
            preparedStatement.setInt(4, annonce.getIdUtilisateur()); // Set id_utilisateur

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if the insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    // Update an existing annonce
    public boolean updateAnnonce(Annonce annonce) {
        String sql = "UPDATE annonce SET titre = ?, type_annonce = ?, description = ?, id_utilisateur = ? WHERE id_annonce = ?";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Validate inputs
            if (annonce.getTitre() == null || annonce.getTypeAnnonce() == null || annonce.getDescription() == null) {
                throw new IllegalArgumentException("Fields titre, type_annonce, and description cannot be null");
            }

            // Set parameters
            preparedStatement.setString(1, annonce.getTitre());
            preparedStatement.setString(2, annonce.getTypeAnnonce());
            preparedStatement.setString(3, annonce.getDescription());
            preparedStatement.setInt(4, annonce.getIdUtilisateur());
            preparedStatement.setInt(5, annonce.getIdAnnonce());

            // Execute update
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating annonce: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Delete an annonce by ID
    public boolean deleteAnnonce(int id) {
        try (Connection connection = ConnexionBDD.getConnection()) {
            String sql = "DELETE FROM annonce WHERE id_annonce = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting annonce: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<Annonce> getAnnoncesByRecruteurId(int idUtilisateur) {
        List<Annonce> annonces = new ArrayList<>();
        String query = "SELECT * FROM annonce WHERE id_utilisateur = ?";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idUtilisateur);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Annonce annonce = new Annonce();
                annonce.setIdAnnonce(resultSet.getInt("id_annonce"));
                annonce.setTitre(resultSet.getString("titre"));
                annonce.setDescription(resultSet.getString("type_annonce"));
                annonce.setTypeAnnonce(resultSet.getString("description"));
                annonce.setDatePublication(String.valueOf(resultSet.getDate("date_publication")));
                annonce.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                annonces.add(annonce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return annonces;
    }
}
