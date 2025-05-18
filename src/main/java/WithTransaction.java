import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class WithTransaction {
    public static final String URL = "jdbc:postgresql://37.187.147.153:8765/analyst?currentSchema=dex_courses";
    public static final String USER_NAME = "postgres";
    public static final String PASS = "rte2545rte";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        PreparedStatement prep3 = null;

        try {
            conn = getConnection(URL,USER_NAME,PASS);
            prep1 = conn.prepareStatement("SELECT * FROM artist "+
                    " WHERE artist_id= ? ");
            prep1.setInt(1,2);
            ResultSet rs = prep1.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
            //UPDATE
            conn.setAutoCommit(false);  //BEGIN TRANSACTION
            prep2 = conn.prepareStatement("UPDATE artist SET name = 'Accept'"+
                    "WHERE artist_id = ?");
            prep2.setInt(1, 2);

            prep2.executeUpdate();
            conn.commit();

            ResultSet rs2 = prep1.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {

        }
    }
}
