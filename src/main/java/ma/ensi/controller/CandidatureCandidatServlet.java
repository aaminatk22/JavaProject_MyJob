package ma.ensi.controller;

import ma.ensi.model.Annonce;
import ma.ensi.model.Candidature;
import ma.ensi.service.AnnonceService;
import ma.ensi.service.CandidatureService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/MesCandidatures")
public class CandidatureCandidatServlet extends HttpServlet {
    private final CandidatureService candidatureService = new CandidatureService();
    private final AnnonceService annonceService = new AnnonceService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Vérifier si l'utilisateur est connecté
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        // Récupérer les détails de l'utilisateur
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if ("candidat".equalsIgnoreCase(role)) {
            // Récupérer les candidatures du candidat connecté
            List<Candidature> candidatures = candidatureService.findByCandidat(userId);
            request.setAttribute("candidatures", candidatures);
            request.getRequestDispatcher("/views/candidat/mesCandidatures.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        }
    }

}
