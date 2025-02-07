package ma.ensi.model;

public class Document {
    private int idPortfolio; // Foreign key to portfolio
    private String type; // Type of document (e.g., Resume, Cover Letter)
    private String filePath; // Path to the file

    // Getters and Setters
    public int getIdPortfolio() {
        return idPortfolio;
    }

    public void setIdPortfolio(int idPortfolio) {
        this.idPortfolio = idPortfolio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setTypeDocument(String resume) {
        this.type = resume;
    }
}
