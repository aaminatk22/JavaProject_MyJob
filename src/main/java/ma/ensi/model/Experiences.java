package ma.ensi.model;

public class Experiences {
    private int id;
    private String candidateName;
    private String role;
    private String startDate;
    private String endDate;
    private String description;

    public Experiences(int id, String candidateName, String role, String startDate, String endDate, String description) {
        this.id = id;
        this.candidateName = candidateName;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
