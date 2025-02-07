package ma.ensi.service;

import ma.ensi.dao.VerificationRequestDAO;
import ma.ensi.model.VerificationRequest;

import java.sql.SQLException;

public class VerificationRequestService {
    private final VerificationRequestDAO verificationRequestDAO = new VerificationRequestDAO();

    public void saveVerificationRequest(VerificationRequest request) throws SQLException {
        verificationRequestDAO.saveVerificationRequest(request);
    }
}
