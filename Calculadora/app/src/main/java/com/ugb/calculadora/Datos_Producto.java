package com.ugb.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Datos_Producto extends AppCompatActivity {
    TextView tempVal;
    Button btn;
    FloatingActionButton btnRegresar;
    String accion="nuevo", id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_producto);

        btnRegresar = findViewById(R.id.btnRegresarListaProductos);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarListaProductos();
            }
        });
        btn = findViewById(R.id.btnGuardarProductos);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempVal = findViewById(R.id.txtnombre);
                String nombre = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtdescripcion);
                String descripcion = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtMarca);
                String marca = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtPresentacion);
                String presentacion = tempVal.getText().toString();

                tempVal = findViewById(R.id.txtPrecio);
                String precio = tempVal.getText().toString();

                DB db = new DB(getApplicationContext(),"", null, 1);
                String[] datos = new String[]{id,nombre,descripcion,marca,presentacion,precio};
                String respuesta = db.administrar_Productos(accion, datos);
                if( respuesta.equals("ok") ){
                    Toast.makeText(getApplicationContext(), "Producto Registrado con Exito.", Toast.LENGTH_SHORT).show();
                    regresarListaProductos();
                }else{
                    Toast.makeText(getApplicationContext(), "Error: "+ respuesta, Toast.LENGTH_LONG).show();
                }
            }
        });
        mostrarDatosProductos();
    }
    private void regresarListaProductos(){
        Intent abrirVentana = new Intent(getApplicationContext(), Lista_Productos.class);
        startActivity(abrirVentana);
    }
    private void mostrarDatosProductos(){
        try {
            Bundle parametros = getIntent().getExtras();
            accion = parametros.getString("accion");
            if( accion.equals("modificar") ){
                String[] datos = parametros.getStringArray("Productos");
                id = datos[0];

                tempVal = findViewById(R.id.txtnombre);
                tempVal.setText(datos[1]);

                tempVal = findViewById(R.id.txtdescripcion);
                tempVal.setText(datos[2]);

                tempVal = findViewById(R.id.txtMarca);
                tempVal.setText(datos[3]);

                tempVal = findViewById(R.id.txtPresentacion);
                tempVal.setText(datos[4]);

                tempVal = findViewById(R.id.txtPrecio);
                tempVal.setText(datos[5]);
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error al mostrar los datos: "+ e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}