package ma.ensi.service;

import ma.ensi.dao.AnnonceDAO;
import ma.ensi.model.Annonce;
import java.util.List;

public class AnnonceService {
    private AnnonceDAO annonceDAO;

    public AnnonceService() {
        this.annonceDAO = new AnnonceDAO(); // or use dependency injection if preferred
    }

    // Fetch all annonces
    public List<Annonce> getAllAnnonces() {
        return annonceDAO.getAllAnnonces();
    }

    // Fetch an annonce by its ID
    public Annonce getAnnonceById(int id) {
        return annonceDAO.getAnnonceById(id);
    }

    // Add a new annonce
    public boolean addAnnonce(Annonce annonce) {
        return annonceDAO.addAnnonce(annonce);
    }

    // Update an existing annonce
    public boolean updateAnnonce(Annonce annonce) {
        return annonceDAO.updateAnnonce(annonce);
    }

    // Delete an annonce by ID
    public boolean deleteAnnonce(int id) {
        return annonceDAO.deleteAnnonce(id);
    }

    public List<Annonce> getAnnoncesByRecruiter(int recruiterId) {
        return annonceDAO.getAnnoncesByRecruteurId(recruiterId);
    }


}
