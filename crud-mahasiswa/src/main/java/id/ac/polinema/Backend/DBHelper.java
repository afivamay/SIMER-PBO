package id.ac.polinema.Backend;

import java.sql.*;

public class DBHelper {

    private static final String URL = "jdbc:postgresql://localhost:5432/merchandise_lengkap";
    private static final String USER = "postgres";
    private static final String PASS = "";

    // Method reusable untuk membuka koneksi
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Mengisi tanda tanya (?) pada query secara berurutan
    private static void isiParameter(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }

    // INSERT lalu mengembalikan id yang dibuat otomatis oleh database (SERIAL)
    public static int insertQueryGetId(String sql, Object... params) {
        try (var c = getConnection();
             var ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            isiParameter(ps, params);
            ps.executeUpdate();
            try (var rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // UPDATE atau DELETE, mengembalikan true jika berhasil
    public static boolean executeQuery(String sql, Object... params) {
        try (var c = getConnection();
             var ps = c.prepareStatement(sql)) {
            isiParameter(ps, params);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // SELECT, mengembalikan ResultSet untuk dibaca di class model/backend
    public static ResultSet selectQuery(String sql, Object... params) throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement(sql);
        isiParameter(ps, params);
        return ps.executeQuery();
    }
}