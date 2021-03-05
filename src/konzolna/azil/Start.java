package konzolna.azil;

public class Start {

    private static final PasManager pasManager = new PasManager();
    private static final VrstaManager vrstaManager = new VrstaManager();
    private static final ZaposlenikManager zaposlenikManager = new ZaposlenikManager();
    private static final BoksManager boksManager = new BoksManager();
    private static final BolestManager bolestManager = new BolestManager();



    public Start() {
        pasManager.ucitajPse();
        vrstaManager.ucitajVrste();
        zaposlenikManager.ucitajZaposlenike();
        boksManager.ucitajBokseve();
        bolestManager.ucitajBolesti();
        izbornik();
    }

    protected static void izbornik() {
        System.out.println("*****AZIL*****");
        stavkeIzbornika();
        switch (Pomoc.ucitajCijeliBroj("Odaberite željeno", 1,6)){
            case 1:
                pasManager.pasIzbornik();
                break;
            case 2:
                boksManager.boksIzbornik();
                break;
            case 3:
                bolestManager.bolestIzbornik();
                break;
            case 4:
                zaposlenikManager.zaposlenikIzbornik();
                break;
            case 5:
                vrstaManager.vrstaIzbornik();
                break;
            case 6:
                System.out.println("Doviđenja");
                break;
        }
    }

    private static void stavkeIzbornika() {

        System.out.println("1. Pas");
        System.out.println("2. Boks");
        System.out.println("3. Bolest");
        System.out.println("4. Zaposlenik");
        System.out.println("5. Vrsta");
        System.out.println("6. Izlaz");
    }

    public static void main(String[] args) {
        new Start();

    }


}