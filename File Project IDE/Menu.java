import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.getContentPane().setBackground(Color.white);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    private Database database;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel nilaiLabel;
    private JComboBox nilaiComboBox;

    // constructor
    public Menu() {
        // buat objek database
        database = new Database();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-Laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // Label untuk ComboBox
        String[] NilaiData = {"", "A", "B", "C", "D", "E"};
        nilaiComboBox.setModel(new DefaultComboBoxModel(NilaiData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                if (selectedIndex >= 0) {
                    // simpan value textfield dan combo box
                    String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                    String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                    String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                    String selectedNilai = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                    // ubah isi textfield dan combo box
                    nimField.setText(selectedNim);
                    namaField.setText(selectedNama);
                    jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                    nilaiComboBox.setSelectedItem(selectedNilai);

                    // ubah button "Add" menjadi "Update"
                    addUpdateButton.setText("Update");
                    // tampilkan button delete
                    deleteButton.setVisible(true);
                }
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Nilai"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");
            int i = 1;

            while (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = i++;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("nilai");

                temp.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error mengambil data: " + e.getMessage());
        }

        return temp;
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String nilai = nilaiComboBox.getSelectedItem().toString();

        // Validasi input kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || nilai.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cek apakah NIM sudah ada
        try {
            ResultSet rs = database.selectQuery("SELECT * FROM mahasiswa WHERE nim = '" + nim + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "NIM sudah terdaftar dalam database!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error saat memeriksa NIM: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // tambahkan data ke dalam database
        String sql = "INSERT INTO mahasiswa (nim, nama, jenis_kelamin, nilai) VALUES ('" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + nilai + "')";

        int result = database.insertUpdateDeleteQuery(sql);

        if (result > 0) {
            // update tabel
            mahasiswaTable.setModel(setTable());
            // bersihkan form
            clearForm();
            // feedback
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menambahkan data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String nilai = nilaiComboBox.getSelectedItem().toString();

        // Validasi input kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || nilai.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Dapatkan NIM dari baris yang dipilih
        String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();

        // Jika NIM diubah, periksa apakah NIM baru sudah ada
        if (!nim.equals(selectedNim)) {
            try {
                ResultSet rs = database.selectQuery("SELECT * FROM mahasiswa WHERE nim = '" + nim + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "NIM sudah terdaftar dalam database!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error saat memeriksa NIM: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Update data di database
        String sql = "UPDATE mahasiswa SET nim = '" + nim + "', nama = '" + nama + "', jenis_kelamin = '" + jenisKelamin + "', nilai = '" + nilai + "' WHERE nim = '" + selectedNim + "'";

        int result = database.insertUpdateDeleteQuery(sql);

        if (result > 0) {
            // update tabel
            mahasiswaTable.setModel(setTable());
            // bersihkan form
            clearForm();
            // feedback
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
        } else {
            JOptionPane.showMessageDialog(null, "Gagal mengubah data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Apakah Anda yakin ingin menghapus data ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String nim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();

            // Hapus data dari database
            String sql = "DELETE FROM mahasiswa WHERE nim = '" + nim + "'";

            int result = database.insertUpdateDeleteQuery(sql);

            if (result > 0) {
                // update tabel
                mahasiswaTable.setModel(setTable());
                // bersihkan form
                clearForm();
                // feedback
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus data!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        nilaiComboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }
}