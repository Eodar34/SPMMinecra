package cat.paucasesnoves.sppspmaincra;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import cat.paucasesnoves.sppspmaincra.datos.Crafteo;
import cat.paucasesnoves.sppspmaincra.datos.Material;
import cat.paucasesnoves.sppspmaincra.utilidades.DBInterface;

public class MainActivity extends AppCompatActivity {

    private DBInterface bd;
    private ImageView imageFrame;
    private TextView nombre;
    private TextView obtencion;
    private EditText editNom;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        // Inicializar elementos
        imageFrame = findViewById(R.id.image);
        nombre = findViewById(R.id.nombre);
        obtencion = findViewById(R.id.obtencion);
        editNom = findViewById(R.id.editTextNom);
        editEmail = findViewById(R.id.editTextEmail);


        //inicializaBBDD();
    }

    public void afegirUsuari(View view) {
        bd = new DBInterface(this);
        bd.abre();
        if (bd.insertarMaterial(new Material(editNom.getText().toString(), getString(R.string.palo), editEmail.getText().toString())) != -1) {
            Toast.makeText(this, "Afegit correctament", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Error a l’afegir", Toast.LENGTH_LONG).show();
        }
        bd.cierra();
        finish();
    }

    public void consultarUsuari(View view) {
        bd = new DBInterface(this);
        bd.abre();
        Cursor c = bd.obtenerDatos();
        c.moveToFirst();
        while (!c.isAfterLast()) {

            nombre.setText(c.getString(0));
            byte[] imageAsBytes = Base64.decode(c.getString(1).getBytes(), Base64.DEFAULT);
            imageFrame.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
            obtencion.setText(c.getString(2));

            c.moveToNext();
        }
        bd.cierra();
    }

    public void inicializaBBDD() {

        bd = new DBInterface(this);
bd.abre();
        Material palo = new Material("Palo",getString(R.string.palo),"Crafteando  dos planchas de madera");
        /*Material madera = new Material("Planchas de Madera",getString(R.string.madera),"Talar madera y craftearla");
        Material piedra = new Material("Piedra",getString(R.string.piedra),"Picando piedra");
        Material hierro = new Material("Hierro",getString(R.string.hierro),"Horneando mena de hierro");
        Material oro = new Material("Lingote de Oro", getString(R.string.oro),"Horneando mena de oro"); */
        Material diamante = new Material("Diamante", getString(R.string.diamante),"Picando mena de diamante");
// añadimos crafteo
        ArrayList<Material> materials = new ArrayList<>();
        materials.add(palo);
        materials.add(diamante);
        Crafteo pico1 = new Crafteo("Pico diamante",getString(R.string.palo), 1, materials);
// convertimos materials
        Gson gson = new Gson();
        String stringMateriales = gson.toJson(materials);
        bd.insertarCrafteo(pico1,stringMateriales);

        bd.cierra();
        /*
        byte[] imageAsBytes = Base64.decode(getString(R.string.palo).getBytes(), Base64.DEFAULT);
        imageFrame.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        */
    }

}
