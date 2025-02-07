package ma.ensi.dao;

import ma.ensi.model.admincandidat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class admincandidatdao {

    // Save a new Candidat
    public void saveCandidat(admincandidat admincandidat) throws SQLException {
        String query = "INSERT INTO utilisateur (nom_utilisateur, email, mot_de_passe, role, nom, prenom, age, nom_universite, niveau_etude) " +
                "VALUES (?, ?, ?, 'candidat', ?, ?, ?, ?, ?)";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, admincandidat.getNom_utilisateur());
            ps.setString(2, admincandidat.getEmail());
            ps.setString(3, admincandidat.getMot_de_passe());
            ps.setString(4, admincandidat.getNom());
            ps.setString(5, admincandidat.getPrenom());
            ps.setInt(6, admincandidat.getAge());
            ps.setString(7, admincandidat.getNom_universite());
            ps.setString(8, admincandidat.getNiveau_etude());

            ps.executeUpdate();
            System.out.println("Candidat saved successfully!");
        }
    }

    // Find a Candidat by ID
    public admincandidat findCandidatById(int id) throws SQLException {
        String query = "SELECT * FROM utilisateur WHERE id_utilisateur = ? AND role = 'candidat'";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCandidat(rs);
                }
            }
        }
        return null;
    }

    // Retrieve all Candidats
    public List<admincandidat> findAllCandidats() throws SQLException {
        List<admincandidat> admincandidats = new ArrayList<>();
        String query = "SELECT * FROM utilisateur WHERE role = 'candidat'";

        try (Connection connection = ConnexionBDD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                admincandidat admincandidat = mapCandidat(rs);
                admincandidats.add(admincandidat);
            }
        }
        return admincandidats;
    }

    // Update a Candidat
    public void updateCandidat(admincandidat admincandidat) throws SQLException {
        String query = "UPDATE utilisateur SET nom_utilisateur = ?, email = ?, mot_de_passe = ?, nom = ?, prenom = ?, " +
                "age = ?, nom_universite = ?, niveau_etude = ? WHERE id_utilisateur = ? AND role = 'candidat'";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, admincandidat.getNom_utilisateur());
            ps.setString(2, admincandidat.getEmail());
            ps.setString(3, admincandidat.getMot_de_passe());
            ps.setString(4, admincandidat.getNom());
            ps.setString(5, admincandidat.getPrenom());
            ps.setInt(6, admincandidat.getAge());
            ps.setString(7, admincandidat.getNom_universite());
            ps.setString(8, admincandidat.getNiveau_etude());
            ps.setInt(9, admincandidat.getId_utilisateur());

            ps.executeUpdate();
            System.out.println("Candidat updated successfully!");
        }
    }

    // Delete a Candidat by ID
    public void deleteCandidatById(int id) throws SQLException {
        String query = "DELETE FROM utilisateur WHERE id_utilisateur = ? AND role = 'candidat'";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Candidat deleted successfully!");
        }
    }

    // Map a ResultSet to a Candidat object
    private admincandidat mapCandidat(ResultSet rs) throws SQLException {
        admincandidat admincandidat = new admincandidat();

        admincandidat.setId_utilisateur(rs.getInt("id_utilisateur"));
        admincandidat.setNom_utilisateur(rs.getString("nom_utilisateur"));
        admincandidat.setEmail(rs.getString("email"));
        admincandidat.setMot_de_passe(rs.getString("mot_de_passe"));
        admincandidat.setRole(rs.getString("role"));
        admincandidat.setNom(rs.getString("nom"));
        admincandidat.setPrenom(rs.getString("prenom"));
        admincandidat.setAge(rs.getInt("age"));
        admincandidat.setNom_universite(rs.getString("nom_universite"));
        admincandidat.setNiveau_etude(rs.getString("niveau_etude"));

        return admincandidat;
    }
}
