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
}
