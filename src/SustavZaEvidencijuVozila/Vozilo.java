package SustavZaEvidencijuVozila;

public class Vozilo{
    protected String registarskiBroj;
    protected String marka;
    protected int godinaProizvodnje;
    private int trenutnaGodina = 2026;

    public Vozilo(String registarskiBroj, String marka, int godinaProizvodnje) throws NeispravniPodaciException{
        //regex za provjeru da li su tablice u formatu za hrvatska vozila
        if (!registarskiBroj.matches("^[A-Z]{2}\\d{3,4}[A-Z]{1,2}$")) {
            throw new NeispravniPodaciException(
                    "Neispravan format registarskog broja."
            );
        }

        if (godinaProizvodnje < 0 ) {
            throw new NeispravniPodaciException(
                    "Godina proizvodnje ne može biti negativna.");
        }

        if (godinaProizvodnje > trenutnaGodina ) {
            throw new NeispravniPodaciException(
                    "Godina proizvodnje ne može biti veća od trenutne godine");
        }

        this.registarskiBroj = registarskiBroj;
        this.marka = marka;
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public String prikaziPodatke(){
        return registarskiBroj + ";" + marka + ";" + godinaProizvodnje + ";";
    }
}
