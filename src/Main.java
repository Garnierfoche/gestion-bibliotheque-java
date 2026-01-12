import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Bibliotheque bibliotheque = new Bibliotheque();
        int choix;

        do {
            System.out.println("\n===== MENU BIBLIOTHEQUE =====");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher les livres");
            System.out.println("3. Rechercher un livre par titre");
            System.out.println("4. Ajouter un membre");
            System.out.println("5. Rechercher un membre par nom");
            System.out.println("6. Enregistrer un emprunt");
            System.out.println("7. Retourner un livre");
            System.out.println("0. Quitter");

            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // vider le buffer

            switch (choix) {

                case 1:
                    System.out.print("ID : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Titre : ");
                    String titre = scanner.nextLine();

                    System.out.print("Auteur : ");
                    String auteur = scanner.nextLine();

                    System.out.print("Catégorie : ");
                    String categorie = scanner.nextLine();

                    System.out.print("Nombre d'exemplaires : ");
                    int nb = scanner.nextInt();

                    Livre livre = new Livre(id, titre, auteur, categorie, nb);
                    bibliotheque.ajouterLivre(livre);
                    break;

                case 2:
                    bibliotheque.afficherLivres();
                    break;

                case 3:
                    System.out.print("Titre à rechercher : ");
                    String t = scanner.nextLine();
                    bibliotheque.rechercherLivreParTitre(t);
                    break;

                case 4:
                    System.out.print("ID : ");
                    int idM = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();

                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();

                    System.out.print("Email : ");
                    String email = scanner.nextLine();

                    Membre membre = new Membre(
                            idM, nom, prenom, email, LocalDate.now()
                    );
                    bibliotheque.ajouterMembre(membre);
                    break;

                case 5:
                    System.out.print("Nom à rechercher : ");
                    String n = scanner.nextLine();
                    bibliotheque.rechercherMembreParNom(n);
                    break;

                case 6:
                    System.out.print("ID Emprunt : ");
                    int idE = scanner.nextInt();

                    System.out.print("ID Membre : ");
                    int idMb = scanner.nextInt();

                    System.out.print("ID Livre : ");
                    int idL = scanner.nextInt();

                    Emprunt emprunt = new Emprunt(
                            idE,
                            idMb,
                            idL,
                            LocalDate.now(),
                            LocalDate.now().plusDays(7)
                    );

                    bibliotheque.enregistrerEmprunt(emprunt);
                    break;

                case 7:
                    System.out.print("ID Emprunt : ");
                    int idRetour = scanner.nextInt();
                    bibliotheque.retournerLivre(idRetour);
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide !");
            }

        } while (choix != 0);

        scanner.close();
    }
}
