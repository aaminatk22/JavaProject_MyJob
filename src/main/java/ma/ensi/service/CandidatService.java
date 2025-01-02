package ma.ensi.service;

import ma.ensi.dao.CandidatDAO;
import ma.ensi.model.Candidat;
import java.sql.SQLException;
import java.util.List;

public class CandidatService {
    private CandidatDAO candidatDAO = new CandidatDAO();

    public Candidat getCandidat(int id) throws SQLException {
        return candidatDAO.getCandidat(id);
    }

    public List<Candidat> getAllCandidats() throws SQLException {
        return candidatDAO.getAllCandidats();
    }

    public void updateCandidat(Candidat candidat) throws SQLException {
        candidatDAO.updateCandidat(candidat);
    }

    public void deleteCandidat(int id) throws SQLException {
        candidatDAO.deleteCandidat(id);
    }

    // Additional methods for business logic if needed
}
