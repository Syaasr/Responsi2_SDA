package com.gojekapp.services;
import com.gojekapp.models.*;
import com.gojekapp.utils.DataInitializer;
import java.util.*;

public class GoFoodService {
    // Demonstrasi: List, Queue, Sorting
    private final Scanner scanner;
    private final List<Resto> restoList = DataInitializer.getRestoList();

    public GoFoodService(Scanner scanner) { this.scanner = scanner; }

    private void bubbleSort(List<Makanan> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (list.get(j).harga > list.get(j + 1).harga) {
                    Makanan temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
    }

    public int handle(Map<Integer, Riwayat> riwayat) {
        System.out.println("\n--- GoFood ---");
        System.out.println("\nPilih Restoran:");
        for(int i = 0; i < restoList.size(); i++) {
             System.out.printf("%d. %s (Rating: %.1f, Antrian saat ini: %d)\n", i+1, restoList.get(i).nama, restoList.get(i).rating, restoList.get(i).antrian.size());
        }
        System.out.print("> Pilihan Resto: ");
        int pilihanResto = scanner.nextInt(); scanner.nextLine();
        Resto resto = restoList.get(pilihanResto - 1);
        List<Makanan> menu = new ArrayList<>(resto.listMakanan);
        
        List<Pesanan> pesananList = new ArrayList<>();
        int total = 0;
        
        while(true) {
            System.out.println("\nMenu di " + resto.nama);
            System.out.println("| ID | Nama Makanan        | Harga      |");
            System.out.println("|----|---------------------|------------|");
            for(int i = 0; i < menu.size(); i++) {
                 System.out.printf("| %-2d | %-19s | Rp %-7d |%n", i+1, menu.get(i).nama, menu.get(i).harga);
            }
            System.out.println("\nKetik 'selesai' untuk checkout, 'sortir' untuk urutkan harga.");
            System.out.print("> Pilihan Menu (ID): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("selesai")) break;
            if (input.equalsIgnoreCase("sortir")) {
                bubbleSort(menu);
                System.out.println("Menu telah diurutkan berdasarkan harga termurah.");
                continue;
            }

            int idMenu = Integer.parseInt(input);
            System.out.print("> Jumlah: ");
            int jumlah = scanner.nextInt(); scanner.nextLine();
            
            Makanan makananDipesan = menu.get(idMenu-1);
            pesananList.add(new Pesanan(makananDipesan.nama, jumlah, makananDipesan.harga*jumlah));
            total += makananDipesan.harga*jumlah;
            System.out.println(makananDipesan.nama + " ditambahkan ke keranjang.");
        }
        
        System.out.println("\n--- Checkout ---");
        for(Pesanan p : pesananList) {
             System.out.printf("- %s x%d -> Rp %,d\n", p.nama, p.jumlah, p.harga);
        }
        System.out.printf("Total Belanja: Rp %,d\n", total);
        
        resto.antrian.add(resto.antrian.size() + 1);
        System.out.printf("Anda adalah antrian ke-%d di %s.\n", resto.antrian.size(), resto.nama);
        
        System.out.print("Setuju untuk memesan? (y/n): ");
        if(scanner.nextLine().equalsIgnoreCase("y")) {
            riwayat.put(riwayat.size()+1, new Riwayat("GoFood", total));
            return total;
        }
        return 0;
    }
}
