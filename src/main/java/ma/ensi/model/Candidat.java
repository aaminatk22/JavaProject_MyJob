package ma.ensi.model;

import java.sql.Timestamp;

public class Candidat extends Utilisateur {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String resume;
    private String profilePicture;
    private String bio;
    private Timestamp createdAt;
    private int age;

    // Default constructor
    public Candidat() {}

    // Parameterized constructor
    public Candidat(int id, String firstName, String lastName, String email, String password, String resume, String profilePicture, String bio, Timestamp createdAt, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.resume = resume;
        this.profilePicture = profilePicture;
        this.bio = bio;
        this.createdAt = createdAt;
        this.age = age;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Methods for consulter_* (consult operations)
    public void consulterAnnonce() {
        // Implementation for consulting job announcements
    }

    public void consulterCandidature() {
        // Implementation for consulting job applications
    }

    public void consulterPlanningEntretien() {
        // Implementation for consulting interview schedules
    }

    public void consulterPortfolio() {
        // Implementation for consulting portfolio
    }
}
