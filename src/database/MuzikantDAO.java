package database;

import model.Muzikant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuzikantDAO {

    DBaccess dBaccess;

    public MuzikantDAO(DBaccess dBaccess) {
        this.dBaccess = dBaccess;
    }

    public void slaMuzikantOp(Muzikant muzikant) {
        String sql = "INSERT INTO muzikant VALUES(?, ?, ?);";
        try {
            PreparedStatement preparedStatement =
                dBaccess.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, muzikant.getNaamMuzikant());
            preparedStatement.setString(2, muzikant.getInstrument());
            preparedStatement.setInt(3, muzikant.getJaarErvaring());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlFout) {
            System.out.println(sqlFout);
        }
    }

    public List<Muzikant> getMuzikanten() {
        List<Muzikant> muzikantenLijst = new ArrayList<>();
        String sql = "SELECT * FROM muzikant;";
        try {
            PreparedStatement preparedStatement = 
                dBaccess.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
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
}
