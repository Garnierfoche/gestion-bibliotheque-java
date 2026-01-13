import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprunt {

    private int idEmprunt;
    private int membreId;
    private int livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    public Emprunt(int idEmprunt, int membreId, int livreId,
                   LocalDate dateEmprunt, LocalDate dateRetourPrevue) {

        this.idEmprunt = idEmprunt;
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = null;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }

    public int getMembreId() {
        return membreId;
    }

    public int getLivreId() {
        return livreId;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    public long calculerPenalite() {
        if (dateRetourEffective == null) {
            return 0;
        }

        long joursRetard = ChronoUnit.DAYS.between(
                dateRetourPrevue, dateRetourEffective);

        if (joursRetard > 0) {
            return joursRetard * 100;
        }

        return 0;
    }

    public void afficherDetails() {
        System.out.println("ID Emprunt : " + idEmprunt);
        System.out.println("ID Membre : " + membreId);
        System.out.println("ID Livre : " + livreId);
        System.out.println("Date emprunt : " + dateEmprunt);
        System.out.println("Retour prévu : " + dateRetourPrevue);
        System.out.println("Retour effectif : " + dateRetourEffective);
        System.out.println("Pénalité : " + calculerPenalite() + " F CFA");
    }

    public int getIdLivre() {
        return 0;
    }

    public int getIdMembre() {
        return 0;
    }

    public LocalDate getDateEmprunt() {
        return null;
    }

    public LocalDate getDateRetour() {
        return null;
    }
}
