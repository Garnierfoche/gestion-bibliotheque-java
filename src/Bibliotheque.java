import java.util.ArrayList;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bibliotheque {


    private ArrayList<Membre> membres;

    public Bibliotheque() {
        membres = new ArrayList<>();
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
            System.out.println(" Livre ajouté avec succès dans la base de données");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du livre");
            e.printStackTrace();
        }
    }

    public void afficherLivres() {

        String sql = "SELECT id, title, author, isbn, publication_year, genre FROM books ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println(" LISTE DES LIVRES");
            System.out.println("--------------------");

            boolean vide = true;

            while (rs.next()) {
                vide = false;
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Titre : " + rs.getString("title"));
                System.out.println("Auteur : " + rs.getString("author"));
                System.out.println("ISBN : " + rs.getString("isbn"));
                System.out.println("Année : " + rs.getInt("publication_year"));
                System.out.println("Catégorie : " + rs.getString("genre"));
                System.out.println("--------------------");
            }

            if (vide) {
                System.out.println("Aucun livre trouvé.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'affichage des livres");
            e.printStackTrace();
        }
    }

    public void rechercherLivreParTitre(String titre) {

        String sql = "SELECT title, author, isbn, publication_year, genre "
                + "FROM books WHERE LOWER(title) LIKE LOWER(?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + titre + "%");
            ResultSet rs = pstmt.executeQuery();

            boolean trouve = false;

            while (rs.next()) {
                trouve = true;
                System.out.println(" LIVRE TROUVÉ");
                System.out.println("Titre : " + rs.getString("title"));
                System.out.println("Auteur : " + rs.getString("author"));
                System.out.println("ISBN : " + rs.getString("isbn"));
                System.out.println("Année : " + rs.getInt("publication_year"));
                System.out.println("Catégorie : " + rs.getString("genre"));
                System.out.println("--------------------");
            }

            if (!trouve) {
                System.out.println("❌ Aucun livre trouvé.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de la recherche");
            e.printStackTrace();
        }
    }

    // =========================
    // 👤 MEMBRES (MÉMOIRE)
    // =========================

    public void ajouterMembre(Membre membre) {
        membres.add(membre);
        System.out.println(" Membre inscrit avec succès !");
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
            System.out.println("Emprunt enregistré avec succès !");

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'enregistrement de l'emprunt");
            e.printStackTrace();
        }
    }

    public void retournerLivre(int idEmprunt) {

        String sql = "UPDATE emprunts SET date_retour = CURRENT_DATE "
                + "WHERE id = ? AND date_retour IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEmprunt);
            int lignes = pstmt.executeUpdate();

            if (lignes > 0) {
                System.out.println("Livre retourné avec succès !");
            } else {
                System.out.println(" Emprunt introuvable ou déjà retourné.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors du retour du livre");
            e.printStackTrace();
        }
    }
}
