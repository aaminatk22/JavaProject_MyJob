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
public class AnnonceServlet extends HttpServlet {
    private final AnnonceService annonceService = new AnnonceService();

    // Affichage des annonces
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Annonce> annonces = annonceService.getAllAnnonces();
        request.setAttribute("annonces", annonces);
        request.getRequestDispatcher("/views/recruteur/annonces.jsp").forward(request, response);
    }

    // Ajout d'une nouvelle annonce
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titre = request.getParameter("titre");
        String typeAnnonce = request.getParameter("typeAnnonce");
        String description = request.getParameter("description");
        String datePublication = request.getParameter("datePublication");
        int idUtilisateur = Integer.parseInt(request.getParameter("idUtilisateur"));

        Annonce annonce = new Annonce();
        annonce.setTitre(titre);
        annonce.setTypeAnnonce(typeAnnonce);
        annonce.setDescription(description);
        annonce.setDatePublication(datePublication);
        annonce.setIdUtilisateur(idUtilisateur);

        boolean success = annonceService.addAnnonce(annonce);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/annonces");
        } else {
            response.getWriter().println("<h3>Erreur lors de l'ajout de l'annonce.</h3>");
        }
    }

    // Modification d'une annonce
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idAnnonce = Integer.parseInt(request.getParameter("idAnnonce"));
        String titre = request.getParameter("titre");
        String typeAnnonce = request.getParameter("typeAnnonce");
        String description = request.getParameter("description");
        String datePublication = request.getParameter("datePublication");

        Annonce annonce = new Annonce();
        annonce.setIdAnnonce(idAnnonce);
        annonce.setTitre(titre);
        annonce.setTypeAnnonce(typeAnnonce);
        annonce.setDescription(description);
        annonce.setDatePublication(datePublication);

        boolean success = annonceService.updateAnnonce(annonce);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/annonces");
        } else {
            response.getWriter().println("<h3>Erreur lors de la mise à jour de l'annonce.</h3>");
        }
    }

    // Suppression d'une annonce
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idAnnonce = Integer.parseInt(request.getParameter("idAnnonce"));

        boolean success = annonceService.deleteAnnonce(idAnnonce);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/annonces");
        } else {
            response.getWriter().println("<h3>Erreur lors de la suppression.</h3>");
        }
    }
}
