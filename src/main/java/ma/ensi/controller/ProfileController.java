package ma.ensi.controller;

import ma.ensi.model.*;
import ma.ensi.service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
@MultipartConfig
public class ProfileController extends HttpServlet {
    private final ProfileService profileService = new ProfileService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is logged in
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        if (utilisateur == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Render the profile creation page
        request.getRequestDispatcher("/views/candidat/CreerProfil.jsp").forward(request, response);
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

            // Update username and password in the database
            profileService.updatePersonalInfo(utilisateur);

            // Step 3: Retrieve personal information
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String tel = request.getParameter("tel"); // Retrieve the `tel` field

            utilisateur.setNom(firstName);
            utilisateur.setPrenom(lastName);
            utilisateur.setEmail(email);
            utilisateur.setTel(tel); // Set `tel` field

            // Update personal information in the database
            profileService.updatePersonalInfo(utilisateur);

            // Step 4: Retrieve form inputs
            String description = request.getParameter("description");
            String university = request.getParameter("university");
            String levelOfStudy = request.getParameter("level");
            String languages = request.getParameter("languages");

            // Step 5: Parse dynamic fields (projects, skills, experiences)
            List<Competence> competences = parseCompetences(request);
            List<Projet> projets = parseProjets(request);
            List<Experience> experiences = parseExperiences(request);

            // Step 6: Handle Resume Upload
            Part resumePart = request.getPart("resume");
            String filePath = null;
            if (resumePart != null && resumePart.getSize() > 0) {
                String fileName = resumePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath("") + "/uploads/";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                filePath = uploadPath + fileName;
                resumePart.write(filePath);
            }

            // Create Document object for resume
            Document resumeDocument = null;
            if (filePath != null) {
                resumeDocument = new Document();
                resumeDocument.setType("Resume");
                resumeDocument.setFilePath(filePath);
            }

            // Step 7: Save Profile
            Portfolio portfolio = profileService.createProfile(
                    idUtilisateur,
                    description,
                    competences,
                    projets,
                    experiences,
                    resumeDocument
            );

            // Step 8: Redirect to a success page
            if (portfolio != null) {
                response.sendRedirect(request.getContextPath() + "/views/candidat/annonces.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving profile.");
            }

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
    private List<Experience> parseExperiences(HttpServletRequest request) {
        List<Experience> experiences = new ArrayList<>();
        String[] experienceTitles = request.getParameterValues("experienceTitle");
        String[] experienceCompanies = request.getParameterValues("experienceCompany");
        String[] experienceDescriptions = request.getParameterValues("experienceDescription");

        if (experienceTitles != null && experienceCompanies != null && experienceDescriptions != null) {
            for (int i = 0; i < experienceTitles.length; i++) {
                if (experienceTitles[i] != null && !experienceTitles[i].trim().isEmpty()
                        && experienceCompanies[i] != null && !experienceCompanies[i].trim().isEmpty()
                        && experienceDescriptions[i] != null && !experienceDescriptions[i].trim().isEmpty()) {
                    Experience experience = new Experience();
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
