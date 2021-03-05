package konzolna.azil;

import java.math.BigDecimal;

public class Boks {

    private int sifra;
    private String naziv;
    private int kapacitet;
    private BigDecimal velicina;
    private String zaposlenik;

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public BigDecimal getVelicina() {
        return velicina;
    }

    public void setVelicina(BigDecimal velicina) {
        this.velicina = velicina;
    }

    public String getZaposlenik() {
        return zaposlenik;
    }

    public void setZaposlenik(String zaposlenik) {
        this.zaposlenik = zaposlenik;
    }


}
