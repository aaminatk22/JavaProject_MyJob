package ma.ensi.dao;

import ma.ensi.model.Recruteur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecruteurDAO {

    // Save a new recruiter
    public void saveRecruteur(Recruteur recruteur) throws SQLException {
        String query = "INSERT INTO utilisateur (nom_utilisateur, email, mot_de_passe, role, nom, prenom, entreprise) VALUES (?, ?, ?, 'recruteur', ?, ?, ?)";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, recruteur.getNomUtilisateur());
            ps.setString(2, recruteur.getEmail());
            ps.setString(3, recruteur.getMotDePasse());
            ps.setString(4, recruteur.getNom());
            ps.setString(5, recruteur.getPrenom());
            ps.setString(6, recruteur.getEntreprise());

            ps.executeUpdate();
        }
    }

    // Retrieve a recruiter by ID
    public Recruteur findRecruteurById(int id) throws SQLException {
        String query = "SELECT * FROM utilisateur WHERE id_utilisateur = ? AND role = 'recruteur'";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRecruteur(rs);
                }
            }
        }
        return null;
    }

    // Retrieve all recruiters
    public List<Recruteur> findAllRecruteurs() throws SQLException {
        String query = "SELECT * FROM utilisateur WHERE role = 'recruteur'";
        List<Recruteur> recruteurs = new ArrayList<>();
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                recruteurs.add(mapRecruteur(rs));
            }
        }
        return recruteurs;
    }

    // Update a recruiter's information
    public void updateRecruteur(Recruteur recruteur) throws SQLException {
        String query = "UPDATE utilisateur SET nom_utilisateur = ?, email = ?, mot_de_passe = ?, nom = ?, prenom = ?, entreprise = ? WHERE id_utilisateur = ? AND role = 'recruteur'";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, recruteur.getNomUtilisateur());
            ps.setString(2, recruteur.getEmail());
            ps.setString(3, recruteur.getMotDePasse());
            ps.setString(4, recruteur.getNom());
            ps.setString(5, recruteur.getPrenom());
            ps.setString(6, recruteur.getEntreprise());
            ps.setInt(7, recruteur.getIdUtilisateur());

            ps.executeUpdate();
        }
    }

    // Delete a recruiter by ID
    public void deleteRecruteurById(int id) throws SQLException {
        String query = "DELETE FROM utilisateur WHERE id_utilisateur = ? AND role = 'recruteur'";
        try (Connection connection = ConnexionBDD.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Map a ResultSet to a Recruteur object
    private Recruteur mapRecruteur(ResultSet rs) throws SQLException {
        Recruteur recruteur = new Recruteur();
        recruteur.setIdUtilisateur(rs.getInt("id_utilisateur"));
        recruteur.setNomUtilisateur(rs.getString("nom_utilisateur"));
        recruteur.setEmail(rs.getString("email"));
        recruteur.setMotDePasse(rs.getString("mot_de_passe"));
        recruteur.setNom(rs.getString("nom"));
        recruteur.setPrenom(rs.getString("prenom"));
        recruteur.setEntreprise(rs.getString("entreprise"));
        return recruteur;
    }
}
