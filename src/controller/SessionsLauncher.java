package controller;


import database.*;
import model.*;

import java.time.LocalDate;
import java.util.List;

public class SessionsLauncher {

    public static void main(String[] args) {

        DBaccess dBaccess =
                new DBaccess("OnlineSessions","userOnlineSessions","userOnlineSessionsPW");
        dBaccess.openConnection();

        Muzikant adele = new Muzikant("Adele", "Zang", 12);
        SessionDAO sessionDAO = new SessionDAO(dBaccess);
        sessionDAO.slaSessionOp(new Session(adele, LocalDate.now(),2.5,false,4));

        dBaccess.closeConnection();

    } // main

} // klasse
