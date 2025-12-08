/** Kelas Minuman (Inheritance dari MenuItem) */
public class Minuman extends MenuItem {
  private String jenisMinuman; // Misal: "Dingin", "Panas"

  public Minuman(String nama, double harga, String jenisMinuman) {
    super(nama, harga, "Minuman");
    this.jenisMinuman = jenisMinuman;
  }

  @Override
  public void tampilMenu() {
    System.out.printf(
        "[Minuman] %-20s | Rp %,.0f | Jenis: %s\n", getNama(), getHarga(), jenisMinuman);
  }
}
