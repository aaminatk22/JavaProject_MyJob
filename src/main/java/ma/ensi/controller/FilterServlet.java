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

@WebServlet("/FilterServlet")
public class FilterServlet extends HttpServlet {
    private ExperienceDAO experienceDAO = new ExperienceDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String candidate = request.getParameter("candidate");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        List<Experiences> filteredResults = experienceDAO.getFilteredExperiences(candidate, role, status, startDate, endDate);

        String json = new Gson().toJson(filteredResults);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}

