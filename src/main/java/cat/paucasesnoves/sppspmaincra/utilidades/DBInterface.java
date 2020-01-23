package cat.paucasesnoves.sppspmaincra.utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import cat.paucasesnoves.sppspmaincra.datos.Crafteo;
import cat.paucasesnoves.sppspmaincra.datos.Material;

public class DBInterface {

    //Declaración de constantes

    // Crear bbdd
    public static final String BD_NOMBRE = "BDMaincra";

    // Crear tabla materiales
    public static final String BD_TABLA_MATERIALES = "materiales";

    // Crear los atributos de la tabla materiales
    public static final String KEY_ID_M = "_id";
    public static final String NOMBRE_M = "nombreMaterial";
    public static final String IMAGEN_M = "imagenMaterial";
    public static final String OBTENCION_M = "obtencionMaterial";

    // Crear tabla
    public static final String BD_CREATE_M ="create table " + BD_TABLA_MATERIALES + "( " + KEY_ID_M +
            " integer primary key autoincrement, " + NOMBRE_M +" TEXT NOT NULL, " + IMAGEN_M + " TEXT NOT NULL, " +
            OBTENCION_M + " TEXT NOT NULL);";

    // Crear tabla crafteo
    public static final String BD_TABLA_CRAFTEO = "materiales";

    // Crear los atributos de la tabla crafteo
    public static final String KEY_ID_C = "_id";
    public static final String NOMBRE_C = "nombreCrafteo";
    public static final String IMAGEN_C = "imagenCrafteo";
    public static final String CANTIDAD_C = "cantidadCrafteo";
    public static final String MATERIALES_C = "materialesCrafteo";

    // Crear tabla
    public static final String BD_CREATE_C ="create table " + BD_TABLA_CRAFTEO  + "( " + KEY_ID_C +
            " integer primary key autoincrement, " + NOMBRE_C +" TEXT NOT NULL, " + IMAGEN_C + " TEXT NOT NULL, " +
            CANTIDAD_C + " integer, " + MATERIALES_C + " TEXT NOT NULL);";

    public static final String TAG = "DBInterface";
    public static final int VERSIO = 1;
    private final Context context;
    private AyudaDB ayudaDB;
    private SQLiteDatabase bd;

    public DBInterface(Context con) {
        this.context = con;
        ayudaDB = new AyudaDB(context);
    }

    // Abrir BBDD
    public DBInterface abre() throws SQLException {
        bd = ayudaDB.getWritableDatabase();
        return this;
    }
    // Cerrar BBDD
    public void cierra() {
        ayudaDB.close();
    }

    // Insertar un material
    public long insertarMaterial(Material material) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NOMBRE_M, material.getNombre());
        initialValues.put(IMAGEN_M, material.getImagen());
        initialValues.put(OBTENCION_M, material.getObtencion());
        return bd.insert(BD_TABLA_MATERIALES, null, initialValues);
    }
    // insertar crafteos
    public long insertarCrafteo(Crafteo crafteo, String materiales) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NOMBRE_C, crafteo.getNombre());
        initialValues.put(IMAGEN_C, crafteo.getImagen());
        initialValues.put(CANTIDAD_C, crafteo.getCantidad());
        initialValues.put(MATERIALES_C, materiales);

        return bd.insert(BD_TABLA_CRAFTEO, null, initialValues);
    }


    // Obtener datos
    public Cursor obtenerDatos() {
        return bd.query(BD_TABLA_MATERIALES, new String[] {NOMBRE_M, IMAGEN_M, OBTENCION_M},
                null,null,null,null,null);
    }
}
