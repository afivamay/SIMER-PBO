# SIMER-PBO

=======
# 🛍️ SIMER — Sistem Merchandise JTI (Desktop App - Java Swing)

Aplikasi desktop berbasis **Java Swing** untuk pengelolaan sistem **Merchandise Jurusan Teknologi Informasi (JTI) Politeknik Negeri Malang**. Proyek ini dikembangkan sebagai implementasi mata kuliah **Pemrograman Berbasis Objek (PBO)** yang terintegrasi langsung dengan studi kasus proyek **Project-Based Learning (PBL) SIMER** serta menggunakan basis data relasional **PostgreSQL (Supabase)**.

---

## 👥 Anggota Kelompok & Pembagian Tugas

Proyek ini dikerjakan oleh 4 anggota kelompok dengan pembagian modul antarmuka (*form*) dan fungsionalitas *backend* masing-masing:

| Nama Anggota | Modul / Form yang Dikerjakan | Deskripsi Fitur Utama |
| :--- | :--- | :--- |
| **Afiva Maylinda** | **Form Login** (`LoginJTIMerch`) | Autentikasi pengguna (`Admin` & `Mahasiswa`), validasi input, fitur *show/hide password*, dan navigasi ke halaman Register / Lupa Password. |
| **Muhammad Akmal Dwiansyah Putra** | **Form Register** (`FormRegister`) | Pendaftaran akun baru khusus peran `Mahasiswa` (integrasi tabel `akun_login` dan profil `mahasiswa` seperti NIM, Nama, No. HP, Alamat). |
| **Valent Ridho Putra Santoso** | **Form Reset Password** (`FormResetPassword`) | Pengajuan dan pembaruan kata sandi pengguna yang lupa password (terintegrasi dengan tabel `akun_login` dan `reset_password`). |
| **Muhammad Zainur Roziqin** | **Form Tambah Admin** (`FormTambahAdmin`) | Pengelolaan fitur **Multi-Admin**, memungkinkan admin yang sedang aktif untuk menambahkan, melihat, dan mengelola akun dengan hak akses `Admin`. |

---

## 🛠️ Teknologi & Arsitektur Proyek

- **Bahasa Pemrograman:** Java (JDK 17)
- **GUI Framework:** Java Swing (`javax.swing.*`)
- **Build Tool & Dependency Manager:** Apache Maven (`pom.xml`)
- **Database:** PostgreSQL 15+ (dihosting bersama menggunakan **Supabase** via JDBC Driver `org.postgresql:postgresql:42.7.3`)
- **Pola Desain:** Pemisahan layer antara `Backend` (koneksi database `DBHelper` & kelas model/entitas) dan `Frontend` (kelas-kelas GUI Swing).

---

## 📂 Struktur Direktori Utama

```text
crud-mahasiswa/
├── pom.xml                                      # Konfigurasi Maven, JDK 17, & PostgreSQL JDBC Driver
├── merchandise_lengkap.sql                      # Skrip skema database PostgreSQL SIMER
└── src/
    └── main/
        └── java/
            └── id/
                └── ac/
                    └── polinema/
                        ├── Backend/
                        │   ├── DBHelper.java            # Helper koneksi JDBC & eksekusi query (PreparedStatement)
                        │   └── ...                      # Model & DAO (AkunLogin, Mahasiswa, KategoriMerchandise, dll.)
                        ├── LoginJTIMerch.java           # Form Login (Afiva)
                        ├── FormRegister.java            # Form Register (Akmal)
                        ├── FormResetPassword.java       # Form Reset Password (Valen)
                        └── FormTambahAdmin.java         # Form Tambah Multi-Admin (Zainur)
>>>>>>> 342c45f5171cd84251df722f8e58bfe0042b6e3f

