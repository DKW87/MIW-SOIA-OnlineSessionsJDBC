package controller;


import database.*;
import model.*;

import java.util.List;

public class SessionsLauncher {

    public static void main(String[] args) {

        DBaccess dBaccess = new DBaccess("OnlineSessions", "userOnlineSessions", "userOnlineSessionsPW");
        dBaccess.openConnection();

        TechnicusDAO technicusDAO = new TechnicusDAO(dBaccess);
        technicusDAO.slaTechnicusOp(new Technicus("691337","Danny","0629400561"));

        List<Technicus> techniciInDeDatabase = technicusDAO.getTechnici();

        for (Technicus technicus : techniciInDeDatabase) {
            System.out.println(technicus);
        }

        dBaccess.closeConnection();

    }
}
