import java.sql.*;

public class jdbc {
    String url, user, password, sql;
    Connection conn;
    ResultSet rs;

    public jdbc() {
        url = "jdbc:mysql://localhost:3306/setest";
        user = "root";
        password = "";
        conn = null;
        rs = null;
    }

    public void query() {
        try {
            sql = "SELECT *" +
                    "FROM grammar";
            conn = DriverManager.getConnection(url,user,password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getInt("c1") + "\t" +
                        rs.getString("c2"));
            }
            //processing here
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public int insert(int index, String word) {
        sql = "INSERT INTO grammar " +
                "VALUES(?,?)";
        int candidateId = 0;

        try {
            conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,index);
            pstmt.setString(2, word);

            int rowAffected = pstmt.executeUpdate();
            if(rowAffected == 1) {
                rs = pstmt.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);
            }
            //processing here
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return candidateId;
    }
}
