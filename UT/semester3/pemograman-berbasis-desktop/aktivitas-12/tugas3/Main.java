import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Kelas Main (Tugas 3) Menjalankan aplikasi dan menangani interaksi user. Mengintegrasikan Menu
 * (Manajemen) dan Pesanan (Transaksi).
 */
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Membuat objek Menu (otomatis memuat data dari file jika ada)
    Menu menuRestoran = new Menu();

    // Objek Pesanan akan dibuat baru setiap kali pelanggan mulai memesan
    Pesanan pesananPelanggan = null;

    boolean isRunning = true;
    while (isRunning) {
      System.out.println("\n=== SISTEM MANAJEMEN RESTORAN (TUGAS 3: OOP) ===");
      System.out.println("1. Tambah Item Baru (Admin)");
      System.out.println("2. Tampilkan Menu");
      System.out.println("3. Buat Pesanan Baru");
      System.out.println("4. Keluar");
      System.out.print("Pilih Menu: ");

      int pilihan = 0;
      try {
        pilihan = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer newline
      } catch (InputMismatchException e) {
        System.out.println(">> Error: Input harus angka!");
        scanner.nextLine(); // Bersihkan buffer error
        continue;
      }

      switch (pilihan) {
        case 1:
          // --- FITUR ADMIN: TAMBAH MENU ---
          System.out.println("\n--- TAMBAH MENU BARU ---");
          System.out.println("1. Makanan");
          System.out.println("2. Minuman");
          System.out.println("3. Item Diskon");
          System.out.print("Pilih Tipe Item: ");

          int tipe = 0;
          try {
            tipe = scanner.nextInt();
            scanner.nextLine();
          } catch (InputMismatchException e) {
            System.out.println(">> Input salah.");
            scanner.nextLine();
            break;
          }

          System.out.print("Nama Item: ");
          String nama = scanner.nextLine();

          // Logika percabangan untuk membuat objek yang tepat (Polymorphism)
          if (tipe == 1) { // Makanan
            System.out.print("Harga: ");
            double harga = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Jenis Makanan (cth: Kuah/Goreng): ");
            String jenis = scanner.nextLine();
            // Menambahkan objek Makanan ke dalam Menu
            menuRestoran.tambahItem(new Makanan(nama, harga, jenis));

          } else if (tipe == 2) { // Minuman
            System.out.print("Harga: ");
            double harga = scanner.nextDouble();
            scanner.nextLine();
            // Asumsi kelas Minuman menerima parameter jenis (sesuai inheritance)
            System.out.print("Jenis Minuman (cth: Panas/Dingin): ");
            String jenis = scanner.nextLine();
            // Menambahkan objek Minuman ke dalam Menu
            menuRestoran.tambahItem(new Minuman(nama, harga, jenis));

          } else if (tipe == 3) { // Diskon
            System.out.print("Besar Diskon (%): ");
            double diskon = scanner.nextDouble();
            scanner.nextLine();
            // Menambahkan objek Diskon ke dalam Menu
            menuRestoran.tambahItem(new Diskon(nama, diskon));
          } else {
            System.out.println(">> Tipe tidak valid.");
          }
          break;

        case 2:
          // --- FITUR TAMPILKAN MENU ---
          menuRestoran.tampilkanSemuaMenu();
          break;

        case 3:
          // --- FITUR PELANGGAN: PESAN ---
          pesananPelanggan = new Pesanan();
          System.out.println("\n--- BUAT PESANAN ---");
          System.out.println("Ketik nama menu persis sesuai daftar.");
          System.out.println("Ketik 'selesai' untuk mengakhiri dan cetak struk.");

          while (true) {
            menuRestoran.tampilkanSemuaMenu();
            System.out.print("\nPilih nama menu: ");
            String inputNama = scanner.nextLine();

            if (inputNama.equalsIgnoreCase("selesai")) {
              break;
            }

            // Exception Handling: Mencoba mencari menu
            try {
              // Mencari objek di database (bisa melempar Exception)
              MenuItem itemDitemukan = menuRestoran.cariMenu(inputNama);

              // Menambahkan ke keranjang belanja
              pesananPelanggan.tambahPesanan(itemDitemukan);
              System.out.println(">> Berhasil: " + itemDitemukan.getNama() + " masuk keranjang.");

            } catch (Exception e) {
              // Menangkap error jika menu tidak ditemukan (Dilempar dari Menu.java)
              System.out.println(">> ERROR: " + e.getMessage());
              System.out.println(">> Silakan cek ejaan dan coba lagi.");
            }
          }

          // Cetak Struk Akhir & Simpan ke File (dihandle oleh class Pesanan)
          if (pesananPelanggan != null) {
            pesananPelanggan.cetakStruk();
          }
          break;

        case 4:
          isRunning = false;
          System.out.println("Aplikasi ditutup. Terima kasih!");
          break;

        default:
          System.out.println("Pilihan tidak valid.");
      }
    }
    scanner.close();
  }
}
