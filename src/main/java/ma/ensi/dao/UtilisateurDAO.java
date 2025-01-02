package ma.ensi.dao;

import ma.ensi.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO {

    // Enregistrer un nouvel utilisateur
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
            ps.setInt(7, utilisateur.getAge());
            ps.setString(8, utilisateur.getEntreprise());
            ps.setString(9, utilisateur.getNomUniversite());

            System.out.println("Executing query: " + ps.toString());
            ps.executeUpdate();
            System.out.println("User saved successfully in the database!");
        }
    }


    // Vérifier si un email existe
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

    // Trouver un utilisateur par email
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

    // Trouver un utilisateur par ID
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

    // Mapper un utilisateur à partir d'un ResultSet
    private Utilisateur mapUtilisateur(ResultSet rs) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(rs.getInt("id_utilisateur"));
        utilisateur.setNomUtilisateur(rs.getString("nom_utilisateur"));
        utilisateur.setEmail(rs.getString("email"));
        utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
        utilisateur.setRole(rs.getString("role"));
        utilisateur.setNom(rs.getString("nom"));
        utilisateur.setPrenom(rs.getString("prenom"));
        utilisateur.setAge(rs.getInt("age"));
        utilisateur.setEntreprise(rs.getString("entreprise"));
        utilisateur.setNomUniversite(rs.getString("nom_universite"));
        return utilisateur;
    }
}
