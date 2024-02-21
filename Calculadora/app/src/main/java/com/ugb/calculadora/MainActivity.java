package com.ugb.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TabHost tbh;
    Button btn;

  Parcial objConversor = new Parcial();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhParcial);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("AreaSuperficie").setContent(R.id.tabSuperficie).setIndicator("Area", null));
        tbh.addTab(tbh.newTabSpec("ALMACENAMIENTO").setContent(R.id.tabCalculo_Agua_Potable).setIndicator("Calculo", null));


        btn=findViewById(R.id.btnAreaConvertir);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {try{
                double resp = objConversor.convertir(0,findViewById(R.id.spnAreaDe),findViewById(R.id.spnAreaA),
                        findViewById(R.id.txtAreaCantidad));
                Toast.makeText(getApplicationContext(), "Respuesta: " + resp, Toast.LENGTH_LONG).show();
            }catch (Exception e){} }
        });
        btn=findViewById(R.id.btnCalculoconvertir);///a
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double resp = objConversor.convertir(1,findViewById(R.id.spnCalculoA),findViewById(R.id.spnCalculoDe),
                            findViewById(R.id.txtCalculoCantidad));
                    Toast.makeText(getApplicationContext(), "Respuesta: " + resp, Toast.LENGTH_LONG).show();
                }catch (Exception e){} }
        });

    }
}
class Parcial{
    double [][] valores = {
            //AreaSuperficie
            {1, 10.7639, 1.4308, 1.19599, 0.001590, 0.0001431, 0.0001}
    };
            ;
    public  double convertir(int opcion,Spinner spnDe,Spinner spnA,EditText txtCantidad){
        int de =spnDe.getSelectedItemPosition();
        int a =spnA.getSelectedItemPosition();
        double cantidad= Double.parseDouble(txtCantidad.getText().toString());
        return valores [opcion][a]/valores[opcion][de]*cantidad;
    }
}
