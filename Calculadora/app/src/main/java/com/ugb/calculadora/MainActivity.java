package com.ugb.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView txt;
    Button btn;
    Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    spn = findViewById(R.id.spnOpciones);
boolean haytxtVacios=false;
                    txt = findViewById(R.id.txtnum1);
                    double num1 =0;
                    if(!txt.getText().toString().isEmpty())
                        num1=Double.parseDouble(txt.getText().toString());

                    txt = findViewById(R.id.txtnum2);
                    double num2= 0;
                    if(!txt.getText().toString().isEmpty())
                        num2 = Double.parseDouble(txt.getText().toString());
                    double resp = 0;

                    boolean num1EsEntero=false;
                    switch (spn.getSelectedItemPosition()){
                        case 0:
                            resp = num1+num2;
                            break;
                        case 1:
                            resp = num1-num2;
                            break;
                        case 2:
                            resp = num1*num2;
                            break;
                        case 3:
                            resp = num1/num2;
                            break;
                        case 4:
                            resp=Math.pow(num1,num2);
                            break;
                        case 5:
                            resp=Math.pow(num1,1/num2);
                            break;
                        case 6:
                            resp=num1*num2/100;
                            break;
                            case 7:
                                num1EsEntero=num1%1==0;
                                if (num1EsEntero){
                                resp=1;
                                for (int i =1;i<=num1;i++){
                                    resp*=i;
                                }}
                                break;
                    }
                    txt = findViewById(R.id.lblrespuesta);
                    if (num1EsEntero)
                    txt.setText("Resultado: " + resp );
                    //Holaaaaaa
                    else
                        txt.setText("El numero debe ser entero");
                }catch (Exception e){
                    txt = findViewById(R.id.lblrespuesta);
                    txt.setText("Error: "+ e.getMessage());
                }
            }
        });
    }
}