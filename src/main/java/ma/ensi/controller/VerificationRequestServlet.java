package ma.ensi.controller;

import ma.ensi.model.VerificationRequest;
import ma.ensi.service.VerificationRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verificationRequest")
public class VerificationRequestServlet extends HttpServlet {

    private final VerificationRequestService verificationRequestService = new VerificationRequestService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve form data
            int candidateId = (int) request.getSession().getAttribute("userId");
            String universityName = request.getParameter("university");
            String documentType = request.getParameter("verificationDocumentType");

            // Create a new verification request
            VerificationRequest verificationRequest = new VerificationRequest();
            verificationRequest.setIdCandidat(candidateId);
            verificationRequest.setUniversityName(universityName);
            verificationRequest.setDocumentType(documentType);

            // Save the request
            verificationRequestService.saveVerificationRequest(verificationRequest);

            // Redirect or show success
            response.sendRedirect(request.getContextPath() + "/views/candidat/CandidatProfil.jsp?message=Verification+Requested");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/views/error.jsp");
        }
    }
}
