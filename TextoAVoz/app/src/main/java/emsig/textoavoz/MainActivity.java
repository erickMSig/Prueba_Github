package emsig.textoavoz;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    TextToSpeech textToSpeech;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEspañol = (Button)findViewById(R.id.btnEspañol);
        Button btnEnglish = (Button)findViewById(R.id.btnEnglish);
        editText = (EditText)findViewById(R.id.texto);

        textToSpeech = new TextToSpeech(this, this);

        btnEspañol.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                textToSpeech.setLanguage(new Locale("spa","ESP"));
                hablar(editText.getText().toString());
            }

        });

        btnEnglish.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                textToSpeech.setLanguage(Locale.ENGLISH);
                hablar(editText.getText().toString());
            }

        });
    }

    public void hablar(String str){
        textToSpeech.speak(str, TextToSpeech.QUEUE_FLUSH, null);//QUEUE_ADD para que se agregue texto a la fila y lo lea
        textToSpeech.setSpeechRate(0.0f);//Recibe un float que permitira que el motor de texto a voz hable mas lento, rapido, grave o agudo
        textToSpeech.setPitch(0.0f);
    }

    public void onInit(int status){
        if (status == textToSpeech.LANG_MISSING_DATA || status == TextToSpeech.LANG_NOT_SUPPORTED)
            Toast.makeText(this, "No se pudo inicializar", Toast.LENGTH_SHORT).show();
    }

    public void onDestroy(){
        if (textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

}
