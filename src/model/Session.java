package model;

import java.time.LocalDate;

public class Session implements Comparable<Session> {

    private int idSession;
    private Muzikant organisator;
    private LocalDate datum;
    private double duur;
    private boolean opgenomen;
    private int aantalMuzikanten;
    private static double OPSLAG_OPNAME = 1.5;
    private final static double STANDAARD_DUUR = 1.0;
    private final static double MAXIMUM_DUUR = 4.0;
    private final static double BEDRAG_PER_MUZIKANT = 5.0;
    private final static double KOSTEN_PER_EXTRA_TIJD = 2.50;
    private final static double DUUR_EXTRA_TIJD = 0.5;
    private final static int MINIMUM_AANTAL_MUZIKANTEN = 1;
    private final static int MAXIMUM_AANTAL_MUZIKANTEN = 10;

    public Session(Muzikant organisator, LocalDate datum, double duur, boolean opgenomen, int aantalMuzikanten) {
        this.organisator = organisator;
        this.datum = datum;
        setDuur(duur);
        this.opgenomen = opgenomen;
        this.aantalMuzikanten = aantalMuzikanten;
    }

    public Session(Muzikant organisator, int aantalMuzikanten) {
        this(organisator, LocalDate.now(), STANDAARD_DUUR, false, aantalMuzikanten);
    }

    public double berekenKosten() {
        double result = aantalMuzikanten * BEDRAG_PER_MUZIKANT;
        result += (duur - STANDAARD_DUUR) / DUUR_EXTRA_TIJD * KOSTEN_PER_EXTRA_TIJD;
        if (opgenomen) {
            result *= OPSLAG_OPNAME;
        }
        return result;
    };


    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public Muzikant getOrganisator() {
        return organisator;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public int getAantalMuzikanten() {
        return aantalMuzikanten;
    }

    public double getDuur() {
        return duur;
    }

    public boolean isOpgenomen() {
        return opgenomen;
    }

    public void setDuur(double duur) {
        if (duur > MAXIMUM_DUUR) {
            System.out.printf("De session mag maximaal %3.1f uur duren!\n", MAXIMUM_DUUR);
            this.duur = STANDAARD_DUUR;
        } else {
            this.duur = duur;
        }
    }

    @Override
    public int compareTo(Session andereSession) {
        return this.datum.compareTo(andereSession.getDatum());
    }

    @Override
    public String toString() {
        String welGeenOpname = "";
        if (!opgenomen) {
            welGeenOpname = "(geen opname)";
        }
        return String.format("Session op %s van %3.1f uur %s\n\tOrganisator: %s", datum,
                duur, welGeenOpname, organisator);
    }
}
