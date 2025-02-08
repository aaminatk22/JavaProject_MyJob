package ma.ensi.controller;

import ma.ensi.model.Annonce;
import ma.ensi.model.Utilisateur;
import ma.ensi.service.AnnonceService;
import ma.ensi.service.RecruteurService;
import ma.ensi.model.Recruteur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/recruteur")
public class RecruteurController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AnnonceService annonceService = new AnnonceService(); // Assuming you have this service

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the authenticated user (recruiter)
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        if (utilisateur == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Fetch the recruiter's announcements by their ID
        List<Annonce> annonces = annonceService.getAnnoncesByRecruiter(utilisateur.getIdUtilisateur());

        // Set the fetched annonces as a request attribute
        request.setAttribute("annonces", annonces);

        // Forward the request to the view
        request.getRequestDispatcher("/views/recruteur/annonces.jsp").forward(request, response);
    }
}

