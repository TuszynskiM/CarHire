package App.DTO;

import App.Model.CarDetails;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectorMySql {

    public Connection getConn() {
        Connection con;
        try {

            String connectionString = "jdbc:mysql://localhost/carhire?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            con = DriverManager.getConnection(connectionString, "root", "admin");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void AddCar(String Mark, String Model, int Vin, int Age) {
        Connection conn = getConn();
        String query = String.format("INSERT INTO `carhire`.`carlist` (`mark`, `brand`, `VIN`, `age`) VALUES ('%s', '%s', '%s', '%s')", Mark, Model, Vin, Age);
        Statement st;

        try {
            st = conn.createStatement();
            st.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UpdateCar(int Id, String Mark, String Model, int Vin, int Age) {
        Connection conn = getConn();
        String query = String.format("UPDATE `carhire`.`carlist` SET `mark` = '%s', `brand` = '%s', `VIN` = '%s', `age` = '%s' WHERE (`id` = '%s');", Mark, Model, Vin, Age, Id);
        Statement st;

        try {
            st = conn.createStatement();
            st.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteCar(int Id) {
        Connection conn = getConn();
        String query = String.format("DELETE FROM `carhire`.`carlist` WHERE (`id` = '%s')", Id);
        Statement st;

        try {
            st = conn.createStatement();
            st.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CarDetails> GetCarList() {
        ArrayList<CarDetails> carsList = new ArrayList<CarDetails>();
        Connection conn = getConn();
        String query = "SELECT * FROM carhire.carlist";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            CarDetails car;
            while (rs.next()) {
                car = new CarDetails(rs.getInt("id"), rs.getString("mark"), rs.getString("brand"), rs.getInt("VIN"), rs.getInt("age"));
                carsList.add(car);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return carsList;
    }

}