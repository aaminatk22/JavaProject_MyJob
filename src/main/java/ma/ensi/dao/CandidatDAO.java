package ma.ensi.dao;

import ma.ensi.model.Candidat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatDAO {

    // Save a new Candidat
    public void saveCandidat(Candidat candidat) throws SQLException {
        String query = "INSERT INTO utilisateur (nom_utilisateur, email, mot_de_passe, role, nom, prenom, age, nom_universite, niveau_etude) " +
                "VALUES (?, ?, ?, 'candidat', ?, ?, ?, ?, ?)";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, candidat.getNom_utilisateur());
            ps.setString(2, candidat.getEmail());
            ps.setString(3, candidat.getMot_de_passe());
            ps.setString(4, candidat.getNom());
            ps.setString(5, candidat.getPrenom());
            ps.setInt(6, candidat.getAge());
            ps.setString(7, candidat.getNom_universite());
            ps.setString(8, candidat.getNiveau_etude());

            ps.executeUpdate();
            System.out.println("Candidat saved successfully!");
        }
    }

    // Find a Candidat by ID
    public Candidat findCandidatById(int id) throws SQLException {
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
    public List<Candidat> findAllCandidats() throws SQLException {
        List<Candidat> candidats = new ArrayList<>();
        String query = "SELECT * FROM utilisateur WHERE role = 'candidat'";

        try (Connection connection = ConnexionBDD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                Candidat candidat = mapCandidat(rs);
                candidats.add(candidat);
            }
        }
        return candidats;
    }

    // Update a Candidat
    public void updateCandidat(Candidat candidat) throws SQLException {
        String query = "UPDATE utilisateur SET nom_utilisateur = ?, email = ?, mot_de_passe = ?, nom = ?, prenom = ?, " +
                "age = ?, nom_universite = ?, niveau_etude = ? WHERE id_utilisateur = ? AND role = 'candidat'";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, candidat.getNom_utilisateur());
            ps.setString(2, candidat.getEmail());
            ps.setString(3, candidat.getMot_de_passe());
            ps.setString(4, candidat.getNom());
            ps.setString(5, candidat.getPrenom());
            ps.setInt(6, candidat.getAge());
            ps.setString(7, candidat.getNom_universite());
            ps.setString(8, candidat.getNiveau_etude());
            ps.setInt(9, candidat.getId_utilisateur());

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
    private Candidat mapCandidat(ResultSet rs) throws SQLException {
        Candidat candidat = new Candidat();

        candidat.setId_utilisateur(rs.getInt("id_utilisateur"));
        candidat.setNom_utilisateur(rs.getString("nom_utilisateur"));
        candidat.setEmail(rs.getString("email"));
        candidat.setMot_de_passe(rs.getString("mot_de_passe"));
        candidat.setRole(rs.getString("role"));
        candidat.setNom(rs.getString("nom"));
        candidat.setPrenom(rs.getString("prenom"));
        candidat.setAge(rs.getInt("age"));
        candidat.setNom_universite(rs.getString("nom_universite"));
        candidat.setNiveau_etude(rs.getString("niveau_etude"));

        return candidat;
    }
}
