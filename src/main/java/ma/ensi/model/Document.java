package ma.ensi.model;

public class Document {
    private int idDocument; // Identifiant unique du document
    private int idUtilisateur; // Référence à l'utilisateur (id_utilisateur dans la table utilisateur)
    private String type; // Type du document (par exemple, "CV", "Certification")
    private String filePath; // Chemin du fichier ou URL du document

    // Constructeur par défaut
    public Document() {}

    // Constructeur avec paramètres
    public Document(int idDocument, int idUtilisateur, String type, String filePath) {
        this.idDocument = idDocument;
        this.idUtilisateur = idUtilisateur;
        this.type = type;
        this.filePath = filePath;
    }

    // Getters et Setters
    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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
}
