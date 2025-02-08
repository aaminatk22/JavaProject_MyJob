package ma.ensi.controller;

import ma.ensi.model.*;
import ma.ensi.service.PortfolioService;
import ma.ensi.service.VerificationRequestService;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/candidat/createPortfolio")
@MultipartConfig
public class PortfolioController extends HttpServlet {
    private final PortfolioService portfolioService = new PortfolioService();
    private final VerificationRequestService verificationService = new VerificationRequestService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("userId") == null) {
                response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
                return;
            }

            // Récupération de l'utilisateur connecté
            int userId = (int) session.getAttribute("userId");
            String description = request.getParameter("description");

            // Création d'un portfolio
            Portfolio portfolio = new Portfolio();
            portfolio.setIdUtilisateur(userId);
            portfolio.setDescription(description);

            // Récupération des compétences
            List<Competence> competences = new ArrayList<>();
            String[] competenceNames = request.getParameterValues("competences[][name]");
            String[] competenceLevels = request.getParameterValues("competences[][level]");
            if (competenceNames != null && competenceLevels != null) {
                for (int i = 0; i < competenceNames.length; i++) {
                    Competence competence = new Competence();
                    competence.setNom(competenceNames[i]);
                    competence.setNiveau(competenceLevels[i]);
                    competences.add(competence);
                }
            }

            // Récupération des expériences
            List<Experiences> experiences = new ArrayList<>();
            String[] experienceTitles = request.getParameterValues("experiences[][title]");
            String[] experienceCompanies = request.getParameterValues("experiences[][company]");
            String[] experienceStartDates = request.getParameterValues("experiences[][startDate]");
            String[] experienceEndDates = request.getParameterValues("experiences[][endDate]");
            String[] experienceDescriptions = request.getParameterValues("experiences[][description]");
            if (experienceTitles != null && experienceCompanies != null) {
                for (int i = 0; i < experienceTitles.length; i++) {
                    Experiences experience = new Experiences();
                    experience.setTitre(experienceTitles[i]);
                    experience.setEntreprise(experienceCompanies[i]);
                    experience.setDateDebut(java.sql.Date.valueOf(experienceStartDates[i]));
                    experience.setDateFin(java.sql.Date.valueOf(experienceEndDates[i]));
                    experience.setDescription(experienceDescriptions[i]);
                    experiences.add(experience);
                }
            }

            // Récupération des projets
            List<Projet> projets = new ArrayList<>();
            String[] projectTitles = request.getParameterValues("projects[][title]");
            String[] projectDescriptions = request.getParameterValues("projects[][description]");
            String[] projectTechnologies = request.getParameterValues("projects[][technologies]");
            if (projectTitles != null && projectDescriptions != null) {
                for (int i = 0; i < projectTitles.length; i++) {
                    Projet projet = new Projet();
                    projet.setTitre(projectTitles[i]);
                    projet.setDescription(projectDescriptions[i]);
                    projet.setTechnologie(projectTechnologies[i]);
                    projets.add(projet);
                }
            }

            // Récupération des documents (nouveau)
            List<Document> documents = new ArrayList<>();
            Part filePart = request.getPart("documents[][file]"); // File input
            String documentType = request.getParameter("documents[][type]");

            if (filePart != null && documentType != null) {
                Document document = new Document();
                document.setType(documentType);
                documents.add(document);
            }

            // Process the portfolio creation
            portfolioService.createPortfolio(portfolio, competences, experiences, projets, documents);



            response.sendRedirect(request.getContextPath() + "/annonces");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create portfolio.");
        }
    }
}
