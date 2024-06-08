package model;

public class Technicus {

    private String codeTechnicus;
    private String voornaam;
    private String mobielNummer;

    public Technicus(String codeTechnicus, String voornaam, String mobielNummer) {
        this.codeTechnicus = codeTechnicus;
        this.voornaam = voornaam;
        this.mobielNummer = mobielNummer;
    }

    public String getCodeTechnicus() {
        return codeTechnicus;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getMobielNummer() {
        return mobielNummer;
    }

    @Override
    public String toString() {
        return "Technicus: " + codeTechnicus + "\n" +
                "\tvoornaam: " + voornaam + "\n" +
                "\tmobiel: " + mobielNummer + "\n";
    }
}
