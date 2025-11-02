import java.util.Scanner;

public class Main {

  // Array untuk menyimpan daftar menu dan pesanan
  static Menu[] menuArray = new Menu[8];

  // Array untuk menyimpan pesanan dengan maksimal 4 item
  static Menu[] pesananMenu = new Menu[4];
  static int[] pesananQty = new int[4];

  // scanner untuk input dari user
  static Scanner scanner = new Scanner(System.in);

  public static void inisialisasiMenu() {
    // kategori makanan (minimal 4)
    menuArray[0] = new Menu("Nasi Goreng", "makanan", 25000.0);
    menuArray[1] = new Menu("Mie Ayam", "makanan", 20000.0);
    menuArray[2] = new Menu("Sate Ayam", "makanan", 30000.0);
    menuArray[3] = new Menu("Gado-Gado", "makanan", 22000.0);

    // kategori minuman (minimal 4)
    menuArray[4] = new Menu("Es Teh", "minuman", 8000.0);
    menuArray[5] = new Menu("Es Jeruk", "minuman", 10000.0);
    menuArray[6] = new Menu("Kopi", "minuman", 15000.0);
    menuArray[7] = new Menu("Jus Alpukat", "minuman", 18000.0);
  }

  public static void tampilkanMenu() {
    System.out.println("\n--- DAFTAR MENU ---");
    System.out.println("Kategori: MAKANAN");

    // sesuai rules tidak boleh menggunakan loop
    if (menuArray[0].kategori.equals("makanan")) {
      System.out.printf("1. %-15s - Rp %,.0f\n", menuArray[0].nama, menuArray[0].harga);
    }
    if (menuArray[1].kategori.equals("makanan")) {
      System.out.printf("2. %-15s - Rp %,.0f\n", menuArray[1].nama, menuArray[1].harga);
    }
    if (menuArray[2].kategori.equals("makanan")) {
      System.out.printf("3. %-15s - Rp %,.0f\n", menuArray[2].nama, menuArray[2].harga);
    }
    if (menuArray[3].kategori.equals("makanan")) {
      System.out.printf("4. %-15s - Rp %,.0f\n", menuArray[3].nama, menuArray[3].harga);
    }

    System.out.println("\nKategori: MINUMAN");
    if (menuArray[4].kategori.equals("minuman")) {
      System.out.printf("5. %-15s - Rp %,.0f\n", menuArray[4].nama, menuArray[4].harga);
    }
    if (menuArray[5].kategori.equals("minuman")) {
      System.out.printf("6. %-15s - Rp %,.0f\n", menuArray[5].nama, menuArray[5].harga);
    }
    if (menuArray[6].kategori.equals("minuman")) {
      System.out.printf("7. %-15s - Rp %,.0f\n", menuArray[6].nama, menuArray[6].harga);
    }
    if (menuArray[7].kategori.equals("minuman")) {
      System.out.printf("8. %-15s - Rp %,.0f\n", menuArray[7].nama, menuArray[7].harga);
    }
  }

  public static void terimaPesanan() {
    System.out.println("\n--- INPUT PESANAN ---");
    System.out.println("Silakan masukkan nomor menu (1-8). Masukkan 0 jika selesai memesan.");

    // Pesanan ke-1
    System.out.print("Pesanan 1 (Nomor Menu): ");
    int idx1 = scanner.nextInt();
    if (idx1 >= 1 && idx1 <= 8) {
      System.out.print("Jumlah Pesanan 1: ");
      pesananQty[0] = scanner.nextInt();
      pesananMenu[0] = menuArray[idx1 - 1]; // -1 karena array 0-based
    }

    // Pesanan ke-2
    System.out.print("Pesanan 2 (Nomor Menu): ");
    int idx2 = scanner.nextInt();
    if (idx2 >= 1 && idx2 <= 8) {
      System.out.print("Jumlah Pesanan 2: ");
      pesananQty[1] = scanner.nextInt();
      pesananMenu[1] = menuArray[idx2 - 1];
    }

    // Pesanan ke-3
    System.out.print("Pesanan 3 (Nomor Menu): ");
    int idx3 = scanner.nextInt();
    if (idx3 >= 1 && idx3 <= 8) {
      System.out.print("Jumlah Pesanan 3: ");
      pesananQty[2] = scanner.nextInt();
      pesananMenu[2] = menuArray[idx3 - 1];
    }

    // Pesanan ke-4
    System.out.print("Pesanan 4 (Nomor Menu): ");
    int idx4 = scanner.nextInt();
    if (idx4 >= 1 && idx4 <= 8) {
      System.out.print("Jumlah Pesanan 4: ");
      pesananQty[3] = scanner.nextInt();
      pesananMenu[3] = menuArray[idx4 - 1];
    }
  }

