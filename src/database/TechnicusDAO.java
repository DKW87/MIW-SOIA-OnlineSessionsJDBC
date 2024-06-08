package database;

import model.Technicus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TechnicusDAO {

    DBaccess dBaccess;

    public TechnicusDAO(DBaccess dBaccess) {
        this.dBaccess = dBaccess;
    }

    public void slaTechnicusOp(Technicus technicus) {
        String sql = "INSERT INTO technicus VALUES(?, ?, ?);";
        try {
            PreparedStatement preparedStatement =
                    dBaccess.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, technicus.getCodeTechnicus());
            preparedStatement.setString(2, technicus.getVoornaam());
            preparedStatement.setString(3, technicus.getMobielNummer());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlFout) {
            System.out.println(sqlFout);
        }
    }

    public List<Technicus> getTechnici() {
        List<Technicus> techniciLijst = new ArrayList<>();
        String sql = "SELECT * FROM technicus;";
        try {
            PreparedStatement preparedStatement =
                    dBaccess.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String codeTechnicus = resultSet.getString("codeTechnicus");
                String voornaam = resultSet.getString("voornaam");
                String mobielNummer = resultSet.getString("mobielNummer");
                Technicus technicus = new Technicus(codeTechnicus, voornaam, mobielNummer);
                techniciLijst.add(technicus);
            }
        } catch (SQLException foutmelding) {
            System.out.println(foutmelding.getMessage());
        }
        return techniciLijst;
    }

}
