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

public class BolestManager {

    List<Bolest> bolesti = new ArrayList<>();
    private static final String PUTANJA_BOLESTI = "bolesti.json";

    protected void bolestIzbornik() {
        System.out.println("Odaberi ponuđeno iz izbornika");

        stavkeBolestIzbornika();
        switch (Pomoc.ucitajCijeliBroj("Odaberite željeno", 0, 5)) {

            case 1:
                prikaziBolesti();
                break;
            case 2:
                dodajNovuBolest();
                break;
            case 3:
                promjeniBolest();
                break;
            case 4:
                izbrisiBolest();
                break;
            case 5:
                detaljiBolesti();
                break;
            case 0:
                Start.izbornik();
                break;
        }


    }

    protected void ucitajBolesti() {

        if (!new File(PUTANJA_BOLESTI).exists()) {
            return;
        }


        try {
            Type listType = new TypeToken<List<Bolest>>() {
            }.getType();
            String json = Files.readString(Path.of(PUTANJA_BOLESTI));
            bolesti = new Gson().fromJson(json, listType);
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }

    }

    private void detaljiBolesti() {

        System.out.println("°°°° INFOMRACIJE O BOLESTI °°°°");
        svebolesti();

        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, bolesti.size()) - 1;

        var bo = bolesti.get(odabir);

        System.out.println("Šifra: " + bo.getSifra());
        System.out.println("Naziv: " + bo.getNaziv());
        System.out.println("Opis: " + bo.getOpis());

        bolestIzbornik();

    }

    private void svebolesti() {

        System.out.println("=============");
        for(int i=0; i< bolesti.size(); i++){
            var bo = bolesti.get(i);
            System.out.println((i+1) + " . " + bo.getNaziv());
        }

        System.out.println("============");
    }


    private void izbrisiBolest() {

        System.out.println("°°°° BRISANJE BOLESTI °°°°");
        System.out.println("0. Povratak na prethodni izbornik");

        svebolesti();
        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, bolesti.size())-1;

        if(odabir==-1){
            bolestIzbornik();

        }

        var bo = bolesti.get(odabir);

        bolesti.remove(bo);

        spremi();
        bolestIzbornik();


    }

    private void promjeniBolest() {

        System.out.println("^^^^ PROMJENA BOLESTI ^^^^");
        System.out.println("0. Povratak na prethodni izbornik");
        svebolesti();

        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, bolesti.size())-1;

        if(odabir==-1){
            bolestIzbornik();
        }

        var bo = bolesti.get(odabir);

        Integer sifra = Pomoc.ucitajCijeliBroj("Šifra (" + bo.getSifra() + ")", false);
        String naziv = Pomoc.ucitajString("Unesi novo ime (naziv) (" + bo.getNaziv() + ")", false);
        String opis = Pomoc.ucitajString("Unesi novi opis (" + bo.getOpis() + ")",false);



        if(sifra!=null){
            bo.setSifra(sifra);
        }

        if(!naziv.isEmpty()) {
            bo.setNaziv(naziv);
        }


        if(!opis.isEmpty()) {
            bo.setOpis(opis);
        }




        bolesti.set(odabir,bo);
        spremi();
        bolestIzbornik();
    }

    private void dodajNovuBolest() {

        System.out.println("°°°° DODAJ NOVU BOLEST °°°°");
        System.out.println("0. Povratak na prethodni izbornik");

        Bolest bo = new Bolest();

        Integer sifra = Pomoc.ucitajCijeliBroj("Unesi šifru bolesti", true);


        if(sifra==0) {
            bolestIzbornik();
        }

        String naziv = Pomoc.ucitajString("Unesi naziv bolesti",true);

        if(naziv.equals("0")) {
            bolestIzbornik();
        }

        String opis = Pomoc.ucitajString("Unesi opis",true);



        bo.setSifra(sifra);
        bo.setNaziv(naziv);
        bo.setOpis(opis);
        bolesti.add(bo);
        spremi();
        bolestIzbornik();
    }

    private void spremi() {

        Gson gson = new Gson();

        try {
            FileWriter fw = new FileWriter(new File(PUTANJA_BOLESTI));
            fw.write(gson.toJson(bolesti));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void prikaziBolesti() {

        System.out.println("^^^^ POPIS SVIH BOLESTI ^^^^");

        svebolesti();
        bolestIzbornik();
    }

    private void stavkeBolestIzbornika() {

        System.out.println("1. Prikaži sve bolesti");
        System.out.println("2. Dodaj novu bolest");
        System.out.println("3. Promjeni postojeću bolest");
        System.out.println("4. Obriši postojeću bolest");
        System.out.println("5. Više informacija o bolesti");
        System.out.println("0. Vraćanje na glavni izbornik");
    }


}
