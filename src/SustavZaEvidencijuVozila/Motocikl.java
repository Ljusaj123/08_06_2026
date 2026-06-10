package SustavZaEvidencijuVozila;

public class Motocikl extends Vozilo {
    private String tipMotora;

    public Motocikl(String registarskiBroj, String marka, int godinaProizvodnje, String tipMotora) throws NeispravniPodaciException{
        super(registarskiBroj, marka, godinaProizvodnje);
        if (tipMotora == null || tipMotora.isBlank()) {
            throw new NeispravniPodaciException(
                    "Tip motora ne smije biti prazan.");
        }
        this.tipMotora = tipMotora;
    }

    @Override
    public String prikaziPodatke() {
        return "Motocikl" + ";" + super.prikaziPodatke() + tipMotora + ";";
    }

    @Override
    public String toString() {
        return "Motocikl" + super.toString() + " i tipom motora " + tipMotora + ".";
    }
}
