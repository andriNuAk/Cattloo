package restAPI;

import helper.LokasiResponse;
import model.AsistenResponse;
import model.BelanjaResponse;
import model.DeleteBelanja;
import model.DeleteBelanjaResponse;
import model.HewanResponse;
import model.InvoiceResponse;
import model.KandangResponse;
import model.KotaResponse;
import model.LaporanBelanjaResponse;
import model.Pasar;
import model.PasarResponse;
import model.Penjualan;
import model.PenjualanResponse;
import model.PerawatanResponse;
import model.PesanResponse;
import model.Pesanan;
import model.PesananResponse;
import model.PeternakResponse;
import model.PeternakUser;
import model.PeternakUserResponse;
import model.SampelResponse;
import model.TernakAsistenResponse;
import model.TernakKandangResponse;
import model.TernakPeternak;
import model.TernakPeternakResponse;
import model.TernakkuResponse;
import model.UpdateStatusPasar;
import model.UpdateStatusPasarResponse;
import model.UserResponse;
import model.VisualResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by drag me to hell on 4/17/2017.
 */

public interface APIInterface {
    //ambil data hewan
    @GET("/getHewan")
    Call<HewanResponse> getHewan();

    //input user
    @POST("/addUser")
    @FormUrlEncoded
    Call<UserResponse> addUser(@Field("id_user") int idUser,
                               @Field("username") String username,
                               @Field("password") String password,
                               @Field("email") String email,
                               @Field("no_telepon") String noTelepon,
                               @Field("nama_depan") String namaDepan,
                               @Field("nama_belakang") String namaBelakang,
                               @Field("foto") String foto,
                               @Field("hak_akses") int hakAkses);

    //login
    @GET("/login")
    Call<UserResponse> login(@Query("username") String username,
                             @Query("password") String password);

    //pasar
    @GET("/getPasar")
    Call<PasarResponse> getPasar();

    //sampelAll
    @GET("/getSampel")
    Call<SampelResponse> getSampel();

    //deletePesan
    @POST("/hapusPesan")
    @FormUrlEncoded
    Call<PesanResponse> deletePesan(@Field("kode_pesan") int kode_pesan);

    //addPesan
    @POST("/addPesan")
    @FormUrlEncoded
    Call<PesanResponse> addPesan(@Field("kode_pesan") int kodePesan,
                                 @Field("kode_sampel") int kodeSampel,
                                 @Field("id_user") int idUser,
                                 @Field("tgl_pesan") String tglPesan,
                                 @Field("status_pesan") String statusPesan,
                                 @Field("jumlah_ekor") int jumlahEkor);

    //getKota
    @GET("/getKota")
    Call<KotaResponse> getKota();

    //get pesanan by id
    @GET("/getPesananID")
    Call<PesananResponse> getPesanan(@Query("id_user") int idUser);

    //addBelanja
    @POST("/addBelanja")
    @FormUrlEncoded
    Call<BelanjaResponse> addBelanja(@Field("id_belanja") int idBelanja,
                                   @Field("tanggal_belanja") String tanggalBelanja,
                                   @Field("harga") int harga,
                                   @Field("id_ternak") int idTernak,
                                   @Field("status_beli") int statusBeli,
                                   @Field("id_user") int idUser);



    //get belanja by id
    @GET("/getBelanjaByID")
    Call<BelanjaResponse> getBelanja(@Query("id_user") int idUser,
                                     @Query("id_ternak") int idTernak);

    //get belanja by iduser
    @GET("/getBelanjaByPembeli")
    Call<BelanjaResponse> getBelanjaPembeli(@Query("id_user") int idUser);

    //get ternak by id ternak
    @GET("/getTernakByID")
    Call<PasarResponse> getTernakID(@Query("id_ternak") int idTernak);

    //laporan belanja
    @GET("/getBelanjaLaporan")
    Call<LaporanBelanjaResponse> getLaporanBelanja(@Query("id_user") int idUser);

