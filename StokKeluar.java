
public class StokKeluar extends TransaksiStok {

    public StokKeluar(String id, double jumlah) {
        super(id, jumlah);
    }

    @Override
    public boolean proses(BahanMakanan bahan) {
        return bahan.kurangiStok(getJumlah());
    }

    @Override
    public String getJenisTransaksi() {
        return "Stok Keluar";
    }
}
