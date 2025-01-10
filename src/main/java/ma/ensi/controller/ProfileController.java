package ma.ensi.controller;

import ma.ensi.model.Portfolio;
import ma.ensi.model.Utilisateur;
import ma.ensi.service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile")
@MultipartConfig // Enables handling file uploads
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
        request.getRequestDispatcher("/views/candidat/createProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Step 1: Retrieve candidate ID and form data
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null || !utilisateur.getRole().equals("candidat")) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
                return;
            }
            int idUtilisateur = utilisateur.getIdUtilisateur();

            String description = request.getParameter("description");
            String competence1 = request.getParameter("competence1");
            String competence2 = request.getParameter("competence2");
            String projet1 = request.getParameter("projet1");
            String projetDescription1 = request.getParameter("projetDescription1");
            String experience1 = request.getParameter("experience1");
            String entreprise1 = request.getParameter("entreprise1");

            // Step 2: Handle file upload
            Part resumePart = request.getPart("resume");
            String fileName = resumePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + "/uploads/";
            String filePath = uploadPath + fileName;
            resumePart.write(filePath);

            // Step 3: Create the portfolio and related entities
            Portfolio portfolio = profileService.createProfile(
                    idUtilisateur, description, competence1, competence2, projet1, projetDescription1,
                    experience1, entreprise1, filePath
            );

            // Step 4: Redirect to profile view page
            if (portfolio != null) {
                response.sendRedirect(request.getContextPath() + "/views/candidat/CreerProfil.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving profile.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing profile.");
        }
    }
}
