package ma.ensi.controller;

import ma.ensi.model.*;
import ma.ensi.service.PortfolioService;
import ma.ensi.service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
@MultipartConfig
public class ProfileController extends HttpServlet {
    private final ProfileService profileService = new ProfileService();
    private final PortfolioService portfolioService = new PortfolioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        if (utilisateur == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Retrieve portfolio associated with the user
        Portfolio portfolio = portfolioService.getPortfolioByUserId(utilisateur.getIdUtilisateur());

        // Add attributes to the request
        request.setAttribute("utilisateur", utilisateur);
        request.setAttribute("portfolio", portfolio);
        if (utilisateur instanceof Candidat) {
            Candidat candidat = (Candidat) utilisateur;
            request.setAttribute("nomUniversite", candidat.getNomUniversite()); // Pass the university name
        }

        // Forward to the profile view page
        request.getRequestDispatcher("/views/candidat/CandidatProfil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Step 1: Check user
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null || !"candidat".equals(utilisateur.getRole())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
                return;
            }
            int idUtilisateur = utilisateur.getIdUtilisateur();

            // Step 2: Retrieve username and password
            String newUsername = request.getParameter("newUsername");
            String newPassword = request.getParameter("newPassword");

            if (newUsername != null && !newUsername.isEmpty()) {
                utilisateur.setNomUtilisateur(newUsername);
            }
            if (newPassword != null && !newPassword.isEmpty()) {
                utilisateur.setMotDePasse(newPassword);
            }

            // Step 3: Retrieve personal information
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String tel = request.getParameter("tel");
            int age = Integer.parseInt(request.getParameter("age")); // Retrieve age
            String university = request.getParameter("university"); // Retrieve university

            utilisateur.setNom(firstName);
            utilisateur.setPrenom(lastName);
            utilisateur.setEmail(email);
            utilisateur.setTel(tel);

            // Cast to Candidat and set age and university
            if (utilisateur instanceof Candidat) {
                Candidat candidat = (Candidat) utilisateur;
                candidat.setAge(age); // Set age
                candidat.setNomUniversite(university); // Set university
            }

            // Update personal information in the database
            profileService.updatePersonalInfo(utilisateur);

            // Rest of the code...
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing profile.");
        }
    }

    // Helper method to parse competences
    private List<Competence> parseCompetences(HttpServletRequest request) {
        List<Competence> competences = new ArrayList<>();
        String[] competenceNames = request.getParameterValues("competence");
        String[] competenceLevels = request.getParameterValues("competenceLevel");

        if (competenceNames != null && competenceLevels != null) {
            for (int i = 0; i < competenceNames.length; i++) {
                if (competenceNames[i] != null && !competenceNames[i].trim().isEmpty()
                        && competenceLevels[i] != null && !competenceLevels[i].trim().isEmpty()) {
                    Competence competence = new Competence();
                    competence.setNom(competenceNames[i].trim());
                    competence.setNiveau(competenceLevels[i].trim());
                    competences.add(competence);
                }
            }
        }
        return competences;
    }

    // Helper method to parse projects
    private List<Projet> parseProjets(HttpServletRequest request) {
        List<Projet> projets = new ArrayList<>();
        String[] projectTitles = request.getParameterValues("projectTitle");
        String[] projectDescriptions = request.getParameterValues("projectDescription");

        if (projectTitles != null && projectDescriptions != null) {
            for (int i = 0; i < projectTitles.length; i++) {
                if (projectTitles[i] != null && !projectTitles[i].trim().isEmpty()
                        && projectDescriptions[i] != null && !projectDescriptions[i].trim().isEmpty()) {
                    Projet projet = new Projet();
                    projet.setTitre(projectTitles[i].trim());
                    projet.setDescription(projectDescriptions[i].trim());
                    projets.add(projet);
                }
            }
        }
        return projets;
    }

    // Helper method to parse experiences
    private List<Experiences> parseExperiences(HttpServletRequest request) {
        List<Experiences> experiences = new ArrayList<>();
        String[] experienceTitles = request.getParameterValues("experienceTitle");
        String[] experienceCompanies = request.getParameterValues("experienceCompany");
        String[] experienceDescriptions = request.getParameterValues("experienceDescription");

        if (experienceTitles != null && experienceCompanies != null && experienceDescriptions != null) {
            for (int i = 0; i < experienceTitles.length; i++) {
                if (experienceTitles[i] != null && !experienceTitles[i].trim().isEmpty()
                        && experienceCompanies[i] != null && !experienceCompanies[i].trim().isEmpty()
                        && experienceDescriptions[i] != null && !experienceDescriptions[i].trim().isEmpty()) {
                    Experiences experience = new Experiences();
                    experience.setTitre(experienceTitles[i].trim());
                    experience.setEntreprise(experienceCompanies[i].trim());
                    experience.setDescription(experienceDescriptions[i].trim());
                    experiences.add(experience);
                }
            }
        }
        return experiences;
    }
}
