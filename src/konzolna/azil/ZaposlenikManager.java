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

public class ZaposlenikManager {

    List<Zaposlenik> zaposlenici = new ArrayList<>();
    private static final String PUTANJA_ZAPOSLENICI= "zaposlenici.json";

    protected void zaposlenikIzbornik() {

        System.out.println("Odaberi ponuđeno iz izbornika");

        stavkeZaposlenikIzbornika();
        switch (Pomoc.ucitajCijeliBroj("Odaberite željeno",0,5)){

            case 1:
                prikaziZaposlenike();
                break;
            case 2:
                dodajNovogaZposlenike();
                break;
            case 3:
                promjeniZaposlenika();
                break;
            case 4:
                izbrisiZaposlenike();
                break;
            case 5:
                detaljiZaposlenike();
                break;
            case 0:
                Start.izbornik();
                break;
        }
    }
    private void dodajNovogaZposlenike() {
        System.out.println("°°°° DODAJ NOVOG ZAPOSLENIKA °°°°");
        System.out.println("0. Povratak na prethodni izbornik");

        Zaposlenik z = new Zaposlenik();

        int sifra = Pomoc.ucitajCijeliBroj("Unesi šifru zaposlenika", true);

        if(sifra==0) {
            zaposlenikIzbornik();
        }

        String ime = Pomoc.ucitajString("Unesi ime",true);

        if(ime.equals("0")) {
            zaposlenikIzbornik();
        }



        String prezime = Pomoc.ucitajString("Unesi prezime",true);
        String oib = Pomoc.ucitajString("Unesi OIB",true);
        String mjestoStanovanja = Pomoc.ucitajString("Unesi mjesto stanovanja",true);
        Long brojTelefona = Pomoc.ucitajLong("Unesi broj telefona", true);



        z.setSifra(sifra);
        z.setBrojTelefona(brojTelefona);
        z.setIme(ime);
        z.setOib(oib);
        z.setPrezime(prezime);
        z.setMjestoStanovanja(mjestoStanovanja);
        zaposlenici.add(z);
        spremi();
        zaposlenikIzbornik();

    }

    private void promjeniZaposlenika() {
        System.out.println("^^^^ PROMJENA ZAPOSLENIKA ^^^^");
        System.out.println("0. Povratak na prethodni izbornik");
        svizaposlenici();

        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, zaposlenici.size())-1;

        if(odabir==-1){
            zaposlenikIzbornik();

        }

        var z = zaposlenici.get(odabir);

        Integer sifra = Pomoc.ucitajCijeliBroj("Šifra (" + z.getSifra() + ")", false);
        String ime = Pomoc.ucitajString("Unesi novo ime (" + z.getIme() + ")", false);
        String prezime = Pomoc.ucitajString("Unesi novo prezime (" + z.getPrezime() + ")", false);
        String oib = Pomoc.ucitajString("Unesi novi OIB (" + z.getOib() + ")", false);
        String mjestoStanovanja = Pomoc.ucitajString("Unesi novo mjesto stanovanja (" + z.getMjestoStanovanja() + ")", false);
        Long brojTelefona = Pomoc.ucitajLong("Unesi novi broj telefona (" + z.getBrojTelefona() + ")", false);


        if(sifra!=null){
            z.setSifra(sifra);
        }

        if(!ime.isEmpty()) {
            z.setIme(ime);
        }

        if(!prezime.isEmpty()) {
            z.setPrezime(prezime);
        }
        if(!oib.isEmpty()) {
            z.setOib(oib);
        }
        if(!mjestoStanovanja.isEmpty()) {
            z.setMjestoStanovanja(mjestoStanovanja);
        }

        if(brojTelefona!=null){
            z.setBrojTelefona(brojTelefona);
        }



        zaposlenici.set(odabir,z);
        spremi();
        zaposlenikIzbornik();

    }

    private void svizaposlenici() {
        System.out.println("=============");
        for(int i=0; i< zaposlenici.size(); i++){
            var z = zaposlenici.get(i);
            System.out.println((i+1) + " . " + z.getIme());
        }

        System.out.println("============");
    }

    private void spremi() {
        Gson gson = new Gson();

        try {
            FileWriter fw = new FileWriter(new File(PUTANJA_ZAPOSLENICI));
            fw.write(gson.toJson(zaposlenici));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void detaljiZaposlenike() {

        System.out.println("°°°° INFOMRACIJE O ZAPOSLENIKU °°°°");
        svizaposlenici();

        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, zaposlenici.size())-1;

        var z = zaposlenici.get(odabir);

        System.out.println("Ime: " + z.getIme());
        System.out.println("Prezime: " + z.getPrezime());
        System.out.println("Šifra: " + z.getSifra());
        System.out.println("OIB: " + z.getOib());
        System.out.println("Mjesto Stanovanja: " + z.getMjestoStanovanja());
        System.out.println("Broj telefona: " + z.getBrojTelefona());

        zaposlenikIzbornik();

    }

    private void izbrisiZaposlenike() {

        System.out.println("°°°° BRISANJE ZAPOSLENIKA °°°°");
        System.out.println("0. Povratak na prethodni izbornik");

        svizaposlenici();
        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, zaposlenici.size())-1;

        if(odabir==-1){
            zaposlenikIzbornik();

        }

        var z = zaposlenici.get(odabir);

        zaposlenici.remove(z);

        spremi();
        zaposlenikIzbornik();
    }





    private void prikaziZaposlenike() {

        System.out.println("^^^^ POPIS SVIH ZAPOSLENIKA ^^^^");

        svizaposlenici();
        zaposlenikIzbornik();
    }

    private void stavkeZaposlenikIzbornika() {

        System.out.println("1. Prikaži sve zaposlenike");
        System.out.println("2. Dodaj novog zaposlenika");
        System.out.println("3. Promjeni postojećeg zaposlenika");
        System.out.println("4. Obriši postojećeg zaposlenika");
        System.out.println("5. Više informacija o zaposleniku");
        System.out.println("0. Vraćanje na glavni izbornik");
    }


    protected void ucitajZaposlenike() {
        if(!new File(PUTANJA_ZAPOSLENICI).exists()){
            return;
        }


        try {
            Type listType = new TypeToken<List<Pas>>(){}.getType();
            String json = Files.readString(Path.of(PUTANJA_ZAPOSLENICI));
            zaposlenici = new Gson().fromJson(json, listType);
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }

    }
}
