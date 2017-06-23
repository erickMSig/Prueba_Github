package emsig.sensores;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class AcelerometroActivity extends AppCompatActivity implements SensorEventListener{

    TextView x,y,z;
    Sensor mAcelerometro;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acelerometro);
        x = (TextView)findViewById(R.id.ejeX);
        y = (TextView)findViewById(R.id.ejeY);
        z = (TextView)findViewById(R.id.ejeZ);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    protected void onResume(){
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Lista de tipo sensor
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0){
            sm.registerListener(this,sensors.get(0),SensorManager.SENSOR_DELAY_GAME);
        }
    }

    protected void onPause(){
        super.onPause();
        SensorManager mSM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSM.unregisterListener(this,mAcelerometro);
    }

    protected void onStop(){
        super.onStop();
        SensorManager mSM = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSM.unregisterListener(this,mAcelerometro);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.x.setText("Eje X = " + sensorEvent.values[SensorManager.DATA_X]);
        this.y.setText("Eje Y = " + sensorEvent.values[SensorManager.DATA_Y]);
        this.z.setText("Eje Z = " + sensorEvent.values[SensorManager.DATA_Z]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
