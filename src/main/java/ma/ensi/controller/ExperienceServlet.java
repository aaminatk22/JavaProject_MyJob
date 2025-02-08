package ma.ensi.controller;

import ma.ensi.dao.ExperienceDAO;
import ma.ensi.model.Experiences;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ExperienceServlet")
public class ExperienceServlet extends HttpServlet {
    private ExperienceDAO experienceDAO = new ExperienceDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String candidateName = request.getParameter("candidate");

        if (candidateName != null && !candidateName.isEmpty()) {
            List<Experiences> experiences = experienceDAO.getExperiencesByCandidate(candidateName);

            // Convert to JSON and send response
            String json = new Gson().toJson(experiences);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}
