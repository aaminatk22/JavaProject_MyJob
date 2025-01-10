package ma.ensi.controller;

import ma.ensi.model.Portfolio;
import ma.ensi.model.Utilisateur;
import ma.ensi.service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
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
            // Log the start of the request
            System.out.println("Starting profile creation");

            // Retrieve user and parameters
            Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
            if (utilisateur == null || !utilisateur.getRole().equals("candidat")) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
                return;
            }
            int idUtilisateur = utilisateur.getIdUtilisateur();
            System.out.println("User ID: " + idUtilisateur);

            // Validate parameters
            String description = request.getParameter("description");
            if (description == null || description.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Description is required.");
                return;
            }
            System.out.println("Description: " + description);

            // Handle file upload
            Part resumePart = request.getPart("resume");
            if (resumePart == null || resumePart.getSize() == 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Resume file is required.");
                return;
            }

            String fileName = resumePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + "/uploads/";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String filePath = uploadPath + fileName;
            System.out.println("File path: " + filePath);
            resumePart.write(filePath);

            // Call service to save profile
            Portfolio portfolio = profileService.createProfile(
                    idUtilisateur, description, request.getParameter("competence1"),
                    request.getParameter("competence2"), request.getParameter("projet1"),
                    request.getParameter("projetDescription1"), request.getParameter("experience1"),
                    request.getParameter("entreprise1"), filePath
            );

            // Handle response
            if (portfolio != null) {
                response.sendRedirect(request.getContextPath() + "/views/candidat/CreerProfil.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving profile.");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing profile.");
        }
    }

}
