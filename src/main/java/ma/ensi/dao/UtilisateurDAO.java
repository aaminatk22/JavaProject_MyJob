package ma.ensi.dao;

import ma.ensi.model.Candidat;
import ma.ensi.model.Recruteur;
import ma.ensi.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO {

    // Save a new utilisateur
    public void saveUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO utilisateur (nom_utilisateur, email, mot_de_passe, role, nom, prenom, age, entreprise, nom_universite) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, utilisateur.getNomUtilisateur());
            ps.setString(2, utilisateur.getEmail());
            ps.setString(3, utilisateur.getMotDePasse());
            ps.setString(4, utilisateur.getRole());
            ps.setString(5, utilisateur.getNom());
            ps.setString(6, utilisateur.getPrenom());

            // Check if the user is a Candidat
            if (utilisateur instanceof Candidat) {
                Candidat candidat = (Candidat) utilisateur;
                ps.setInt(7, candidat.getAge()); // Age is specific to Candidat
                ps.setNull(8, java.sql.Types.VARCHAR); // Entreprise is not applicable for Candidat
                ps.setString(9, candidat.getNomUniversite()); // Nom Universite is specific to Candidat
            }
            // Check if the user is a Recruteur
            else if (utilisateur instanceof Recruteur) {
                Recruteur recruteur = (Recruteur) utilisateur;
                ps.setNull(7, java.sql.Types.INTEGER); // Age is not applicable for Recruteur
                ps.setString(8, recruteur.getEntreprise()); // Entreprise is specific to Recruteur
                ps.setNull(9, java.sql.Types.VARCHAR); // Nom Universite is not applicable for Recruteur
            }
            // Default case for other roles
            else {
                ps.setNull(7, java.sql.Types.INTEGER); // Age is null
                ps.setNull(8, java.sql.Types.VARCHAR); // Entreprise is null
                ps.setNull(9, java.sql.Types.VARCHAR); // Nom Universite is null
            }

            System.out.println("Executing query: " + ps.toString());
            ps.executeUpdate();
            System.out.println("User saved successfully in the database!");
        }
    }


    // Check if an email exists
    public boolean emailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM utilisateur WHERE email = ?";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Find a user by email
    public Utilisateur findByEmail(String email) throws SQLException {
        String query = "SELECT * FROM utilisateur WHERE email = ?";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUtilisateur(rs);
                }
            }
        }
        return null;
    }

    // Find a user by ID
    public Utilisateur findById(int idUtilisateur) throws SQLException {
        String query = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, idUtilisateur);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUtilisateur(rs);
                }
            }
        }
        return null;
    }

    // Map a user from ResultSet
    private Utilisateur mapUtilisateur(ResultSet rs) throws SQLException {
        // Determine the role and create the appropriate object
        String role = rs.getString("role");
        Utilisateur utilisateur;

        if ("candidat".equals(role)) {
            Candidat candidat = new Candidat();
            candidat.setAge(rs.getInt("age")); // Age
            candidat.setNomUniversite(rs.getString("nom_universite")); // Nom Universite
            utilisateur = candidat;
        } else if ("recruteur".equals(role)) {
            Recruteur recruteur = new Recruteur();
            recruteur.setEntreprise(rs.getString("entreprise")); // Entreprise
            utilisateur = recruteur;
        } else {
            utilisateur = new Utilisateur(); // Default for other roles
        }

        // Set common attributes
        utilisateur.setIdUtilisateur(rs.getInt("id_utilisateur"));
        utilisateur.setNomUtilisateur(rs.getString("nom_utilisateur"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
        utilisateur.setRole(role);
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setPrenom(rs.getString("prenom"));

        return utilisateur;
    }
}
