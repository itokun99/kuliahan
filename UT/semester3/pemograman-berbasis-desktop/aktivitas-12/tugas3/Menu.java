import java.io.*;
import java.util.ArrayList;

/**
 * Kelas Menu (Tugas 3) Berfungsi sebagai Pengelola (Manager) untuk semua item di restoran.
 * Menyimpan data dalam ArrayList dan File Teks.
 */
public class Menu {
  // Atribut ArrayList untuk menyimpan daftar menu (Makanan/Minuman/Diskon)
  private ArrayList<MenuItem> daftarMenu;
  private final String FILE_NAME = "menu_database.txt";

  // Constructor
  public Menu() {
    daftarMenu = new ArrayList<>();
    muatMenuDariFile(); // Load data otomatis saat aplikasi dimulai
  }

  // Method untuk menambah item ke daftar dan simpan ke file
  public void tambahItem(MenuItem item) {
    daftarMenu.add(item);
    System.out.println(">> Item berhasil ditambahkan ke database!");
    simpanMenuKeFile(); // Auto-save
  }

  // Method menampilkan semua menu menggunakan Polymorphism
  public void tampilkanSemuaMenu() {
    System.out.println("\n=== DAFTAR MENU RESTORAN ===");
    if (daftarMenu.isEmpty()) {
      System.out.println("(Menu masih kosong)");
    } else {
      for (int i = 0; i < daftarMenu.size(); i++) {
        System.out.print((i + 1) + ". ");
        // Memanggil method tampilMenu() milik masing-masing item (Polymorphism)
        daftarMenu.get(i).tampilMenu();
      }
    }
  }

  // Mencari item berdasarkan nama
  public MenuItem cariMenu(String nama) throws Exception {
    for (MenuItem item : daftarMenu) {
      if (item.getNama().equalsIgnoreCase(nama)) {
        return item;
      }
    }
    // Exception Handling: Melempar error jika menu tidak ditemukan
    throw new Exception("Menu '" + nama + "' tidak ditemukan.");
  }

  // --- OPERASI FILE (I/O) ---

  // Menyimpan data ke file menu_database.txt
  public void simpanMenuKeFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
      for (MenuItem item : daftarMenu) {
        // Format penyimpanan: Tipe;Nama;Harga;InfoTambahan
        String line = "";
        if (item instanceof Makanan) {
          line =
              "Makanan;"
                  + item.getNama()
                  + ";"
                  + item.getHarga()
                  + ";"
                  + ((Makanan) item).getJenisMakanan();
        } else if (item instanceof Minuman) {
          // Pastikan urutan parameter sesuai constructor Minuman Anda
          line = "Minuman;" + item.getNama() + ";" + item.getHarga() + ";-";
        } else if (item instanceof Diskon) {
          line = "Diskon;" + item.getNama() + ";0;" + ((Diskon) item).getBesarDiskon();
        }
        writer.write(line);
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.println("Gagal menyimpan menu ke file: " + e.getMessage());
    }
  }

  // Memuat data dari file menu_database.txt saat program jalan
  public void muatMenuDariFile() {
    File file = new File(FILE_NAME);
    if (!file.exists()) return;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      daftarMenu.clear();
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(";");
        if (parts.length >= 4) {
          String tipe = parts[0];
          String nama = parts[1];
          double harga = Double.parseDouble(parts[2]);
          String info = parts[3];

          // Rekonstruksi objek berdasarkan tipe (Inheritance)
          if (tipe.equals("Makanan")) {
            daftarMenu.add(new Makanan(nama, harga, info));
          } else if (tipe.equals("Minuman")) {
            // Sesuaikan info tambahan jika kelas Minuman Anda memilikinya
            daftarMenu.add(new Minuman(nama, harga, "Umum"));
          } else if (tipe.equals("Diskon")) {
            daftarMenu.add(new Diskon(nama, Double.parseDouble(info)));
          }
        }
      }
    } catch (IOException | NumberFormatException e) {
      System.out.println("Gagal memuat menu dari file: " + e.getMessage());
    }
  }
}
