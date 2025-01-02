package ma.ensi.service;

import ma.ensi.dao.UtilisateurDAO;
import ma.ensi.model.Utilisateur;

import java.sql.SQLException;

public class UtilisateurService {
    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    // Inscription d'un nouvel utilisateur
    public void registerUtilisateur(Utilisateur utilisateur) throws SQLException {
        if (utilisateurDAO.emailExists(utilisateur.getEmail())) {
            throw new IllegalArgumentException("L'email est déjà utilisé !");
        }
        utilisateurDAO.saveUtilisateur(utilisateur);
    }

    // Connexion d'un utilisateur
    public Utilisateur login(String email, String motDePasse) throws SQLException {
        Utilisateur utilisateur = utilisateurDAO.findByEmail(email);
        if (utilisateur == null || !utilisateur.getMotDePasse().equals(motDePasse)) {
            throw new IllegalArgumentException("Email ou mot de passe incorrect !");
        }
        return utilisateur;
    }

    // Récupérer un utilisateur par ID
    public Utilisateur getUtilisateurById(int idUtilisateur) throws SQLException {
        return utilisateurDAO.findById(idUtilisateur);
    }

    public void register(Utilisateur utilisateur) throws SQLException {
        System.out.println("Validating user data...");
        if (utilisateur.getNomUtilisateur() == null || utilisateur.getNomUtilisateur().isEmpty()) {
            throw new IllegalArgumentException("Le nom d'utilisateur est obligatoire.");
        }
        if (utilisateur.getEmail() == null || utilisateur.getEmail().isEmpty()) {
            throw new IllegalArgumentException("L'email est obligatoire.");
        }
        if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe est obligatoire.");
        }

        if (utilisateurDAO.emailExists(utilisateur.getEmail())) {
            throw new IllegalArgumentException("Cet email est déjà utilisé !");
        }

        System.out.println("User validated. Proceeding to save...");
        utilisateurDAO.saveUtilisateur(utilisateur);
    }


}
