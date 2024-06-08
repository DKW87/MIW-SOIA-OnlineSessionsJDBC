package controller;


import database.*;
import model.*;

import java.util.List;

public class SessionsLauncher {

    public static void main(String[] args) {

        DBaccess dBaccess = new DBaccess("OnlineSessions", "userOnlineSessions", "userOnlineSessionsPW");
        dBaccess.openConnection();

        MuzikantDAO muzikantDAO = new MuzikantDAO(dBaccess);
        muzikantDAO.slaMuzikantOp(new Muzikant("Lilly Vos", "zang", 23));

        List<Muzikant> muzikantenInDeDatabase = muzikantDAO.getMuzikanten();

        for (Muzikant muzikant : muzikantenInDeDatabase) {
            System.out.println(muzikant);
        }

        dBaccess.closeConnection();

    }
}
