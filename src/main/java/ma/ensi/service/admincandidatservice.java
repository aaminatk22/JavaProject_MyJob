package ma.ensi.service;

import ma.ensi.dao.admincandidatdao;
import ma.ensi.model.admincandidat;

import java.sql.SQLException;
import java.util.List;

public class admincandidatservice {
    private final admincandidatdao candidatDAO = new admincandidatdao();

    // Retrieve a candidate by ID
    public admincandidat getCandidatById(int id) throws SQLException {
        return candidatDAO.findCandidatById(id);
    }

    // Retrieve all candidates
    public List<admincandidat> getAllCandidats() throws SQLException {
        return candidatDAO.findAllCandidats();
    }

    // Save a new candidate
    public void saveCandidat(admincandidat admincandidat) throws SQLException {
        candidatDAO.saveCandidat(admincandidat);
    }

    // Update an existing candidate
    public void updateCandidat(admincandidat admincandidat) throws SQLException {
        if (admincandidat.getId_utilisateur() == 0) {
            throw new IllegalArgumentException("Candidate ID is required for an update.");
        }
        candidatDAO.updateCandidat(admincandidat);
    }

    // Delete a candidate by ID
    public void deleteCandidat(int id) throws SQLException {
        candidatDAO.deleteCandidatById(id);
    }
}
