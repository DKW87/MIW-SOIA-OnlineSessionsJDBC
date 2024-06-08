package database;

import model.Muzikant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuzikantDAO extends AbstractDAO {

    public MuzikantDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    public void slaMuzikantOp(Muzikant muzikant) {
        String sql = "INSERT INTO muzikant VALUES(?, ?, ?);";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, muzikant.getNaamMuzikant());
            preparedStatement.setString(2, muzikant.getInstrument());
            preparedStatement.setInt(3, muzikant.getJaarErvaring());
            executeManipulateStatement();
        } catch (SQLException sqlFout) {
            System.out.println(sqlFout);
        }
    }

    public List<Muzikant> getMuzikanten() {
        List<Muzikant> muzikantenLijst = new ArrayList<>();
        String sql = "SELECT * FROM muzikant;";
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                String artiestenNaam = resultSet.getString("artiestenNaam");
            String instrument = resultSet.getString("instrument");
            int jaarErvaring = resultSet.getInt("jaarErvaring");
            Muzikant muzikant = new Muzikant(artiestenNaam,instrument, jaarErvaring);
            muzikantenLijst.add(muzikant);
            }
        } catch (SQLException foutmelding) {
            System.out.println(foutmelding.getMessage());
        }
        return muzikantenLijst;
    }

    public Muzikant getMuzikantPerNaam(String artiestenNaam){
        Muzikant muzikant = null;
        String sql = "SELECT * FROM muzikant WHERE artiestenNaam = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, artiestenNaam);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                String instrument = resultSet.getString("instrument");
                int jaarErvaring = resultSet.getInt("jaarErvaring");
                muzikant = new Muzikant(artiestenNaam,instrument,jaarErvaring);
            }
        } catch (SQLException sqlFout) {
            System.out.println(sqlFout);
        }
        return muzikant;
    }

}
