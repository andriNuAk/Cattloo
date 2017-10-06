package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by drag me to hell on 7/7/2017.
 */

public class Invoice {
    @SerializedName("kode_invoice")
    @Expose
    private Integer kodeInvoice;
    @SerializedName("bukti_transfer")
    @Expose
    private String buktiTransfer;
    @SerializedName("tgl_bayar")
    @Expose
    private String tglBayar;
    @SerializedName("nominal")
    @Expose
    private Integer nominal;
    @SerializedName("id_belanja")
    @Expose
    private Integer idBelanja;
    @SerializedName("nama_pemilik_rekening")
    @Expose
    private String namaPemilikRekening;
    @SerializedName("bank_pengirim")
    @Expose
    private String bankPengirim;

    public Integer getKodeInvoice() {
        return kodeInvoice;
    }

    public void setKodeInvoice(Integer kodeInvoice) {
        this.kodeInvoice = kodeInvoice;
    }

    public String getBuktiTransfer() {
        return buktiTransfer;
    }

    public void setBuktiTransfer(String buktiTransfer) {
        this.buktiTransfer = buktiTransfer;
    }

    public String getTglBayar() {
        return tglBayar;
    }

    public void setTglBayar(String tglBayar) {
        this.tglBayar = tglBayar;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public Integer getIdBelanja() {
        return idBelanja;
    }

    public void setIdBelanja(Integer idBelanja) {
        this.idBelanja = idBelanja;
    }

    public String getNamaPemilikRekening() {
        return namaPemilikRekening;
    }

    public void setNamaPemilikRekening(String namaPemilikRekening) {
        this.namaPemilikRekening = namaPemilikRekening;
    }

    public String getBankPengirim() {
        return bankPengirim;
    }

    public void setBankPengirim(String bankPengirim) {
        this.bankPengirim = bankPengirim;
    }
}
