package controller;


import database.*;
import model.*;

import java.time.LocalDate;
import java.util.List;

public class SessionsLauncher {

    public static void main(String[] args) {

        DBaccess dBaccess =
                new DBaccess("OnlineSessions", "userOnlineSessions", "userOnlineSessionsPW");
        dBaccess.openConnection();
        SessionDAO sessionDAO = new SessionDAO(dBaccess);
        MuzikantDAO muzikantDAO = new MuzikantDAO(dBaccess);

        Muzikant miley = new Muzikant("Miley Cyrus", "Zang", 15);

        if (muzikantDAO.getMuzikantPerNaam("Miley Cyrus") == null) {
            muzikantDAO.slaMuzikantOp(miley);
        }

        sessionDAO.slaSessionOp(new Session(miley, LocalDate.now(), 3, false, 7));
        List<Session> sessionsInDeDatabase = sessionDAO.getSessions();

        for (Session session : sessionsInDeDatabase) {
            System.out.println(session);
        }

        int jaar = 2020;
        int maand = 12;
        Agenda agenda = new Agenda(maand, jaar);

        for (Session session : sessionDAO.getSessionsPerMaandJaar(maand, jaar)) {
            agenda.voegSessionToe(session);
        }
        System.out.println(agenda);

        dBaccess.closeConnection();

    } // main

} // klasse
