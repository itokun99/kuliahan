/**
 * Kelas Diskon (Inheritance dari MenuItem) Item ini digunakan untuk menerapkan diskon pada pesanan.
 */
public class Diskon extends MenuItem {
  private double besarDiskon; // Dalam persen, misal 10.0 untuk 10%

  public Diskon(String nama, double besarDiskon) {
    // Harga 0 karena ini bukan barang berbayar, tapi pengurang biaya
    super(nama, 0, "Diskon");
    this.besarDiskon = besarDiskon;
  }

  @Override
  public void tampilMenu() {
    System.out.printf("[Diskon ] %-20s | Potongan: %.0f%%\n", getNama(), besarDiskon);
  }

  public double getBesarDiskon() {
    return besarDiskon;
  }
}
