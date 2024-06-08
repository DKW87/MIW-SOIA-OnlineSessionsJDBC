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

        SessionDAO sessionDAO = new SessionDAO(dBaccess);
        List<Session> sessionsInDeDatabase = sessionDAO.getSessions();
        dBaccess.closeConnection();

        for (Session session : sessionsInDeDatabase) {
            System.out.println(session);
        }

    } // main

} // klasse
