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
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        List<Annonce> annonces = annonceService.getAllAnnonces();

        if (annonces != null) {
            session.setAttribute("annonces", annonces);
            response.sendRedirect(request.getContextPath() + "/views/candidat/annonces.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve annonces.");
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
