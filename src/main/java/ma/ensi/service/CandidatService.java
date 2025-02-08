package ma.ensi.service;

import ma.ensi.dao.CandidatDAO;
import ma.ensi.model.Candidat;

import java.sql.SQLException;
import java.util.List;

public class CandidatService {
    private final CandidatDAO candidatDAO = new CandidatDAO();

    // Retrieve a candidate by ID
    public Candidat getCandidatById(int id) throws SQLException {
        return candidatDAO.findCandidatById(id);
    }

    // Retrieve all candidates
    public List<Candidat> getAllCandidats() throws SQLException {
        return candidatDAO.findAllCandidats();
    }

    // Save a new candidate
    public void saveCandidat(Candidat candidat) throws SQLException {
        candidatDAO.saveCandidat(candidat);
    }

    // Update an existing candidate
    public void updateCandidat(Candidat candidat) throws SQLException {
        if (candidat.getIdUtilisateur() == 0) {
            throw new IllegalArgumentException("Candidate ID is required for an update.");
        }
        candidatDAO.updateCandidat(candidat);
    }

    // Delete a candidate by ID
    public void deleteCandidat(int id) throws SQLException {
        candidatDAO.deleteCandidatById(id);
    }
}
