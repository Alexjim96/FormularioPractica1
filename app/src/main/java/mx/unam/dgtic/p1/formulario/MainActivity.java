package mx.unam.dgtic.p1.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Variables de la parte grafica
    EditText etNombre,etApellido;
    Button btnSig;
    //Variables para el calendario
    Calendar ca;
    DatePickerDialog dpd;
    EditText etFecha;
    int[] fecha = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        btnSig = findViewById(R.id.btnSiguiente);

        etFecha = findViewById(R.id.etFecha);
        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ca = Calendar.getInstance();
                int dia = ca.get(Calendar.DAY_OF_MONTH);
                int mes = ca.get(Calendar.MONTH);
                int anio = ca.get(Calendar.YEAR);

                dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        etFecha.setText(day+"/"+(month+1)+"/"+year);
                        fecha[0] = day;
                        fecha[1] = month+1;
                        fecha[2] = year;
                    }
                }, dia,mes,anio);

                dpd.show();
            }
        });

        btnSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);

                if (etNombre.getText().toString().length()>0 && etApellido.getText().toString().length()>0
                    && etFecha.getText().toString().length()>0){
                    Bundle bundle = new Bundle();
                    bundle.putString("Nombre",etNombre.getText().toString());
                    bundle.putString("Apellido",etApellido.getText().toString());
                    //bundle.putString("Nacimiento",etFecha.getText().toString());
                    bundle.putIntArray("Nacimiento",fecha);

                    intent.putExtras(bundle);

                    startActivity(intent);

                }else{
                    if (etNombre.getText().toString().length()==0){
                        etNombre.setError(getResources().getString(R.string.error));
                    }
                    if (etApellido.getText().toString().length()==0){
                        etApellido.setError(getResources().getString(R.string.error));
                    }
                    if (etFecha.getText().toString().length()==0) {
                        etFecha.setError(getResources().getString(R.string.error));
                    }

                }



            }
        });
    }

}
