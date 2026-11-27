package id.ac.polinema.Frontend;

import id.ac.polinema.Backend.AkunLogin;
import javax.swing.*;

public class LoginJTIMerch extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnShow, btnForgot, btnSubmit, btnRegister;

    public LoginJTIMerch() {
        setTitle("Login - JTI Merch");
        setSize(380, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Header Judul & Subjudul
        JLabel lblTitle = new JLabel("JTI MERCH");
        lblTitle.setBounds(30, 25, 300, 25);
        add(lblTitle);

        JLabel lblSub = new JLabel("Technology for a Brighter Future");
        lblSub.setBounds(30, 50, 300, 20);
        add(lblSub);

        // Input Username / Email
        JLabel lblUsername = new JLabel("Username atau Email");
        lblUsername.setBounds(30, 90, 200, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(30, 115, 300, 28);
        add(txtUsername);

        // Input Password + Tombol Show/Hide
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(30, 155, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(30, 180, 225, 28);
        add(txtPassword);

        btnShow = new JButton("Show");
        btnShow.setBounds(260, 180, 70, 28);
        add(btnShow);

        // Tombol Navigasi Lupa Password (ke Form Valen)
        btnForgot = new JButton("Lupa Password?");
        btnForgot.setBounds(180, 218, 150, 25);
        add(btnForgot);

        // Tombol Submit Login
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(30, 260, 300, 32);
        add(btnSubmit);

        // Label Pemisah & Tombol Register (ke Form Akmal)
        JLabel lblOr = new JLabel("Tidak punya akun?", SwingConstants.CENTER);
        lblOr.setBounds(30, 305, 300, 20);
        add(lblOr);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(30, 335, 300, 32);
        add(btnRegister);

        // --- EVENT LISTENER ---

        // 1. Show / Hide Password
        btnShow.addActionListener(e -> {
            if (txtPassword.getEchoChar() == (char) 0) {
                txtPassword.setEchoChar('•');
                btnShow.setText("Show");
            } else {
                txtPassword.setEchoChar((char) 0);
                btnShow.setText("Hide");
            }
        });

        // 2. Proses Login ke Backend (AkunLogin)
        btnSubmit.addActionListener(e -> prosesLogin());

        // 3. Navigasi ke Form Register (Akmal)
        btnRegister.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Membuka halaman Register...");
            // Nanti diganti:
            // new FormRegister().setVisible(true);
            // this.dispose();
        });

        // 4. Navigasi ke Form Reset Password (Valen)
        btnForgot.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Membuka halaman Lupa Password...");
            // Nanti diganti:
            // new FormResetPassword().setVisible(true);
            // this.dispose();
        });
    }

    private void prosesLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Username/Email dan Password wajib diisi!",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Memanggil method static login() dari Backend/AkunLogin.java
        AkunLogin akun = AkunLogin.login(username, password);

        if (akun != null) {
            JOptionPane.showMessageDialog(this,
                "Login Berhasil!\nHalo, " + akun.getUsername() + " (" + akun.getRole() + ")",
                "Sukses", JOptionPane.INFORMATION_MESSAGE);

            if (akun.getRole().equalsIgnoreCase("Admin")) {
                // Arahkan ke halaman Admin / Tambah Admin (Zainur)
                // new FormTambahAdmin().setVisible(true);
                // this.dispose();
            } else {
                // Arahkan ke halaman utama Mahasiswa
                // new DashboardMahasiswa().setVisible(true);
                // this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Username/Email atau Password salah!",
                "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginJTIMerch().setVisible(true);
        });
    }
}