    //get ternak by id user
    @GET("/getTernakByUser")
    Call<TernakkuResponse> getTernakUser(@Query("id_user") int idUser);

    //get visual by id ternak
    @GET("/getVIsualByID")
    Call<VisualResponse> getVisual(@Query("id_ternak") int idTernak);

    //update status pasar

    @POST("/updateStatusPasar")
    @FormUrlEncoded
    Call<UpdateStatusPasarResponse> updateStatus(@Field("status_pasar") int status_pasar, @Field("id_ternak") int id_ternak);

    // update transaksi
    @POST("/updateTransaksi")
    @FormUrlEncoded
    Call<UpdateStatusPasarResponse> updateTransaksi(@Field("status_pasar") int status_pasar, @Field("status") String status, @Field("id_ternak") int id_ternak);

    //delete belanja
    @POST("/deleteBelanja")
    @FormUrlEncoded
    Call<DeleteBelanjaResponse> hapusBelanja(@Field("id_belanja") int idBelanja);

    //laporan penjualan
    @GET("/getPenjualanByUser")
    Call<PenjualanResponse> getPenjualan(@Query("id_user") int idUser);

    //laporan perawatan
    @GET("/getPerawatanByUser")
    Call<PerawatanResponse> getPerawatan(@Query("id_user") int idUser);

    //getLokasi
    @GET("/getLokasi")
    Call<LokasiResponse> getLokasi();

    //getPasarByJenisHewan
    @GET("/getPasarByHewan")
    Call<PasarResponse> getPasarByHewan(@Query("nama_jenis_hewan") String jenisHewan);

    //getPasarByJenisLokasi
    @GET("/getPasarByLokasi")
    Call<PasarResponse> getPasarByLokasi(@Query("name") String namaDaerah);

    //getPasarByHarga < 10jt
    @GET("/getPasarByBetween1")
    Call<PasarResponse> getPasarByBetween1();

    //getPasarByHarga 10jt - 20jd
    @GET("/getPasarByBetween2")
    Call<PasarResponse> getPasarByBetween2();

    //getPasarByHarga 20jt - 3jt
    @GET("/getPasarByBetween3")
    Call<PasarResponse> getPasarByBetween3();

    //getPasarByHarga > 30jt
    @GET("/getPasarByBetween4")
    Call<PasarResponse> getPasarByBetween4();

    //addInvoice
    @POST("/addInvoice")
    @FormUrlEncoded
    Call<InvoiceResponse> addInvoice(@Field("kode_invoice") int kodeInvoice,
                                     @Field("bukti_transfer") String buktiTransfer,
                                     @Field("tgl_bayar") String tglBayar,
                                     @Field("nominal") int nominal,
                                     @Field("id_belanja") int id_belanja,
                                     @Field("nama_pemilik_rekening") String namaPemilikRekening,
                                     @Field("bank_pengirim") String bankPengirim);

    //upload foto
    //coba upload foto dan addInvoice disatukan
    @Multipart
    @POST("/uploadFoto")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);

    //ambil detail ternak berdasarkan peternak
    @GET("/getPeternakByUser")
    Call<TernakPeternakResponse> getPeternakUser(@Query("id_peternak") int idPeternak);
    //ambil peternak berdasarkan user
    @GET("/getPeternak")
    Call<PeternakResponse> getPeternak(@Query("id_user") int idUser);

    //ambil kandang berdasarkan user
    @GET("/getKandang")
    Call<KandangResponse> getKandang(@Query("id_user") int idUser);

    //ambil detail ternak berdasarkan kandang
    @GET("/getKandangByUser")
    Call<TernakKandangResponse> getTernakKandang(@Query("id_kandang") int idKandang);

    //ambil asisten berdasarkan user
    @GET("/getAsisten")
    Call<AsistenResponse> getAsisten(@Query("id_user") int idUser);

    //ambil detail ternak berdasarkan asisten
    @GET("/getAsistenByUser")
    Call<TernakAsistenResponse> getTernakAsisten(@Query("id_asisten") int idAsisten);
}
