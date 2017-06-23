package emsig.sensores;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView salida;
    Button vibrador, giroscopio, proximidad, acelerometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanciar los elementos
        vibrador = (Button)findViewById(R.id.vibrador);
        giroscopio = (Button)findViewById(R.id.giroscopio);
        proximidad = (Button)findViewById(R.id.proximidad);
        acelerometro = (Button)findViewById(R.id.acelerometro);
        salida = (TextView)findViewById(R.id.salida);

        vibrador.setOnClickListener(this);
        giroscopio.setOnClickListener(this);
        proximidad.setOnClickListener(this);
        acelerometro.setOnClickListener(this);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Lista que guarda los datos que recibamos
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
        //para cada sensor que este en la lista lo imprimiremos en el textView
        for (Sensor sensor : listaSensores){
            log(sensor.getName());
        }

    }

    public void log(String string){
        salida.append(string + "\n");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.vibrador:
                Intent vib = new Intent(this,VibracionActivity.class);
                startActivity(vib);
                break;
            case R.id.acelerometro:
                Intent i = new Intent(this,AcelerometroActivity.class);
                startActivity(i);
                break;
            case R.id.giroscopio:
                Intent gir = new Intent(this,GiroscopioActivity.class);
                startActivity(gir);
                break;
            case R.id.proximidad:
                Intent prox = new Intent(this,ProximidadActivity.class);
                startActivity(prox);
                break;
            default:
                break;
        }
    }
}
