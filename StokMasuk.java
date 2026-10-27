
public class StokMasuk extends TransaksiStok {

    public StokMasuk(String id, double jumlah) {
        super(id, jumlah);
    }

    @Override
    public boolean proses(BahanMakanan bahan) {
        if (getJumlah() <= 0) {
            return false;
        }

        bahan.tambahStok(getJumlah());
        return true;
    }

    @Override
    public String getJenisTransaksi() {
        return "Stok Masuk";
    }
}

