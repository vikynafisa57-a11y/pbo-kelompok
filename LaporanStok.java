
import java.util.List;

public abstract class LaporanStok {
    private List<BahanMakanan> daftarBahan;

    public LaporanStok(List<BahanMakanan> daftarBahan) {
        this.daftarBahan = daftarBahan;
    }

    protected List<BahanMakanan> getDaftarBahan() {
        return daftarBahan;
    }

    public abstract void buatLaporan();
}
