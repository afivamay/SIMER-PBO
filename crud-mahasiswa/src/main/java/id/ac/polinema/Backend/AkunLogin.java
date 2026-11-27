package id.ac.polinema.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AkunLogin {
    private int idAkun;
    private String username;
    private String email;
    private String password;
    private String role;

    public AkunLogin() {
    }

    public AkunLogin(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getter & Setter
    public int getIdAkun() { return idAkun; }
    public void setIdAkun(int idAkun) { this.idAkun = idAkun; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Memverifikasi kecocokan username/email dan password di tabel akun_login
    public static AkunLogin login(String usernameOrEmail, String password) {
        String sql = "SELECT id_akun, username, email, password, role FROM akun_login "
                   + "WHERE (username = ? OR email = ?) AND password = ?";

        try (Connection c = DBHelper.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, usernameOrEmail);
            ps.setString(2, usernameOrEmail);
            ps.setString(3, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AkunLogin akun = new AkunLogin();
                    akun.setIdAkun(rs.getInt("id_akun"));
                    akun.setUsername(rs.getString("username"));
                    akun.setEmail(rs.getString("email"));
                    akun.setPassword(rs.getString("password"));
                    akun.setRole(rs.getString("role"));
                    return akun;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // public static void main(String[] args) {
    //     System.out.println("=== TEST BACKEND LOGIN ===");
    //     AkunLogin hasil = AkunLogin.login("admin_java", "admin123");

    //     if (hasil != null) {
    //         System.out.println("Login Berhasil!");
    //         System.out.println("ID Akun  : " + hasil.getIdAkun());
    //         System.out.println("Username : " + hasil.getUsername());
    //         System.out.println("Email    : " + hasil.getEmail());
    //         System.out.println("Role     : " + hasil.getRole());
    //     } else {
    //         System.out.println("Login Gagal! Cek username/password atau data di database.");
    //     }
    // }
}