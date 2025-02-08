package ma.ensi.controller;

import ma.ensi.dao.ExaminationDAO;
import ma.ensi.model.Examination;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ExaminationServlet")
public class ExaminationServlet extends HttpServlet {
    private ExaminationDAO examDAO = new ExaminationDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addExam(request, response);
        } else if ("approve".equals(action)) {
            updateExamStatus(request, response, "approved");
        } else if ("reject".equals(action)) {
            updateExamStatus(request, response, "rejected");
        } else if ("delete".equals(action)) {
            deleteExam(request, response);
        }
    }

    private void addExam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String documentName = request.getParameter("document_name");
        String candidateName = request.getParameter("candidate_name");
        String submissionDate = request.getParameter("submission_date");

        Examination exam = new Examination(0, documentName, candidateName, submissionDate, "pending");
        examDAO.addExam(exam);
        response.sendRedirect("Examination.jsp");
    }

    private void updateExamStatus(HttpServletRequest request, HttpServletResponse response, String status) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        examDAO.updateStatus(id, status);
        response.sendRedirect("Examination.jsp");
    }

    private void deleteExam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        examDAO.deleteExam(id);
        response.sendRedirect("Examination.jsp");
    }
}
