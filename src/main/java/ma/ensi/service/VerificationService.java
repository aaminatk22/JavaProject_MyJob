package ma.ensi.service;

public class VerificationService {

    public void sendVerificationRequest(int userId, String university, String documentType) {
        // Logic to handle the verification request
        // For example, save the request in the database or send an email
        System.out.println("Sending verification request for user " + userId + " to university: " + university);
        System.out.println("Document Type: " + documentType);

        // Here you could add logic to send an email or save the request to the database
    }
}
