
public abstract class BahanMakanan {
    private String idBahan;
    private String namaBahan;
    private double stok;
    private double harga;
    private String satuan;
    private double stokMinimum;

    public BahanMakanan(String idBahan, String namaBahan,
                        double stok, double harga,
                        String satuan, double stokMinimum) {
        this.idBahan = idBahan;
        this.namaBahan = namaBahan;
        this.stok = stok;
        this.harga = harga;
        this.satuan = satuan;
        this.stokMinimum = stokMinimum;
    }

    public String getIdBahan() {
        return idBahan;
    }

    public String getNamaBahan() {
        return namaBahan;
    }

    public double getStok() {
        return stok;
    }

    public double getHarga() {
        return harga;
    }

    public String getSatuan() {
        return satuan;
    }

    public double getStokMinimum() {
        return stokMinimum;
    }

    public abstract String getJenis();

    public void tambahStok(double jumlah) {
        if (jumlah > 0) {
            stok += jumlah;
        }
    }

    public boolean kurangiStok(double jumlah) {
        if (jumlah > 0 && jumlah <= stok) {
            stok -= jumlah;
            return true;
        }
        return false;
    }

    public void sesuaikanStok(double stokBaru) {
        if (stokBaru >= 0) {
            stok = stokBaru;
        }
    }

    public void tampilkanInfo() {
        System.out.printf(
            "%-8s | %-20s | %-12s | Stok: %.2f %s | Harga: Rp%.0f%n",
            idBahan, namaBahan, getJenis(),
            stok, satuan, harga
        );
    }
}
