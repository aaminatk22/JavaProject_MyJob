package ma.ensi.model;

import java.time.LocalDate;

public class Candidature {
    private int idCandidature;
    private int idAnnonce;
    private int idUtilisateur;
    private LocalDate dateSoumission;
    private String statut;
    private String messageCandidat;

    // Getters et Setters

    public void setIdCandidature(int idCandidature) {
        this.idCandidature = idCandidature;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public LocalDate getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(LocalDate dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getMessageCandidat() {
        return messageCandidat;
    }

    public void setMessageCandidat(String messageCandidat) {
        this.messageCandidat = messageCandidat;
    }
    
}
