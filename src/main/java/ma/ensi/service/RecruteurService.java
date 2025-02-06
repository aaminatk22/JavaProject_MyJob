package ma.ensi.service;

import ma.ensi.dao.RecruteurDAO;
import ma.ensi.model.Recruteur;

import java.sql.SQLException;
import java.util.List;

public class RecruteurService {
    private final RecruteurDAO recruteurDAO = new RecruteurDAO();

    // Retrieve all recruiters
    public List<Recruteur> getAllRecruteurs() {
        try {
            return recruteurDAO.getAllRecruteurs();
        } catch (Exception e) {
            System.out.println("Error retrieving all recruiters: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



    // Retrieve a recruiter by ID
    public Recruteur getRecruteurById(int id) {
        try {
            return recruteurDAO.getRecruteurById(id);
        } catch (Exception e) {
            System.out.println("Error retrieving recruiter by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Add a new recruiter
    public boolean addRecruteur(Recruteur recruteur) {
        try {
            return recruteurDAO.addRecruteur(recruteur);
        } catch (Exception e) {
            System.out.println("Error adding recruiter: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing recruiter
    public boolean updateRecruteur(Recruteur recruteur) {
        try {
            return recruteurDAO.updateRecruteur(recruteur);
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

    public void saveRecruteur(Recruteur recruteur) {

    }
}
