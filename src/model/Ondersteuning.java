package model;

public class Ondersteuning {

    Technicus ondersteuner;
    Session session;
    String rol;

    public Ondersteuning(Technicus ondersteuner, Session session, String rol) {
        this.ondersteuner = ondersteuner;
        this.session = session;
        this.rol = rol;
    }

    
}
