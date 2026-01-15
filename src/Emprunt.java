import java.time.LocalDate;

public class Emprunt {

    private int idEmprunt;
    private int idMembre;
    private int idLivre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(int idEmprunt, int idMembre, int idLivre,
                   LocalDate dateEmprunt, LocalDate dateRetour) {

        this.idEmprunt = idEmprunt;
        this.idMembre = idMembre;
        this.idLivre = idLivre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }
}
