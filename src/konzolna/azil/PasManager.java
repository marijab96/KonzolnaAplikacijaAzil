package konzolna.azil;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PasManager {


    List<Pas> psi = new ArrayList<>();
    private static final String PUTANJA_PSI= "psi.json";

    protected void pasIzbornik() {
        System.out.println("Odaberi ponuđeno iz izbornika");

        stavkePasIzbornika();
        switch (Pomoc.ucitajCijeliBroj("Odaberite željeno",0,5)){

            case 1:
                prikaziPse();
                break;
            case 2:
                dodajNovogPsa();
                break;
            case 3:
                promjeniPsa();
                break;
            case 4:
                izbrisiPsa();
                break;
            case 5:
                detaljiPsa();
                break;
            case 0:
                Start.izbornik();
                break;
        }
    }

    protected void ucitajPse() {

        if(!new File(PUTANJA_PSI).exists()){
            return;
        }


        try {
            Type listType = new TypeToken<List<Pas>>(){}.getType();
            String json = Files.readString(Path.of(PUTANJA_PSI));
            psi = new Gson().fromJson(json, listType);
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }

    }

    private void detaljiPsa() {
        System.out.println("°°°° INFOMRACIJE O PSU °°°°");
        sviPsi();

        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, psi.size())-1;

        var p = psi.get(odabir);

        System.out.println("Ime: " + p.getIme());
        System.out.println("Šifra: " + p.getSifra());
        System.out.println("Boks: " + p.getBoks());
        System.out.println("Broj čipa: " + p.getBrojCipa());
        System.out.println("Datum rođenja: " + p.getDatumRodenja());
        System.out.println("Vrsta: " + p.getVrsta());
        System.out.println("Zaposlenik: " + p.getZaposlenik());

        pasIzbornik();


    }

    private void izbrisiPsa() {
        System.out.println("°°°° BRISANJE PSA °°°°");
        System.out.println("0. Povratak na prethodni izbornik");

        sviPsi();
        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, psi.size())-1;

        if(odabir==-1){
            pasIzbornik();

        }



        var p = psi.get(odabir);

        psi.remove(p);

        spremi();
        pasIzbornik();
    }

    private void promjeniPsa() {
        System.out.println("^^^^ PROMJENA PSA ^^^^");
        System.out.println("0. Povratak na prethodni izbornik");
        sviPsi();

        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, psi.size())-1;

        if(odabir==-1){
            pasIzbornik();
        }

        var p = psi.get(odabir);

        Integer sifra = Pomoc.ucitajCijeliBroj("Šifra (" + p.getSifra() + ")", false);
        String ime = Pomoc.ucitajString("Unesi novo ime (" + p.getIme() + ")", false);
        String vrsta = Pomoc.ucitajString("Unesi novu vrstu psa (" + p.getVrsta() + ")",false);
        Long brojCipa = Pomoc.ucitajLong("Unesi novi broj čipa (" + p.getBrojCipa() + ")", false);
        String datumRodenja = Pomoc.ucitajString("Unesi novi datum rođenja (" + p.getDatumRodenja() + ")", false);
        Integer boks = Pomoc.ucitajCijeliBroj("Unesi novi broj boksa (" + p.getBoks() + ")", false);
        boolean udomljen = Pomoc.ucitajBoolena("Je li je pas udomljen? (" + p.isUdomljen() + ")", p.isUdomljen(), false);
        String zaposlenik= Pomoc.ucitajString("Ime novog zaposlenika (" + p.getZaposlenik() + ")",false);


        if(sifra!=null){
            p.setSifra(sifra);
        }

        if(!ime.isEmpty()) {
            p.setIme(ime);
        }

        if(brojCipa!=null){
            p.setBrojCipa(brojCipa);
        }

        if(!datumRodenja.isEmpty()) {
            p.setDatumRodenja(datumRodenja);
        }

        if(boks!=null){
            p.setBoks(boks);        }

        if(!datumRodenja.isEmpty()) {
            p.setDatumRodenja(datumRodenja);
        }

        if(!vrsta.isEmpty()) {
            p.setVrsta(vrsta);
        }

        if(!zaposlenik.isEmpty()) {
            p.setZaposlenik(zaposlenik);
        }





        p.setUdomljen(udomljen);
        psi.set(odabir,p);
        spremi();
        pasIzbornik();
    }



    private void dodajNovogPsa() {
        System.out.println("°°°° DODAJ NOVOG PSA °°°°");
        System.out.println("0. Povratak na prethodni izbornik");

        Pas p = new Pas();

        Integer sifra = Pomoc.ucitajCijeliBroj("Unesi šifru psa", true);


        if(sifra==0) {
            pasIzbornik();
        }

        String ime = Pomoc.ucitajString("Unesi ime psa",true);

        if(ime.equals("0")) {
            pasIzbornik();
        }

        String vrsta = Pomoc.ucitajString("Unesi vrstu psa",true);
        Long brojCipa = Pomoc.ucitajLong("Unesi broj čipa", true);
        String datumRodenja = Pomoc.ucitajString("Unesi datum rođenja",true);
        Integer boks = Pomoc.ucitajCijeliBroj("Unesi broj boksa", true);
        boolean udomljen = Pomoc.ucitajBoolena("Je li je pas udomljen?", p.isUdomljen(),true);
        String zaposlenik= Pomoc.ucitajString("Ime zaposlenika",true);

        p.setZaposlenik(zaposlenik);
        p.setUdomljen(udomljen);
        p.setBoks(boks);
        p.setDatumRodenja(datumRodenja);
        p.setBrojCipa(brojCipa);
        p.setVrsta(vrsta);
        p.setSifra(sifra);
        p.setIme(ime);
        psi.add(p);
        spremi();
        pasIzbornik();
    }

    private void stavkePasIzbornika() {
        System.out.println("1. Prikaži sve pse");
        System.out.println("2. Dodaj novog psa");
        System.out.println("3. Promjeni postojećeg psa");
        System.out.println("4. Obriši postojećeg psa");
        System.out.println("5. Više informacija o psu");
        System.out.println("0. Vraćanje na glavni izbornik");



    }

    private void sviPsi(){
        System.out.println("=============");
        for(int i=0; i< psi.size(); i++){
            var p = psi.get(i);
            System.out.println((i+1) + " . " + p.getIme());
        }

        System.out.println("============");
    }

    private void prikaziPse() {
        System.out.println("^^^^ POPIS SVIH PASA ^^^^");

        sviPsi();
        pasIzbornik();

    }

    private void spremi(){
        Gson gson = new Gson();

        try {
            FileWriter fw = new FileWriter(new File(PUTANJA_PSI));
            fw.write(gson.toJson(psi));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
