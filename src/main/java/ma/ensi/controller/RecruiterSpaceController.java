package ma.ensi.controller;

import ma.ensi.service.CandidatureService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/recruiter/dashboard")
public class RecruiterSpaceController extends HttpServlet {
    private final CandidatureService candidatureService = new CandidatureService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId");

        // Retrieve candidature data
        int totalCandidatures = candidatureService.getTotalCandidaturesByRecruiter(userId);
        int acceptedCandidatures = candidatureService.getAcceptedCandidaturesByRecruiter(userId);
        int rejectedCandidatures = candidatureService.getRejectedCandidaturesByRecruiter(userId);

        // Pass data to the JSP
        request.setAttribute("totalCandidatures", totalCandidatures);
        request.setAttribute("acceptedCandidatures", acceptedCandidatures);
        request.setAttribute("rejectedCandidatures", rejectedCandidatures);

        // Forward to the dashboard JSP
        request.getRequestDispatcher("/views/recruteur/dashboard.jsp").forward(request, response);
    }
}
