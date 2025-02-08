package ma.ensi.model;

public class Candidat extends Utilisateur {
    private int age; // Candidate's age
    private String nomUniversite; // Candidate's university name
    private String niveauEtude; // Candidate's education level

    // Default Constructor
    public Candidat() {
        super(); // Calls the parent (Utilisateur) default constructor
    }

    // Parameterized Constructor
    public Candidat(int idUtilisateur, String nomUtilisateur, String email, String motDePasse, String role,
                    String nom, String prenom, String tel, int age, String nomUniversite, String niveauEtude) {
        super(); // Call the parent class constructor
        this.setIdUtilisateur(idUtilisateur);
        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setMotDePasse(motDePasse);
        this.setRole(role);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setTel(tel);

        this.age = age;
        this.nomUniversite = nomUniversite;
        this.niveauEtude = niveauEtude;
    }



    // Getters and Setters for additional fields

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNomUniversite() {
        return nomUniversite;
    }

    public void setNomUniversite(String nomUniversite) {
        this.nomUniversite = nomUniversite;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    // Additional Method: consulterPortfolio
    public void consulterPortfolio() {
        System.out.println("Portfolio du candidat: " + this.getNom() + " " + this.getPrenom());
        System.out.println("Âge: " + this.age);
        System.out.println("Université: " + this.nomUniversite);
        System.out.println("Niveau d'étude: " + this.niveauEtude);
        System.out.println("Téléphone: " + this.getTel());
    }



}
