package ma.ensi.service;

import ma.ensi.dao.EntretienDAO;
import ma.ensi.model.Entretien;
import java.sql.SQLException;
import java.util.List;

public class EntretienService {
    private final EntretienDAO entretienDAO = new EntretienDAO();

    public void scheduleEntretien(Entretien entretien) throws SQLException {
        entretienDAO.saveEntretien(entretien);
    }

    public List<Entretien> getEntretiensByCandidature(int idCandidature) throws SQLException {
        return entretienDAO.getEntretiensByCandidatureId(idCandidature);
    }
}
