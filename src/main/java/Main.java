import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Main {
    public static final String URL = "jdbc:postgresql://37.187.147.153:8765/analyst";
    public static final String USER_NAME = "postgres";
    public static final String PASS = "rte2545rte";

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement("SELECT * FROM dex_courses.artist "+
                    " WHERE artist_id BETWEEN ? AND ?");
            SetQueries(st);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }

        }
    }

    private static void SetQueries(PreparedStatement st) throws SQLException {
        st.setInt(1,2);
        st.setInt(2,10);
        ResultSet rs = st.executeQuery();
        System.out.println("Результат выборки:");
        while(rs.next()){
            System.out.println(rs.getRow() + " " + rs.getInt("artist_id")
             +" " + rs.getString("name"));
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASS);
    }
}
