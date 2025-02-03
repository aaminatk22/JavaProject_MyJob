package ma.ensi.controller;

import ma.ensi.model.Recruteur;
import ma.ensi.service.RecruteurService;

import java.sql.SQLException;
import java.util.List;

public class RecruteurController {
    private final RecruteurService recruteurService = new RecruteurService();

    // Add a new recruiter
    public void addRecruteur(String nomUtilisateur, String email, String motDePasse, String nom, String prenom, String entreprise) {
        Recruteur recruteur = new Recruteur(nomUtilisateur, email, motDePasse, nom, prenom, entreprise);
        try {
            recruteurService.saveRecruteur(recruteur);
            System.out.println("Recruteur ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du recruteur : " + e.getMessage());
        }
    }

    // Get recruiter by ID
    public void getRecruteurById(int id) {
        try {
            Recruteur recruteur = recruteurService.getRecruteurById(id);
            if (recruteur != null) {
                System.out.println("Recruteur trouvé : " + recruteur);
            } else {
                System.out.println("Aucun recruteur trouvé avec l'ID : " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération du recruteur : " + e.getMessage());
        }
    }

    // Get all recruiters
    public void getAllRecruteurs() {
        try {
            List<Recruteur> recruteurs = recruteurService.getAllRecruteurs();
            System.out.println("Liste des recruteurs : ");
            recruteurs.forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des recruteurs : " + e.getMessage());
        }
    }

    // Update a recruiter's information
    public void updateRecruteur(int id, String nomUtilisateur, String email, String motDePasse, String nom, String prenom, String entreprise) {
        Recruteur recruteur = new Recruteur(id, nomUtilisateur, email, motDePasse, nom, prenom, entreprise);
        try {
            recruteurService.updateRecruteur(recruteur);
            System.out.println("Recruteur mis à jour avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du recruteur : " + e.getMessage());
        }
    }

    // Delete a recruiter by ID
    public void deleteRecruteur(int id) {
        try {
            recruteurService.deleteRecruteur(id);
            System.out.println("Recruteur supprimé avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du recruteur : " + e.getMessage());
        }
    }
}
