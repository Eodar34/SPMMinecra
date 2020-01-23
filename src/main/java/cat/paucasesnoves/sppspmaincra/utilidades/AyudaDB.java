package cat.paucasesnoves.sppspmaincra.utilidades;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static cat.paucasesnoves.sppspmaincra.utilidades.DBInterface.BD_CREATE_C;
import static cat.paucasesnoves.sppspmaincra.utilidades.DBInterface.BD_CREATE_M;
import static cat.paucasesnoves.sppspmaincra.utilidades.DBInterface.BD_NOMBRE;
import static cat.paucasesnoves.sppspmaincra.utilidades.DBInterface.BD_TABLA_MATERIALES;
import static cat.paucasesnoves.sppspmaincra.utilidades.DBInterface.BD_TABLA_CRAFTEO;

import static cat.paucasesnoves.sppspmaincra.utilidades.DBInterface.TAG;
import static cat.paucasesnoves.sppspmaincra.utilidades.DBInterface.VERSIO;

public class AyudaDB extends SQLiteOpenHelper {

    AyudaDB(Context con) {
        super(con, BD_NOMBRE, null, VERSIO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(BD_CREATE_M);
            db.execSQL(BD_CREATE_C);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersioAntiga, int VersioNova) {
        Log.w(TAG, "Actualitzant Base de dades de la versió" + VersioAntiga + " a " + VersioNova + ". Destruirà totes les dades");
        db.execSQL("DROP TABLE IF EXISTS " + BD_TABLA_MATERIALES);
        db.execSQL("DROP TABLE IF EXISTS " + BD_TABLA_CRAFTEO);
        onCreate(db); }
}