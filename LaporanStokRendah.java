
import java.util.List;

public class LaporanStokRendah extends LaporanStok {

    public LaporanStokRendah(List<BahanMakanan> daftarBahan) {
        super(daftarBahan);
    }

    @Override
    public void buatLaporan() {
        System.out.println("\n=== LAPORAN STOK RENDAH ===");
        boolean ditemukan = false;

        for (BahanMakanan bahan : getDaftarBahan()) {
            if (bahan.getStok() < bahan.getStokMinimum()) {
                bahan.tampilkanInfo();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada bahan dengan stok rendah.");
        }
    }
}
