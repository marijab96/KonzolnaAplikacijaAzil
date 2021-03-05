package konzolna.azil;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BoksManager {

    List<Boks> boksevi = new ArrayList<>();
    private static final String PUTANJA_BOKSEVI= "boksevi.json";

    protected void boksIzbornik() {
        System.out.println("Odaberi ponuđeno iz izbornika");

        stavkeBoksIzbornika();
        switch (Pomoc.ucitajCijeliBroj("Odaberite željeno",0,5)){

            case 1:
                prikaziBokseve();
                break;
            case 2:
                dodajNoviBoks();
                break;
            case 3:
                promjeniBoks();
                break;
            case 4:
                izbrisiBoks();
                break;
            case 5:
                detaljiBoksa();
                break;
            case 0:
                Start.izbornik();
                break;
        }
    }

    protected void ucitajBokseve() {
        
        if(!new File(PUTANJA_BOKSEVI).exists()){
            return;
        }

        try {
            Type listType = new TypeToken<List<Boks>>(){}.getType();
            String json = Files.readString(Path.of(PUTANJA_BOKSEVI));
            boksevi = new Gson().fromJson(json, listType);
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }

    }

    private void detaljiBoksa() {

        System.out.println("°°°° INFOMRACIJE O BOKSU °°°°");
        sviBoksevi();

        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, boksevi.size())-1;

        var b = boksevi.get(odabir);


        System.out.println("Šifra: " + b.getSifra());
        System.out.println("Naziv: " + b.getNaziv());
        System.out.println("Kapacitet (broj pasa): " + b.getKapacitet());
        System.out.println("Veličina boksa: " + b.getVelicina());
        System.out.println("Zaposlenik: " + b.getZaposlenik());

        boksIzbornik();

    }

    private void izbrisiBoks() {

        System.out.println("°°°° BRISANJE BOKSA °°°°");
        System.out.println("0. Povratak na prethodni izbornik");

        sviBoksevi();
        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, boksevi.size())-1;

        if(odabir==-1){
            boksIzbornik();

        }

        var b = boksevi.get(odabir);

        boksevi.remove(b);

        spremi();
        boksIzbornik();
    }

    private void promjeniBoks() {

        System.out.println("^^^^ PROMJENA BOKSA ^^^^");
        System.out.println("0. Povratak na prethodni izbornik");
        sviBoksevi();

        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, boksevi.size())-1;

        if(odabir==-1){
            boksIzbornik();
        }

        var b = boksevi.get(odabir);

        Integer sifra = Pomoc.ucitajCijeliBroj("Šifra (" + b.getSifra() + ")", false);
        String naziv = Pomoc.ucitajString("Unesi novo ime(naziv) (" + b.getNaziv() + ")", false);
        Integer kapacitet = Pomoc.ucitajCijeliBroj("Unesi novi broj pasa (kapacitet) (" + b.getKapacitet() + ")", false);
        String zaposlenik= Pomoc.ucitajString("Ime novog zaposlenika (" + b.getZaposlenik() + ")",false);
        BigDecimal velicina = Pomoc.ucitajDecimalniBroj("Veličina boksa: (" + b.getVelicina() + ")", false);



        if(sifra!=null){
            b.setSifra(sifra);
        }

        if(!naziv.isEmpty()) {
            b.setNaziv(naziv);
        }

        if(kapacitet!=null){
            b.setKapacitet(kapacitet);
        }

        if(!zaposlenik.isEmpty()) {
            b.setZaposlenik(zaposlenik);
        }

        if(velicina!=null){
            b.setVelicina(velicina);        }


        boksevi.set(odabir,b);
        spremi();
        boksIzbornik();
    }

    private void dodajNoviBoks() {

        System.out.println("°°°° DODAJ NOVOI BOKS °°°°");
        System.out.println("0. Povratak na prethodni izbornik");

        Boks b = new Boks();

        Integer sifra = Pomoc.ucitajCijeliBroj("Unesi šifru", true);


        if(sifra==0) {
            boksIzbornik();
        }

        String naziv = Pomoc.ucitajString("Unesi naziv boksa",true);

        if(naziv.equals("0")) {
            boksIzbornik();
        }

        Integer kapacitet = Pomoc.ucitajCijeliBroj("Unesi kapacitet boksa", true);
        
        String zaposlenik = Pomoc.ucitajString("Unesi zaposlenika",true);

        BigDecimal velicina = Pomoc.ucitajDecimalniBroj("Unesi velicinu boksa", true);
        

        b.setKapacitet(kapacitet);
        b.setNaziv(naziv);
        b.setSifra(sifra);
        b.setZaposlenik(zaposlenik);
        b.setVelicina(velicina);
        boksevi.add(b);
        spremi();
        boksIzbornik();
    }

    private void spremi() {
        Gson gson = new Gson();

        try {
            FileWriter fw = new FileWriter(new File(PUTANJA_BOKSEVI));
            fw.write(gson.toJson(boksevi));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prikaziBokseve() {

        System.out.println("^^^^ POPIS SVIH BOKSEVA ^^^^");

        sviBoksevi();
        boksIzbornik();
    }

    private void sviBoksevi() {
        System.out.println("=============");
        for(int i=0; i< boksevi.size(); i++){
            var b = boksevi.get(i);
            System.out.println((i+1) + " . " + b.getNaziv());
        }

        System.out.println("============");
    }

    private void stavkeBoksIzbornika() {

        System.out.println("1. Prikaži sve bokseve");
        System.out.println("2. Dodaj novi boks");
        System.out.println("3. Promjeni postojeći boks");
        System.out.println("4. Obriši postojeći boks");
        System.out.println("5. Više informacija o boksu");
        System.out.println("0. Vraćanje na glavni izbornik");


    }

}
