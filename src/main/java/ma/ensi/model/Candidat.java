package ma.ensi.model;

public class Candidat extends Utilisateur {
    private int age;
    private String nomUniversite;
    private String niveauEtude;

    // Default Constructor
    public Candidat() {
        super(); // Calls the parent (Utilisateur) default constructor
    }

    // Parameterized Constructor
    public Candidat(int idUtilisateur, String nomUtilisateur, String email, String motDePasse, String role,
                    String nom, String prenom, int age, String nomUniversite, String niveauEtude) {
        super(); // Call the parent class constructor if needed
        this.setIdUtilisateur(idUtilisateur);
        this.setNomUtilisateur(nomUtilisateur);
        this.setEmail(email);
        this.setMotDePasse(motDePasse);
        this.setRole(role);
        this.setNom(nom);
        this.setPrenom(prenom);

        this.age = age;
        this.nomUniversite = nomUniversite;
        this.niveauEtude = niveauEtude;
    }

    // Getters and Setters
    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        this.age = age;
    }

    public static String getNomUniversite() {
        return nomUniversite;
    }

    public static void setNomUniversite(String nomUniversite) {
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
    }
}
