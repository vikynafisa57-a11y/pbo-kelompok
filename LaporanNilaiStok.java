
import java.util.List;

public class LaporanNilaiStok extends LaporanStok {

    public LaporanNilaiStok(List<BahanMakanan> daftarBahan) {
        super(daftarBahan);
    }

    @Override
    public void buatLaporan() {
        System.out.println("\n=== LAPORAN NILAI PERSEDIAAN ===");

        double totalNilai = 0;

        for (BahanMakanan bahan : getDaftarBahan()) {
            double nilai = bahan.getStok() * bahan.getHarga();
            totalNilai += nilai;

            System.out.printf(
                "%s | %.2f %s | Nilai: Rp%.0f%n",
                bahan.getNamaBahan(),
                bahan.getStok(),
                bahan.getSatuan(),
                nilai
            );
        }

        System.out.printf("Total nilai persediaan: Rp%.0f%n", totalNilai);
    }
}
