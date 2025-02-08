package ma.ensi.controller;

import ma.ensi.model.Annonce;
import ma.ensi.model.Candidature;
import ma.ensi.model.Entretien;
import ma.ensi.service.AnnonceService;
import ma.ensi.service.CandidatureService;
import ma.ensi.service.EntretienService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/MesCandidatures")
public class CandidatureCandidatServlet extends HttpServlet {
    private final CandidatureService candidatureService = new CandidatureService();
    private final AnnonceService annonceService = new AnnonceService();
    private final EntretienService entretienService = new EntretienService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        try {
            Integer userId = (Integer) session.getAttribute("userId");
            String role = (String) session.getAttribute("role");

            if ("candidat".equalsIgnoreCase(role)) {
                List<Candidature> candidatures = candidatureService.findByCandidat(userId);

                // Log candidatures count
                System.out.println("DEBUG: Servlet - Candidatures found: " + candidatures.size());

                Map<Integer, Entretien> entretiensMap = new HashMap<>();
                if (candidatures != null) {
                    for (Candidature candidature : candidatures) {
                        List<Entretien> entretiens = entretienService.getEntretiensByCandidature(candidature.getIdCandidature());
                        if (entretiens != null && !entretiens.isEmpty()) {
                            entretiensMap.put(candidature.getIdCandidature(), entretiens.get(0));
                        }
                    }
                }

                request.setAttribute("candidatures", candidatures);
                request.setAttribute("entretiensMap", entretiensMap);
                request.getRequestDispatcher("/views/candidat/mesCandidatures.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/views/error.jsp");
        }
    }


}
