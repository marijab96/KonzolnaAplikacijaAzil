package konzolna.azil;

public class Pas {

    private int sifra;
    private String ime;
    private String vrsta;
    private Long brojCipa;
    private String datumRodenja;
    private int boks;
    private boolean udomljen;
    private String zaposlenik;

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public Long getBrojCipa() {
        return brojCipa;
    }

    public void setBrojCipa(Long brojCipa) {
        this.brojCipa = brojCipa;
    }

    public String getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(String datumRodenja) {
        this.datumRodenja = datumRodenja;
    }

    public int getBoks() {
        return boks;
    }

    public void setBoks(int boks) {
        this.boks = boks;
    }

    public boolean isUdomljen() {
        return udomljen;
    }

    public void setUdomljen(boolean udomljen) {
        this.udomljen = udomljen;
    }

    public String getZaposlenik() {
        return zaposlenik;
    }

    public void setZaposlenik(String zaposlenik) {
        this.zaposlenik = zaposlenik;
    }





    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
