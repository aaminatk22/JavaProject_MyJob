package ma.ensi.dao;

import ma.ensi.model.Recruteur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecruteurDAO {

    // Retrieve all recruiters
    public List<Recruteur> getAllRecruteurs() {
        List<Recruteur> recruteurs = new ArrayList<>();
        try (Connection connection = ConnexionBDD.getConnection()) {  // Auto-close connection with try-with-resources
            String sql = "SELECT * FROM recruteur";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Recruteur recruteur = new Recruteur();
                recruteur.setEntreprise(resultSet.getString("entreprise"));
                recruteur.setPoste(resultSet.getString("poste"));
                recruteurs.add(recruteur);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching recruiters: " + e.getMessage());
            e.printStackTrace();
        }
        return recruteurs;
    }

    // Retrieve a recruiter by ID
    public Recruteur getRecruteurById(int id) {
        Recruteur recruteur = null;
        try (Connection connection = ConnexionBDD.getConnection()) {
            String sql = "SELECT * FROM recruteur WHERE idUtilisateur = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                recruteur = new Recruteur();
                recruteur.setEntreprise(resultSet.getString("entreprise"));
                recruteur.setPoste(resultSet.getString("poste"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching recruiter by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return recruteur;
    }

    // Add a new recruiter
    public boolean addRecruteur(Recruteur recruteur) {
        try (Connection connection = ConnexionBDD.getConnection()) {
            String sql = "INSERT INTO recruteur (entreprise, poste) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recruteur.getEntreprise());
            preparedStatement.setString(2, recruteur.getPoste());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error adding recruiter: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Update an existing recruiter
    public boolean updateRecruteur(Recruteur recruteur) {
        try (Connection connection = ConnexionBDD.getConnection()) {
            String sql = "UPDATE recruteur SET entreprise = ?, poste = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recruteur.getEntreprise());
            preparedStatement.setString(2, recruteur.getPoste());
            preparedStatement.setInt(3, recruteur.getIdUtilisateur());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating recruiter: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Delete a recruiter by ID
    public boolean deleteRecruteur(int id) {
        try (Connection connection = ConnexionBDD.getConnection()) {
            String sql = "DELETE FROM recruteur WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting recruiter: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
