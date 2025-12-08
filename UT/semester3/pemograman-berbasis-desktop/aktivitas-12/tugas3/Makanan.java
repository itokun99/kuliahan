/** Kelas Makanan (Inheritance dari MenuItem) */
public class Makanan extends MenuItem {
  private String jenisMakanan; // Misal: "Gorengan", "Kuah", "Bakar"

  public Makanan(String nama, double harga, String jenisMakanan) {
    super(nama, harga, "Makanan"); // Panggil constructor parent
    this.jenisMakanan = jenisMakanan;
  }

  // Implementasi Polymorphism
  @Override
  public void tampilMenu() {
    System.out.printf(
        "[Makanan] %-20s | Rp %,.0f | Jenis: %s\n", getNama(), getHarga(), jenisMakanan);
  }

  public String getJenisMakanan() {
    return jenisMakanan;
  }
}
