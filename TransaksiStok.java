
import java.time.LocalDate;

public abstract class TransaksiStok {
    private String idTransaksi;
    private LocalDate tanggal;
    private double jumlah;

    public TransaksiStok(String idTransaksi, double jumlah) {
        this.idTransaksi = idTransaksi;
        this.tanggal = LocalDate.now();
        this.jumlah = jumlah;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public double getJumlah() {
        return jumlah;
    }

    public abstract boolean proses(BahanMakanan bahan);

    public abstract String getJenisTransaksi();

    public void tampilkanTransaksi() {
        System.out.println(
            idTransaksi + " | " + tanggal + " | "
            + getJenisTransaksi() + " | " + jumlah
        );
    }
}
