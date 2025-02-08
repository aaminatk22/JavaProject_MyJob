package ma.ensi.model;

public class Examination {
    private int id;
    private String documentName;  // ✅ Changed from title to documentName
    private String candidateName;
    private String submissionDate;
    private String status;

    public Examination(int id, String documentName, String candidateName, String submissionDate, String status) {
        this.id = id;
        this.documentName = documentName;
        this.candidateName = candidateName;
        this.submissionDate = submissionDate;
        this.status = status;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public String getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(String submissionDate) { this.submissionDate = submissionDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

