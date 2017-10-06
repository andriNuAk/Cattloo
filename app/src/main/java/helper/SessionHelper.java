package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by drag me to hell on 4/25/2017.
 */

public class SessionHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DataAkun";
    private final static String TABLES[] = {"id_user", "username", "password","email", "no_telepon", "nama_depan", "nama_belakang" };
    private final static  String NAMA_TABEL = "tb_akun";


    public SessionHelper(Context context) {
        super(context ,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTabelAkun(SQLiteDatabase db){
        db.execSQL("CREATE TABLE if not exists "+NAMA_TABEL+" (id_user INTEGER PRIMARY KEY , username TEXT, password TEXT, email TEXT, no_telepon TEXT, nama_depan TEXT, nama_belakang TEXT);");
    }

    public void insertAkun(SQLiteDatabase db, int idUser, String username, String password, String email, String noTelepon, String namaDepan, String namaBelakang) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_user", idUser);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("no_telepon", noTelepon);
        contentValues.put("nama_depan", namaDepan);
        contentValues.put("nama_belakang", namaBelakang);
        db.insert(NAMA_TABEL, null, contentValues);
    }

    public void deleteDataAkun(SQLiteDatabase db, int idUser) {
        db.delete(NAMA_TABEL, "id_user =" + idUser, null);
    }

    public Cursor getAllAkun(SQLiteDatabase db){
        return  db.query(NAMA_TABEL, TABLES, null, null, null, null, null);
    }
}
