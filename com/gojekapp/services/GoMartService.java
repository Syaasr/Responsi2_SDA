package com.gojekapp.services;
import com.gojekapp.models.*;
import com.gojekapp.structures.MinimarketBST;
import com.gojekapp.utils.DataInitializer;
import java.util.*;

public class GoMartService {
    private final Scanner scanner;
    private final MinimarketBST bst = DataInitializer.createMinimarketBST();
    private final List<MenuToko> menuToko = DataInitializer.getMenuToko();

    public GoMartService(Scanner scanner) { this.scanner = scanner; }

    public int handle(Map<Integer, Riwayat> riwayat) throws InterruptedException {
        System.out.println("\n--- GoMart ---");
        System.out.println("\nDaftar Minimarket Terdekat:");
        
        List<MinimarketBST.Node> marketList = bst.getNodesInOrder();
        for (int i = 0; i < marketList.size(); i++) {
            MinimarketBST.Node node = marketList.get(i);
            System.out.printf("%d. %s (Jarak: %.1f km, Rating: %d/5)\n", i + 1, node.getNama(), node.getJarak(), node.getRating());
        }
        
        System.out.print("\n> Pilih Minimarket berdasarkan ID: ");
        int pilihanId = scanner.nextInt(); scanner.nextLine();
        
        if (pilihanId < 1 || pilihanId > marketList.size()) {
            System.out.println("ID Minimarket tidak valid.");
            Thread.sleep(2000);
            return 0;
        }

        System.out.println("Anda memilih: " + marketList.get(pilihanId - 1).getNama());

        Stack<Pesanan> keranjang = new Stack<>();
        int total = 0;

        while(true) {
            System.out.println("\nMenu Tersedia:");
            for (int i=0; i<menuToko.size(); i++) {
                 System.out.printf("%d. %s - Rp %,d\n", i+1, menuToko.get(i).nama, menuToko.get(i).harga);
            }
            System.out.println("\nKetik '0' untuk checkout.");
            System.out.print("> Pilihan Menu (ID): ");
            int idMenu = scanner.nextInt();
            
            if (idMenu == 0) break;
            System.out.print("> Jumlah: ");
            int jumlah = scanner.nextInt(); scanner.nextLine();

            MenuToko item = menuToko.get(idMenu-1);
            keranjang.push(new Pesanan(item.nama, jumlah, item.harga * jumlah));
            System.out.println(item.nama + " dimasukkan ke keranjang.");
        }

        System.out.println("\n--- Checkout ---");
        System.out.println("Isi Keranjang (dikeluarkan dari Stack LIFO):");
        while(!keranjang.isEmpty()){
            Pesanan p = keranjang.pop();
            total += p.harga;
            System.out.printf("- %s x%d -> Rp %,d\n", p.nama, p.jumlah, p.harga);
        }
        System.out.printf("Total Belanja: Rp %,d\n", total);
        
        System.out.print("Setuju untuk memesan? (y/n): ");
        if(scanner.nextLine().equalsIgnoreCase("y")) {
            riwayat.put(riwayat.size()+1, new Riwayat("GoMart", total));
            return total;
        }
        return 0;
    }
}