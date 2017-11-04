package com.example.jesus.salarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtNombre,txtPagoxHora,txtSalarioBruto,txtDescuento,txtSalarioNeto;
    Spinner spnHoras;
    Button btnCalcular,btnLimpiar;
    String nombre;
    int horas;
    double pagoxHora,salarioBruto,descuento,salrioNeto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();
        List<String> horasTrabajadas=new ArrayList<>();

        horasTrabajadas.add("10");
        horasTrabajadas.add("20");
        horasTrabajadas.add("30");
        horasTrabajadas.add("40");
        horasTrabajadas.add("50");
        horasTrabajadas.add("60");
        horasTrabajadas.add("70");
        horasTrabajadas.add("80");
        horasTrabajadas.add("90");

        llenarSpinner(horasTrabajadas,spnHoras);

        btnCalcular.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calcularSalario();
                    }
                }
        );
        btnLimpiar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        limpiarPantalla();
                    }
                }
        );

        spnHoras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tV= (TextView) view;
                horas=Integer.parseInt(tV.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void inicializaComponentes(){
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtPagoxHora=(EditText)findViewById(R.id.txtPagoxHora);
        txtSalarioBruto=(EditText)findViewById(R.id.txtSalarioBruto);
        txtDescuento=(EditText)findViewById(R.id.txtDescuento);
        txtSalarioNeto=(EditText)findViewById(R.id.salarioNeto);
        spnHoras=(Spinner)findViewById(R.id.spnHoras);
        btnCalcular=(Button)findViewById(R.id.btnCalcular);
        btnLimpiar=(Button)findViewById(R.id.btnLimpiar);
    }
    //Apaptador
    public void llenarSpinner(List<String> items, Spinner spinner){
        ArrayAdapter<String> adp= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        spinner.setAdapter(adp);
    }

    //Metodos de botones
    public void calcularSalario(){
        if (txtNombre.length()==0||txtPagoxHora.length()==0){
            Toast.makeText(this,"Favor de llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
        else {
            nombre=txtNombre.getText().toString();
            pagoxHora=Double.parseDouble(txtPagoxHora.getText().toString());
            salarioBruto=(horas*pagoxHora);
            descuento=salarioBruto*.2;
            salrioNeto=salarioBruto-descuento;

            txtSalarioBruto.setText(Double.toString(salarioBruto));
            txtDescuento.setText(Double.toString(descuento));
            txtSalarioNeto.setText(Double.toString(salrioNeto));
        }
    }

    public void limpiarPantalla(){
        txtNombre.setText("");
        txtPagoxHora.setText("");
        txtSalarioBruto.setText("");
        txtDescuento.setText("");
        txtSalarioNeto.setText("");
    }
}
