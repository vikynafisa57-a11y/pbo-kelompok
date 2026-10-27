
public class PenyesuaianStok extends TransaksiStok {

    public PenyesuaianStok(String id, double stokBaru) {
        super(id, stokBaru);
    }

    @Override
    public boolean proses(BahanMakanan bahan) {
        if (getJumlah() < 0) {
            return false;
        }

        bahan.sesuaikanStok(getJumlah());
        return true;
    }

    @Override
    public String getJenisTransaksi() {
        return "Penyesuaian Stok";
    }
}
