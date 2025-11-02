import java.util.Arrays;
import java.util.LinkedList;

public class Tugas1 {

  public static void main(String[] args) {

    // Biodata mahasiswa
    System.out.println("Tugas 1: Struktur Data");
    System.out.println("Nama: Indrawan Lisanto");
    System.out.println("NIM: 053724113");
    System.out.println("--");
    System.out.println();

    // Soal 1: Variabel dengan tipe data integer bernama 'StrukturBaris'
    int StrukturBaris;
    StrukturBaris = 10; // Memberi nilai contoh agar bisa dicetak
    System.out.println("Jawaban soal 1:");
    System.out.println("int StrukturBaris = " + StrukturBaris);
    System.out.println();

    // Soal 2: Variabel dengan tipe data string bernama 'KataBaru'
    // yang berisi kata 'Deklarasi tipe data String'
    String KataBaru = "Deklarasi tipe data String";
    System.out.println("Jawaban soal 2:");
    System.out.println("String KataBaru = " + KataBaru);
    System.out.println();

    // Soal 3: Array satu dimensi dengan nama 'empatAngka', tipe data
    // integer, yang berisi angka (07, 10, 20, 23)
    int[] empatAngka = {7, 10, 20, 23}; // Angka 07 dalam literal integer adalah 7
    System.out.println("Jawaban soal 3:");
    System.out.println("Array 1D 'empatAngka' = " + Arrays.toString(empatAngka));
    System.out.println();

    // Soal 4: Array dua dimensi dengan nama 'Angka', tipe data String,
    // yang terdiri dari tiga baris dan tiga kolom, isi baris dan kolom berisi angka
    // (1, 3, 5, 14, 19, 20, 22, 27, 29)
    String[][] Angka = {
      {"1", "3", "5"}, // baris 1
      {"14", "19", "20"}, // baris 2
      {"22", "27", "29"} // baris 3
    };
    System.out.println("Jawaban soal 4:");
    System.out.println("Array 2D 'Angka' (3 baris x 3 kolom) = ");
    for (String[] baris : Angka) {
      // Mencetak setiap baris dari array 2D
      System.out.println(Arrays.toString(baris));
    }
    System.out.println();

    // Soal 5: Deklarasi linked list 'listAngka' dengan nilai (22, 19, 44, 60, 72)
    // import LinkedList class from java.util
    LinkedList<Integer> listAngka = new LinkedList<>();

    // Menambahkan elemen ke dalam LinkedList
    listAngka.add(22);
    listAngka.add(19);
    listAngka.add(44);
    listAngka.add(60);
    listAngka.add(72);

    System.out.println("Jawaban Soal 5:");
    System.out.println("LinkedList 'listAngka' = " + listAngka);
  }
}
