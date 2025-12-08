/**
 * Kelas Menu (File: Menu.java) Berfungsi sebagai cetakan (blueprint) untuk setiap item makanan atau
 * minuman.
 */
public class Menu {
  // Atribut (Public agar mudah diakses langsung di Tugas 2 ini)
  public String nama;
  public double harga;
  public String kategori; // "makanan" atau "minuman"

  // Constructor: Method khusus yang dipanggil saat membuat objek baru
  public Menu(String nama, double harga, String kategori) {
    this.nama = nama;
    this.harga = harga;
    this.kategori = kategori;
  }

  // Method bantuan untuk menampilkan info menu dengan format rapi
  // Menggunakan printf untuk perataan teks
  public void tampilMenu() {
    System.out.printf("%-20s | %-10s | Rp %,.0f\n", nama, kategori, harga);
  }
}
