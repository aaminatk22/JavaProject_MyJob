package ma.ensi.service;

import ma.ensi.dao.adminrecruteurdao;
import ma.ensi.model.adminrecruteur;

import java.util.List;

public class adminrecruteurservice {
    private final adminrecruteurdao recruteurDAO = new adminrecruteurdao();

    // Retrieve all recruiters
    public List<adminrecruteur> getAllRecruteurs() {
        try {
            return recruteurDAO.getAllRecruteurs();
        } catch (Exception e) {
            System.out.println("Error retrieving all recruiters: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



    // Retrieve a recruiter by ID
    public adminrecruteur getRecruteurById(int id) {
        try {
            return recruteurDAO.getRecruteurById(id);
        } catch (Exception e) {
            System.out.println("Error retrieving recruiter by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



    // Update an existing recruiter
    public boolean updateRecruteur(adminrecruteur adminrecruteur) {
        try {
            return recruteurDAO.updateRecruteur(adminrecruteur);
        } catch (Exception e) {
            System.out.println("Error updating recruiter: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Delete a recruiter by ID
    public boolean deleteRecruteur(int id) {
        try {
            return recruteurDAO.deleteRecruteur(id);
        } catch (Exception e) {
            System.out.println("Error deleting recruiter: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void deleteJobPost(int jobId) {

    }

    public void saveRecruteur(adminrecruteur adminrecruteur) {

    }
}
