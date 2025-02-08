package ma.ensi.controller;

import com.google.gson.Gson;
import ma.ensi.dao.DashboardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private DashboardDAO dashboardDAO = new DashboardDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> stats = dashboardDAO.getDashboardStatistics();

        // Convert to JSON
        String json = new Gson().toJson(stats);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
