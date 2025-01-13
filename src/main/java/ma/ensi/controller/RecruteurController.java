package ma.ensi.controller;

import ma.ensi.service.RecruteurService;
import ma.ensi.model.Recruteur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recruteurs")
public class RecruteurController extends HttpServlet {
    private final RecruteurService recruteurService = new RecruteurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Recruteur> recruteurs = recruteurService.getAllRecruteurs();

        if (recruteurs != null) {

            request.getSession().setAttribute("recruteurs", recruteurs);

            response.sendRedirect(request.getContextPath() + "/views/admin/recruteurs.jsp");
        } else {

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve recruiters.");
        }
    }
}
