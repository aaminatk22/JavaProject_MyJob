package ma.ensi.service;

import ma.ensi.dao.PortfolioDAO;
import ma.ensi.model.*;

import java.util.List;

public class PortfolioService {
    private final PortfolioDAO portfolioDAO = new PortfolioDAO();

    public void createPortfolio(Portfolio portfolio, List<Competence> competences,
                                List<Experience> experiences, List<Projet> projets) {
        // Création du portfolio
        int portfolioId = portfolioDAO.createPortfolio(portfolio);

        // Sauvegarde des compétences
        for (Competence competence : competences) {
            competence.setIdPortfolio(portfolioId);
            portfolioDAO.addCompetence(competence);
        }

        // Sauvegarde des expériences
        for (Experience experience : experiences) {
            experience.setIdPortfolio(portfolioId);
            portfolioDAO.addExperience(experience);
        }

        // Sauvegarde des projets
        for (Projet projet : projets) {
            projet.setIdPortfolio(portfolioId);
            portfolioDAO.addProjet(projet);
        }
    }

    public Portfolio getPortfolioByUserId(int idUtilisateur) {
        PortfolioDAO portfolioDAO = new PortfolioDAO(); // Create an instance of PortfolioDAO
        return portfolioDAO.getPortfolioByUserId(idUtilisateur); // Call the method on the instance
    }

}
