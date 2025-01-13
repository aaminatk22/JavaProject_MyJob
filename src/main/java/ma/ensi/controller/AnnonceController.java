package ma.ensi.controller;

import ma.ensi.service.AnnonceService;
import ma.ensi.model.Annonce;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/annonces")
public class AnnonceController extends HttpServlet {
    private final AnnonceService annonceService = new AnnonceService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Annonce> annonces = annonceService.getAllAnnonces();

        if (annonces != null) {
            request.getSession().setAttribute("annonces", annonces);
            response.sendRedirect(request.getContextPath() + "/views/candidat/annonces.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve annonces.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String titre = request.getParameter("titre");
        String typeAnnonce = request.getParameter("typeAnnonce");
        String description = request.getParameter("description");

        // Automatically get the user ID from the session (example)
        int idUtilisateur = (int) request.getSession().getAttribute("userId");

        // Create a new Annonce object
        Annonce annonce = new Annonce();
        annonce.setTitre(titre);
        annonce.setTypeAnnonce(typeAnnonce);
        annonce.setDescription(description);
        annonce.setIdUtilisateur(idUtilisateur); // This is optional if not used in the database for this operation.

        // Use the service to add the annonce
        boolean isAdded = annonceService.addAnnonce(annonce);

        if (isAdded) {
            // Redirect to the list of annonces if successful
            response.sendRedirect(request.getContextPath() + "/annonces");
        } else {
            // Return an error message if adding the annonce fails
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add annonce.");
        }
    }

}
