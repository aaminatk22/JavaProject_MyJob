package ma.ensi.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Entretien {
    private int idEntretien;
    private int idCandidature;
    private LocalDate dateEntretien;
    private LocalTime heureEntretien;
    private String lieu;
    private String statut;

    // Getters and Setters
    public int getIdEntretien() {
        return idEntretien;
    }

    public void setIdEntretien(int idEntretien) {
        this.idEntretien = idEntretien;
    }

    public int getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(int idCandidature) {
        this.idCandidature = idCandidature;
    }

    public LocalDate getDateEntretien() {
        return dateEntretien;
    }

    public void setDateEntretien(LocalDate dateEntretien) {
        this.dateEntretien = dateEntretien;
    }

    public LocalTime getHeureEntretien() {
        return heureEntretien;
    }

    public void setHeureEntretien(LocalTime heureEntretien) {
        this.heureEntretien = heureEntretien;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
