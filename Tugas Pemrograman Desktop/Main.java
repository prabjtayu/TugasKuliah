import java.util.Scanner;


class Menu {
    String nama;
    double harga;
    String kategori;

    
    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Menu[] daftarMenu = new Menu[20];
    
    
    static int jumlahMenuSaatIni = 0;

    public static void inisialisasiMenu() {
        daftarMenu[0] = new Menu("Nasi Padang", 25000, "makanan");
        daftarMenu[1] = new Menu("Sate Ayam", 30000, "makanan");
        daftarMenu[2] = new Menu("Bakso Urat", 20000, "makanan");
        daftarMenu[3] = new Menu("Gado-Gado", 18000, "makanan");
        daftarMenu[4] = new Menu("Es Teh Manis", 5000, "minuman");
        daftarMenu[5] = new Menu("Jus Alpukat", 15000, "minuman");
        daftarMenu[6] = new Menu("Kopi Susu", 12000, "minuman");
        daftarMenu[7] = new Menu("Air Mineral", 4000, "minuman");
        jumlahMenuSaatIni = 8;
    }

    public static void tampilkanMenu() {
        System.out.println("========= SELAMAT DATANG DI RESTORAN =========");
        System.out.println("               DAFTAR MENU KAMI");
        System.out.println("----------------------------------------------");

        System.out.println("--- Makanan ---");
        for (int i = 0; i < jumlahMenuSaatIni; i++) {
            if (daftarMenu[i].kategori.equals("makanan")) {
                System.out.println((i + 1) + ". " + daftarMenu[i].nama + " \t\t(Rp " + daftarMenu[i].harga + ")");
            }
        }

        System.out.println("\n--- Minuman ---");
        for (int i = 0; i < jumlahMenuSaatIni; i++) {
            if (daftarMenu[i].kategori.equals("minuman")) {
                System.out.println((i + 1) + ". " + daftarMenu[i].nama + " \t\t(Rp " + daftarMenu[i].harga + ")");
            }
        }
        System.out.println("==============================================");
    }

    
    public static int bacaPilihan(String prompt, int min, int max) {
        int pilihan;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Input tidak valid. Silakan masukkan angka.");
                scanner.next();
                System.out.print(prompt);
            }
            pilihan = scanner.nextInt();
            if (pilihan < min || pilihan > max) {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan < min || pilihan > max);
        return pilihan;
    }

    public static boolean konfirmasi(String prompt) {
        System.out.print(prompt + " [Ya/Tidak]: ");
        String jawaban = scanner.next();
        return jawaban.equalsIgnoreCase("Ya");
    }

    public static void jalankanMenuPelanggan() {
        
        Menu[] pesananMenu = new Menu[4];
        int[] pesananJumlah = new int[4];
        int jumlahItemDipesan = 0; 

        tampilkanMenu();

        System.out.println("\nSilakan masukkan pesanan Anda (Maksimal 4 item).");
        System.out.println("Format: [Nomor Menu] [Jumlah]");
        System.out.println("Contoh: 1 2 (Memesan 2 Nasi padang)");
        System.out.println("Masukkan '0 0' jika sudah selesai memesan.");

        for (int i = 0; i < 4; i++) {
            System.out.print("Pesanan " + (i + 1) + ": ");
            int nomorMenu = scanner.nextInt();
            int jumlah = scanner.nextInt();

            
            if (nomorMenu == 0 && jumlah == 0) {
                break;
            }

            if (nomorMenu > 0 && nomorMenu <= jumlahMenuSaatIni && jumlah > 0) {
                pesananMenu[i] = daftarMenu[nomorMenu - 1];
                pesananJumlah[i] = jumlah;
                jumlahItemDipesan++;
            } else {
                System.out.println("Nomor menu atau jumlah tidak valid. Silakan ulangi.");
                i--; 
            }
        }

        if (jumlahItemDipesan > 0) {
            hitungDanCetakStruk(pesananMenu, pesananJumlah);
        } else {
            System.out.println("Anda tidak memesan apa-apa.");
        }


        System.out.println("\nTekan enter untuk keluar...");
        
        try {
            System.in.read();
        } catch (Exception e) {
            
        }
    }

    public static void hitungDanCetakStruk(Menu[] pesananMenu, int[] pesananJumlah) {
        System.out.println("\n\n========= STRUK PEMBAYARAN ANDA =========");

        double subTotal = 0;

        
        for (int i = 0; i < pesananMenu.length; i++) {
            if (pesananMenu[i] != null) {
                double totalItem = pesananMenu[i].harga * pesananJumlah[i];
                subTotal += totalItem;
                System.out.println(pesananMenu[i].nama + " \t " + pesananJumlah[i] + " x " + pesananMenu[i].harga + " = " + totalItem);
            }
        }
        
        System.out.println("-------------------------------------------");
        System.out.println("Subtotal \t\t\t = Rp " + subTotal);

        double biayaPajak = subTotal * 0.10;
        double biayaPelayanan = 20000;

        System.out.println("Pajak (10%) \t\t\t = Rp " + biayaPajak);
        System.out.println("Biaya Pelayanan \t\t = Rp " + biayaPelayanan);

        double totalAkhir = subTotal + biayaPajak + biayaPelayanan;
        System.out.println("Total Awal \t\t\t = Rp " + totalAkhir);
        System.out.println("-------------------------------------------");

        double diskonPersen = 0;
        double diskonBOGO = 0;

        if (subTotal > 100000) {
            diskonPersen = totalAkhir * 0.10;
            System.out.println("Anda Hemat! (Diskon 10%) \t = Rp - " + diskonPersen);
            totalAkhir -= diskonPersen;
        }

        
        
        double hargaMinumanTermurah = Double.MAX_VALUE;
        boolean adaMinuman = false;

        if (subTotal > 50000) {
            for (int i = 0; i < pesananMenu.length; i++) {
                if (pesananMenu[i] != null && pesananMenu[i].kategori.equals("minuman")) {
                    adaMinuman = true;
                    if (pesananMenu[i].harga < hargaMinumanTermurah) {
                        hargaMinumanTermurah = pesananMenu[i].harga;
                    }
                }
            }
            
            if (adaMinuman) {
                diskonBOGO = hargaMinumanTermurah;
            }
        }

        if (diskonBOGO > 0) {
            System.out.println("Anda Hemat! (Promo BOGO) \t = Rp - " + diskonBOGO);
            totalAkhir -= diskonBOGO;
        }
        
        
        
        System.out.println("===========================================");
        System.out.println("TOTAL AKHIR \t\t\t = Rp " + totalAkhir);
        System.out.println("      Terima kasih atas kunjungan Anda!      ");
        System.out.println("===========================================");
    }

    
    public static void tambahMenu() {
        System.out.println("\n--------- TAMBAH MENU BARU ---------");
        if (jumlahMenuSaatIni >= daftarMenu.length) {
            System.out.println("Maaf, kapasitas menu sudah penuh. Tidak dapat menambahkan menu baru.");
            return;
        }

        scanner.nextLine(); 
        System.out.print("Masukkan nama menu: ");
        String namaMenu = scanner.nextLine();
        System.out.print("Masukkan harga menu: ");
        double hargaMenu = scanner.nextDouble();
        System.out.print("Masukkan kategori menu (makanan/minuman): ");
        String kategoriMenu = scanner.next();

        daftarMenu[jumlahMenuSaatIni] = new Menu(namaMenu, hargaMenu, kategoriMenu);
        jumlahMenuSaatIni++;
        
        
        System.out.println("Berhasil! " + namaMenu + " ditambahkan ke daftar menu.");
    }

    public static void ubahHargaMenu() {
        System.out.println("\n--------- UBAH HARGA MENU ---------");
        tampilkanMenu();
        int nomorMenu = bacaPilihan("Masukkan nomor menu yang ingin diubah harganya: ", 1, jumlahMenuSaatIni);

        Menu menuYangDiubah = daftarMenu[nomorMenu - 1];
        System.out.println("Menu yang dipilih: " + menuYangDiubah.nama + " (Harga saat ini: Rp " + menuYangDiubah.harga + ")");

        if (konfirmasi("Apakah Anda yakin ingin mengubah harga menu ini?")) {
            System.out.print("Masukkan harga baru untuk " + menuYangDiubah.nama + ": ");
            double hargaBaru = scanner.nextDouble();
            menuYangDiubah.harga = hargaBaru;
            System.out.println("Harga " + menuYangDiubah.nama + " berhasil diubah menjadi Rp " + hargaBaru);
        } else {
            System.out.println("Perubahan harga dibatalkan.");
        }
    }

    public static void hapusMenu() {
        System.out.println("\n--------- HAPUS MENU ---------");
        tampilkanMenu();
        int nomorMenu = bacaPilihan("Masukkan nomor menu yang ingin dihapus: ", 1, jumlahMenuSaatIni);

        Menu menuYangDihapus = daftarMenu[nomorMenu - 1];
        System.out.println("Menu yang dipilih: " + menuYangDihapus.nama + " (Harga: Rp " + menuYangDihapus.harga + ")");

        if (konfirmasi("Apakah Anda yakin ingin menghapus menu ini?")) {
            for (int i = nomorMenu - 1; i < jumlahMenuSaatIni - 1; i++) {
                daftarMenu[i] = daftarMenu[i + 1];
            }
            daftarMenu[jumlahMenuSaatIni - 1] = null;
            jumlahMenuSaatIni--;
            System.out.println("Menu " + menuYangDihapus.nama + " berhasil dihapus dari daftar menu.");
        } else {
            System.out.println("Penghapusan menu dibatalkan.");
        }
    }

    public static void jalankanMenuPengelolaan() {
        boolean kembali = false;

        do {
            System.out.println("\n========= MENU PENGELOLAAN =========");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Lihat Daftar Menu");
            System.out.println("5. Kembali ke Menu Utama");

            
            int pilihan = bacaPilihan("Pilihan Anda: ", 1, 5);

            
            switch (pilihan) {
                case 1:
                    tambahMenu();
                    break;
                case 2:
                    ubahHargaMenu();
                    break;
                case 3:
                    hapusMenu();
                    break;
                case 4:
                    tampilkanMenu();
                    break;
                case 5:
                    kembali = true;
                    System.out.println("Kembali ke Menu Utama...");
                    break;
            }
        } while (!kembali);
    }

    
    public static void mainMenu() {
        boolean keluar = false;

        do {
            System.out.println("\n========= MENU UTAMA =========");
            System.out.println("1. Menu Pelanggan");
            System.out.println("2. Menu Pengelolaan");
            System.out.println("3. Keluar");

            int pilihan = bacaPilihan("Pilihan Anda: ", 1, 3);

            switch (pilihan) {
                case 1:
                    jalankanMenuPelanggan();
                    break;
                case 2:
                    jalankanMenuPengelolaan();
                    break;
                case 3:
                    keluar = true;
                    System.out.println("Terima kasih! Sampai jumpa lagi.");
                    break;
            }
        } while (!keluar);

        scanner.close();
    }
    
    
    public static void main(String[] args) {
        
        inisialisasiMenu();
        
        mainMenu();
    }
}