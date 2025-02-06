package ma.ensi.controller;

import ma.ensi.model.Recruteur;
import ma.ensi.service.RecruteurService;

import java.util.List;

public class AdminRecruiterController {
    private final RecruteurService recruteurService = new RecruteurService();

    // Get all recruiters
    public List<Recruteur> getAllRecruiters() {
        try {
            return recruteurService.getAllRecruteurs();
        } catch (Exception e) { // Catch more general exceptions
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch recruiters.");
        }
    }

    // Delete a recruiter by ID
    public void deleteRecruiter(int id_utilisateur) {
        try {
            recruteurService.deleteRecruteur(id_utilisateur);
            System.out.println("Recruiter deleted successfully.");
        } catch (Exception e) { // Catch more general exceptions
            e.printStackTrace();
            throw new RuntimeException("Failed to delete recruiter.");
        }
    }

    // Update a recruiter's account
    public void updateRecruiter(Recruteur recruteur) {
        try {
            recruteurService.updateRecruteur(recruteur);
            System.out.println("Recruiter updated successfully.");
        } catch (Exception e) { // Catch more general exceptions
            e.printStackTrace();
            throw new RuntimeException("Failed to update recruiter.");
        }
    }

    // Delete a job posting
    public void deleteJobPost(int id_annonce) {
        try {
            recruteurService.deleteJobPost(id_annonce);
            System.out.println("Job post deleted successfully.");
        } catch (Exception e) { // Catch more general exceptions
            e.printStackTrace();
            throw new RuntimeException("Failed to delete job post.");
        }
    }
}
