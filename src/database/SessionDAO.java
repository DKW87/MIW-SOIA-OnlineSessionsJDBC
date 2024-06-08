package database;

import model.Muzikant;
import model.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO extends AbstractDAO {

    public SessionDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    public void slaSessionOp(Session session) {
        String sql = "INSERT INTO session(sessionDatum, tijdsduur, opname, " + "aantalMuzikanten, organisator) VALUES (?, ?, ?, ?, ?);";
        int primaryKey = 0;
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, session.getDatum().toString());
            preparedStatement.setDouble(2, session.getDuur());
            preparedStatement.setBoolean(3, session.isOpgenomen());
            preparedStatement.setInt(4, session.getAantalMuzikanten());
            preparedStatement.setString(5, session.getOrganisator().getNaamMuzikant());
            primaryKey = executeInsertStatementWithKey();
            session.setIdSession(primaryKey);
        } catch (SQLException sqlFout) {
            System.out.println(sqlFout);
        }
    }

    public List<Session> getSessions() {
        MuzikantDAO muzikantDAO = new MuzikantDAO(dBaccess);
        List<Session> sessionList = new ArrayList<>();
        String sql = "SELECT * FROM session;";
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                String naam = resultSet.getString("organisator");
                Muzikant organisator = muzikantDAO.getMuzikantPerNaam(naam);
                LocalDate datum = LocalDate.parse(resultSet.getString("sessionDatum"));
                double duur = resultSet.getDouble("tijdsduur");
                boolean isOpgenomen = resultSet.getBoolean("opname");
                int aantal = resultSet.getInt("aantalMuzikanten");
                Session session = new Session(organisator, datum, duur, isOpgenomen, aantal);
                session.setIdSession(resultSet.getInt("idSession"));
                sessionList.add(session);
            }
        } catch (SQLException foutmelding) {
            System.out.println(foutmelding.getMessage());
        }
        return sessionList;
    }

    public List<Session> getSessionsPerMaandJaar(int maand, int jaar) {
        MuzikantDAO muzikantDAO = new MuzikantDAO(dBaccess);
        List<Session> sessionList = new ArrayList<>();
        String sql = "SELECT * FROM session " +
                "WHERE month(sessionDatum) = ? AND year(sessionDatum) = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, maand);
            preparedStatement.setInt(2, jaar);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                String naam = resultSet.getString("organisator");
                Muzikant organisator = muzikantDAO.getMuzikantPerNaam(naam);
                LocalDate datum =
                        LocalDate.parse(resultSet.getString("sessionDatum"));
                double duur = resultSet.getDouble("tijdsduur");
                boolean isOpgenomen = resultSet.getBoolean("opname");
                int aantal = resultSet.getInt("aantalMuzikanten");
                Session session =
                        new Session(organisator, datum, duur, isOpgenomen,
                                aantal);
                session.setIdSession(resultSet.getInt("idSession"));
                sessionList.add(session);
            }
        } catch (SQLException foutmelding) {
            System.out.println(foutmelding.getMessage());
        }
        return sessionList;
    }

}
