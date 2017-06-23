package emsig.mediaplayer_curso;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton play;
    private ImageButton pause;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    //private final int PLAY = 0;
    //private final int PAUSE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if(button.state==PLAY){

        }*/

        play = (ImageButton)findViewById(R.id.play);
        pause = (ImageButton)findViewById(R.id.pause);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://razartec.dtdns.net/music/Rock/Franz%20Ferdinand/Franz%20Ferdinand%20-%20Take%20Me%20Out.mp3";
                //DEFINIMOS EL CANAL PARA EL STREAMING DE DATOS
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try{
                    //AGREGAMOS EL RECURSO
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();

                }catch(Exception e){
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                //LANZAMOS EL RECURSO
                mediaPlayer.start();

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

    }
}
