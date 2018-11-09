package com.example.android.ejemplosqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombre_et,dni_et,telefono_et,ciudad_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre_et=(EditText)findViewById(R.id.editTextNombre);
        dni_et=(EditText)findViewById(R.id.editTextDNI);
        telefono_et=(EditText)findViewById(R.id.editTextTelefono);
        ciudad_et=(EditText)findViewById(R.id.editTextCiudad);
    }

    // Damos de alta los usuarios en nuestra aplicación
    public void alta(View v) {

        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,

                "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = dni_et.getText().toString();
        String nombre = nombre_et.getText().toString();
        String ciudad = ciudad_et.getText().toString();
        String numero = telefono_et.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("dni", dni);
        registro.put("nombre", nombre);
        registro.put("ciudad", ciudad);
        registro.put("numero", numero);

        // los inserto en la base de datos

        bd.insert("usuario", null, registro);

        bd.close();

        // ponemos los campos a vacío para insertar el siguiente usuario
        dni_et.setText(""); nombre_et.setText(""); ciudad_et.setText(""); telefono_et.setText("");

        Toast.makeText(this, "Datos del usuario cargados", Toast.LENGTH_SHORT).show();

    }

    /* Método para dar de baja al usuario insertado*/
    public void baja(View v) {

        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,

                "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = dni_et.getText().toString();

        // aquí borro la base de datos del usuario por el dni
        int cant = bd.delete("usuario", "dni=" + dni, null);

        bd.close();

        dni_et.setText(""); nombre_et.setText(""); ciudad_et.setText(""); telefono_et.setText("");

        if (cant == 1)

            Toast.makeText(this, "Usuario eliminado",

                    Toast.LENGTH_SHORT).show();

        else

            Toast.makeText(this, "No existe usuario",

                    Toast.LENGTH_SHORT).show();
    }


    // Método para modificar la información del usuario
    public void modificacion(View v) {

        AdminSQLiteHelper admin = new AdminSQLiteHelper(this,

                "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = dni_et.getText().toString();
        String nombre = nombre_et.getText().toString();
        String ciudad = ciudad_et.getText().toString();
        String numero = telefono_et.getText().toString();

        ContentValues registro = new ContentValues();

        // actualizamos con los nuevos datos, la información cambiada
        registro.put("nombre", nombre);
        registro.put("ciudad", ciudad);
        registro.put("numero", numero);

       // int cant = bd.update("usuario", registro, "dni=" + dni, null);
        int cant=1;//quitar

        /////////Ejemplo de select
        Cursor c = bd.rawQuery("SELECT dni,ciudad FROM usuario WHERE dni='123'", null);
        Log.d("consulta","antes de mover el cursor");
        if (c.moveToFirst()){
            do {
                // Passing values
                String column1 = c.getString(0);
                String column2 = c.getString(1);

                // Do something Here with values
                Log.d("consulta",column2);
            } while(c.moveToNext());
        }
        Log.d("consulta","fin del select");
        ////////////
        c.close();

        bd.close();

        if (cant == 1)

            Toast.makeText(this, "Datos modificados con éxito", Toast.LENGTH_SHORT)

                    .show();

        else

            Toast.makeText(this, "No existe usuario",

                    Toast.LENGTH_SHORT).show();

    }

    /* fin del programa */

}
