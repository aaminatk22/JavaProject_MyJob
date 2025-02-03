package ma.ensi.controller;

import ma.ensi.model.Candidat;
import ma.ensi.service.CandidatService;

import java.sql.SQLException;
import java.util.List;

public class AdminCandidateController {
    private final CandidatService candidatService = new CandidatService();

    // Get all candidates
    public List<Candidat> getAllCandidates() {
        try {
            return candidatService.getAllCandidats();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch candidates.");
        }
    }

    // Delete a candidate by ID
    public void deleteCandidate(int id) {
        try {
            candidatService.deleteCandidat(id);
            System.out.println("Candidate deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete candidate.");
        }
    }

    // Update a candidate's account
    public void updateCandidate(Candidat candidat) {
        try {
            candidatService.updateCandidat(candidat);
            System.out.println("Candidate updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update candidate.");
        }
    }

    // Access a candidate's portfolio
    public void viewCandidatePortfolio(int candidateId) {
        try {
            Candidat candidat = candidatService.getCandidatById(candidateId);
            if (candidat != null) {
                candidat.consulterPortfolio(); // Call the portfolio method
            } else {
                System.out.println("Candidate not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch candidate portfolio.");
        }
    }
}
