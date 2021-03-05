package konzolna.azil;

public class Zaposlenik {

    private int sifra;
    private String ime;
    private String prezime;
    private String oib;
    private String mjestoStanovanja;
    private Long brojTelefona;

    public int getSifra()
    {
        return sifra;
    }

    public void setSifra(int sifra)
    {
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getMjestoStanovanja() {
        return mjestoStanovanja;
    }

    public void setMjestoStanovanja(String mjestoStanovanja) {
        this.mjestoStanovanja = mjestoStanovanja;
    }

    public Long getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(Long brojTelefona) {
        this.brojTelefona = brojTelefona;
    }
}
