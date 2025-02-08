package ma.ensi.dao;

import ma.ensi.model.Candidat;
import ma.ensi.model.Candidature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO {


    public void saveCandidature(Candidature candidature) throws SQLException {
        String query = "INSERT INTO candidature (id_annonce, id_utilisateur, date_soumission, statut) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, candidature.getIdAnnonce());
            ps.setInt(2, candidature.getIdUtilisateur());
            ps.setObject(3, candidature.getDateSoumission());
            ps.setString(4, candidature.getStatut());


            ps.executeUpdate();
        }
    }

    public List<Candidature> findByAnnonce(int idAnnonce) {
        List<Candidature> candidatures = new ArrayList<>();

        String sql = "SELECT * FROM candidature WHERE id_annonce = ?";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idAnnonce);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Candidature candidature = new Candidature();
                candidature.setIdCandidature(resultSet.getInt("id_candidature"));
                candidature.setIdAnnonce(resultSet.getInt("id_annonce"));
                candidature.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                candidature.setDateSoumission(resultSet.getDate("date_soumission").toLocalDate());
                candidature.setStatut(resultSet.getString("statut"));

                candidatures.add(candidature);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidatures;
    }



    public Candidature getCandidatureById(int id) {
        Candidature candidature = null;
        String query = "SELECT * FROM candidature WHERE id_candidature = ?";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);  // Set the candidature ID in the query
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Map the result set to a Candidature object
                candidature = new Candidature();
                candidature.setIdCandidature(resultSet.getInt("id_candidature"));
                candidature.setIdAnnonce(resultSet.getInt("id_annonce"));
                candidature.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                candidature.setDateSoumission(resultSet.getDate("date_soumission").toLocalDate());
                candidature.setStatut(resultSet.getString("statut"));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception appropriately
        }

        return candidature;
    }

    // Method to update the status of a Candidature
    public void updateCandidature(Candidature candidature) {
        String query = "UPDATE candidature SET statut = ? WHERE id_candidature = ?";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, candidature.getStatut());  // Set the new status
            statement.setInt(2, candidature.getIdCandidature());         // Set the candidature ID
            statement.executeUpdate();  // Execute the update query

        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception appropriately
        }
    }


    public List<Candidature> findByCandidat(int idCandidat) {
        List<Candidature> candidatures = new ArrayList<>();
        String sql = "SELECT * FROM candidature WHERE id_utilisateur = ?";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCandidat);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Candidature candidature = new Candidature();
                candidature.setIdCandidature(rs.getInt("id_candidature"));
                candidature.setIdAnnonce(rs.getInt("id_annonce"));
                candidature.setIdUtilisateur(rs.getInt("id_utilisateur"));
                candidature.setDateSoumission(rs.getDate("date_soumission").toLocalDate());
                candidature.setStatut(rs.getString("statut"));

                candidatures.add(candidature);
            }

            // Print number of candidatures fetched
            System.out.println("DEBUG: DAO - Fetched " + candidatures.size() + " candidatures for user ID: " + idCandidat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidatures;
    }





    public List<Candidature> getAllCandidatures() {
        List<Candidature> candidatures = new ArrayList<>();
        try (Connection connection = ConnexionBDD.getConnection()) {  // Auto-close connection
            String sql = "SELECT * FROM candidature";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Candidature candidature = new Candidature();
                candidature.setIdCandidature(resultSet.getInt("id_candidature"));
                candidature.setIdAnnonce(resultSet.getInt("id_annonce"));
                candidature.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                candidature.setDateSoumission(resultSet.getDate("date_soumission").toLocalDate());
                candidature.setStatut(resultSet.getString("statut"));
                candidatures.add(candidature);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching candidatures: " + e.getMessage());
            e.printStackTrace();
        }
        return candidatures;
    }


    // ✅ **Méthode utilitaire pour éviter la répétition du code**
    private Candidature mapResultSetToCandidature(ResultSet resultSet) throws SQLException {
        Candidature candidature = new Candidature();
        candidature.setIdCandidature(resultSet.getInt("id_candidature"));
        candidature.setIdAnnonce(resultSet.getInt("id_annonce"));
        candidature.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
        candidature.setDateSoumission(resultSet.getDate("date_soumission").toLocalDate());
        candidature.setStatut(resultSet.getString("statut"));
        return candidature;
    }

}