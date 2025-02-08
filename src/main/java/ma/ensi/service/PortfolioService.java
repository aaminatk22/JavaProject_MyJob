package ma.ensi.service;

import ma.ensi.dao.DocumentDAO;
import ma.ensi.dao.PortfolioDAO;
import ma.ensi.model.*;

import java.util.List;

public class PortfolioService {
    private final PortfolioDAO portfolioDAO = new PortfolioDAO();
    private final DocumentDAO documentDAO = new DocumentDAO();

    public void createPortfolio(Portfolio portfolio, List<Competence> competences,
                                List<Experiences> experiences, List<Projet> projets, List<Document> documents) {
        // Création du portfolio
        int portfolioId = portfolioDAO.createPortfolio(portfolio);

        // Sauvegarde des compétences
        for (Competence competence : competences) {
            competence.setIdPortfolio(portfolioId);
            portfolioDAO.addCompetence(competence);
        }

        // Sauvegarde des expériences
        for (Experiences experience : experiences) {
            experience.setIdPortfolio(portfolioId);
            portfolioDAO.addExperience(experience);
        }

        // Sauvegarde des projets
        for (Projet projet : projets) {
            projet.setIdPortfolio(portfolioId);
            portfolioDAO.addProjet(projet);
        }
    }

    public Portfolio getPortfolioByUserId(int userId) {
        Portfolio portfolio = portfolioDAO.getPortfolioByUserId(userId);

        if (portfolio != null) {
            // Retrieve and set documents for the portfolio
            List<Document> documents = portfolioDAO.getDocumentsByPortfolioId(portfolio.getIdPortfolio());
            portfolio.setDocuments(documents);
        }

        return portfolio;
    }
}
