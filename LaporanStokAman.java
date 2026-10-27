
import java.util.List;

public class LaporanStokAman extends LaporanStok {

    public LaporanStokAman(List<BahanMakanan> daftarBahan) {
        super(daftarBahan);
    }

    @Override
    public void buatLaporan() {
        System.out.println("\n=== LAPORAN STOK AMAN ===");
        boolean ditemukan = false;

        for (BahanMakanan bahan : getDaftarBahan()) {
            if (bahan.getStok() >= bahan.getStokMinimum()) {
                bahan.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Belum ada bahan dengan stok aman.");
        }
    }
}
