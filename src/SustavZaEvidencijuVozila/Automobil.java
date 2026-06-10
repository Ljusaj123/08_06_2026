package SustavZaEvidencijuVozila;

public class Automobil extends Vozilo {
    private int brojVrata;

    public Automobil(String registarskiBroj, String marka, int godinaProizvodnje, int brojVrata) throws NeispravniPodaciException {
        super(registarskiBroj, marka, godinaProizvodnje);
        if (brojVrata <= 0) {
            throw new NeispravniPodaciException(
                    "Broj vrata mora biti veći od 0.");
        }

        this.brojVrata = brojVrata;
    }

    @Override
    public String prikaziPodatke() {
        return "Automobil" + ";" + super.prikaziPodatke() + brojVrata + ";";
    }

    @Override
    public String toString() {
        return "Automobil" + super.toString() + " i brojem vrata " + brojVrata + ".";
    }

}
