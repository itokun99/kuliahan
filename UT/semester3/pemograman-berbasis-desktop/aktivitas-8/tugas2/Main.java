import java.util.Scanner;

/**
 * Kelas Main (File: Main.java) Menangani logika utama aplikasi, termasuk interaksi pelanggan dan
 * manajemen admin.
 */
public class Main {
  // --- VARIABEL GLOBAL (STATIC) ---
  // Batas maksimal menu dan pesanan (karena menggunakan Array statis)
  static final int MAX_MENU = 100;
  static final int MAX_PESANAN = 100;

  // Array untuk menyimpan Database Menu Restoran
  static Menu[] daftarMenu = new Menu[MAX_MENU];
  static int jumlahMenu = 0; // Melacak jumlah menu yang aktif

  // Array untuk menyimpan Keranjang Pesanan Pelanggan
  static Menu[] pesananMenu = new Menu[MAX_PESANAN];
  static int[] pesananQty = new int[MAX_PESANAN];
  static int jumlahPesanan = 0;

  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    // 1. Inisialisasi data awal (Dummy Data)
    isiDataAwal();

    boolean isRunning = true;

    // Loop Utama Aplikasi (Agar program tidak langsung berhenti)
    while (isRunning) {
      System.out.println("\n======================================");
      System.out.println("   SISTEM MANAJEMEN RESTORAN UT   ");
      System.out.println("======================================");
      System.out.println("1. Mode Pelanggan (Pemesanan)");
      System.out.println("2. Mode Manajemen (Admin Menu)");
      System.out.println("3. Keluar Aplikasi");
      System.out.print("Pilih Menu Utama (1-3): ");

      int pilihan = 0;
      try {
        pilihan = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer enter
      } catch (Exception e) {
        System.out.println("Input harus angka!");
        scanner.nextLine();
        continue;
      }

      // Navigasi Menu Utama
      switch (pilihan) {
        case 1:
          modePelanggan();
          break;
        case 2:
          modeManajemen();
          break;
        case 3:
          System.out.println("Terima kasih telah menggunakan aplikasi ini.");
          isRunning = false;
          break;
        default:
          System.out.println("Pilihan tidak valid.");
      }
    }
  }

  // ==========================================
  // BAGIAN 1: LOGIKA PELANGGAN (PEMESANAN)
  // ==========================================

  static void modePelanggan() {
    // Reset keranjang pesanan untuk pelanggan baru
    jumlahPesanan = 0;

    System.out.println("\n--- MODE PELANGGAN ---");
    tampilkanDaftarMenu();

    boolean memesan = true;
    // Loop pemesanan berulang (fitur "tidak terbatas")
    while (memesan) {
      System.out.println(
          "\nMasukkan nama menu yang ingin dipesan (atau ketik 'selesai' untuk bayar):");
      System.out.print("> ");
      String inputNama = scanner.nextLine();

      // Cek kondisi berhenti (Case Insensitive)
      if (inputNama.equalsIgnoreCase("selesai")) {
        if (jumlahPesanan > 0) {
          hitungDanCetakStruk();
        } else {
          System.out.println("Anda membatalkan pemesanan atau belum memesan apapun.");
        }
        memesan = false; // Keluar dari loop
        break;
      }

      // Mencari menu di array (Looping Search)
      Menu menuDitemukan = null;
      for (int i = 0; i < jumlahMenu; i++) {
        if (daftarMenu[i].nama.equalsIgnoreCase(inputNama)) {
          menuDitemukan = daftarMenu[i];
          break;
        }
      }

      // Validasi menu ditemukan atau tidak
      if (menuDitemukan != null) {
        System.out.print("Masukkan jumlah pesan: ");
        int qty = 0;
        try {
          qty = scanner.nextInt();
          scanner.nextLine(); // clean buffer
        } catch (Exception e) {
          System.out.println("Jumlah harus angka!");
          scanner.nextLine();
          continue;
        }

        if (qty > 0) {
          // Simpan ke array pesanan
          pesananMenu[jumlahPesanan] = menuDitemukan;
          pesananQty[jumlahPesanan] = qty;
          jumlahPesanan++;
          System.out.println(
              "Berhasil menambahkan " + qty + " " + menuDitemukan.nama + " ke keranjang.");
        } else {
          System.out.println("Jumlah pesan minimal 1.");
        }
      } else {
        System.out.println("Menu tidak ditemukan! Silakan cek ejaan nama menu.");
      }
    }
  }

  static void hitungDanCetakStruk() {
    double totalBiaya = 0;

    System.out.println("\n----------------------------------------");
    System.out.println("             STRUK PEMBAYARAN           ");
    System.out.println("----------------------------------------");

    // Loop menghitung subtotal
    for (int i = 0; i < jumlahPesanan; i++) {
      double subtotal = pesananMenu[i].harga * pesananQty[i];
      totalBiaya += subtotal;
      System.out.printf("%-18s x%d   Rp %,.0f\n", pesananMenu[i].nama, pesananQty[i], subtotal);
    }

    System.out.println("----------------------------------------");
    System.out.printf("Total Pesanan       : Rp %,.0f\n", totalBiaya);

    // Biaya Tambahan
    double pajak = totalBiaya * 0.10; // Pajak 10%
    double service = 20000; // Biaya Layanan Rp 20.000

    // Logika Diskon & Penawaran
    double diskon = 0;
    double potonganMinuman = 0;

    // a. Diskon 10% jika total > 100.000
    if (totalBiaya > 100000) {
      diskon = totalBiaya * 0.10;
    }

    // b. Beli 1 Gratis 1 Minuman jika total > 50.000
    // Cari minuman termurah di keranjang, lalu gratiskan harganya
    if (totalBiaya > 50000) {
      double hargaMinumanTermurah = Double.MAX_VALUE;
      boolean adaMinuman = false;

      for (int i = 0; i < jumlahPesanan; i++) {
        if (pesananMenu[i].kategori.equalsIgnoreCase("minuman")) {
          if (pesananMenu[i].harga < hargaMinumanTermurah) {
            hargaMinumanTermurah = pesananMenu[i].harga;
          }
          adaMinuman = true;
        }
      }

      if (adaMinuman) {
        potonganMinuman = hargaMinumanTermurah;
      }
    }

    double grandTotal = totalBiaya + pajak + service - diskon - potonganMinuman;

    // Cetak Rincian
    System.out.printf("Pajak (10%%)         : Rp %,.0f\n", pajak);
    System.out.printf("Biaya Layanan       : Rp %,.0f\n", service);

    if (diskon > 0) {
      System.out.printf("Diskon (10%%)        : -Rp %,.0f\n", diskon);
    }
    if (potonganMinuman > 0) {
      System.out.printf("Promo Minuman       : -Rp %,.0f\n", potonganMinuman);
    }

    System.out.println("----------------------------------------");
    System.out.printf("TOTAL BAYAR         : Rp %,.0f\n", grandTotal);
    System.out.println("----------------------------------------\n");
  }

  // ==========================================
  // BAGIAN 2: LOGIKA MANAJEMEN (ADMIN)
  // ==========================================

  static void modeManajemen() {
    boolean manage = true;
    while (manage) {
      System.out.println("\n--- MENU MANAJEMEN (ADMIN) ---");
      System.out.println("1. Tambah Menu Baru");
      System.out.println("2. Ubah Harga Menu");
      System.out.println("3. Hapus Menu");
      System.out.println("4. Kembali ke Menu Utama");
      System.out.print("Pilihan Admin: ");

      int pil = 0;
      try {
        pil = scanner.nextInt();
        scanner.nextLine();
      } catch (Exception e) {
        System.out.println("Input salah.");
        scanner.nextLine();
        continue;
      }

      switch (pil) {
        case 1:
          tambahMenuBaru();
          break;
        case 2:
          ubahHargaMenu();
          break;
        case 3:
          hapusMenu();
          break;
        case 4:
          manage = false;
          break;
        default:
          System.out.println("Input salah.");
      }
    }
  }

  static void tambahMenuBaru() {
    if (jumlahMenu >= MAX_MENU) {
      System.out.println("Kapasitas menu penuh!");
      return;
    }

    System.out.println("\n[Tambah Menu]");
    System.out.print("Nama Menu: ");
    String nama = scanner.nextLine();

    System.out.print("Harga: ");
    double harga = 0;
    try {
      harga = scanner.nextDouble();
      scanner.nextLine();
    } catch (Exception e) {
      System.out.println("Harga harus angka!");
      scanner.nextLine();
      return;
    }

    System.out.print("Kategori (makanan/minuman): ");
    String kat = scanner.nextLine();

    // Tambah ke array
    daftarMenu[jumlahMenu] = new Menu(nama, harga, kat);
    jumlahMenu++;
    System.out.println("Menu berhasil ditambahkan!");
    tampilkanDaftarMenu();
  }

  static void ubahHargaMenu() {
    tampilkanDaftarMenu();
    System.out.println("\n[Ubah Harga]");
    System.out.print("Masukkan Nomor Menu yang akan diubah: ");

    int index = -1;
    try {
      index = scanner.nextInt() - 1; // user input 1, array mulai 0
      scanner.nextLine();
    } catch (Exception e) {
      System.out.println("Input harus angka!");
      scanner.nextLine();
      return;
    }

    if (index >= 0 && index < jumlahMenu) {
      System.out.print("Masukkan Harga Baru: ");
      double hargaBaru = 0;
      try {
        hargaBaru = scanner.nextDouble();
        scanner.nextLine();
      } catch (Exception e) {
        System.out.println("Harga harus angka!");
        scanner.nextLine();
        return;
      }

      System.out.print("Yakin ubah harga " + daftarMenu[index].nama + "? (Ya/Tidak): ");
      String confirm = scanner.nextLine();

      if (confirm.equalsIgnoreCase("Ya")) {
        daftarMenu[index].harga = hargaBaru;
        System.out.println("Harga berhasil diubah.");
      } else {
        System.out.println("Batal mengubah harga.");
      }
    } else {
      System.out.println("Nomor menu tidak valid.");
    }
  }

  static void hapusMenu() {
    tampilkanDaftarMenu();
    System.out.println("\n[Hapus Menu]");
    System.out.print("Masukkan Nomor Menu yang akan dihapus: ");

    int index = -1;
    try {
      index = scanner.nextInt() - 1;
      scanner.nextLine();
    } catch (Exception e) {
      System.out.println("Input harus angka!");
      scanner.nextLine();
      return;
    }

    if (index >= 0 && index < jumlahMenu) {
      System.out.print("Yakin HAPUS " + daftarMenu[index].nama + "? (Ya/Tidak): ");
      String confirm = scanner.nextLine();

      if (confirm.equalsIgnoreCase("Ya")) {
        // Logika Hapus Array: Geser elemen kiri untuk menutup lubang
        for (int i = index; i < jumlahMenu - 1; i++) {
          daftarMenu[i] = daftarMenu[i + 1];
        }
        daftarMenu[jumlahMenu - 1] = null; // Kosongkan elemen terakhir
        jumlahMenu--; // Kurangi counter
        System.out.println("Menu berhasil dihapus.");
      } else {
        System.out.println("Batal menghapus.");
      }
    } else {
      System.out.println("Nomor menu tidak valid.");
    }
  }

  // ==========================================
  // UTILITIES
  // ==========================================

  static void tampilkanDaftarMenu() {
    System.out.println("\n--- DAFTAR MENU SAAT INI ---");
    System.out.printf("%-3s | %-20s | %-10s | %s\n", "No", "Nama", "Kategori", "Harga");
    System.out.println("-----------------------------------------------------");

    for (int i = 0; i < jumlahMenu; i++) {
      System.out.printf(
          "%-3d | %-20s | %-10s | Rp %,.0f\n",
          (i + 1), daftarMenu[i].nama, daftarMenu[i].kategori, daftarMenu[i].harga);
    }
  }

  static void isiDataAwal() {
    daftarMenu[jumlahMenu++] = new Menu("Nasi Padang", 25000, "makanan");
    daftarMenu[jumlahMenu++] = new Menu("Ayam Bakar", 18000, "makanan");
    daftarMenu[jumlahMenu++] = new Menu("Soto Ayam", 15000, "makanan");
    daftarMenu[jumlahMenu++] = new Menu("Mie Goreng", 12000, "makanan");

    daftarMenu[jumlahMenu++] = new Menu("Es Teh Manis", 5000, "minuman");
    daftarMenu[jumlahMenu++] = new Menu("Jus Alpukat", 12000, "minuman");
    daftarMenu[jumlahMenu++] = new Menu("Kopi Hitam", 8000, "minuman");
    daftarMenu[jumlahMenu++] = new Menu("Air Mineral", 4000, "minuman");
  }
}
