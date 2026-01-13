import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class Bibliotheque {



    private ArrayList<Membre> membres;
    private ArrayList<Emprunt> emprunts;

    public Bibliotheque() {
        membres = new ArrayList<>();
        emprunts = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {

        String sql = "INSERT INTO books (title, author, isbn, publication_year, genre) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livre.getTitre());
            pstmt.setString(2, livre.getAuteur());
            pstmt.setString(3, livre.getIsbn());
            pstmt.setInt(4, livre.getAnneePublication());
            pstmt.setString(5, livre.getCategorie());

            pstmt.executeUpdate();
            System.out.println("📘 Livre ajouté avec succès dans la base");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // (sera connecté à PostgreSQL plus tard)
    public void afficherLivres() {

        String sql = "SELECT title, author, genre FROM books ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nLISTE DES LIVRES");
            System.out.println("--------------------");

            boolean vide = true;

            while (rs.next()) {
                vide = false;
                System.out.println("Titre : " + rs.getString("title"));
                System.out.println("Auteur : " + rs.getString("author"));
                System.out.println("Catégorie : " + rs.getString("genre"));
                System.out.println("--------------------");
            }

            if (vide) {
                System.out.println("Aucun livre dans la base de données.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'affichage des livres");
            e.printStackTrace();
        }
    }

    public void rechercherLivreParTitre(String titre) {

        String sql = "SELECT title, author, genre FROM books "
                + "WHERE LOWER(title) LIKE LOWER(?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + titre + "%");

            ResultSet rs = pstmt.executeQuery();

            boolean trouve = false;

            while (rs.next()) {
                trouve = true;
                System.out.println("\n LIVRE TROUVÉ");
                System.out.println("Titre : " + rs.getString("title"));
                System.out.println("Auteur : " + rs.getString("author"));
                System.out.println("Catégorie : " + rs.getString("genre"));
                System.out.println("--------------------");
            }

            if (!trouve) {
                System.out.println("❌ Aucun livre trouvé avec ce titre.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la recherche du livre");
            e.printStackTrace();
        }
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

        String sql = "INSERT INTO emprunts (membre_id, livre_id, date_emprunt, date_retour) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, emprunt.getIdMembre());
            pstmt.setInt(2, emprunt.getIdLivre());
            pstmt.setDate(3, java.sql.Date.valueOf(emprunt.getDateEmprunt()));
            pstmt.setDate(4, java.sql.Date.valueOf(emprunt.getDateRetour()));

            pstmt.executeUpdate();
            System.out.println("📄 Emprunt enregistré en base");

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void afficherEmprunts() {

        String sql =
                "SELECT e.id, e.membre_id, e.livre_id, e.date_emprunt, e.date_retour " +
                        "FROM emprunts e ORDER BY e.id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n📄 LISTE DES EMPRUNTS");
            System.out.println("--------------------");

            boolean vide = true;

            while (rs.next()) {
                vide = false;
                System.out.println("Emprunt ID : " + rs.getInt("id"));
                System.out.println("ID Membre  : " + rs.getInt("membre_id"));
                System.out.println("ID Livre   : " + rs.getInt("livre_id"));
                System.out.println("Date emprunt : " + rs.getDate("date_emprunt"));
                System.out.println("Date retour  : " + rs.getDate("date_retour"));
                System.out.println("--------------------");
            }

            if (vide) {
                System.out.println("Aucun emprunt enregistré.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'affichage des emprunts");
            e.printStackTrace();
        }
    }

}
