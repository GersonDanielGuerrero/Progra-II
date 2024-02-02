package com.ugb.calculadora;

import static com.ugb.calculadora.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TabHost tbh;

    TextView txt;
    Button btn;

    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

     /*a
        tbh=findViewById(R.id.tbhconversor);
        tbh.setup();
        tbh.addTab(tbh.newTabSpec("LONGITUS").setContent(id.tabLongitud).setIndicator("LONGITUD",null));
        tbh.addTab(tbh.newTabSpec("ALMACENAMIENTO").setContent(id.tabAlmacenemiento).setIndicator("ALMACENAMIENTO",null));
        tbh.addTab(tbh.newTabSpec("MONEDAS").setContent(id.tabMonedas).setIndicator("MONEDAS",null));
*/

    }
}