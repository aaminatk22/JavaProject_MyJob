package ma.ensi.controller;

import ma.ensi.model.admincandidat;
import ma.ensi.service.admincandidatservice;

import java.sql.SQLException;
import java.util.List;

public class admincandidatcontroller {
    private final admincandidatservice candidatService = new admincandidatservice();

    // Get all candidates
    public List<admincandidat> getAllCandidates() {
        try {
            return candidatService.getAllCandidats();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch candidates.");
        }
    }

    // Delete a candidate by ID
    public void deleteCandidate(int id_utilisateur) {
        try {
            candidatService.deleteCandidat(id_utilisateur);
            System.out.println("Candidate deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete candidate.");
        }
    }

    // Update a candidate's account
    public void updateCandidate(admincandidat admincandidat) {
        try {
            candidatService.updateCandidat(admincandidat);
            System.out.println("Candidate updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update candidate.");
        }
    }

    // Access a candidate's portfolio
    public void viewCandidatePortfolio(int id_utilisateur) {
        try {
            admincandidat admincandidat = candidatService.getCandidatById(id_utilisateur);
            if (admincandidat != null) {
                admincandidat.consulterPortfolio(); // Call the portfolio method
            } else {
                System.out.println("Candidate not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch candidate portfolio.");
        }
    }
}
