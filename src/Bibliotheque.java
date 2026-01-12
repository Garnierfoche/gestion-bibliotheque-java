import java.util.ArrayList;
import java.time.LocalDate;

public class Bibliotheque {

    private ArrayList<Livre> livres;
    private ArrayList<Membre> membres;
    private ArrayList<Emprunt> emprunts;

    public Bibliotheque() {
        livres = new ArrayList<>();
        membres = new ArrayList<>();
        emprunts = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
        System.out.println("Livre ajouté avec succès !");
    }

    public void afficherLivres() {
        for (Livre livre : livres) {
            livre.afficherDetails();
            System.out.println("--------------------");
        }
    }

    public void rechercherLivreParTitre(String titre) {
        for (Livre livre : livres) {
            if (livre.getTitre().equalsIgnoreCase(titre)) {
                livre.afficherDetails();
                return;
            }
        }
        System.out.println("Livre non trouvé.");
    }

    public void ajouterMembre(Membre membre) {
        membres.add(membre);
        System.out.println("Membre inscrit avec succès !");
    }

    public void rechercherMembreParNom(String nom) {
        for (Membre membre : membres) {
            if (membre.getNom().equalsIgnoreCase(nom)) {
                membre.afficherDetails();
                return;
            }
        }
        System.out.println("Membre non trouvé.");
    }

    public void enregistrerEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
        System.out.println("Emprunt enregistré !");
    }

    public void retournerLivre(int idEmprunt) {
        for (Emprunt e : emprunts) {
            if (e.getIdEmprunt() == idEmprunt) {
                e.setDateRetourEffective(LocalDate.now());
                System.out.println("Livre retourné.");
                System.out.println("Pénalité : " + e.calculerPenalite() + " F CFA");
                return;
            }
        }
        System.out.println("Emprunt introuvable.");
    }
}
