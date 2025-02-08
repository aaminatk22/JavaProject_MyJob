package ma.ensi.service;

import ma.ensi.dao.*;
import ma.ensi.model.*;

import java.util.List;

public class ProfileService {
    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private final PortfolioDAO portfolioDAO = new PortfolioDAO();
    private final CompetenceDAO competenceDAO = new CompetenceDAO();
    private final ProjetDAO projetDAO = new ProjetDAO();
    private final ExperienceDAO experienceDAO = new ExperienceDAO();
    private final DocumentDAO documentDAO = new DocumentDAO();




    /**
     * Update personal information, including the telephone number.
     *
     * @param utilisateur The user object containing updated information.
     */
    public void updatePersonalInfo(Utilisateur utilisateur) {
        try {
            System.out.println("Updating personal information for user ID: " + utilisateur.getIdUtilisateur());
            utilisateurDAO.updateUtilisateur(utilisateur); // Use DAO method to update user info
            System.out.println("Personal information updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update personal information");
        }
    }

    /**
     * Create a complete profile for a user.
     *
     * @param idUtilisateur The ID of the user creating the profile.
     * @param description The profile description.
     * @param competences List of competences associated with the profile.
     * @param projets List of projects associated with the profile.
     * @param experiences List of experiences associated with the profile.
     * @param resumeDocument The resume document to be associated with the profile.
     * @return The created Portfolio object.
     */
    public Portfolio createProfile(int idUtilisateur, String description,
                                   List<Competence> competences, List<Projet> projets,
                                   List<Experiences> experiences, Document resumeDocument) {
        try {
            System.out.println("Creating profile for user ID: " + idUtilisateur);

            // Step 1: Create the Portfolio and associate it with the user
            Portfolio portfolio = new Portfolio();
            portfolio.setIdUtilisateur(idUtilisateur);
            portfolio.setDescription(description);

            int portfolioId = portfolioDAO.createPortfolio(portfolio); // Save portfolio and get its ID
            portfolio.setIdPortfolio(portfolioId);

            // Step 2: Save Competences
            if (competences != null && !competences.isEmpty()) {
                for (Competence competence : competences) {
                    competence.setIdPortfolio(portfolioId); // Associate competence with portfolio
                    competenceDAO.saveCompetence(competence);
                }
                System.out.println("Competences saved successfully!");
            }

            // Step 3: Save Projects
            if (projets != null && !projets.isEmpty()) {
                for (Projet projet : projets) {
                    projet.setIdPortfolio(portfolioId); // Associate project with portfolio
                    projetDAO.saveProjet(projet);
                }
                System.out.println("Projects saved successfully!");
            }

            // Step 4: Save Experiences
            if (experiences != null && !experiences.isEmpty()) {
                for (Experiences experience : experiences) {
                    experience.setIdPortfolio(portfolioId); // Associate experience with portfolio
                    experienceDAO.saveExperience(experience);
                }
                System.out.println("Experiences saved successfully!");
            }

            // Step 5: Save Resume Document
            if (resumeDocument != null) {
                resumeDocument.setIdPortfolio(portfolioId); // Associate document with portfolio
                documentDAO.saveDocument(resumeDocument);
                System.out.println("Resume document saved successfully!");
            }

            return portfolio; // Return the created portfolio

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error occurred while creating profile");
        }
    }



}
