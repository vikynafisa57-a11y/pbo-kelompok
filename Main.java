
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<BahanMakanan> daftarBahan =
            new ArrayList<>();

    private static int nomorTransaksi = 1;

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n================================");
            System.out.println("         STOCKBITE MINI");
            System.out.println(" Sistem Stok Bahan Restoran");
            System.out.println("================================");
            System.out.println("1. Tambah Bahan Makanan");
            System.out.println("2. Lihat Daftar Stok");
            System.out.println("3. Bahan Masuk");
            System.out.println("4. Bahan Keluar");
            System.out.println("5. Penyesuaian Stok");
            System.out.println("6. Laporan Stok Rendah");
            System.out.println("7. Laporan Stok Aman");
            System.out.println("8. Laporan Nilai Stok");
            System.out.println("0. Keluar");

            pilihan = inputInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tambahBahan();
                    break;
                case 2:
                    lihatStok();
                    break;
                case 3:
                    transaksiMasuk();
                    break;
                case 4:
                    transaksiKeluar();
                    break;
                case 5:
                    penyesuaianStok();
                    break;
                case 6:
                    new LaporanStokRendah(daftarBahan).buatLaporan();
                    break;
                case 7:
                    new LaporanStokAman(daftarBahan).buatLaporan();
                    break;
                case 8:
                    new LaporanNilaiStok(daftarBahan).buatLaporan();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan StockBite!");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 0);

        input.close();
    }

    private static void tambahBahan() {
        System.out.println("\n=== TAMBAH BAHAN ===");

        String id = inputString("ID bahan: ");

        if (cariBahan(id) != null) {
            System.out.println("ID bahan sudah digunakan.");
            return;
        }

        String nama = inputString("Nama bahan: ");

        System.out.println("Jenis bahan:");
        System.out.println("1. Bahan Segar");
        System.out.println("2. Bahan Kering");
        System.out.println("3. Bahan Beku");

        int jenis = inputInt("Pilih jenis: ");

        if (jenis < 1 || jenis > 3) {
            System.out.println("Jenis bahan tidak valid.");
            return;
        }

        String satuan = inputString("Satuan (kg, liter, pcs): ");
        double stok = inputDouble("Stok awal: ");
        double harga = inputDouble("Harga per satuan: Rp");
        double minimum = inputDouble("Batas stok minimum: ");

        if (stok < 0 || harga < 0 || minimum < 0) {
            System.out.println("Angka tidak boleh negatif.");
            return;
        }

        BahanMakanan bahan;

        switch (jenis) {
            case 1:
                bahan = new BahanSegar(
                    id, nama, stok, harga, satuan, minimum
                );
                break;
            case 2:
                bahan = new BahanKering(
                    id, nama, stok, harga, satuan, minimum
                );
                break;
            default:
                bahan = new BahanBeku(
                    id, nama, stok, harga, satuan, minimum
                );
                break;
        }

        daftarBahan.add(bahan);
        System.out.println("Bahan berhasil ditambahkan.");
    }

    private static void lihatStok() {
        System.out.println("\n=== DAFTAR STOK BAHAN ===");

        if (daftarBahan.isEmpty()) {
            System.out.println("Belum ada data bahan.");
            return;
        }

        for (BahanMakanan bahan : daftarBahan) {
            bahan.tampilkanInfo();
        }
    }

    private static void transaksiMasuk() {
        BahanMakanan bahan = pilihBahan();

        if (bahan == null) {
            return;
        }

        double jumlah = inputDouble("Jumlah bahan masuk: ");

        if (jumlah <= 0) {
            System.out.println("Jumlah harus lebih dari nol.");
            return;
        }

        StokMasuk transaksi =
            new StokMasuk("TRX" + nomorTransaksi++, jumlah);

        if (transaksi.proses(bahan)) {
            transaksi.tampilkanTransaksi();
            System.out.println("Stok berhasil ditambahkan.");
        }
    }

    private static void transaksiKeluar() {
        BahanMakanan bahan = pilihBahan();

        if (bahan == null) {
            return;
        }

        double jumlah = inputDouble("Jumlah bahan keluar: ");

        StokKeluar transaksi =
            new StokKeluar("TRX" + nomorTransaksi++, jumlah);

        if (transaksi.proses(bahan)) {
            transaksi.tampilkanTransaksi();
            System.out.println("Stok berhasil dikurangi.");
        } else {
            System.out.println(
                "Transaksi gagal. Jumlah harus positif "
                + "dan tidak boleh melebihi stok tersedia."
            );
        }
    }

    private static void penyesuaianStok() {
        BahanMakanan bahan = pilihBahan();

        if (bahan == null) {
            return;
        }

        System.out.println("Stok saat ini: " + bahan.getStok());

        double stokBaru =
            inputDouble("Masukkan jumlah stok fisik terbaru: ");

        PenyesuaianStok transaksi =
            new PenyesuaianStok("TRX" + nomorTransaksi++, stokBaru);

        if (transaksi.proses(bahan)) {
            transaksi.tampilkanTransaksi();
            System.out.println("Stok berhasil disesuaikan.");
        } else {
            System.out.println("Jumlah stok tidak valid.");
        }
    }

    private static BahanMakanan pilihBahan() {
        if (daftarBahan.isEmpty()) {
            System.out.println("Belum ada data bahan.");
            return null;
        }

        lihatStok();

        String id = inputString("Masukkan ID bahan: ");
        BahanMakanan bahan = cariBahan(id);

        if (bahan == null) {
            System.out.println("Bahan tidak ditemukan.");
        }

        return bahan;
    }

    private static BahanMakanan cariBahan(String id) {
        for (BahanMakanan bahan : daftarBahan) {
            if (bahan.getIdBahan().equalsIgnoreCase(id)) {
                return bahan;
            }
        }

        return null;
    }

    private static String inputString(String pesan) {
        System.out.print(pesan);
        return input.nextLine().trim();
    }

    private static int inputInt(String pesan) {
        while (true) {
            try {
                return Integer.parseInt(inputString(pesan));
            } catch (NumberFormatException e) {
                System.out.println("Masukkan bilangan bulat yang valid.");
            }
        }
    }

    private static double inputDouble(String pesan) {
        while (true) {
            try {
                return Double.parseDouble(inputString(pesan));
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid, gunakan titik untuk desimal.");
            }
        }
    }
}
