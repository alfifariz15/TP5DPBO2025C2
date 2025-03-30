# TP5DPBO2025C2
Saya Muhammad Alfi Fariz dengan NIM 2311174 mengerjakan TP 5 dalam mata kuliah Desain Pemrograman Berorientasi Objek
untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Penjelasan design program
Program ini merupakan aplikasi desktop untuk manajemen data mahasiswa dengan pendekatan arsitektur yang memisahkan:
- Database (Model): Menangani koneksi dan operasi database
- Mahasiswa (Entitas): Merepresentasikan objek mahasiswa dengan propertinya
- Menu (View & Controller): Menampilkan antarmuka dan menangani interaksi pengguna

1. Kelas Database
Bertanggung jawab untuk:
- Mengelola koneksi ke database MySQL
- Menyediakan metode untuk operasi database (SELECT, INSERT, UPDATE, DELETE)
Komponen utama:
- Connection dan Statement sebagai objek untuk koneksi database
- Metode selectQuery() untuk operasi SELECT
- Metode insertUpdateDeleteQuery() untuk operasi INSERT, UPDATE, DELETE

2. Kelas Mahasiswa
Bertanggung jawab untuk:
- Menyimpan informasi mahasiswa (nim, nama, jenis kelamin, nilai)
- Menyediakan getter dan setter untuk properti-properti tersebut
Pola desain: Menggunakan pola "JavaBean" dengan properti privat dan akses melalui getter/setter

3. Kelas Menu
Bertanggung jawab untuk:
- Menampilkan UI aplikasi menggunakan Swing
- Menangani interaksi pengguna melalui event listener
- Mengkoordinasikan operasi CRUD melalui kelas Database
Komponen UI:
- Form input (TextField, ComboBox)
- Tabel untuk menampilkan data
- Tombol untuk operasi (Add/Update, Delete, Cancel)

# Penjelasan Alur Program
1. Inisialisasi Aplikasi
   1. Program dimulai dari method main() pada kelas Menu
   2. Window aplikasi dibuat dan ditampilkan dengan komponen-komponennya
   3. Koneksi database dibuat saat instance Menu diinisialisasi
   4. Data mahasiswa dimuat dari database dan ditampilkan dalam tabel

2. Operasi Create (Tambah Data)
   1. Pengguna mengisi form (NIM, Nama, Jenis Kelamin, Nilai)
   2. Pengguna menekan tombol "Add"
   3. Method insertData() dipanggil yang akan:
      - Melakukan validasi input (tidak boleh kosong)
      - Memeriksa apakah NIM sudah ada dalam database
      - Menjalankan query INSERT ke database
      - Memperbarui tampilan tabel
      - Menampilkan pesan sukses/gagal
      - Membersihkan form

3. Operasi Read (Tampilkan Data)
   1. Data mahasiswa ditampilkan dalam tabel saat aplikasi dimulai
   2. Method setTable() mengambil data dari database dengan query SELECT
   3. Data dikonversi menjadi model tabel untuk ditampilkan

4. Operasi Update (Ubah Data)

   1. Pengguna memilih baris pada tabel dengan mengklik
   2. Data mahasiswa yang dipilih ditampilkan pada form
   3. Tombol "Add" berubah menjadi "Update" dan tombol "Delete" muncul
   4. Pengguna mengubah data pada form
   5. Pengguna menekan tombol "Update"
   6. Method updateData() dipanggil yang akan:
      - Melakukan validasi input
      - Jika NIM diubah, memeriksa apakah NIM baru sudah ada
      - Menjalankan query UPDATE ke database
      - Memperbarui tampilan tabel
      - Menampilkan pesan sukses/gagal
      - Membersihkan form

5. Operasi Delete (Hapus Data)
   1. Pengguna memilih baris pada tabel
   2. Pengguna menekan tombol "Delete"
   3. Konfirmasi penghapusan ditampilkan
   4. Jika pengguna mengkonfirmasi, method deleteData() dipanggil yang akan:
      - Menjalankan query DELETE ke database
      - Memperbarui tampilan tabel
      - Menampilkan pesan sukses/gagal
      - Membersihkan form

6. Operasi Cancel (Batal)
   1. Pengguna menekan tombol "Cancel"
   2. Method clearForm() dipanggil yang akan:
      - Mengosongkan semua field input
      - Mengembalikan tombol "Update" menjadi "Add"
      - Menyembunyikan tombol "Delete"
      - Menghapus seleksi baris pada tabel

# Dokumentasi Program Saat Di Jalankan

https://github.com/user-attachments/assets/ca2509dd-2917-4bee-82b9-045baa33f859
