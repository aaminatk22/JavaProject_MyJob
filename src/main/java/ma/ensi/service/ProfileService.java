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
            // Create the portfolio
            Portfolio portfolio = new Portfolio();
            portfolio.setIdUser(idUtilisateur);
            portfolio.setDescription(description);
            int portfolioId = portfolioDAO.savePortfolio(portfolio);

            // Add competencies
            if (competence1 != null && !competence1.isEmpty()) {
                Competence c1 = new Competence();
                c1.setIdPortfolio(portfolioId);
                c1.setNom(competence1);
                competenceDAO.saveCompetence(c1);
            }
            if (competence2 != null && !competence2.isEmpty()) {
                Competence c2 = new Competence();
                c2.setIdPortfolio(portfolioId);
                c2.setNom(competence2);
                competenceDAO.saveCompetence(c2);
            }

            // Add projects
            if (projet1 != null && !projet1.isEmpty()) {
                Projet projet = new Projet();
                projet.setIdPortfolio(portfolioId);
                projet.setTitre(projet1);
                projet.setDescription(projetDescription1);
                projetDAO.saveProjet(projet);
            }

            // Add experiences
            if (experience1 != null && !experience1.isEmpty()) {
                Experience experience = new Experience();
                experience.setIdPortfolio(portfolioId);
                experience.setTitre(experience1);
                experience.setEntreprise(entreprise1);
                experienceDAO.saveExperience(experience);
            }

            // Add document (resume)
            Document resume = new Document();
            resume.setIdUtilisateur(idUtilisateur);
            resume.setType("Resume");
            resume.setFilePath(resumePath);
            documentDAO.saveDocument(resume);

            return portfolio;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
