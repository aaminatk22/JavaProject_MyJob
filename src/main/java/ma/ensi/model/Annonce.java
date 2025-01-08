package ma.ensi.model;

public class Annonce {
    private int idAnnonce;
    private String titre;
    private String typeAnnonce;
    private String description;
    private String datePublication;
    private int idUtilisateur;

    // Getters and Setters
    public int getIdAnnonce() { return idAnnonce; }
    public void setIdAnnonce(int idAnnonce) { this.idAnnonce = idAnnonce; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getTypeAnnonce() { return typeAnnonce; }
    public void setTypeAnnonce(String typeAnnonce) { this.typeAnnonce = typeAnnonce; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDatePublication() { return datePublication; }
    public void setDatePublication(String datePublication) { this.datePublication = datePublication; }

    public int getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }
}
