package ma.ensi.service;

import ma.ensi.dao.RecruteurDAO;
import ma.ensi.model.Recruteur;

import java.sql.SQLException;
import java.util.List;

public class RecruteurService {
    private final RecruteurDAO recruteurDAO = new RecruteurDAO();

    public void saveRecruteur(Recruteur recruteur) throws SQLException {
        recruteurDAO.saveRecruteur(recruteur);
    }

    public Recruteur getRecruteurById(int id) throws SQLException {
        return recruteurDAO.findRecruteurById(id);
    }

    public List<Recruteur> getAllRecruteurs() throws SQLException {
        return recruteurDAO.findAllRecruteurs();
    }

    public void updateRecruteur(Recruteur recruteur) throws SQLException {
        recruteurDAO.updateRecruteur(recruteur);
    }

    public void deleteRecruteur(int id) throws SQLException {
        recruteurDAO.deleteRecruteurById(id);
    }
}
