package emsig.sensores;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class VibracionActivity extends AppCompatActivity {

    //TODO LO QUE ES INICIALIZACIÓN VA DENTRO DEL ONCREATE

    Button unaV,repetidas,stop;
    boolean estaVibrando = false;
    Vibrator vibrador;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vibracion);

        unaV = (Button)findViewById(R.id.vibrarUna);
        repetidas = (Button)findViewById(R.id.vibrarRepetir);
        stop = (Button)findViewById(R.id.stop);
        vibrador = ((Vibrator) getSystemService(VIBRATOR_SERVICE));
        vibrador.cancel();

    }

    public void vibrarUnaVez (View view) {
        if (!vibrador.hasVibrator()) {
            Toast.makeText(VibracionActivity.this, "No soporta vibración", Toast.LENGTH_LONG).show();
            return;
        }
        if (estaVibrando) {
            vibrador.cancel();
        }
        vibrador.vibrate(1000);//Duración de la vibración
        estaVibrando = true;
        stop.setEnabled(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                estaVibrando = false;
                stop.setEnabled(false);
            }
        }, 1000);

    }

        public void vibrarRepetidas (View view){

            if(!vibrador.hasVibrator()){
                Toast.makeText(VibracionActivity.this,"No soporta vibración",Toast.LENGTH_LONG).show();
                return;
            }
            if(estaVibrando)
                vibrador.cancel();
            long delay = 250;
            long sigueVip = 250;
            long on_off = 250;
            int repeat = 1;

            vibrador.vibrate(new long[]{delay,sigueVip,on_off},repeat);
            estaVibrando=true;
            stop.setEnabled(true);

        }

        public void detener (View view){
            if(!vibrador.hasVibrator()){
                Toast.makeText(VibracionActivity.this,"No soporta vibración",Toast.LENGTH_LONG).show();
                return;
            }
            if(estaVibrando){
                vibrador.cancel();
                stop.setEnabled(false);
                estaVibrando=false;
            }

        }
    }


