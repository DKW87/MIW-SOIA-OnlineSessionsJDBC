package model;

public class Muzikant {

    private String naamMuzikant;
    private String instrument;
    private int jaarErvaring;

    public Muzikant(String naamMuzikant, String instrument, int jaarErvaring) {
        this.naamMuzikant = naamMuzikant;
        this.instrument = instrument;
        setJaarErvaring(jaarErvaring);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%-10s", naamMuzikant));
        stringBuilder.append(String.format("%-10s", instrument));
        stringBuilder.append(String.format("%8d", jaarErvaring));
        return stringBuilder.toString();
        //return String.format("%s op %s met %d jaar ervaring", naamMuzikant, instrument, jaarErvaring);
    }

    public String getNaamMuzikant() {
        return naamMuzikant;
    }

    public String getInstrument() {
        return instrument;
    }

    public int getJaarErvaring() {
        return jaarErvaring;
    }

    public void setJaarErvaring(int jaarErvaring) {
        this.jaarErvaring = jaarErvaring;
    }
}
