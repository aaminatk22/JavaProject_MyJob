package ma.ensi.controller;

import ma.ensi.model.Candidat;
import ma.ensi.service.CandidatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CandidatServlet")
public class CandidatServlet extends HttpServlet {
    private final CandidatService candidatService = new CandidatService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Candidat> candidats = candidatService.getAllCandidats();
            request.setAttribute("candidats", candidats);
            request.getRequestDispatcher("candidats.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int idCandidat = Integer.parseInt(request.getParameter("id"));
            try {
                candidatService.deleteCandidat(idCandidat);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("CandidatServlet");
        } else if ("edit".equals(action)) {
            int idCandidat = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String universite = request.getParameter("universite");
            String etablissement = request.getParameter("etablissement");

            Candidat candidat = new Candidat();
            candidat.setIdUtilisateur(idCandidat);
            candidat.setNom(nom);
            candidat.setPrenom(prenom);
            candidat.setNomUniversite(universite);
            candidat.setNiveauEtude(etablissement);

            try {
                candidatService.updateCandidat(candidat);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("CandidatServlet");
        }
    }
}
