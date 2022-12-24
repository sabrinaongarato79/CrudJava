package ar.charlycimino.cac.crud.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class Conexion {


    private Conexion() {
    }

    public static Connection getConnection() throws SQLException {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.jdbc.Driver"); // .newInstance()
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "cac_crud_bd_22552?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conexion;
    }
}
