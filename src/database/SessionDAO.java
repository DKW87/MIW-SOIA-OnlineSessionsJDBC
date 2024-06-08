package database;

import model.Session;

import java.sql.SQLException;

public class SessionDAO extends AbstractDAO {

    public SessionDAO(DBaccess dBaccess) { super(dBaccess); }

    public void slaSessionOp(Session session) {
        String sql = "INSERT INTO session(sessionDatum, tijdsduur, opname, "
                + "aantalMuzikanten, organisator) VALUES (?, ?, ?, ?, ?);";
        int primaryKey = 0;
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, session.getDatum().toString());
            preparedStatement.setDouble(2, session.getDuur());
            preparedStatement.setBoolean(3, session.isOpgenomen());
            preparedStatement.setInt(4, session.getAantalMuzikanten());
            preparedStatement.setString(5,
                    session.getOrganisator().getNaamMuzikant());
            primaryKey = executeInsertStatementWithKey();
            session.setIdSession(primaryKey);
        } catch (SQLException sqlFout) {
            System.out.println(sqlFout);
        }
    }

}