  public static void hitungDanCetakStruk() {
    System.out.println("\n--- STRUK PEMBAYARAN ---");

    // --- 1. Hitung Total Biaya Awal ---
    double totalBiaya = 0.0;

    // Hitung total item 1 (tanpa loop)
    if (pesananMenu[0] != null && pesananQty[0] > 0) {
      double subtotal = pesananMenu[0].harga * pesananQty[0];
      System.out.printf("%-15s x%d - Rp %,.0f\n", pesananMenu[0].nama, pesananQty[0], subtotal);
      totalBiaya += subtotal;
    }
    // Hitung total item 2 (tanpa loop)
    if (pesananMenu[1] != null && pesananQty[1] > 0) {
      double subtotal = pesananMenu[1].harga * pesananQty[1];
      System.out.printf("%-15s x%d - Rp %,.0f\n", pesananMenu[1].nama, pesananQty[1], subtotal);
      totalBiaya += subtotal;
    }
    // Hitung total item 3 (tanpa loop)
    if (pesananMenu[2] != null && pesananQty[2] > 0) {
      double subtotal = pesananMenu[2].harga * pesananQty[2];
      System.out.printf("%-15s x%d - Rp %,.0f\n", pesananMenu[2].nama, pesananQty[2], subtotal);
      totalBiaya += subtotal;
    }
    // Hitung total item 4 (tanpa loop)
    if (pesananMenu[3] != null && pesananQty[3] > 0) {
      double subtotal = pesananMenu[3].harga * pesananQty[3];
      System.out.printf("%-15s x%d - Rp %,.0f\n", pesananMenu[3].nama, pesananQty[3], subtotal);
      totalBiaya += subtotal;
    }

    System.out.println("---------------------------------");
    System.out.printf("Total Biaya         : Rp %,.0f\n", totalBiaya);

    // --- 2. Hitung Pajak dan Biaya Pelayanan ---
    double biayaPajak = totalBiaya * 0.10; // 10%
    double biayaPelayanan = 20000.0;

    System.out.printf("Pajak (10%%)         : Rp %,.0f\n", biayaPajak);
    System.out.printf("Biaya Pelayanan     : Rp %,.0f\n", biayaPelayanan);

    // --- 3. Hitung Diskon dan Penawaran (Indikator 3: Struktur Keputusan) ---
    double totalDiskon = 0.0;
    double penawaranMinuman = 0.0;

    // a. Diskon 10% jika total biaya > Rp 100.000
    // Indikator 3: Menggunakan 'if'
    if (totalBiaya > 100000) {
      totalDiskon = totalBiaya * 0.10;
      System.out.printf("Diskon (10%%)        : -Rp %,.0f\n", totalDiskon);
    }

    // b. Penawaran "Beli 1 Gratis 1 Minuman" jika total biaya > Rp 50.000
    //    Interpretasi: Kita berikan gratis 1 minuman termurah yang dipesan.
    // Indikator 3: Menggunakan 'if-else if' dan 'nested if'
    if (totalBiaya > 50000) {
      double minumanTermurah = Double.MAX_VALUE; // Nilai awal sangat besar
      boolean adaMinuman = false;

      // Cek pesanan 1 (tanpa loop)
      if (pesananMenu[0] != null
          && pesananQty[0] > 0
          && pesananMenu[0].kategori.equals("minuman")) {
        if (pesananMenu[0].harga < minumanTermurah) {
          minumanTermurah = pesananMenu[0].harga;
        }
        adaMinuman = true;
      }
      // Cek pesanan 2 (tanpa loop)
      if (pesananMenu[1] != null
          && pesananQty[1] > 0
          && pesananMenu[1].kategori.equals("minuman")) {
        if (pesananMenu[1].harga < minumanTermurah) {
          minumanTermurah = pesananMenu[1].harga;
        }
        adaMinuman = true;
      }
      // Cek pesanan 3 (tanpa loop)
      if (pesananMenu[2] != null
          && pesananQty[2] > 0
          && pesananMenu[2].kategori.equals("minuman")) {
        if (pesananMenu[2].harga < minumanTermurah) {
          minumanTermurah = pesananMenu[2].harga;
        }
        adaMinuman = true;
      }
      // Cek pesanan 4 (tanpa loop)
      if (pesananMenu[3] != null
          && pesananQty[3] > 0
          && pesananMenu[3].kategori.equals("minuman")) {
        if (pesananMenu[3].harga < minumanTermurah) {
          minumanTermurah = pesananMenu[3].harga;
        }
        adaMinuman = true;
      }

      // Jika ada minuman yang dipesan, berikan gratis yang termurah
      if (adaMinuman) {
        penawaranMinuman = minumanTermurah;
        System.out.printf("Penawaran Gratis 1   : -Rp %,.0f\n", penawaranMinuman);
      }
    }

    // --- 4. Hitung Grand Total ---
    double grandTotal = totalBiaya + biayaPajak + biayaPelayanan - totalDiskon - penawaranMinuman;

    System.out.println("---------------------------------");
    System.out.printf("TOTAL BAYAR         : Rp %,.0f\n", grandTotal);
  }

  // main method application
  public static void main(String[] args) {
    System.out.println("Selamat Datang di Restoran Indrawan!");

    // 1. Siapkan menu-menu
    inisialisasiMenu();

    // 2. Tampilkan menu ke pelanggan
    tampilkanMenu();

    // 3. Terima pesanan dari pelanggan
    terimaPesanan();

    // 4. Hitung dan cetak struk
    hitungDanCetakStruk();

    scanner.close();
    System.out.println("\nTerima kasih telah memesan!");
  }
}
