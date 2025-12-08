import java.util.*;

// Kelas Graph merepresentasikan graf menggunakan Adjacency Matrix
class Graph {
  private int MAX_NODES;
  private int[][] adjMatrix; // Matriks ketetanggaan untuk menyimpan hubungan antar node
  private String[] nodeList; // Menyimpan nama node (a1, a2, ...)

  // Konstruktor
  public Graph(int maxNodes) {
    this.MAX_NODES = maxNodes;
    adjMatrix = new int[maxNodes][maxNodes];
    nodeList = new String[maxNodes];

    // Inisialisasi matriks dengan 0
    for (int i = 0; i < maxNodes; i++) {
      for (int j = 0; j < maxNodes; j++) {
        adjMatrix[i][j] = 0;
      }
    }
  }

  // Method untuk memberi nama node
  public void setNodeName(int index, String name) {
    nodeList[index] = name;
  }

  // Method untuk menambah edge (hubungan) antar node
  // Graph ini diasumsikan Undirected (dua arah)
  public void addEdge(int i, int j) {
    adjMatrix[i][j] = 1;
    adjMatrix[j][i] = 1;
  }

  // --- SOAL 1: Depth-First Search (DFS) ---
  // Menggunakan struktur data STACK (Tumpukan)
  public void dfs(String target) {
    System.out.println("\n=== MULAI PENCARIAN DFS ===");
    System.out.println("Mencari node: " + target);

    boolean[] visited = new boolean[MAX_NODES];
    Stack<Integer> stack = new Stack<>();

    // Mulai dari node pertama (indeks 0 / a1)
    stack.push(0);

    while (!stack.isEmpty()) {
      // Ambil node dari atas stack
      int current = stack.pop();

      // Jika belum dikunjungi, maka kunjungi
      if (!visited[current]) {
        visited[current] = true;
        System.out.print("Visited: " + nodeList[current]);

        // Cek apakah ini node yang dicari
        if (nodeList[current].equals(target)) {
          System.out.println(" -> DITEMUKAN!");
          return; // Selesai
        }
        System.out.println(" -> Bukan target.");

        // Masukkan tetangga ke stack
        // Loop mundur agar urutan masuk ke stack sesuai urutan alami (kanan dulu, baru kiri agar
        // kiri di-pop duluan)
        for (int i = MAX_NODES - 1; i >= 0; i--) {
          if (adjMatrix[current][i] == 1 && !visited[i]) {
            stack.push(i);
          }
        }
      }
    }
    System.out.println("Node tidak ditemukan.");
  }

  // --- SOAL 2: Breadth-First Search (BFS) ---
  // Menggunakan struktur data QUEUE (Antrean)
  public void bfs(String target) {
    System.out.println("\n=== MULAI PENCARIAN BFS ===");
    System.out.println("Mencari node: " + target);

    boolean[] visited = new boolean[MAX_NODES];
    Queue<Integer> queue = new LinkedList<>();

    // Mulai dari node pertama (indeks 0 / a1)
    visited[0] = true;
    queue.add(0);

    while (!queue.isEmpty()) {
      // Ambil node dari depan antrean
      int current = queue.poll();

      System.out.print("Mengunjungi: " + nodeList[current]);

      // Cek apakah ini node yang dicari
      if (nodeList[current].equals(target)) {
        System.out.println(" -> DITEMUKAN!");
        return; // Selesai
      }
      System.out.println(" -> Bukan target.");

      // Masukkan sibling yang belum dikunjungi ke queue
      for (int i = 0; i < MAX_NODES; i++) {
        if (adjMatrix[current][i] == 1 && !visited[i]) {
          visited[i] = true; // Tandai visited agar tidak duplicate antriannya
          queue.add(i);
        }
      }
    }
    System.out.println("Node tidak ditemukan.");
  }
}

public class Tugas3 {
  public static void main(String[] args) {
    // Graph dengan 8 Node
    int jumlahNode = 8;
    Graph graph = new Graph(jumlahNode);

    // label a1 sampai a8
    for (int i = 0; i < jumlahNode; i++) {
      graph.setNodeName(i, "a" + (i + 1));
    }

    // hubungan antar node (Skenario Graph)
    // a1 (0) terhubung ke a2 (1) dan a3 (2)
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);

    // a2 (1) terhubung ke a4 (3) dan a5 (4)
    graph.addEdge(1, 3);
    graph.addEdge(1, 4);

    // a3 (2) terhubung ke a6 (5) dan a7 (6)
    graph.addEdge(2, 5);
    graph.addEdge(2, 6);

    // a5 (4) terhubung ke a8 (7)
    graph.addEdge(4, 7);

    // Target node: "a8"
    String target = "a8";

    // Run DFS
    graph.dfs(target);

    // Run BFS
    graph.bfs(target);
  }
}
