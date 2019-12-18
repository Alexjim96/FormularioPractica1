package mx.unam.dgtic.p1.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    TextView tvSaludo, tvSigno;
    ImageView ivSigno;
    private static final String LOGTAG = "DEPURACION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvSaludo = findViewById(R.id.tvEdad);
        tvSigno = findViewById(R.id.tvSigno);
        ivSigno = findViewById(R.id.ivSigno);
        //Se obtienen los datos que envio el activity anterior
        Bundle bundle = this.getIntent().getExtras();
        String nombre = bundle.getString("Nombre");
        String apellido = bundle.getString("Apellido");
        int[] cumple = bundle.getIntArray("Nacimiento");

        Log.d(LOGTAG,"Nombre: "+nombre+"; "
                    +"Apellido:  "+apellido+"; "
                    +"Fecha cumple: "+cumple[0]+"/"+cumple[1]+"/"+cumple[2]);

        Calendar fechaHoy = Calendar.getInstance();
        int[] fechaHoyArr = new int[3];
        fechaHoyArr[0] = fechaHoy.get(Calendar.DAY_OF_MONTH);
        fechaHoyArr[1] = fechaHoy.get(Calendar.MONTH)+1;
        fechaHoyArr[2] = fechaHoy.get(Calendar.YEAR);

        Log.d(LOGTAG,"Fecha actual: "+fechaHoyArr[0]+"/"+fechaHoyArr[1]+"/"+fechaHoyArr[2]);

        tvSaludo.setText(getResources().getString(R.string.hey)+nombre+getResources().getString(R.string.espacio)+apellido);
        tvSigno.setText( getResources().getString(R.string.resp)+signo(cumple)+
                        getResources().getString(R.string.espacio)+
                        getResources().getString(R.string.edad)+edad(fechaHoyArr,cumple));
        //Toast.makeText(this,"Fecha hoy: "+fechaHoyArr[0]+"/"+fechaHoyArr[1]+"/"+fechaHoyArr[2],Toast.LENGTH_LONG).show();
        //Toast.makeText(this,"Fecha cumple: "+cumple[0]+"/"+cumple[1]+"/"+cumple[2],Toast.LENGTH_LONG).show();
        Log.d(LOGTAG,"Edad: "+edad(fechaHoyArr,cumple)+"; "+"Signo: "+signo(cumple));
    }
    private int edad(int[] hoy, int[] nacimiento){
        int[] restaFechas = new int[3];
        for (int i = 0; i<3; i++){
            restaFechas[i] = hoy[i] - nacimiento[i];
        }

        if (restaFechas[1]<0){//Si la resta de meses es negativo aun no cumple años en este año por lo que se le resta a los años calculados
            restaFechas[2]--;
        }else if (restaFechas[0]<0 && restaFechas[1]==0){ //cuando estamos parados en el mes de cumpleaños, es decir, la resta es 0 pero se debe de
                                    //validar si el dia ya llego o aun no. Entonces Si los dias son negativos aun no ha pasado su cumpleaños
            restaFechas[2]--;
        }
        return restaFechas[2];
    }
    private String signo(int[] nacimiento){
        int anio = nacimiento[2];
        String sign;
        switch (anio%12) {
            case 0:
                sign = getResources().getString(R.string.s_mono);
                ivSigno.setImageResource(R.drawable.mono);
                break;
            case 1:
                sign = getResources().getString(R.string.s_gallo);
                ivSigno.setImageResource(R.drawable.gallo);
                break;
            case 2:
                sign = getResources().getString(R.string.s_perro);
                ivSigno.setImageResource(R.drawable.perro);
                break;
            case 3:
                sign = getResources().getString(R.string.s_cerdo);
                ivSigno.setImageResource(R.drawable.cerdo);
                break;
            case 4:
                sign = getResources().getString(R.string.s_rata);
                ivSigno.setImageResource(R.drawable.rata);
                break;
            case 5:
                sign = getResources().getString(R.string.s_buey);
                ivSigno.setImageResource(R.drawable.buey);
                break;
            case 6:
                sign = getResources().getString(R.string.s_tigre);
                ivSigno.setImageResource(R.drawable.tigre);
                break;
            case 7:
                sign = getResources().getString(R.string.s_conejo);
                ivSigno.setImageResource(R.drawable.conejo);
                break;
            case 8:
                sign = getResources().getString(R.string.s_dragon);
                ivSigno.setImageResource(R.drawable.dragon);
                break;
            case 9:
                sign = getResources().getString(R.string.s_serpiente);
                ivSigno.setImageResource(R.drawable.serpiente);
                break;
            case 10:
                sign = getResources().getString(R.string.s_caballo);
                ivSigno.setImageResource(R.drawable.caballo);
                break;
            case 11:
                sign = getResources().getString(R.string.s_cabra);
                ivSigno.setImageResource(R.drawable.cabra);
                break;
            default:
                sign = "";
                break;
        }
        return sign;
    }
}
