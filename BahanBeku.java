
public class BahanBeku extends BahanMakanan {

    public BahanBeku(String idBahan, String namaBahan,
                     double stok, double harga,
                     String satuan, double stokMinimum) {
        super(idBahan, namaBahan, stok, harga, satuan, stokMinimum);
    }

    @Override
    public String getJenis() {
        return "Bahan Beku";
    }
}
