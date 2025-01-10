package ma.ensi.service;

import ma.ensi.dao.*;
import ma.ensi.model.*;

public class ProfileService {
    private final PortfolioDAO portfolioDAO = new PortfolioDAO();
    private final CompetenceDAO competenceDAO = new CompetenceDAO();
    private final ProjetDAO projetDAO = new ProjetDAO();
    private final ExperienceDAO experienceDAO = new ExperienceDAO();
    private final DocumentDAO documentDAO = new DocumentDAO();

    public Portfolio createProfile(int idUtilisateur, String description, String competence1, String competence2,
                                   String projet1, String projetDescription1, String experience1, String entreprise1,
                                   String resumePath) {
        try {
            System.out.println("Creating profile for user ID: " + idUtilisateur);

            // Step 1: Create the portfolio and link it to the user
            Portfolio portfolio = new Portfolio();
            portfolio.setIdUtilisateur(idUtilisateur);
            portfolio.setDescription(description);

            int portfolioId = portfolioDAO.savePortfolio(portfolio);
            System.out.println("Portfolio saved with ID: " + portfolioId);

            // Step 2: Save the document (resume) linked to the portfolio
            Document resume = new Document();
            resume.setIdPortfolio(portfolioId); // Link to the portfolio
            resume.setType("Resume");
            resume.setFilePath(resumePath);
            documentDAO.saveDocument(resume);

            return portfolio;
        } catch (Exception e) {
            System.err.println("Error in createProfile: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
