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

public class VrstaManager {

    List<Vrsta> vrste  = new ArrayList<>();
    private static final String PUTANJA_VRSTE= "vrste.json";


    protected void vrstaIzbornik() {
        System.out.println("Odaberi ponuđeno iz izbornika");

        stavkeVrstaIzbornika();
        switch (Pomoc.ucitajCijeliBroj("Odaberite željeno",0,5)){

            case 1:
                prikaziVrste();
                break;
            case 2:
                dodajNovuVrstu();
                break;
            case 3:
                promjeniVrstu();
                break;
            case 4:
                izbrisiVrstu();
                break;
            case 5:
                detaljiVrste();
                break;
            case 0:
                Start.izbornik();
                break;
        }
    }

    protected void ucitajVrste() {
        if(!new File(PUTANJA_VRSTE).exists()){
            return;
        }

        try {
            Type listType = new TypeToken<List<Pas>>(){}.getType();
            String json = Files.readString(Path.of(PUTANJA_VRSTE));
            vrste = new Gson().fromJson(json, listType);
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }

    }

    private void sveVrste(){
        System.out.println("=============");
        for(int i=0; i< vrste.size(); i++){
            var p = vrste.get(i);
            System.out.println((i+1) + " . " + p.getNaziv());
        }

        System.out.println("============");
    }



    private void detaljiVrste() {

        System.out.println("°°°° INFOMRACIJE O VRSTI °°°°");
        sveVrste();

        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, vrste.size())-1;

        var v = vrste.get(odabir);

        System.out.println("Naziv: " + v.getNaziv());
        System.out.println("Šifra: " + v.getSifra());


        vrstaIzbornik();
    }

    private void izbrisiVrstu() {
        System.out.println("°°°° BRISANJE VRSTE °°°°");
        System.out.println("0. Povratak na prethodni izbornik");

        sveVrste();
        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, vrste.size())-1;

        if(odabir==-1){
            vrstaIzbornik();

        }

        var p = vrste.get(odabir);

        vrste.remove(p);

        spremi();
        vrstaIzbornik();
    }

    private void spremi() {
        Gson gson = new Gson();

        try {
            FileWriter fw = new FileWriter(new File(PUTANJA_VRSTE));
            fw.write(gson.toJson(vrste));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void promjeniVrstu() {
        System.out.println("^^^^ PROMJENI VRSTU ^^^^");
        System.out.println("0. Povratak na prethodni izbornik");
        sveVrste();

        int odabir = Pomoc.ucitajCijeliBroj("Odaberite redni broj stavke",
                1, vrste.size())-1;

        if(odabir==-1){
            vrstaIzbornik();

        }

        var p = vrste.get(odabir);

        String naziv = Pomoc.ucitajString("Naziv (" + p.getNaziv() + ")",false);
        p.setSifra(Pomoc.ucitajCijeliBroj("Šifra (" + p.getSifra() + ")", false));

        if(!naziv.isEmpty()) {
            p.setNaziv(naziv);
        }


        vrste.set(odabir,p);
        spremi();
        vrstaIzbornik();
    }



    private void dodajNovuVrstu() {

        System.out.println("°°°° DODAJ NOVU VRSTU °°°°");
        System.out.println("0. Povratak na prethodni izbornik");

        Vrsta v = new Vrsta();

        int sifra = Pomoc.ucitajCijeliBroj("Unesi šifru vrste", true);


        if(sifra==0) {
            vrstaIzbornik();
        }

        String naziv = Pomoc.ucitajString("Unesi naziv vrste",true);

        if(naziv.equals("0")) {
            vrstaIzbornik();
        }


        v.setSifra(sifra);
        v.setNaziv(naziv);
        vrste.add(v);
        spremi();
        vrstaIzbornik();
    }

    private void prikaziVrste() {
        System.out.println("^^^^ POPIS SVIH VRSTA ^^^^");

        sveVrste();
        vrstaIzbornik();
    }

    private void stavkeVrstaIzbornika() {
        System.out.println("1. Prikaži sve vrste");
        System.out.println("2. Dodaj novu vrst");
        System.out.println("3. Promjeni postojeću vrstu");
        System.out.println("4. Obriši postojeću vrstu");
        System.out.println("5. Više informacija o vrsti");
        System.out.println("0. Vraćanje na glavni izbornik");
    }


}
