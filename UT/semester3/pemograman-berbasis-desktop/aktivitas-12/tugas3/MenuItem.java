import java.io.Serializable;

/**
 * Abstract Class MenuItem Kelas dasar untuk semua item di restoran. Mengimplementasikan
 * Encapsulation (private attributes + getter/setter).
 */
public abstract class MenuItem implements Serializable {
  private String nama;
  private double harga;
  private String kategori;

  public MenuItem(String nama, double harga, String kategori) {
    this.nama = nama;
    this.harga = harga;
    this.kategori = kategori;
  }

  // Abstract method: Wajib diimplementasikan oleh kelas anak
  // Ini adalah bentuk Polymorphism (metode sama, perilaku beda)
  public abstract void tampilMenu();

  // Getter dan Setter (Encapsulation)
  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public double getHarga() {
    return harga;
  }

  public void setHarga(double harga) {
    this.harga = harga;
  }

  public String getKategori() {
    return kategori;
  }

  public void setKategori(String kategori) {
    this.kategori = kategori;
  }
}
