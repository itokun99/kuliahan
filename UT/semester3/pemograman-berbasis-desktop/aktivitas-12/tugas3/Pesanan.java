import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pesanan {
  // Atribut ArrayList untuk menyimpan item pesanan
  private ArrayList<MenuItem> daftarPesanan;

  public Pesanan() {
    daftarPesanan = new ArrayList<>();
  }

  public void tambahPesanan(MenuItem item) {
    daftarPesanan.add(item);
  }

  public double hitungTotal() {
    double subtotal = 0;
    double totalDiskonPersen = 0;

    // Iterasi item
    for (MenuItem item : daftarPesanan) {
      if (item instanceof Diskon) {
        // Jika item adalah Diskon, tambahkan persentasenya
        totalDiskonPersen += ((Diskon) item).getBesarDiskon();
      } else {
        // Jika Makanan/Minuman, tambahkan harganya
        subtotal += item.getHarga();
      }
    }

    double potongan = subtotal * (totalDiskonPersen / 100);
    return subtotal - potongan;
  }

  public void cetakStruk() {
    System.out.println("\n--- STRUK PESANAN ---");
    for (MenuItem item : daftarPesanan) {
      // Polymorphism tampilMenu()
      item.tampilMenu();
    }
    System.out.println("---------------------");
    System.out.printf("TOTAL BAYAR: Rp %,.0f\n", hitungTotal());

    // Simpan struk ke file
    simpanStrukKeFile();
  }

  // Operasi I/O: Menyimpan struk ke file
  private void simpanStrukKeFile() {
    try (BufferedWriter writer =
        new BufferedWriter(new FileWriter("struk_transaksi.txt", true))) { // true = append
      writer.write("\n--- TRANSAKSI BARU ---");
      writer.newLine();
      for (MenuItem item : daftarPesanan) {
        writer.write(item.getNama() + " - " + item.getHarga());
        writer.newLine();
      }
      writer.write("TOTAL: " + hitungTotal());
      writer.newLine();
      writer.write("----------------------");
      writer.newLine();
      System.out.println("(Struk telah disimpan ke 'struk_transaksi.txt')");
    } catch (IOException e) {
      System.out.println("Gagal menyimpan struk: " + e.getMessage());
    }
  }
}
