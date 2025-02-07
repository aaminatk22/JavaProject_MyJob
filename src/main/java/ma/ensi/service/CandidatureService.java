package ma.ensi.service;

import ma.ensi.dao.CandidatureDAO;
import ma.ensi.model.Candidat;
import ma.ensi.model.Candidature;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CandidatureService {
    private final CandidatureDAO candidatureDAO = new CandidatureDAO();

    public void saveCandidature(Candidature candidature) throws SQLException {
        // Valider les données avant de les enregistrer
        if (candidature.getIdAnnonce() <= 0 || candidature.getIdUtilisateur() <= 0) {
            throw new IllegalArgumentException("ID de l'annonce ou de l'utilisateur invalide !");
        }

        // Ajouter un statut par défaut si non fourni
        if (candidature.getStatut() == null || candidature.getStatut().isEmpty()) {
            candidature.setStatut("En attente");
        }

        // Appeler le DAO pour sauvegarder la candidature
        candidatureDAO.saveCandidature(candidature);
    }

    public List<Candidature> getCandidaturesByAnnonce(int idAnnonce) {
        return candidatureDAO.findByAnnonce(idAnnonce);
    }

    public Candidature getCandidatureById(int id) {
        return candidatureDAO.getCandidatureById(id);
    }

    public void updateCandidature(Candidature candidature) {
         candidatureDAO.updateCandidature(candidature);
    }

    public List<Candidature> getAllCandidatures (){
        return candidatureDAO.getAllCandidatures();
    }

    public List<Candidature> findByCandidat (int id) {
        return candidatureDAO.findByCandidat(id);
    }

}
