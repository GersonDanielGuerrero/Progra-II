package com.ugb.calculadora;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class Lista_Productos extends AppCompatActivity {
    Bundle parametros = new Bundle();
    FloatingActionButton btn;
    ListView lts;
    Cursor cProductos;
    DB dbProductos;

    productos misProductos;
    final ArrayList<productos> alProductos=new ArrayList<productos>();
    final ArrayList<productos> alProductosCopy=new ArrayList<productos>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_productos);

        btn = findViewById(R.id.btnAbrirNuevosProductos);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parametros.putString("accion", "nuevo");
                abrirActividad(parametros);
            }
        });
        obtenerProductos();
        buscarProductos();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);

        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo)menuInfo;
        cProductos.moveToPosition(info.position);
        menu.setHeaderTitle(cProductos.getString(1));//1 es el nombre del Producto
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        try {
            switch (item.getItemId()){
                case R.id.mnxAgregar:
                    parametros.putString("accion", "nuevo");
                    abrirActividad(parametros);
                    break;
                case R.id.mnxModificar:
                    String Productos[] = {
                            cProductos.getString(0), //idProducto
                            cProductos.getString(1), //nombre
                            cProductos.getString(2), //direccion
                            cProductos.getString(3), //tel
                            cProductos.getString(4), //email
                            cProductos.getString(5) //dui
                    };
                    parametros.putString("accion","modificar");
                    parametros.putStringArray("Productos", Productos);
                    abrirActividad(parametros);
                case R.id.mnxEliminar:
                    eliminarProducto();
                    break;
            }
            return true;
        }catch (Exception e){
            mostrarMsg("Error en menu: "+ e.getMessage());
            return super.onContextItemSelected(item);
        }
    }
    private void eliminarProducto(){
        try {
            AlertDialog.Builder confirmacion = new AlertDialog.Builder(Lista_Productos.this);
            confirmacion.setTitle("Esta seguro de Eliminar a: ");
            confirmacion.setMessage(cProductos.getString(1));

            confirmacion.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String respuesta = dbProductos.administrar_Productos("eliminar", new String[]{cProductos.getString(0),"","","","",""});
                    if (respuesta.equals("ok")) {
                        mostrarMsg("El producto ha sido eliminado con exito.");
                        obtenerProductos();
                    } else {
                        mostrarMsg("Error al eliminar producto: " + respuesta);
                    }
                }
            });
            confirmacion.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            confirmacion.create().show();
        }catch (Exception e){
            mostrarMsg("Error al eliminar: "+ e.getMessage());
        }
    }
    private void abrirActividad(Bundle parametros){
        Intent abriVentana = new Intent(getApplicationContext(), Datos_Producto.class);
        abriVentana.putExtras(parametros);
        startActivity(abriVentana);
    }
    private void obtenerProductos(){
        try{
            alProductos.clear();
            alProductosCopy.clear();

            dbProductos = new DB(Lista_Productos.this, "", null, 1);
            cProductos = dbProductos.consultar_Productos();

            if ( cProductos.moveToFirst() ){
                lts = findViewById(R.id.ltsProductos);
                do{
                    misProductos = new productos(
                            cProductos.getString(0),//idProducto
                            cProductos.getString(1),//nombre
                            cProductos.getString(2),//direccion
                            cProductos.getString(3),//tel
                            cProductos.getString(4),//email
                            cProductos.getString(5)//dui
                    );
                    alProductos.add(misProductos);
                }while(cProductos.moveToNext());
                alProductosCopy.addAll(alProductos);

                adaptadorImagenes adImagenes = new adaptadorImagenes(getApplicationContext(), alProductos);
                lts.setAdapter(adImagenes);

                registerForContextMenu(lts);
            }else{
                mostrarMsg("No hay Productos que mostrar");
            }
        }catch (Exception e){
            mostrarMsg("Error al obtener Productos: "+ e.getMessage());
        }
    }
    private void mostrarMsg(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
    private void buscarProductos(){
        TextView tempVal;
        tempVal = findViewById(R.id.txtBuscarProductos);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    alProductos.clear();
                    String valor = tempVal.getText().toString().trim().toLowerCase();
                    if( valor.length()<=0 ){
                        alProductos.addAll(alProductosCopy);
                    }else{
                        for( productos Producto : alProductosCopy ){
                            String nombre = Producto.getNombre();
                            String descripcion = Producto.getDescripcion();
                            String marca = Producto.getMarca();
                            String presentacion = Producto.getPresentacion();
                            if( nombre.toLowerCase().trim().contains(valor) ||
                                    descripcion.toLowerCase().trim().contains(valor) ||
                                    marca.trim().contains(valor) ||
                                    presentacion.trim().toLowerCase().contains(valor) ){
                                alProductos.add(Producto);
                            }
                        }
                        adaptadorImagenes adImagenes = new adaptadorImagenes(getApplicationContext(), alProductos);
                        lts.setAdapter(adImagenes);
                    }
                }catch (Exception e){
                    mostrarMsg("Error al buscar: "+e.getMessage() );
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}