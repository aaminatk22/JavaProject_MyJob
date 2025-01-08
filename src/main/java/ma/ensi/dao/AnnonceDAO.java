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
        try (Connection connection = ConnexionBDD.getConnection()) {
            String sql = "INSERT INTO annonce (titre, type_annonce, description, date_publication, id_utilisateur) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, annonce.getTitre());
            preparedStatement.setString(2, annonce.getTypeAnnonce());
            preparedStatement.setString(3, annonce.getDescription());
            preparedStatement.setString(4, annonce.getDatePublication());
            preparedStatement.setInt(5, annonce.getIdUtilisateur());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error adding annonce: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Update an existing annonce
    public boolean updateAnnonce(Annonce annonce) {
        try (Connection connection = ConnexionBDD.getConnection()) {
            String sql = "UPDATE annonce SET titre = ?, type_annonce = ?, description = ?, date_publication = ?, id_utilisateur = ? WHERE id_annonce = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, annonce.getTitre());
            preparedStatement.setString(2, annonce.getTypeAnnonce());
            preparedStatement.setString(3, annonce.getDescription());
            preparedStatement.setString(4, annonce.getDatePublication());
            preparedStatement.setInt(5, annonce.getIdUtilisateur());
            preparedStatement.setInt(6, annonce.getIdAnnonce());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating annonce: " + e.getMessage());
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
}
