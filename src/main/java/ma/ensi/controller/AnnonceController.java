package ma.ensi.controller;

import ma.ensi.dao.AnnonceDAO;
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

        // Ensure user is logged in
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Retrieve user details
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        List<Annonce> annonces;
        if ("candidat".equalsIgnoreCase(role)) {
            annonces = annonceService.getAllAnnonces();
            session.setAttribute("annonces", annonces);
            response.sendRedirect(request.getContextPath() + "/views/candidat/annonces.jsp");
        } else if ("recruteur".equalsIgnoreCase(role)) {
            annonces = annonceService.getAnnoncesByRecruiter(userId);
            session.setAttribute("annonces", annonces);

            // Count the number of annonces
            AnnonceDAO annonceDAO = new AnnonceDAO();
            int annonceCount = annonceDAO.countAnnoncesByRecruiter(userId);

            // Log the count (optional)
            System.out.println("Nombre d'annonces : " + annonceCount);

            // Pass the attribute to the JSP
            request.setAttribute("annonceCount", annonceCount);

            // Forward to RecruiterSpace.jsp
            request.getRequestDispatcher("/views/recruteur/RecruiterSpace.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String method = request.getParameter("_method");
        if ("put".equalsIgnoreCase(method)) {
            doPut(request, response); // Manually call doPut()
            return;
        } else if ("delete".equalsIgnoreCase(method)) {
            doDelete(request, response); // Manually call doDelete()
            return;
        }

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
        int idUtilisateur = (int) session.getAttribute("userId");

        // Create a new Annonce object
        Annonce annonce = new Annonce();
        annonce.setTitre(titre);
        annonce.setTypeAnnonce(typeAnnonce);
        annonce.setDescription(description);
        annonce.setIdUtilisateur(idUtilisateur);

        // Add annonce using the service
        boolean isAdded = annonceService.addAnnonce(annonce);

        if (isAdded) {
            response.sendRedirect(request.getContextPath() + "/annonces");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Échec de l'ajout de l'annonce.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Retrieve form parameters
        String id = request.getParameter("id");
        String titre = request.getParameter("titre");
        String typeAnnonce = request.getParameter("typeAnnonce");
        String description = request.getParameter("description");

        if (id == null || id.isEmpty() ||
                titre == null || titre.isEmpty() ||
                typeAnnonce == null || typeAnnonce.isEmpty() ||
                description == null || description.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tous les champs sont obligatoires.");
            return;
        }

        int annonceId;
        try {
            annonceId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de l'annonce invalide.");
            return;
        }

        // Retrieve user ID from session
        int idUtilisateur = (int) session.getAttribute("userId");

        // Create updated Annonce object
        Annonce annonce = new Annonce();
        annonce.setIdAnnonce(annonceId);
        annonce.setTitre(titre);
        annonce.setTypeAnnonce(typeAnnonce);
        annonce.setDescription(description);
        annonce.setIdUtilisateur(idUtilisateur);

        // Update annonce using the service
        boolean isUpdated = annonceService.updateAnnonce(annonce);

        if (isUpdated) {
            response.sendRedirect(request.getContextPath() + "/annonces");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Échec de la mise à jour de l'annonce.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Vous devez être connecté.");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'ID de l'annonce est requis pour la suppression.");
            return;
        }

        int idAnnonce;
        try {
            idAnnonce = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID invalide.");
            return;
        }

        boolean isDeleted = annonceService.deleteAnnonce(idAnnonce);

        if (isDeleted) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect(request.getContextPath() + "/annonces");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Échec de la suppression de l'annonce.");
        }
    }
}