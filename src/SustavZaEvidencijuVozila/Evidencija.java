package SustavZaEvidencijuVozila;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Evidencija {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Vozilo> vozila = new ArrayList<>();
            while (true) {
                System.out.println("Odaberi:\n1 -Unos novog vozila\n2 - Spremi podatke u datoteku\n3 - Ucitaj podatke iz datoteke\n4 - Ispis podataka na konzolu\n5 - Izlaz");
                int odabir = scanner.nextInt();
                scanner.nextLine();

                switch (odabir) {
                    case 1:
                        try {
                            Vozilo novoVozilo = unosVozila(scanner);
                            vozila.add(novoVozilo);

                        } catch (NeispravniPodaciException e) {
                            System.out.println("Greška: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Unesite putanju datoteke");
                        String putanjaZaSpremanje = scanner.nextLine();
                        spremiPodatkeUDatoteku(putanjaZaSpremanje, vozila);

                        break;
                    case 3:
                        System.out.println("Unesite putanju datoteke");
                        String putanjaZaUcitavanje = scanner.nextLine();
                        ucitajPodatkeIzDatoteke(putanjaZaUcitavanje, vozila);
                        break;
                    case 4:
                        if (vozila.isEmpty()) {
                            System.out.println("Nema upisanih podataka");
                            break;
                        }
                        for (Vozilo vozilo : vozila) {
                            System.out.println(vozilo.prikaziPodatke());
                        }
                        break;
                    case 5:
                        return;
                    default:
                        throw new InputMismatchException();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Odabir mora biti broj izmedu 1 i 5");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void spremiPodatkeUDatoteku(String putanjaDatoteke, ArrayList<Vozilo> vozila) throws IOException {
            for (Vozilo vozilo : vozila) {
                Files.writeString(Path.of(putanjaDatoteke), vozilo.prikaziPodatke());
            }

            System.out.println("Podaci uspješno spremljeni.");
    }

    public static void ucitajPodatkeIzDatoteke(String putanjaDatoteke, ArrayList<Vozilo> vozila) {
        Path path = Path.of(putanjaDatoteke);

        if (!Files.exists(path)) {
            System.out.println("Datoteka ne postoji.");
            return;
        }

        try {
            for (String red : Files.readAllLines(path)) {

                String[] podaci = red.split(";");
                switch (podaci[0].toLowerCase()){
                    case "automobil":
                        vozila.add(new Automobil(podaci[1], podaci[2], Integer.parseInt(podaci[3]), Integer.parseInt(podaci[4])));
                        break;
                    case "motocikl":
                        vozila.add(new Motocikl(podaci[1], podaci[2], Integer.parseInt(podaci[3]), podaci[4]));
                        break;
                    default:
                        System.out.printf("Nije prepoznat tip vozila %s", podaci[0]);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Vozilo unosVozila(Scanner scanner) throws NeispravniPodaciException {
        System.out.println("Tip vozila:\n1 - Automobil\n2 - Motocikl");
        int tip = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Registracijski broj: ");
        String registracija = scanner.nextLine();

        System.out.print("Marka: ");
        String marka = scanner.nextLine();

        System.out.print("Godina proizvodnje: ");
        int godina = scanner.nextInt();
        scanner.nextLine();

        switch (tip) {
            case 1:
                System.out.print("Broj vrata: ");
                int brojVrata = scanner.nextInt();
                scanner.nextLine();
                return new Automobil(registracija, marka, godina, brojVrata);

            case 2:
                System.out.print("Tip motora: ");
                String tipMotora = scanner.nextLine();
                return new Motocikl(registracija, marka, godina, tipMotora);

            default:
                throw new InputMismatchException("Neispravan tip vozila");
        }
    }
}
