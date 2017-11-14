package tpmenu.temperatura;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private CiudadSQLiteHelper ciudadSQLiteHelper;
    private SQLiteDatabase db;

    private ListView listaCiudades;
    private CiudadAdapter adapter;

    private ArrayList<Ciudad> ciudades;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_agregar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_agregar:
                Intent intent = new Intent(this, agregarciudad.class);
                startActivity(intent);
                return true;
            case R.id.mnu_actualizar:
                update();
                //refreshLista.setRefreshing(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCiudades = (ListView) findViewById(R.id.listView);
        ciudades = new ArrayList<Ciudad>();


        ciudadSQLiteHelper = new CiudadSQLiteHelper(this, "Temp", null, 1);
        db = ciudadSQLiteHelper.getWritableDatabase();

        adapter = new CiudadAdapter(ciudades);
        listaCiudades.setAdapter(adapter);
        listaCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Ciudad ciudad_select = ciudades.get(position);
                seg_ciudad(ciudad_select);

            }
        });
        create();// cargo
        update();// actualizo
    }

    private void update() {
        ciudades.clear();
        ciudades.addAll(getAllContactos());
        adapter.notifyDataSetChanged();
    }

    // al iniciar por primera ves cargo los datos en la base de datos
    private void create() {
        if(db!=null){
            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("id",3835994);
            nuevoRegistro.put("nombre","Santa Rosa");

            ContentValues nuevoRegistro2 = new ContentValues();
            nuevoRegistro2.put("id",3860259);
            nuevoRegistro2.put("nombre","Cordoba");

            ContentValues nuevoRegistro3 = new ContentValues();
            nuevoRegistro3.put("id",6693229);
            nuevoRegistro3.put("nombre","Buenos Aires");

            ContentValues nuevoRegistro4 = new ContentValues();
            nuevoRegistro4.put("id",3844421);
            nuevoRegistro4.put("nombre","Mendoza");

            ContentValues nuevoRegistro5 = new ContentValues();
            nuevoRegistro5.put("id",3838233);
            nuevoRegistro5.put("nombre","Salta");

            ContentValues nuevoRegistro6 = new ContentValues();
            nuevoRegistro6.put("id",3836564);
            nuevoRegistro6.put("nombre","Jujuy");

            ContentValues nuevoRegistro7 = new ContentValues();
            nuevoRegistro7.put("id",3836277);
            nuevoRegistro7.put("nombre","Santa Fe");

            ContentValues nuevoRegistro8 = new ContentValues();
            nuevoRegistro8.put("id",3429886);
            nuevoRegistro8.put("nombre","Posadas");

            ContentValues nuevoRegistro9 = new ContentValues();
            nuevoRegistro9.put("id",3839307);
            nuevoRegistro9.put("nombre","Rawson");

            ContentValues nuevoRegistro10 = new ContentValues();
            nuevoRegistro10.put("id",3843123);
            nuevoRegistro10.put("nombre","Neuquen");



            db.insert("ciudades", null, nuevoRegistro);
            db.insert("ciudades", null, nuevoRegistro2);
            db.insert("ciudades", null, nuevoRegistro3);
            db.insert("ciudades", null, nuevoRegistro4);
            db.insert("ciudades", null, nuevoRegistro5);
            db.insert("ciudades", null, nuevoRegistro6);
            db.insert("ciudades", null, nuevoRegistro7);
            db.insert("ciudades", null, nuevoRegistro8);
            db.insert("ciudades", null, nuevoRegistro9);
            db.insert("ciudades", null, nuevoRegistro10);
        }
    }
    private void seg_ciudad(Ciudad ciudad_select){
        Intent intent = new Intent(this, CiudadActivity.class);
        intent.putExtra("ID",ciudad_select.getTId());
        startActivity(intent);
    }
    private List<Ciudad> getAllContactos(){

        Cursor cursor = db.rawQuery("SELECT * FROM ciudades",null);
        List<Ciudad> lista = new ArrayList<>();

        if(cursor.moveToFirst()){

            while (cursor.isAfterLast()==false){

                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));

                lista.add(new Ciudad(id,nombre));
                cursor.moveToNext();
            }
        }
        return lista;
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
