package ma.ensi.controller;

import ma.ensi.model.Recruteur;
import ma.ensi.service.RecruteurService;

import java.sql.SQLException;
import java.util.List;

public class AdminRecruiterController {
    private final RecruteurService recruteurService = new RecruteurService();

    // Get all recruiters
    public List<Recruteur> getAllRecruiters() {
        try {
            return recruteurService.getAllRecruteurs();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch recruiters.");
        }
    }

    // Delete a recruiter by ID
    public void deleteRecruiter(int id) {
        try {
            recruteurService.deleteRecruteur(id);
            System.out.println("Recruiter deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete recruiter.");
        }
    }

    // Update a recruiter's account
    public void updateRecruiter(Recruteur recruteur) {
        try {
            recruteurService.updateRecruteur(recruteur);
            System.out.println("Recruiter updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update recruiter.");
        }
    }

    // Delete a job posting
    public void deleteJobPost(int jobId) {
        try {
            recruteurService.deleteJobPost(jobId);
            System.out.println("Job post deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete job post.");
        }
    }
}
