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
        String query = "INSERT INTO utilisateur (nom_utilisateur, email, mot_de_passe, role, nom, prenom, age, entreprise, nom_universite, tel) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, utilisateur.getNomUtilisateur());
            ps.setString(2, utilisateur.getEmail());
            ps.setString(3, utilisateur.getMotDePasse()); // Save password
            ps.setString(4, utilisateur.getRole());
            ps.setString(5, utilisateur.getNom());
            ps.setString(6, utilisateur.getPrenom());
            ps.setString(10, utilisateur.getTel()); // Correctly set the `tel` field

            // Check if the user is a Candidat
            if (utilisateur instanceof Candidat) {
                Candidat candidat = (Candidat) utilisateur;
                ps.setInt(7, candidat.getAge()); // Set `age` for Candidat
                ps.setNull(8, java.sql.Types.VARCHAR); // `entreprise` is not applicable for Candidat
                ps.setString(9, candidat.getNomUniversite()); // Set `nom_universite`
            }
            // Check if the user is a Recruteur
            else if (utilisateur instanceof Recruteur) {
                Recruteur recruteur = (Recruteur) utilisateur;
                ps.setNull(7, java.sql.Types.INTEGER); // `age` is not applicable for Recruteur
                ps.setString(8, recruteur.getEntreprise()); // Set `entreprise` for Recruteur
                ps.setNull(9, java.sql.Types.VARCHAR); // `nom_universite` is not applicable for Recruteur
            }
            // Default case
            else {
                ps.setNull(7, java.sql.Types.INTEGER); // `age` is null
                ps.setNull(8, java.sql.Types.VARCHAR); // `entreprise` is null
                ps.setNull(9, java.sql.Types.VARCHAR); // `nom_universite` is null
            }

            ps.executeUpdate();
            System.out.println("User saved successfully in the database!");
        }
    }

    // Update personal information of a user
    public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
        String query = "UPDATE utilisateur SET nom_utilisateur = ?, mot_de_passe = ?, nom = ?, prenom = ?, email = ?, tel = ?, age = ?, nom_universite = ? WHERE id_utilisateur = ?";

        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, utilisateur.getNomUtilisateur());
            ps.setString(2, utilisateur.getMotDePasse());
            ps.setString(3, utilisateur.getNom());
            ps.setString(4, utilisateur.getPrenom());
            ps.setString(5, utilisateur.getEmail());
            ps.setString(6, utilisateur.getTel());

            // Set age and nom_universite for Candidat
            if (utilisateur instanceof Candidat) {
                Candidat candidat = (Candidat) utilisateur;
                ps.setInt(7, candidat.getAge()); // Set age
                ps.setString(8, candidat.getNomUniversite()); // Set nom_universite
            } else {
                ps.setNull(7, java.sql.Types.INTEGER); // age is null
                ps.setNull(8, java.sql.Types.VARCHAR); // nom_universite is null
            }

            ps.setInt(9, utilisateur.getIdUtilisateur()); // Set user ID for the update condition

            ps.executeUpdate();
            System.out.println("User information updated successfully!");
        }
    }
    // Check if an email exists
    public boolean emailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM utilisateur WHERE email = ?";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
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
        String role = rs.getString("role");
        Utilisateur utilisateur;

        if ("candidat".equals(role)) {
            Candidat candidat = new Candidat();
            candidat.setAge(rs.getInt("age")); // Set `age`
            candidat.setNomUniversite(rs.getString("nom_universite")); // Set `nom_universite`
            utilisateur = candidat;
        } else if ("recruteur".equals(role)) {
            Recruteur recruteur = new Recruteur();
            recruteur.setEntreprise(rs.getString("entreprise")); // Set `entreprise`
            utilisateur = recruteur;
        } else {
            utilisateur = new Utilisateur();
        }

        utilisateur.setIdUtilisateur(rs.getInt("id_utilisateur"));
        utilisateur.setNomUtilisateur(rs.getString("nom_utilisateur"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
        utilisateur.setRole(role);
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setPrenom(rs.getString("prenom"));
        utilisateur.setTel(rs.getString("tel")); // Set `tel`

        return utilisateur;
    }
}
