package ma.ensi.model;

public class admincandidat extends adminutilisateur {
    private static final int id_utilisateur = 0;
    private int age; // Candidate's age
    private String nom_universite; // Candidate's university name (updated to match database)
    private String niveau_etude; // Candidate's education level (updated to match database)

    // Default Constructor
    public admincandidat() {
        super(); // Appelle le constructeur par défaut de Utilisateur
       
    }

    // Parameterized Constructor
    public admincandidat(int id_utilisateur, String nom_utilisateur, String email, String mot_de_passe, String role,
                         String nom, String prenom, String tel, int age, String nom_universite, String niveau_etude) {
        super(nom_utilisateur, email, mot_de_passe, "candidat", nom, prenom); // Call the parent class constructor
        this.setId_utilisateur(id_utilisateur);
        this.setNom_utilisateur(nom_utilisateur);
        this.setEmail(email);
        this.setMot_de_passe(mot_de_passe);
        this.setRole(role);
        this.setNom(nom);
        this.setPrenom(prenom);


        this.age = age;
        this.nom_universite = nom_universite;
        this.niveau_etude = niveau_etude;
    }

    // Getters and Setters for additional fields

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom_universite() {
        return nom_universite;
    }

    public void setNom_universite(String nom_universite) {
        this.nom_universite = nom_universite;
    }

    public String getNiveau_etude() {
        return niveau_etude;
    }

    public void setNiveau_etude(String niveau_etude) {
        this.niveau_etude = niveau_etude;
    }

    // Additional Method: consulterPortfolio
    public void consulterPortfolio() {
        System.out.println("Portfolio du candidat: " + this.getNom() + " " + this.getPrenom());
        System.out.println("Âge: " + this.age);
        System.out.println("Université: " + this.nom_universite);
        System.out.println("Niveau d'étude: " + this.niveau_etude);

    }
}
