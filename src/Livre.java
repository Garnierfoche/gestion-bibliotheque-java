public class Livre {

    private int id;
    private String titre;
    private String auteur;
    private String isbn;
    private int anneePublication;
    private String categorie;

    public Livre(int id, String titre, String auteur, String isbn,
                 int anneePublication, String categorie) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
        this.categorie = categorie;
    }

    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public String getIsbn() { return isbn; }
    public int getAnneePublication() { return anneePublication; }
    public String getCategorie() { return categorie; }

    public void afficherDetails() {
        System.out.println("Titre : " + titre);
        System.out.println("Auteur : " + auteur);
        System.out.println("ISBN : " + isbn);
        System.out.println("Année : " + anneePublication);
        System.out.println("Catégorie : " + categorie);
    }
}
