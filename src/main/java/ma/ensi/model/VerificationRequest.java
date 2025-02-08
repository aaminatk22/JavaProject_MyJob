package ma.ensi.model;

import java.sql.Timestamp;

public class VerificationRequest {
    private int id;
    private int idCandidat;
    private String universityName;
    private String documentType;
    private String status = "Pending";
    private Timestamp dateRequested;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCandidat() { return idCandidat; }
    public void setIdCandidat(int idCandidat) { this.idCandidat = idCandidat; }

    public String getUniversityName() { return universityName; }
    public void setUniversityName(String universityName) { this.universityName = universityName; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getDateRequested() { return dateRequested; }
    public void setDateRequested(Timestamp dateRequested) { this.dateRequested = dateRequested; }
}
