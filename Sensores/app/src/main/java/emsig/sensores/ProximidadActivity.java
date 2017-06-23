package emsig.sensores;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.hardware.SensorEventListener;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by erick on 21/06/17.
 */

public class ProximidadActivity extends AppCompatActivity implements SensorEventListener {

    LinearLayout lin_lay;//Vamos a cambiar el fondo de la pantalla
    Sensor sensor;
    SensorManager sen_man;
    TextView info, contador;
    int num_entradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        num_entradas = 0;
        setContentView(R.layout.proximidad_layout);
        lin_lay = (LinearLayout)findViewById(R.id.layout_prox);
        info = (TextView)findViewById(R.id.texto_prox);
        contador = (TextView)findViewById(R.id.prox_contador);
        sen_man = (SensorManager)getSystemService(SENSOR_SERVICE);//ESTO ES LO BÁSICO PARA MANEJAR SENSORES
        sensor = sen_man.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sen_man.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        /*PARA EL SENSOR DE PROXIMIDAD, EL ARREGLO VALUES SOLO TIENE INFORMACIÓN EN SU POSICIÓN 0 Y
        ESTA ES LA DISTANCIA QUE HAY ENTRE EL SENSOR Y UN OBJETO QUE LO INTERRUMPA*/

        String distancia = String.valueOf(event.values[0]);
        num_entradas++;
        contador.setText("Entrada: " + num_entradas);
        info.setText("Distancia: " + distancia);
        //Vamos a cambiar el fondo de la pantalla
        float dis_value = Float.parseFloat(distancia);

        if(dis_value <= 3){
            lin_lay.setBackgroundColor(Color.GREEN);
        }
        else {
            lin_lay.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
