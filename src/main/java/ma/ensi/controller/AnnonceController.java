package ma.ensi.controller;

import ma.ensi.model.Annonce;
import ma.ensi.service.AnnonceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/annonces")
public class AnnonceController extends HttpServlet {
    private final AnnonceService annonceService = new AnnonceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if the session exists and user is logged in
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Retrieve userId and role from session
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if ("candidat".equalsIgnoreCase(role)) {
            // Fetch all annonces for candidates
            List<Annonce> annonces = annonceService.getAllAnnonces();

            if (annonces != null) {
                // Store annonces in session and redirect to the candidate page
                session.setAttribute("annonces", annonces);
                response.sendRedirect(request.getContextPath() + "/views/candidat/annonces.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve annonces.");
            }
        } else if ("recruteur".equalsIgnoreCase(role)) {
            // Fetch only annonces shared by this recruiter
            List<Annonce> annonces = annonceService.getAnnoncesByRecruiter(userId);

            if (annonces != null) {
                // Store annonces in session and redirect to the recruiter space page
                session.setAttribute("annonces", annonces);
                response.sendRedirect(request.getContextPath() + "/views/recruteur/RecruiterSpace.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve recruiter-specific annonces.");
            }
        } else {
            // If role is unknown, redirect to the login page
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Retrieve form parameters
        String titre = request.getParameter("titre");
        String typeAnnonce = request.getParameter("typeAnnonce");
        String description = request.getParameter("description");


        // Retrieve user ID from the session
        int idUtilisateur;
        try {
            idUtilisateur = (int) session.getAttribute("userId");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Create a new Annonce object
        Annonce annonce = new Annonce();
        annonce.setTitre(titre);
        annonce.setTypeAnnonce(typeAnnonce);
        annonce.setDescription(description);
        annonce.setIdUtilisateur(idUtilisateur);

        // Use the service to add the annonce
        boolean isAdded = annonceService.addAnnonce(annonce);

        if (isAdded) {
            response.sendRedirect(request.getContextPath() + "/annonces");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add annonce.");
        }
    }
}
